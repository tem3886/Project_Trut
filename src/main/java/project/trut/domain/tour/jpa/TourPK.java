package project.trut.domain.tour.jpa;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TourPK implements Serializable {

    private Long id;
    private LocalDate dateTime;

    public TourPK() {
    }
}
