package project.trut.domain.location;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter @Setter
public class LocationUpdateDto {

    private String departure;
    private String destination;

    public LocationUpdateDto() {
    }

    public LocationUpdateDto(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }
}
