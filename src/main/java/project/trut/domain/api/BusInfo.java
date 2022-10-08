package project.trut.domain.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
@EqualsAndHashCode
public class BusInfo {

    private String busNo;
    private String startStation;
    private String endStation;
}
