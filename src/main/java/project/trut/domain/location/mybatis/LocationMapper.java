package project.trut.domain.location.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.trut.domain.location.Location;
import project.trut.domain.location.LocationUpdateDto;

import java.util.Optional;

@Mapper
public interface LocationMapper {

    void save(Location location);

    void update(@Param("id") Long id, @Param("updateParam") LocationUpdateDto updateParam);

    Optional<Location> findById(Long id);

}
