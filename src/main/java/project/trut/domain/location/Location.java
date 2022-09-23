package project.trut.domain.location;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Location {

    @Id
    private Long id;

    private String departure;
    private String destination;

}
