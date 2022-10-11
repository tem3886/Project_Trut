package project.trut.web.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.TrueFalseType;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.trut.domain.api.GraphPos;
import project.trut.domain.api.OdsayApiDto;
import project.trut.domain.coordinate.Coordinate;
import project.trut.domain.member.Member;
import project.trut.service.tour.DBService;
import project.trut.service.tour.OdsayApiService;
import project.trut.service.tour.OrderService;
import project.trut.domain.tour.TourLocalRepository;
import project.trut.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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

        log.info("result = {}", result.size());
        log.info("odsayList = {}", odsayList.size());

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

    /*@GetMapping("/{mapObj}")
    public String findBus(@PathVariable("mapObj") String mapObj,
                          Model model,
                          HttpServletRequest request,
                          RedirectAttributes redirectAttributes) {

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<List<GraphPos>> lane;
        try {
            lane = odsayApiService.getLane(mapObj);
        } catch (ParseException e) {
            redirectAttributes.addAttribute("mapObj", mapObj);
            redirectAttributes.addAttribute("parse", true);
            return "redirect:/trut/bus/{mapObj}";
        }

        model.addAttribute("lane", lane);

        return "trut/findPath";
    }*/

}
