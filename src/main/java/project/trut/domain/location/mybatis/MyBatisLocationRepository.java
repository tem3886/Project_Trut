package project.trut.domain.location.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import project.trut.domain.location.Location;
import project.trut.domain.location.LocationRepository;
import project.trut.domain.location.LocationUpdateDto;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyBatisLocationRepository implements LocationRepository {

    private final LocationMapper locationMapper;

    @Override
    public Location save(Location location) {
        locationMapper.save(location);
        return location;
    }

    @Override
    public void update(Long id, LocationUpdateDto updateParam) {
        locationMapper.update(id, updateParam);
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationMapper.findById(id);
    }
}
