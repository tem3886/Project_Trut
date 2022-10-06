package project.trut.domain.location;

import project.trut.domain.tour.Tour;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {

    void save(Location location);

    void update(Long id, LocalDate dateTime, LocationUpdateDto updateParam);

    Optional<Location> findById(Long id);

    List<Location> findByIdAndDateTime(Long id, LocalDate dateTime);
}
