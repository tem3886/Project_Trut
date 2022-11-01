package project.trut.repository.coordinate.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.coordinate.Coordinate;
import project.trut.repository.coordinate.CoordinateRepository;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaCoordinateRepositoryImple implements CoordinateRepository {

    private final JpaCoordinateRepository jpaCoordinateRepository;

    @Override
    public void save(Coordinate coordinate) {
        jpaCoordinateRepository.save(coordinate);
    }

    @Override
    public Coordinate findByTitle(String title) {
        return jpaCoordinateRepository.findByTitle(title);
    }
}
