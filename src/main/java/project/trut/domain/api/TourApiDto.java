package project.trut.domain.api;

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
    private double mapX;
    private double mapY;
    private String tel;
    private String title;
}
