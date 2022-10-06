package project.trut.domain.service.tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.tour.TourLocalRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TourDbService {

    private final TourLocalRepository tourLocalRepository;

    @Transactional
    void save(List<CoordinateForm> forms) {

    }
}
