package project.trut.domain.tour;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode
@Getter @Setter
public class Tour {

    @Id
    private Long id;

    private LocalDate dateTime;
    private String titleA;
    private String titleB;
    private String titleC;

    public Tour() {
    }

    public Tour(LocalDate dateTime, String titleA, String titleB, String titleC) {
        this.dateTime = dateTime;
        this.titleA = titleA;
        this.titleB = titleB;
        this.titleC = titleC;
    }

    public Tour(Long id, LocalDate dateTime, String titleA, String titleB, String titleC) {
        this.id = id;
        this.dateTime = dateTime;
        this.titleA = titleA;
        this.titleB = titleB;
        this.titleC = titleC;
    }
}
