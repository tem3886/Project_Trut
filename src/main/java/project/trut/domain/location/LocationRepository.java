package project.trut.domain.location;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public interface LocationRepository {

    Location save(Location location);

    void update(Long id, LocalDate dateTime, LocationUpdateDto updateParam);

    Optional<Location> findById(Long id);
}
