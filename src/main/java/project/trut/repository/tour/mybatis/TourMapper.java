package project.trut.repository.tour.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.trut.domain.tour.Tour;
import project.trut.repository.tour.TourUpdateDto;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TourMapper {

    void save(Tour tour);

    void update(@Param("id") Long id, @Param("dateTime") LocalDate dateTime, @Param("updateDto") TourUpdateDto updateDto);

    List<Tour> findById(Long id);

    Tour findByIdAndDateTime(@Param("id")Long id, @Param("dateTime")LocalDate dateTime);
}
