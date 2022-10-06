package project.trut.repository.coordinate;

import project.trut.domain.coordinate.Coordinate;

public interface CoordinateRepository {

    void save(Coordinate coordinate);

    Coordinate findByTitle(String title);
}
