package project.trut.domain.coordinate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Required;

@Setter @Getter
@ToString
public class Coordinate {

    private String title;
    private String mapX;
    private String mapY;

    public Coordinate() {
    }

    public Coordinate(String title, String mapX, String mapY) {
        this.title = title;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
