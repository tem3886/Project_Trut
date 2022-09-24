package project.trut.domain.service.location;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.location.Location;
import project.trut.domain.location.LocationUpdateDto;
import project.trut.domain.member.Member;
import project.trut.domain.member.MemberUpdateDto;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class LocationServiceTest {

    @Autowired
    LocationService locationService;
    final LocalDate dateTime = LocalDate.now();

    @Test
    void save() {
        //given
        Location location = new Location(dateTime, "춘천역", "춘천역");
        location.setId(1L);

        //when
        locationService.save(location);

        //then
        Location findLocation = locationService.findById(location.getId()).get();
        assertThat(findLocation).isEqualTo(location);

    }

    @Test
    void update() {
        //given
        Long id = 1L;
        Location location = new Location(id, dateTime, "춘천역", "춘천역");
        Location saveLocation = locationService.save(location);

        //when
        LocationUpdateDto updateParam = new LocationUpdateDto("남춘천역", "남춘천역");
        locationService.update(id, dateTime, updateParam);

        //then
        Location findLocation = locationService.findById(id).get();
        assertThat(findLocation.getDeparture()).isEqualTo(updateParam.getDeparture());
        assertThat(findLocation.getDestination()).isEqualTo(updateParam.getDestination());

    }

    @Test
    void findById() {
        //given
        Long id = 1L;
        Location location = new Location(id, dateTime, "춘천역", "춘천역");
        locationService.save(location);

        //when
        Location findLocation = locationService.findById(id).get();

        //then
        assertThat(findLocation).isEqualTo(location);

    }
}