package project.trut.web.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.trut.domain.location.InitLocation;
import project.trut.domain.location.Location;
import project.trut.domain.member.Member;
import project.trut.domain.service.location.LocationService;
import project.trut.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/trut")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @ModelAttribute("initLocations")
    public InitLocation[] initLocations() {
        return InitLocation.values();
    }

    @GetMapping
    public String location(){
        return "trut/startTrut";
    }

    @PostMapping
    public String selectLocation(
            @RequestParam InitLocation departure,
            @RequestParam InitLocation destination,
            HttpServletRequest request){

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        Location location = new Location();
        location.setId(member.getId());
        location.setDeparture(departure.getDescription());
        location.setDestination(destination.getDescription());

        log.info("location = {}", location.toString());

        locationService.save(location);

        return "trut/trutWay";
    }
}
