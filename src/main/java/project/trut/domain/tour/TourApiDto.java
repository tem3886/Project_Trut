package project.trut.domain.tour;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
@EqualsAndHashCode
public class TourApiDto {
    private String addr;
    private String image;
    private String mapX;
    private String mapY;
    private String tel;
    private String title;
}
