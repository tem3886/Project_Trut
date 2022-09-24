package project.trut.web.location;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import project.trut.domain.location.InitLocation;

import javax.validation.constraints.NotNull;

@Setter @Getter
@RequiredArgsConstructor
public class LocationForm {

    @NotNull(message = "출발 지역을 선택해 주세요.")
    private InitLocation departure;
    @NotNull(message = "도착 지역을 선택해 주세요.")
    private InitLocation destination;
}
