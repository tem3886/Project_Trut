package project.trut.domain.location;

import lombok.Getter;

@Getter
public enum InitLocation {

    CHUNCHEON("춘천"), NAMCHUNCHEON("남춘천");

    private final String description;


    InitLocation(String description) {
        this.description = description;
    }
}
