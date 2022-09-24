package project.trut.domain.service.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.trut.domain.location.Location;
import project.trut.domain.location.LocationRepository;
import project.trut.domain.location.LocationUpdateDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location save(Location location){
        return locationRepository.save(location);
    }

    public void update(Long id, LocalDate dateTime, LocationUpdateDto updateParam) {
        locationRepository.update(id,dateTime,updateParam);
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }
}
