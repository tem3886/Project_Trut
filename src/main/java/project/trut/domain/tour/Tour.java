package project.trut.domain.tour;

import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter @Setter
@ToString
@EqualsAndHashCode
@Entity
public class Tour {

    @Id
    private Long id;

    private LocalDate dateTime;
    private String departure;
    private String destination;
    private String titleA;
    private String titleB;
    private String titleC;

    public Tour() {
    }

    public Tour(LocalDate dateTime, String departure, String destination, String titleA, String titleB, String titleC) {
        this.dateTime = dateTime;
        this.departure = departure;
        this.destination = destination;
        this.titleA = titleA;
        this.titleB = titleB;
        this.titleC = titleC;
    }
}
