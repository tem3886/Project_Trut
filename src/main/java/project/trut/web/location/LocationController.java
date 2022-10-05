package project.trut.web.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.trut.domain.location.InitLocation;
import project.trut.domain.location.Location;
import project.trut.domain.location.LocationForm;
import project.trut.domain.location.LocationUpdateDto;
import project.trut.domain.member.Member;
import project.trut.domain.service.location.LocationService;
import project.trut.domain.tour.TourLocalRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
@Slf4j
@RequestMapping("/trut")
public class LocationController {

    private final LocationService locationService;
    private final LocationForm locationRepository;

    public LocationController(LocationService locationService, TourLocalRepository tourLocalRepository) {
        this.locationService = locationService;
        this.locationRepository = tourLocalRepository.getLocation();
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

        if (bindingResult.hasErrors()) {
            log.info("location binding Error", bindingResult.hasErrors());
            return "trut/startTrut";
        }

        locationRepository.setDeparture(locationForm.getDeparture());
        locationRepository.setDestination(locationForm.getDestination());

        return "redirect:/trut/tour";
    }

    private void updateLocation(Location location) {
        LocationUpdateDto updateParam = new LocationUpdateDto(location.getDeparture(), location.getDestination());
        locationService.update(location.getId(), location.getDateTime(), updateParam);
    }

    private Location getLocation(LocationForm locationForm, Member member) {
        Location location = new Location();
        location.setId(member.getId());
        location.setDateTime(LocalDate.now());
        location.setDeparture(locationForm.getDeparture().getDescription());
        location.setDestination(locationForm.getDestination().getDescription());
        return location;
    }
}
