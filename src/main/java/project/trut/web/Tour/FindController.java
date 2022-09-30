package project.trut.web.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.trut.domain.service.tour.TourApiDto;
import project.trut.domain.tour.TourLocalRepository;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/trut/find")
@RequiredArgsConstructor
public class FindController {

    private final TourLocalRepository tourLocalRepository;

    @GetMapping
    public String findForm() {
        log.info("findPath");
        log.info("tourList = {}", tourLocalRepository.getTourList().toString());
        return "trut/findPath";
    }

}
