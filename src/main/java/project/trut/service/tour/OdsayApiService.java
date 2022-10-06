package project.trut.service.tour;

import org.springframework.stereotype.Service;

@Service
public class OdsayApiService {


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
