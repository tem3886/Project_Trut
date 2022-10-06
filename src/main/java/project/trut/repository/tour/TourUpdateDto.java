package project.trut.repository.tour;

import lombok.*;

@Setter @Getter
@ToString
@RequiredArgsConstructor
public class TourUpdateDto {

    private String departure;
    private String destination;
    private String titleA;
    private String titleB;
    private String titleC;

}
