package project.trut.domain.tour;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import project.trut.domain.location.LocationUpdateDto;
import project.trut.domain.service.tour.TourApiDto;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@Getter @ToString
public class TourLocalRepository {

    private LocationUpdateDto location;
    private List<TourApiDto> tourList = new ArrayList<>(3);


}
