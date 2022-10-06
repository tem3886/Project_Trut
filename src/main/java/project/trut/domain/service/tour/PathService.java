package project.trut.domain.service.tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.trut.domain.ApiKey;
import project.trut.domain.location.LocationForm;
import project.trut.domain.tour.TourApiDto;
import project.trut.domain.tour.TourLocalRepository;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PathService {

    private final TourLocalRepository tourLocalRepository;
    private final TourDbService tourDbService;
    private final ApiKey apiKey;

    public void getOrder(){
        PathCalc pathCalc = new PathCalc(tourLocalRepository);
        int[] order = pathCalc.getOrder();
        List<CoordinateForm> result = initOrder(order);

        tourDbService.save(result);

        for (CoordinateForm tmp: result) {
            log.info("tmp = {}", tmp);
        }

    }

    private List<CoordinateForm> initOrder(int[] order) {
        List<CoordinateForm> result = new ArrayList<>();
        List<TourApiDto> tourList = tourLocalRepository.getTourList();

        String title = tourLocalRepository.getLocation().getDeparture().getDescription();
        String mapX = String.valueOf(tourLocalRepository.getLocation().getDeparture().coordinate().get("mapX"));
        String mapY = String.valueOf(tourLocalRepository.getLocation().getDeparture().coordinate().get("mapY"));
        result.add(new CoordinateForm(title, mapX, mapY));

        for (int idx: order) {
            title = tourList.get(idx).getTitle();
            mapX = String.valueOf(tourList.get(idx).getMapX());
            mapY = String.valueOf(tourList.get(idx).getMapY());
            result.add(new CoordinateForm(title, mapX, mapY));
        }

        title = tourLocalRepository.getLocation().getDestination().getDescription();
        mapX = String.valueOf(tourLocalRepository.getLocation().getDestination().coordinate().get("mapX"));
        mapY = String.valueOf(tourLocalRepository.getLocation().getDestination().coordinate().get("mapY"));
        result.add(new CoordinateForm(title, mapX, mapY));

        return result;
    }

    /*public void getOdsay() {
        String urlInfo = makeUri();

        URI uri = URI.create(urlInfo);

        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri, String.class);
        List<TourApiDto> tourApiDtoList = getJsonParse(jsonString, tourPaging);

        return tourApiDtoList;
    }

    public String makeUri() {
        String BaseUrl = "https://api.odsay.com/v1/api/searchPubTransPathT?lang=0&OPT=0&SearchType=0&SearchPathType=2";
        String start = "&SX=127.7166&SY=37.8844";
        String end = "&EX=127.7238&EY=37.8641";
        String serviceKey = "?apiKey=" + apiKey.getOdsayApi();

        return BaseUrl + start + end + serviceKey;
    }*/

}
