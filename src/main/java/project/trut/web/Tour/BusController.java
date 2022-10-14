package project.trut.web.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.trut.domain.api.GraphPos;
import project.trut.domain.api.OdsayApiDto;
import project.trut.domain.coordinate.Coordinate;
import project.trut.domain.member.Member;
import project.trut.domain.tour.Tour;
import project.trut.service.tour.DBService;
import project.trut.service.tour.OdsayApiService;
import project.trut.service.tour.OrderService;
import project.trut.web.TourLocalRepository;
import project.trut.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/trut/bus")
@RequiredArgsConstructor
public class BusController {

    private final OdsayApiService odsayApiService;
    private final OrderService orderService;
    private final TourLocalRepository tourLocalRepository;
    private final DBService dbService;

    @GetMapping
    public String findForm(Model model, HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {

        if (tourLocalRepository.getTourList().size() == 0) {
            redirectAttributes.addAttribute("empty", true);
            return "redirect:/trut/tour";
        }

        List<List<OdsayApiDto>> odsayList;

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<Coordinate> result = orderService.getOrder(member);

        try {
            odsayList = odsayApiService.getOdsay(result);
        } catch (ParseException e) {
            redirectAttributes.addAttribute("parse", true);
            return "redirect:/trut/tour";
        }

        log.info("result = {}", result);
        log.info("odsayList = {}", odsayList);

        model.addAttribute("result", result);
        model.addAttribute("odsayList", odsayList);

        return "trut/busPath";
    }

    @PostMapping
    public String findMapPath(@ModelAttribute("mapPath") MapPathForm mapPathForm,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        List<List<GraphPos>> lane;
        try {
            lane = odsayApiService.getLane(mapPathForm.getMapObj());
        } catch (ParseException e) {
            redirectAttributes.addAttribute("parse", true);
            return "redirect:/trut/tour";
        }

        model.addAttribute("lane", lane);

        return "trut/findPath";

    }

    @GetMapping("/{date}")
    public String readList(@PathVariable("date") String date,
                           HttpServletRequest request, Model model,
                           RedirectAttributes redirectAttributes) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Tour tour = dbService.getTourByIdAndDateTime(member.getId(), localDate);

        log.info("tour = {}", tour);

        List<Coordinate> result = tourProcessing(tour);
        List<List<OdsayApiDto>> odsayList;

        try {
            odsayList = odsayApiService.getOdsay(result);
        } catch (ParseException e) {
            redirectAttributes.addAttribute("parse", true);
            return "redirect:/trut/tour";
        }

        log.info("result = {}", result);
        log.info("odsayList = {}", odsayList);

        model.addAttribute("result", result);
        model.addAttribute("odsayList", odsayList);

        return "/trut/busPath";
    }

    private List<Coordinate> tourProcessing(Tour tour) {
        List<Coordinate> result = new ArrayList<>();

        result.add(dbService.getCoordinate(tour.getDeparture()));

        List<String> titleList = new ArrayList<>();
        titleList.add(tour.getTitleA());
        titleList.add(tour.getTitleB());
        titleList.add(tour.getTitleC());

        for (String t: titleList) {
            if (t != null) {
                result.add(dbService.getCoordinate(t));
            }
        }
        result.add(dbService.getCoordinate(tour.getDestination()));

        return result;
    }
}
