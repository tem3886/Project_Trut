package project.trut.domain.location;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class LocationUpdateDto {

    private String departure;
    private String destination;

    private List<String> waypoint;
}
