package project.trut.web.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.trut.domain.location.InitLocation;
import project.trut.domain.location.LocationForm;
import project.trut.web.TourLocalRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/trut")
public class LocationController {

    private final TourLocalRepository tourLocalRepository;

    public LocationController(TourLocalRepository tourLocalRepository) {
        this.tourLocalRepository = tourLocalRepository;
    }

    @ModelAttribute("initLocations")
    public InitLocation[] initLocations() {
        return InitLocation.values();
    }

    @GetMapping
    public String location(@ModelAttribute LocationForm locationForm){
        return "trut/startTrut";
    }

    @PostMapping
    public String selectLocation(
            @Validated @ModelAttribute LocationForm locationForm,
            BindingResult bindingResult,
            HttpServletRequest request){

        LocationForm locationRepository = tourLocalRepository.getLocation();

        if (bindingResult.hasErrors()) {
            log.info("location binding Error", bindingResult.hasErrors());
            return "trut/startTrut";
        }

        locationRepository.setDeparture(locationForm.getDeparture());
        locationRepository.setDestination(locationForm.getDestination());

        return "redirect:/trut/tour";
    }
}
