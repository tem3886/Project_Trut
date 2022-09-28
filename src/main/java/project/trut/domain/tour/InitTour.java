package project.trut.domain.tour;

import lombok.Getter;

// 자연: A01, 인문: A02, 레포츠: A03, 쇼핑: A04, 음식: A05, 숙박: B02
@Getter
public enum InitTour {
    nature("자연"){
        @Override
        public String getCode() {
            return "A01";
        }
    }, humanities("인문"){
        @Override
        public String getCode() {
            return "A02";
        }
    }, leports("레포츠"){
        @Override
        public String getCode() {
            return "A03";
        }
    },
    shopping("쇼핑"){
        @Override
        public String getCode() {
            return "A04";
        }
    }, food("음식"){
        @Override
        public String getCode() {
            return "A05";
        }
    }, stay("숙박"){
        @Override
        public String getCode() {
            return "B02";
        }
    };

    private final String description;

    InitTour(String description) {
        this.description = description;
    }

    public abstract String getCode();
}
