package project.trut.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter @ToString
public class ApiKey {

    @Value("${navermap-key}")
    private String naverApi;

    @Value("${tour-key}")
    private String tourApi;
}
