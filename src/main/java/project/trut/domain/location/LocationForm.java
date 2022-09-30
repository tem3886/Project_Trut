package project.trut.domain.location;

import lombok.*;
import project.trut.domain.location.InitLocation;

import javax.validation.constraints.NotNull;

@Setter @Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class LocationForm {

    @NotNull(message = "출발 지역을 선택해 주세요.")
    private InitLocation departure;
    @NotNull(message = "도착 지역을 선택해 주세요.")
    private InitLocation destination;
}
