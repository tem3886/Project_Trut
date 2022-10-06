package project.trut.domain.location.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.trut.domain.location.Location;
import project.trut.domain.location.LocationUpdateDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface LocationMapper {

    void save(Location location);

    void update(@Param("id") Long id, @Param("dateTime") LocalDate dateTime, @Param("updateParam") LocationUpdateDto updateParam);

    Optional<Location> findById(Long id);

    List<Location> findByIdAndDateTime(Long id, LocalDate date);

}
