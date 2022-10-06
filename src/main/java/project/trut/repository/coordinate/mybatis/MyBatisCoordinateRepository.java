package project.trut.repository.coordinate.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.trut.domain.coordinate.Coordinate;
import project.trut.repository.coordinate.CoordinateRepository;

@Repository
@RequiredArgsConstructor
public class MyBatisCoordinateRepository implements CoordinateRepository {

    private final CoordinateMapper coordinateMapper;

    @Override
    public void save(Coordinate coordinate) {
        coordinateMapper.save(coordinate);
    }

    @Override
    public Coordinate findByTitle(String title) {
        return coordinateMapper.findByTitle(title);
    }
}
