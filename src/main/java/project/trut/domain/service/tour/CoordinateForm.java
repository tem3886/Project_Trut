package project.trut.domain.service.tour;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Setter @Getter
@ToString
public class CoordinateForm {

    private String mapX;
    private String mapY;

    public CoordinateForm() {
    }

    public CoordinateForm(String mapX, String mapY) {
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
