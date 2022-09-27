package project.trut.domain.tour;

import lombok.Getter;

@Getter
public enum InitTour {
    nature("자연"), humanities("인문"), leports("레포츠"),
    shopping("쇼핑"), food("음식"), stay("숙박");

    private final String description;

    InitTour(String description) {
        this.description = description;
    }
}
