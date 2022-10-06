package project.trut.service.tour;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.coordinate.Coordinate;
import project.trut.repository.coordinate.CoordinateRepository;
import project.trut.repository.tour.TourRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class DBService {
    private final TourRepository tourRepository;
    private final CoordinateRepository coordinateRepository;

}
