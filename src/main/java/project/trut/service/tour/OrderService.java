package project.trut.service.tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.trut.domain.ApiKey;
import project.trut.domain.coordinate.Coordinate;
import project.trut.domain.tour.TourApiDto;
import project.trut.domain.tour.TourLocalRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final TourLocalRepository tourLocalRepository;
    private final ApiKey apiKey;

    public void getOrder(){
        OrderCalc orderCalc = new OrderCalc(tourLocalRepository);
        int[] order = orderCalc.getOrder();
        List<Coordinate> result = initOrder(order);

        //db 저장

        for (Coordinate tmp: result) {
            log.info("tmp = {}", tmp);
        }

    }

    private List<Coordinate> initOrder(int[] order) {
        List<Coordinate> result = new ArrayList<>();
        List<TourApiDto> tourList = tourLocalRepository.getTourList();

        String title = tourLocalRepository.getLocation().getDeparture().getDescription();
        String mapX = String.valueOf(tourLocalRepository.getLocation().getDeparture().coordinate().get("mapX"));
        String mapY = String.valueOf(tourLocalRepository.getLocation().getDeparture().coordinate().get("mapY"));
        result.add(new Coordinate(title, mapX, mapY));

        for (int idx: order) {
            title = tourList.get(idx).getTitle();
            mapX = String.valueOf(tourList.get(idx).getMapX());
            mapY = String.valueOf(tourList.get(idx).getMapY());
            result.add(new Coordinate(title, mapX, mapY));
        }

        title = tourLocalRepository.getLocation().getDestination().getDescription();
        mapX = String.valueOf(tourLocalRepository.getLocation().getDestination().coordinate().get("mapX"));
        mapY = String.valueOf(tourLocalRepository.getLocation().getDestination().coordinate().get("mapY"));
        result.add(new Coordinate(title, mapX, mapY));

        return result;
    }

}
