package project.trut.web.Tour;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode
public class MapPathForm {
    private String title1;
    private Double mapX1;
    private Double mapY1;
    private String title2;
    private Double mapX2;
    private Double mapY2;
    private String mapObj;
}
