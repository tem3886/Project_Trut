package project.trut.domain.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter @Getter
@ToString
@EqualsAndHashCode
public class OdsayApiDto {

    private String mapObj;
    private Long totalTime;
    private List<BusInfo> busInfoList;

}
