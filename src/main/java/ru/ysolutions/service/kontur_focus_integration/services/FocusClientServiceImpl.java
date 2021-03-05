package ru.ysolutions.service.kontur_focus_integration.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.ysolutions.service.kontur_focus_integration.configs.ConfigProperties;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class FocusClientServiceImpl implements FocusClientService {
    private static final Logger log = LoggerFactory.getLogger(FocusClientServiceImpl.class);

    private RestTemplate restTemplate;

    private ConfigProperties configProperties;

    @Autowired
    public FocusClientServiceImpl(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @PostConstruct
    public void initRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public byte[] getFilePDF(EnumFocusController url_part, String ogrn, String inn) throws RestClientException{
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("inn", inn);
        urlParams.put("ogrn", ogrn);
        return getResponceByteArr(getUrl(getUrlParams(urlParams), url_part));
        //return restTemplate.getForEntity(String.format("%s/%s?key=%s&inn=%s&ogrn=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), inn, ogrn), byte[].class).getBody();
    }

    @Override
    public String findMessBankrFiz(EnumFocusController url_part, String q, String date) throws RestClientException{
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("q", q);
        urlParams.put("date", date);
        return getResponceBody(getUrl(getUrlParams(urlParams), url_part));
        /*
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

         */
    }

    @Override
    public String getInfoMessBankrFiz(EnumFocusController url_part, String innfl, String fio, String birthDate) throws RestClientException{
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("innfl", innfl);
        urlParams.put("fio", fio);
        urlParams.put("birthDate", birthDate);
        return getResponceBody(getUrl(getUrlParams(urlParams), url_part));
        /*String params = String.format("key=%s", configProperties.getKey());

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

        return restTemplate.getForEntity(String.format("%s/%s?%s&xml", configProperties.getUrl(), url_part.getValue(), params), String.class).getBody();*/
    }

    @Override
    public String isInvalidPassports(EnumFocusController url_part, String passportNumber) throws RestClientException{
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("passportNumber", passportNumber);
        return getResponceBody(getUrl(getUrlParams(urlParams), url_part));
        //return restTemplate.getForEntity(String.format("%s/%s?key=%s&passportNumber=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), passportNumber), String.class).getBody();
    }

    @Override
    public String findPublicDolLic(EnumFocusController url_part, String fio) throws RestClientException{
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("fio", fio);
        return getResponceBody(getUrl(getUrlParams(urlParams), url_part));
        //return restTemplate.getForEntity(String.format("%s/%s?key=%s&fio=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), fio), String.class).getBody();
    }

    @Override
    public String getForeignRepresen(EnumFocusController url_part, String inn, String nza) throws RestClientException{
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("inn", inn);
        urlParams.put("nza", nza);
        return getResponceBody(getUrl(getUrlParams(urlParams), url_part));
    }

    @Override
    public String getInfoUL(EnumFocusController url_part, String ogrn, String inn) throws RestClientException{
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("inn", inn);
        urlParams.put("ogrn", ogrn);
        return getResponceBody(getUrl(getUrlParams(urlParams), url_part));

        //return restTemplate.getForEntity(String.format("%s/%s?key=%s&inn=%s&ogrn=%s&xml", configProperties.getUrl(), url_part.getValue(), configProperties.getKey(), inn, ogrn), String.class).getBody();
    }

    @Override
    public byte[] getFilePDFBriefReport(EnumFocusController url_part, String ogrn, String inn, boolean pdf) throws RestClientException{
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("inn", inn);
        urlParams.put("ogrn", ogrn);
        urlParams.put("pdf", Boolean.valueOf(pdf).toString());
        return getResponceByteArr(getUrl(getUrlParams(urlParams), url_part));

        /*String params = String.format("key=%s", configProperties.getKey());

        if (inn != null) {
            params += String.format("&inn=%s", inn);
        }

        if (ogrn != null) {
            params += String.format("&ogrn=%s", ogrn);
        }
        System.out.println(String.format("%s/%s?%s&pdf=%s", configProperties.getUrl(), url_part.getValue(), params, pdf));
        return restTemplate.getForEntity(String.format("%s/%s?%s&pdf=%s", configProperties.getUrl(), url_part.getValue(), params, pdf), byte[].class).getBody();
        */
    }

    private byte[] getResponceByteArr(String url) {
        log.info("url request to kontr focus: " + url);

        byte[] responce = new byte[0];
        try {
            responce = restTemplate.getForEntity(url, byte[].class).getBody();
        } catch (RestClientException e) {
            log.info(e.getMessage());
        }
        return responce;
    }

    private String getResponceBody(String url) throws RestClientException{
        log.info("url request to kontr focus: " + url);

        String responce = "";
        try {
            responce = restTemplate.getForEntity(url, String.class).getBody();
        } catch (RestClientException e) {
            log.info(e.getMessage());
            throw e;
        }
        return responce;
    }

    private String getUrlParams(Map<String, String> params) {
        if (log.isDebugEnabled()) {
            log.debug("input params to method getUrlParams: " + params);
        }

        String urlParams = String.format("key=%s", configProperties.getKey());

        for (Map.Entry e : params.entrySet()) {
            if (e.getValue() != null) {
                urlParams += String.format("&%s=%s", e.getKey(), e.getValue());
            }
        }
        return urlParams;
    }

    private String getUrl(String urlParams, EnumFocusController url_part) {
        if (log.isDebugEnabled()) {
            log.debug("input params to method " + FocusClientServiceImpl.class.getName() + ".getUrl: " + "urlParams = " + urlParams + ", url_part = " + url_part);
        }

        return String.format("%s/%s?%s&xml", configProperties.getUrl(), url_part.getValue(), urlParams);
    }
}
