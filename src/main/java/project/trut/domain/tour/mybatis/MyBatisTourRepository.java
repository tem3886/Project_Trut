package project.trut.domain.tour.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.trut.domain.tour.Tour;
import project.trut.domain.tour.TourRepository;
import project.trut.domain.tour.TourUpdateDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisTourRepository implements TourRepository {

    private final TourMapper tourMapper;


    @Override
    public void save(Tour tour) {
        tourMapper.save(tour);
    }

    @Override
    public void update(Long id, LocalDate dateTime, TourUpdateDto updateParam) {
        tourMapper.update(id, dateTime, updateParam);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourMapper.findById(id);
    }

    @Override
    public List<Tour> findByIdAndDateTime(Long id, LocalDate dateTime) {
        return tourMapper.findByIdAndDateTime(id, dateTime);
    }
}
