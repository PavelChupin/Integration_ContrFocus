package ru.ysolutions.service.kontur_focus_integration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ysolutions.service.kontur_focus_integration.configs.ConfigProperties;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class FocusClientServiceService implements FocusClientServiceImpl {
    private RestTemplate restTemplate;

    private ConfigProperties configProperties;

    @Autowired
    public FocusClientServiceService(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @PostConstruct
    public void initRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public byte[] getFilePDF(EnumFocusController url_part,  String ogrn, String inn) {
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&inn=%s&ogrn=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(),  inn, ogrn), byte[].class).getBody();
    }

    @Override
    public String findMessBankrFiz(EnumFocusController url_part, String q, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy'%2F'MM'%2F'dd", Locale.ENGLISH);
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&q=%s&date=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(),  q, dateFormat.format(date)), String.class).getBody();
    }

    @Override
    public String getInfoMessBankrFiz(EnumFocusController url_part, String innfl, String fio, Date birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&innfl=%s&fio=%s&birthDate=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), innfl, fio,dateFormat.format(birthDate)), String.class).getBody();
    }

    @Override
    public String isInvalidPassports(EnumFocusController url_part, String passportNumber) {
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&passportNumber=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(),passportNumber), String.class).getBody();
    }

    @Override
    public String findPublicDolLic(EnumFocusController url_part, String fio) {
        //Map<String,String> map = new HashMap<>();
        //map.put("key",configProperties.getKey());
        //map.put("fio",fio);
        //map.put("xml",null);
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&fio=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(),fio), String.class).getBody();
        //return restTemplate.getForEntity(String.format("%s/%s", configProperties.getUrl(), url_part.getValue()), String.class,map).getBody();
    }

    @Override
    public String getForeignRepresen(EnumFocusController url_part, String inn, String nza) {
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&inn=%s&nza=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(),inn,nza), String.class).getBody();
    }

    @Override
    public String getInfoUL(EnumFocusController url_part,  String ogrn, String inn) {
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&inn=%s&ogrn=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(),  inn, ogrn), String.class).getBody();
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
