package project.trut.domain.location;

import lombok.Getter;

import java.util.Map;

@Getter
public enum InitLocation {

    CHUNCHEON("춘천"){
        @Override
        public Map<String, Double> coordinate() {
            Map<String, Double> map = Map.of("mapX", 127.7166, "mapY", 37.8844);
            return map;
        }
    }, NAMCHUNCHEON("남춘천"){
        @Override
        public Map<String, Double> coordinate() {
            Map<String, Double> map = Map.of("mapX", 127.7238, "mapY", 37.8641);
            return map;
        }
    };

    private final String description;


    InitLocation(String description) {
        this.description = description;
    }

    public abstract Map<String,Double> coordinate();
}
