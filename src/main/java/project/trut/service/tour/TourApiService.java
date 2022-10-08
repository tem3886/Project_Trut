package project.trut.service.tour;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.trut.domain.ApiKey;
import project.trut.domain.api.TourApiDto;
import project.trut.web.Tour.TourPaging;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourApiService {

    private final ApiKey apiKey;

    public List<TourApiDto> getTourList(TourPaging tourPaging) throws ParseException {
        String strUri = makeUri(tourPaging);

        URI uri = URI.create(strUri);

        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri, String.class);
        List<TourApiDto> tourApiDtoList = getJsonParse(jsonString, tourPaging);

        return tourApiDtoList;
    }

    private String makeUri(TourPaging tourPaging) {
        String BaseUrl = "http://apis.data.go.kr/B551011/KorService/areaBasedList?MobileOS=ETC&MobileApp=AppTest&listYN=Y&areaCode=32&sigunguCode=13&_type=json";
        String serviceKey = "&serviceKey=" + apiKey.getTourApi();
        String page = "&numOfRows=10&pageNo=" + tourPaging.getPageNum();
        String cat = "&cat1=" + tourPaging.getCode();

        return BaseUrl + serviceKey + page + cat;
    }

    private List<TourApiDto> getJsonParse(String jsonString, TourPaging tourPaging) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
        JSONObject jsonBody = (JSONObject) jsonResponse.get("body");
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");
        JSONArray jsonItem = (JSONArray) jsonItems.get("item");

        Long tmp = (Long) jsonBody.get("totalCount");
        tourPaging.setTotalCount(tmp.intValue());

        List<TourApiDto> result = new ArrayList<>();

        for (Object o : jsonItem) {
            JSONObject item = (JSONObject) o;
            result.add(makeTourDto(item));
        }
        return result;
    }

    private TourApiDto makeTourDto(JSONObject item) {

        TourApiDto dto = new TourApiDto();
        dto.setAddr((String) item.get("addr1"));
        dto.setImage((String) item.get("firstimage"));
        dto.setMapX(Double.parseDouble((String) item.get("mapx")));
        dto.setMapY(Double.parseDouble((String) item.get("mapy")));
        dto.setTel((String) item.get("tel"));
        dto.setTitle((String) item.get("title"));

        return dto;
    }
}
