package project.trut.web.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import project.trut.domain.ApiKey;
import project.trut.domain.tour.InitTour;

import java.net.URI;
import java.util.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/trut/tour")
public class TourController {

    private final ApiKey apiKey;

    @ModelAttribute("initTours")
    public InitTour[] initTours(){
        return InitTour.values();
    }


    @GetMapping
    public String wayPoint(Model model) {
        List<TourApiDto> tourList = new ArrayList<>();
        model.addAttribute("tourList", tourList);
        return "trut/tour";
    }

    @PostMapping
    public String selectTour(@ModelAttribute("classification") InitTour initTour) {
        List<TourApiDto> tourList = getTourList();
        log.info("select classification = {}", initTour.getDescription());
        log.info("Code = {}", initTour.getCode());
        return "trut/tour";
    }

    private List<TourApiDto> getTourList() {
        String strUri = "http://apis.data.go.kr/B551011/KorService/areaBasedList?serviceKey=" +
                apiKey.getTourApi() + "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&areaCode=32&sigunguCode=13&_type=json";

        URI uri = URI.create(strUri);

        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri, String.class);

        try {
            List<TourApiDto> tourApiDtoList = getJsonParse(jsonString);
            log.info("tourApiDtoList = {}", tourApiDtoList.size());
            return tourApiDtoList;
        } catch (ParseException e) {
            log.info("parse error", e);
            throw new RuntimeException(e);
        }
    }

    private List<TourApiDto> getJsonParse(String jsonString) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
        JSONObject jsonBody = (JSONObject) jsonResponse.get("body");
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");
        JSONArray jsonItem = (JSONArray) jsonItems.get("item");

        log.info("totalCount = {}", jsonBody.get("totalCount"));

        List<TourApiDto> result = new ArrayList<>();

        for (Object o : jsonItem) {
            JSONObject item = (JSONObject) o;
            result.add(makeTourDto(item));
        }
        return result;
    }

    private TourApiDto makeTourDto(JSONObject item) {

        TourApiDto dto = new TourApiDto();
        dto.setAddr((String) item.get("add1"));
        dto.setImage((String) item.get("firstimage"));
        dto.setMapX((String) item.get("mapx"));
        dto.setMapY((String) item.get("mapy"));
        dto.setTel((String) item.get("tel"));
        dto.setTitle((String) item.get("title"));

        return dto;
    }

}
