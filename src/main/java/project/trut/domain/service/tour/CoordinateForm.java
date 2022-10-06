package project.trut.domain.service.tour;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Setter @Getter
@ToString
public class CoordinateForm {

    private String title;
    private String mapX;
    private String mapY;

    public CoordinateForm() {
    }


    public CoordinateForm(String title, String mapX, String mapY) {
        this.title = title;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
