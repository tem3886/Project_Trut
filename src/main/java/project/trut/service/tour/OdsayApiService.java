package project.trut.service.tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.trut.domain.ApiKey;
import project.trut.domain.api.BusInfo;
import project.trut.domain.api.GraphPos;
import project.trut.domain.api.OdsayApiDto;
import project.trut.domain.coordinate.Coordinate;
import project.trut.domain.api.TourApiDto;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OdsayApiService {

    private final ApiKey apiKey;

    public List<List<OdsayApiDto>> getOdsay(List<Coordinate> result) throws ParseException {

        List<List<OdsayApiDto>> odsayApiDtoList = new ArrayList<>();

        for (int idx = 1; idx < result.size(); idx++) {
            String strUri = makeUri(result.get(idx - 1), result.get(idx));

            URI uri = URI.create(strUri);

            RestTemplate restTemplate = new RestTemplate();
            String jsonString = restTemplate.getForObject(uri, String.class);
            odsayApiDtoList.add(getJsonParse(jsonString));
        }

        return odsayApiDtoList;
    }

    public List<List<GraphPos>> getLane(String mapObj) throws ParseException {
        String strUri = makeLaneUri(mapObj);

        URI uri = URI.create(strUri);

        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri, String.class);
        return getLaneJsonParse(jsonString);

    }

    private List<List<GraphPos>> getLaneJsonParse(String jsonString) throws ParseException {
        List<List<GraphPos>> graphPosList = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONObject jsonResult = (JSONObject) jsonObject.get("result");
        JSONArray jsonLane = (JSONArray) jsonResult.get("lane");

        for (Object lane : jsonLane) {
            JSONObject subLane = (JSONObject) lane;
            JSONArray jsonSection = (JSONArray) subLane.get("section");

            graphPosList.add(getGraphPos(jsonSection));
        }

        return graphPosList;

    }

    private List<GraphPos> getGraphPos(JSONArray jsonSection) {
        List<GraphPos> list = new ArrayList<>();

        for (Object section : jsonSection) {
            JSONObject subSection = (JSONObject) section;
            JSONArray jsonGraphPos = (JSONArray) subSection.get("graphPos");

            for (Object graphPos : jsonGraphPos) {
                JSONObject subGraphPos = (JSONObject) graphPos;

                GraphPos g = new GraphPos();
                g.setX((Double) subGraphPos.get("x"));
                g.setY((Double) subGraphPos.get("y"));
                list.add(g);
            }
        }
        return list;
    }

    private String makeLaneUri(String mapObj) {
        String BaseUrl = "https://api.odsay.com/v1/api/loadLane?mapObject=0:0@";
        String mapObject = mapObj;
        String serviceKey = null;

        try {
            serviceKey = "&apiKey=" + URLEncoder.encode(apiKey.getOdsayApi(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return BaseUrl + mapObject + serviceKey;
    }


    private String makeUri(Coordinate departure, Coordinate destination) {
        String BaseUrl = "https://api.odsay.com/v1/api/searchPubTransPathT?OPT=0&SearchType=0&SearchPathType=2";
        String start = "&SX=" + departure.getMapX() + "&SY=" + departure.getMapY();
        String end = "&EX="+ destination.getMapX() + "&EY=" + destination.getMapY();
        String serviceKey = null;
        try {
            serviceKey = "&apiKey=" + URLEncoder.encode(apiKey.getOdsayApi(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return BaseUrl + start + end + serviceKey;
    }

    private List<OdsayApiDto> getJsonParse(String jsonString) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONObject jsonResult = (JSONObject) jsonObject.get("result");

        JSONArray jsonPath = (JSONArray) jsonResult.get("path");

        List<OdsayApiDto> result = new ArrayList<>();

        for (Object o : jsonPath) {
            JSONObject item = (JSONObject) o;
            result.add(makeOdsayDto(item));
        }

        return result;
    }

    private OdsayApiDto makeOdsayDto(JSONObject item) {
        OdsayApiDto dto = new OdsayApiDto();

        JSONObject jsonInfo = (JSONObject) item.get("info");

        dto.setTotalTime((Long) jsonInfo.get("totalTime"));
        dto.setMapObj((String) jsonInfo.get("mapObj"));

        JSONArray subPath = (JSONArray) item.get("subPath");

        dto.setBusInfoList(makeBusInfo(subPath));

        return dto;
    }

    private List<BusInfo> makeBusInfo(JSONArray subPath) {

        List<BusInfo> busInfoList = new ArrayList<>();

        for (Object o : subPath) {
            BusInfo busInfo = new BusInfo();
            JSONObject tmp = (JSONObject) o;

            Long trafficType = (Long) tmp.get("trafficType");
            if (trafficType != 2l) {
                continue;
            }

            busInfo.setStartStation((String) tmp.get("startName"));
            busInfo.setEndStation((String) tmp.get("endName"));

            JSONArray jsonLane = (JSONArray) tmp.get("lane");

            JSONObject jsonBus = (JSONObject) jsonLane.get(0);

            busInfo.setBusNo((String) jsonBus.get("busNo"));

            busInfoList.add(busInfo);
        }
        return busInfoList;
    }


}
