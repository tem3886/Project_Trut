package project.trut.web.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.trut.domain.tour.InitTour;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/trut/tour")
public class TourController {

    @ModelAttribute("initTours")
    public InitTour[] initTours(){
        return InitTour.values();
    }


    @GetMapping
    public String wayPoint() {
        return "trut/tour";
    }

    @PostMapping
    public String selectTour(@ModelAttribute("classification") InitTour initTour) {
        log.info("select classification = {}", initTour.getDescription());
        return "trut/tour";
    }
}
