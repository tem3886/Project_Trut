package project.trut.web.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.trut.domain.ApiKey;
import project.trut.domain.service.tour.PathService;
import project.trut.domain.tour.TourLocalRepository;

@Controller
@Slf4j
@RequestMapping("/trut/find")
@RequiredArgsConstructor
public class FindController {

    private final TourLocalRepository tourLocalRepository;
    private final PathService pathService;

    @GetMapping
    public String findForm(Model model) {

        log.info("tourList = {}", tourLocalRepository.getTourList());
        pathService.getOrder();

        model.addAttribute("location", tourLocalRepository.getLocation());
        model.addAttribute("tourList", tourLocalRepository.getTourList());

        return "trut/findPath";
    }

    @GetMapping("/bus")
    public String findBus(){

        return "trut/busPath";
    }

}
