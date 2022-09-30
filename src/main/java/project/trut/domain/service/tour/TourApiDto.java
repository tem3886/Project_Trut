package project.trut.domain.service.tour;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class TourApiDto {
    private String addr;
    private String image;
    private String mapX;
    private String mapY;
    private String tel;
    private String title;
}
