package project.trut.domain.coordinate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter @Getter
@ToString
@Entity
public class Coordinate {

    @Id
    private String title;
    @Column(name = "map_x")
    private String mapX;
    @Column(name = "map_y")
    private String mapY;

    public Coordinate() {
    }

    public Coordinate(String title, String mapX, String mapY) {
        this.title = title;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
