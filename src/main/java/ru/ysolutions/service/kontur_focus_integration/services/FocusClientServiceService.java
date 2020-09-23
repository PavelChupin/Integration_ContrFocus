package ru.ysolutions.service.kontur_focus_integration.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

import javax.annotation.PostConstruct;

@Service
public class FocusClientServiceService implements FocusClientServiceImpl {
    private RestTemplate restTemplate;

    @PostConstruct
    public void initRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public byte[] getFilePDF(EnumFocusController url_part, String key, String ogrn, String inn) {
        return restTemplate.getForEntity(String.format("https://focus-api.kontur.ru/api3/%s?key=%s&inn=%s&ogrn=%s&xml", url_part.getValue(), key, inn, ogrn), byte[].class).getBody();
    }

    @Override
    public String getInfoUL(EnumFocusController url_part, String key, String ogrn, String inn) {
        return restTemplate.getForEntity(String.format("https://focus-api.kontur.ru/api3/%s?key=%s&inn=%s&ogrn=%s&xml", url_part.getValue(), key, inn, ogrn), String.class).getBody();
    }
    /*
    @Override
    public String req(String key, String ogrn, String inn) {
        return restTemplate.getForEntity(String.format("https://focus-api.kontur.ru/api3/req?key=%s&inn=%s&ogrn=%s&xml", key, inn, ogrn), String.class).getBody();
    }

    @Override
    public String getEgrDetails(String key, String ogrn, String inn) {
        return restTemplate.getForEntity(String.format("https://focus-api.kontur.ru/api3/egrDetails?key=%s&inn=%s&ogrn=%s&xml", key, inn, ogrn), String.class).getBody();
    }

    @Override
    public String getAnalytics(String key, String ogrn, String inn) {
        //String url = String.format("https://focus-api.kontur.ru/api3/analytics?key=%s&inn=%s&ogrn=%s&xml",key,inn,ogrn);


        //ResponseEntity<Analytics[]> responseEntity = restTemplate.getForEntity(url, Analytics[].class);
        //List<Analytics> analytics = Arrays.asList(responseEntity.getBody());


        //ResponseEntity<String> responseBody = restTemplate.getForEntity(url, String.class);
        //ResponseEntity<String> responseBody = restTemplate.exchange(url, HttpMethod.GET,null,String.class,null);

        return restTemplate.getForEntity(String.format("https://focus-api.kontur.ru/api3/analytics?key=%s&inn=%s&ogrn=%s&xml", key, inn, ogrn), String.class).getBody();
    }*/
}
