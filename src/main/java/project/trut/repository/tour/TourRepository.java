package project.trut.repository.tour;

import project.trut.domain.tour.Tour;

import java.time.LocalDate;
import java.util.List;

public interface TourRepository {

    void save(Tour tour);

    void update(Long id, LocalDate dateTime, TourUpdateDto updateDto);

    List<Tour> findById(Long id);

    Tour findByIdAndDateTime(Long id, LocalDate dateTime);

}
