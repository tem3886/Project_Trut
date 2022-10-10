package project.trut.web.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.trut.domain.api.OdsayApiDto;
import project.trut.domain.coordinate.Coordinate;
import project.trut.domain.member.Member;
import project.trut.service.tour.OdsayApiService;
import project.trut.service.tour.OrderService;
import project.trut.domain.tour.TourLocalRepository;
import project.trut.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/trut/bus")
@RequiredArgsConstructor
public class BusController {

    private final TourLocalRepository tourLocalRepository;
    private final OdsayApiService odsayApiService;
    private final OrderService orderService;

    @GetMapping
    public String findForm(Model model, HttpServletRequest request) {
        List<List<OdsayApiDto>> odsayList;

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<Coordinate> result = orderService.getOrder(member);

        try {
            odsayList = odsayApiService.getOdsay(result);
        } catch (ParseException e) {
            return "redirect:/trut/tour?parse=true";
        }

        log.info("result = {}", result.size());
        log.info("odsayList = {}", odsayList.size());

        model.addAttribute("result", result);
        model.addAttribute("odsayList", odsayList);

        return "trut/busPath";
    }

    @GetMapping("/{mapObj}")
    public String findBus(){

        return "trut/findPath";
    }

}