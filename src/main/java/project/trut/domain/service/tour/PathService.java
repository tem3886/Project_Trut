package project.trut.domain.service.tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trut.domain.ApiKey;
import project.trut.domain.location.LocationForm;
import project.trut.domain.tour.TourApiDto;
import project.trut.domain.tour.TourLocalRepository;

import java.io.UnsupportedEncodingException;
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
    private final ApiKey apiKey;

    public void getOrder(){
        PathCalc pathCalc = new PathCalc(tourLocalRepository);
        int[] order = pathCalc.getOrder();
        List<CoordinateForm> result = initOrder(order);
        log.info("result = {}", result);
        getOdsay();

    }

    private List<CoordinateForm> initOrder(int[] order) {
        List<CoordinateForm> result = new ArrayList<>();
        List<TourApiDto> tourList = tourLocalRepository.getTourList();

        String mapX = String.valueOf(tourLocalRepository.getLocation().getDeparture().coordinate().get("mapX"));
        String mapY = String.valueOf(tourLocalRepository.getLocation().getDeparture().coordinate().get("mapY"));
        result.add(new CoordinateForm(mapX, mapY));

        for (int idx: order) {
            mapX = String.valueOf(tourList.get(idx).getMapX());
            mapY = String.valueOf(tourList.get(idx).getMapY());
            result.add(new CoordinateForm(mapX, mapY));
        }

        mapX = String.valueOf(tourLocalRepository.getLocation().getDestination().coordinate().get("mapX"));
        mapY = String.valueOf(tourLocalRepository.getLocation().getDestination().coordinate().get("mapY"));
        result.add(new CoordinateForm(mapX, mapY));

        return result;
    }

    public void getOdsay() {
        String urlInfo = makeUri();
    }

    public String makeUri() {
        String BaseUrl = "https://api.odsay.com/v1/api/searchPubTransPathT?lang=0&OPT=0&SearchType=0&SearchPathType=2";
        String start = "&SX=126.9027279&SY=37.5349277";
        String end = "&EX=126.9145430&EY=37.5499421";
        String serviceKey = "?apiKey=" + apiKey.getOdsayApi();

        return BaseUrl + start + end + serviceKey;
    }

}
