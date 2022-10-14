package project.trut.service.tour;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import project.trut.domain.location.LocationForm;
import project.trut.domain.api.TourApiDto;
import project.trut.web.TourLocalRepository;

import java.util.List;

import static java.lang.Math.*;

@Slf4j
public class OrderCalc {

    private final List<TourApiDto> tourList;
    private final LocationForm location;
    private int[] order;
    private double dis = Integer.MAX_VALUE;

    @Autowired
    public OrderCalc(TourLocalRepository tourLocalRepository) {
        this.tourList = tourLocalRepository.getTourList();
        this.location = tourLocalRepository.getLocation();
    }

    public int[] getOrder(){
        int size = tourList.size();
        int[] idx = new int[size];
        for (int i = 0; i < size; i++) {
            idx[i] = i;
        }
        permutation(idx,0,size);

        return order;
    }


    private void permutation(int[] arr, int depth, int n) {
        if (depth == n) {
            distance(arr);
            return;
        }
        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n);
            swap(arr, depth, i);
        }
    }

    private void swap(int[] arr, int depth, int i) {
        int tmp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = tmp;
    }

    private void distance(int[] arr) {
        double startX = location.getDeparture().coordinate().get("mapX");
        double startY = location.getDeparture().coordinate().get("mapY");
        double endX = location.getDestination().coordinate().get("mapX");
        double endY = location.getDestination().coordinate().get("mapY");

        double tmp = calc(startX, startY, tourList.get(arr[0]).getMapX(), tourList.get(arr[0]).getMapY());

        for (int i = 1; i < arr.length; i++) {
            tmp += calc(tourList.get(arr[i - 1]).getMapX(), tourList.get(arr[i - 1]).getMapY(), tourList.get(arr[i]).getMapX(), tourList.get(arr[i]).getMapY());
        }

        int size = arr.length-1;
        tmp += calc(tourList.get(arr[size]).getMapX(), tourList.get(arr[size]).getMapY(), endX, endY);

        if (tmp < dis) {
            dis = tmp;
            order = arr.clone();
        }
    }

    private double calc(double x, double y, double x1, double y1) {
        return sqrt(pow(abs(x1 - x), 2) + pow(abs(y1 - y), 2));
    }


}
