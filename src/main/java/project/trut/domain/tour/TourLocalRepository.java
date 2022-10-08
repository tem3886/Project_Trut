package project.trut.domain.tour;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import project.trut.domain.api.TourApiDto;
import project.trut.domain.location.LocationForm;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@Getter @ToString
public class TourLocalRepository {

    private LocationForm location = new LocationForm();
    private List<TourApiDto> tourList = new ArrayList<>(3);


    public void setLocation(LocationForm location) {
        this.location = location;
    }

    public void setTourList(List<TourApiDto> list) {
        this.tourList = list;
    }


}
