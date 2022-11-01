package project.trut.repository.tour.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.tour.Tour;
import project.trut.repository.tour.TourRepository;
import project.trut.repository.tour.TourUpdateDto;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaTourRepositoryImple implements TourRepository {

    private final JpaTourRepository jpaTourRepository;

    @Override
    public void save(Tour tour) {
        jpaTourRepository.save(tour);
    }

    @Override
    public void update(Long id, LocalDate dateTime, TourUpdateDto updateDto) {
        Tour findTour = jpaTourRepository.findByIdAndDateTime(id, dateTime);
        findTour.setDeparture(updateDto.getDeparture());
        findTour.setDestination(updateDto.getDestination());
        findTour.setTitleA(updateDto.getTitleA());
        findTour.setTitleB(updateDto.getTitleB());
        findTour.setTitleC(updateDto.getTitleC());
    }

    @Override
    public List<Tour> findById(Long id) {
        return jpaTourRepository.findByIdOrderByDateTimeDesc(id);
    }

    @Override
    public Tour findByIdAndDateTime(Long id, LocalDate dateTime) {
        return jpaTourRepository.findByIdAndDateTime(id, dateTime);
    }
}
