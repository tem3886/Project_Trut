package project.trut.repository.coordinate.mybatis;

import org.apache.ibatis.annotations.Mapper;
import project.trut.domain.coordinate.Coordinate;

@Mapper
public interface CoordinateMapper {

    void save(Coordinate coordinate);

    Coordinate findByTitle(String title);
}
