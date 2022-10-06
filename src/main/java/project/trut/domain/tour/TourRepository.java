package project.trut.domain.tour;

import project.trut.domain.location.Location;
import project.trut.domain.location.LocationUpdateDto;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TourRepository {

    void save(Tour tour);

    void update(Long id, LocalDate dateTime, TourUpdateDto updateParam);

    Optional<Tour> findById(Long id);

    List<Tour> findByIdAndDateTime(Long id, LocalDate dateTime);

}
