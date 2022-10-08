package project.trut.repository.tour;

import lombok.*;

@Setter @Getter
@ToString
public class TourUpdateDto {

    private String departure;
    private String destination;
    private String titleA;
    private String titleB;
    private String titleC;

    public TourUpdateDto() {
    }

    public TourUpdateDto(String departure, String destination, String titleA, String titleB, String titleC) {
        this.departure = departure;
        this.destination = destination;
        this.titleA = titleA;
        this.titleB = titleB;
        this.titleC = titleC;
    }
}
