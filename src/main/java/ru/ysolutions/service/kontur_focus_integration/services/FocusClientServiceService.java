package ru.ysolutions.service.kontur_focus_integration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ysolutions.service.kontur_focus_integration.configs.ConfigProperties;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

import javax.annotation.PostConstruct;

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
    public byte[] getFilePDF(EnumFocusController url_part, String ogrn, String inn) {
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&inn=%s&ogrn=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), inn, ogrn), byte[].class).getBody();
    }

    @Override
    public byte[] getFilePDFBriefReport(EnumFocusController url_part, String ogrn, String inn, boolean pdf) {
        String params = String.format("key=%s", configProperties.getKey());

        if (inn != null) {
            params += String.format("&inn=%s", inn);
        }

        if (ogrn != null) {
            params += String.format("&ogrn=%s", ogrn);
        }
        System.out.println(String.format("%s/%s?%s&pdf=%s", configProperties.getUrl(), url_part.getValue(), params, pdf));
        return restTemplate.getForEntity(String.format("%s/%s?%s&pdf=%s", configProperties.getUrl(), url_part.getValue(), params, pdf), byte[].class).getBody();
    }

    @Override
    public String findMessBankrFiz(EnumFocusController url_part, String q, String date) {
        String params = String.format("key=%s", configProperties.getKey());

        if (q != null) {
            params += String.format("&q=%s", q);
        }

        if (date != null) {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy'%2F'MM'%2F'dd", Locale.ENGLISH);
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            //params += String.format("&date=%s", dateFormat.format(date));
            params += String.format("&date=%s", date);
        }

        return restTemplate.getForEntity(String.format("%s/%s?%s&xml", configProperties.getUrl(), url_part.getValue(), params), String.class).getBody();
    }

    @Override
    public String getInfoMessBankrFiz(EnumFocusController url_part, String innfl, String fio, String birthDate) {
        String params = String.format("key=%s", configProperties.getKey());

        if (innfl != null) {
            params += String.format("&innfl=%s", innfl);
        }
        if (fio != null) {
            params += String.format("&fio=%s", fio);
        }
        if (birthDate != null) {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            //params += String.format("&birthDate=%s", dateFormat.format(birthDate));
            params += String.format("&date=%s", birthDate);
        }

        return restTemplate.getForEntity(String.format("%s/%s?%s&xml", configProperties.getUrl(), url_part.getValue(), params), String.class).getBody();
    }

    @Override
    public String isInvalidPassports(EnumFocusController url_part, String passportNumber) {
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&passportNumber=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), passportNumber), String.class).getBody();
    }

    @Override
    public String findPublicDolLic(EnumFocusController url_part, String fio) {
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&fio=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), fio), String.class).getBody();
    }

    @Override
    public String getForeignRepresen(EnumFocusController url_part, String inn, String nza) {
        String params = String.format("key=%s", configProperties.getKey());

        if (inn != null) {
            params += String.format("&inn=%s", inn);
        }

        if (nza != null) {
            params += String.format("&nza=%s", nza);
        }
        return restTemplate.getForEntity(String.format("%s/%s?%s&xml", configProperties.getUrl(), url_part.getValue(), params), String.class).getBody();
    }

    @Override
    public String getInfoUL(EnumFocusController url_part, String ogrn, String inn) {
        return restTemplate.getForEntity(String.format("%s/%s?key=%s&inn=%s&ogrn=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), inn, ogrn), String.class).getBody();
    }
}
