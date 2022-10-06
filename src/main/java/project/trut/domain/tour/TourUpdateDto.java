package project.trut.domain.tour;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class TourUpdateDto {

    private String titleA;
    private String titleB;
    private String titleC;

    public TourUpdateDto() {
    }

    public TourUpdateDto(String titleA, String titleB, String titleC) {
        this.titleA = titleA;
        this.titleB = titleB;
        this.titleC = titleC;
    }
}
