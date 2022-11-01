package project.trut.repository.tour.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import project.trut.domain.tour.Tour;
import project.trut.domain.tour.jpa.TourPK;

import java.time.LocalDate;
import java.util.List;

public interface JpaTourRepository extends JpaRepository<Tour, TourPK> {


    List<Tour> findByIdOrderByDateTimeDesc(Long id);

    Tour findByIdAndDateTime(Long id, LocalDate dateTime);
}
