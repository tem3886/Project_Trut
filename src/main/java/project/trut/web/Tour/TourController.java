package project.trut.web.Tour;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String wayPoint(@RequestParam(name = "error", defaultValue = "false") boolean error,
                           Model model) {
        if (error) {
            model.addAttribute("error", "잠시 후에 다시 시도해 주세요.");
        }
        model.addAttribute("paging", new TourPaging());
        model.addAttribute("tourList", new ArrayList<TourApiDto>());
        model.addAttribute("classification", InitTour.nature);
        return "trut/tour";
    }

    @PostMapping
    public String findTour(@ModelAttribute("classification") InitTour initTour,
                             Model model) {

        TourPaging paging = new TourPaging();
        paging.setCode(initTour.getCode());
        try {
            getTourList(model, paging);
        } catch (ParseException e) {
            return "redirect:/trut/tour?error=true";
        }

        return "trut/tour";
    }

    @GetMapping("/{pageNum}")
    public String tourPage(@PathVariable("pageNum") int pageNum,
                           @RequestParam("code") String code,
                           Model model) {

        log.info("error");
        TourPaging paging = new TourPaging();
        InitTour initTour = InitTour.nature;
        for (InitTour tour: InitTour.values()) {
            if (tour.getCode() == code) {
                initTour = tour;
            }
        }
        paging.setCode(initTour.getCode());
        paging.setPageNum(pageNum);

        try {
            getTourList(model, paging);
        } catch (ParseException e) {
            return "redirect:/trut/tour?error=true";
        }

        return "trut/tour";
    }

    private void getTourList(Model model, TourPaging paging) throws ParseException {
        List<TourApiDto> tourList = getTourList(paging);
        model.addAttribute("tourList", tourList);
        model.addAttribute("paging", paging);
    }


    private List<TourApiDto> getTourList(TourPaging tourPaging) throws ParseException {
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
        log.info("jsonString = {}", jsonString);
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
        dto.setMapX((String) item.get("mapx"));
        dto.setMapY((String) item.get("mapy"));
        dto.setTel((String) item.get("tel"));
        dto.setTitle((String) item.get("title"));

        return dto;
    }

}
