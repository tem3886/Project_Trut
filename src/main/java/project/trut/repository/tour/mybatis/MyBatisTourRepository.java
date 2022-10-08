package project.trut.repository.tour.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.trut.domain.tour.Tour;
import project.trut.repository.tour.TourRepository;
import project.trut.repository.tour.TourUpdateDto;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisTourRepository implements TourRepository {

    private final TourMapper tourMapper;

    @Override
    public void save(Tour tour) {
        tourMapper.save(tour);
    }

    @Override
    public void update(Long id, LocalDate dateTime, TourUpdateDto updateDto) {
        tourMapper.update(id, dateTime, updateDto);
    }

    @Override
    public List<Tour> findById(Long id) {
        return tourMapper.findById(id);
    }

    @Override
    public Tour findByIdAndDateTime(Long id, LocalDate dateTime) {
        return tourMapper.findByIdAndDateTime(id, dateTime);
    }
}
