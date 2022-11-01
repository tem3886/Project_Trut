package project.trut.repository.coordinate.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import project.trut.domain.coordinate.Coordinate;

public interface JpaCoordinateRepository extends JpaRepository<Coordinate, String> {

    Coordinate findByTitle(String title);
}
