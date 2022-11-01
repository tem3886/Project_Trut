package project.trut.domain.tour;

import lombok.*;
import net.bytebuddy.asm.Advice;
import project.trut.domain.tour.jpa.TourPK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;

@Getter @Setter
@ToString
@EqualsAndHashCode
@Entity
@IdClass(TourPK.class)
public class Tour {

    @Id
    private Long id;

    @Id
    @Column(name = "date_time")
    private LocalDate dateTime;

    private String departure;
    private String destination;
    @Column(name = "title_a")
    private String titleA;
    @Column(name = "title_b")
    private String titleB;
    @Column(name = "title_c")
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
