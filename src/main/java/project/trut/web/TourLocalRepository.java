package project.trut.web;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import project.trut.domain.api.TourApiDto;
import project.trut.domain.location.LocationForm;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@Getter @ToString
public class TourLocalRepository {

    private LocationForm location = new LocationForm();
    private List<TourApiDto> tourList = new ArrayList<>(3);

    public TourLocalRepository() {
        log.info("TourLocalRepository 생성");
    }

    public void setLocation(LocationForm location) {
        this.location = location;
    }

    public void setTourList(List<TourApiDto> list) {
        this.tourList = list;
    }

    @PreDestroy
    public void destroy() {
        log.info("TourLocalRepository 파괴");
    }
}
