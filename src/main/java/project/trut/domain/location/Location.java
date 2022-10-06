package project.trut.domain.location;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode
@Getter @Setter
public class Location {

    @Id
    private Long id;

    private LocalDate dateTime;
    private String departure;
    private String destination;

    public Location(){}

    public Location(LocalDate dateTime, String departure, String destination) {
        this.dateTime = dateTime;
        this.departure = departure;
        this.destination = destination;
    }

    public Location(Long id, LocalDate dateTime, String departure, String destination) {
        this.id = id;
        this.dateTime = dateTime;
        this.departure = departure;
        this.destination = destination;
    }
}
