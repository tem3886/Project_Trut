package project.trut.domain.location;

import java.util.Optional;

public interface LocationRepository {

    Location save(Location location);

    void update(Long id, LocationUpdateDto updateParam);

    Optional<Location> findById(Long id);
}
