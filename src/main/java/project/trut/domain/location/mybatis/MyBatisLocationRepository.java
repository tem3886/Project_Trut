package project.trut.domain.location.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import project.trut.domain.location.Location;
import project.trut.domain.location.LocationRepository;
import project.trut.domain.location.LocationUpdateDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyBatisLocationRepository implements LocationRepository {

    private final LocationMapper locationMapper;

    @Override
    public void save(Location location) {
        locationMapper.save(location);
    }

    @Override
    public void update(Long id, LocalDate dateTime, LocationUpdateDto updateParam) {
        locationMapper.update(id, dateTime, updateParam);
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationMapper.findById(id);
    }

    @Override
    public List<Location> findByIdAndDateTime(Long id, LocalDate date) {
        return locationMapper.findByIdAndDateTime(id, date);
    }
}
