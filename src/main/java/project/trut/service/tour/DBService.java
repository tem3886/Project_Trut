package project.trut.service.tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.coordinate.Coordinate;
import project.trut.domain.member.Member;
import project.trut.domain.tour.Tour;
import project.trut.repository.coordinate.CoordinateRepository;
import project.trut.repository.tour.TourRepository;
import project.trut.repository.tour.TourUpdateDto;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DBService {
    private final TourRepository tourRepository;
    private final CoordinateRepository coordinateRepository;

    @Transactional
    public void save(List<Coordinate> list, Member member) {

        for (int i = list.size()-1; i < 4; i++) {
            list.add(i,new Coordinate(null, null, null));
        }

        Tour tour = getTour(list, member);

        try {
            tourRepository.save(tour);
        } catch (DuplicateKeyException e) {
            TourUpdateDto updateDto = getTourUpdateDto(tour);
            tourRepository.update(member.getId(), LocalDate.now(), updateDto);
        }

        for (Coordinate tmp: list) {
            if (tmp.getTitle() != null) {
                try {
                    coordinateRepository.save(tmp);
                } catch (DuplicateKeyException e) {
                    log.info("이미 존재하는 데이터 입니다. info = {}", tmp.toString());
                }
            }
        }
    }

    @Transactional
    public List<Tour> getTourById(Long id) {
        return tourRepository.findById(id);
    }

    private Tour getTour(List<Coordinate> list, Member member) {
        Tour tour = new Tour();

        tour.setId(member.getId());
        tour.setDateTime(LocalDate.now());
        tour.setDeparture(list.get(0).getTitle());
        tour.setTitleA(list.get(1).getTitle());
        tour.setTitleB(list.get(2).getTitle());
        tour.setTitleC(list.get(3).getTitle());
        tour.setDestination(list.get(4).getTitle());

        return tour;
    }

    private TourUpdateDto getTourUpdateDto(Tour tour) {
        TourUpdateDto updateDto = new TourUpdateDto();
        updateDto.setDeparture(tour.getDeparture());
        updateDto.setDestination(tour.getDestination());
        updateDto.setTitleA(tour.getTitleA());
        updateDto.setTitleB(tour.getTitleB());
        updateDto.setTitleC(tour.getTitleC());
        return updateDto;
    }

}

