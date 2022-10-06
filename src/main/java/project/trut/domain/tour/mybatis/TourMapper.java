package project.trut.domain.tour.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.trut.domain.location.Location;
import project.trut.domain.location.LocationUpdateDto;
import project.trut.domain.tour.Tour;
import project.trut.domain.tour.TourUpdateDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface TourMapper {

    void save(Tour tour);

    void update(@Param("id") Long id, @Param("dateTime") LocalDate dateTime, @Param("updateParam") TourUpdateDto updateParam);

    Optional<Tour> findById(Long id);

    List<Tour> findByIdAndDateTime(Long id, LocalDate dateTime);


}
