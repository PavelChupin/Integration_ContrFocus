package ru.ysolutions.service.kontur_focus_integration.services.v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.ysolutions.service.kontur_focus_integration.configs.ConfigProperties;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;
import ru.ysolutions.service.kontur_focus_integration.dao.entities.PersonBankruptcy;
import ru.ysolutions.service.kontur_focus_integration.dao.repositories.PersonBankruptcyRepository;
import ru.ysolutions.service.kontur_focus_integration.services.v1.FocusClientServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FocusClientServiceNewImpl implements FocusClientServiceNew {
    private static final Logger log = LoggerFactory.getLogger(FocusClientServiceNewImpl.class);

    private RestTemplate restTemplate;

    private final ConfigProperties configProperties;

    private final PersonBankruptcyRepository personBankruptcy;

    @Autowired
    public FocusClientServiceNewImpl(ConfigProperties configProperties, PersonBankruptcyRepository personBankruptcy) {
        this.configProperties = configProperties;
        this.personBankruptcy = personBankruptcy;
    }

    @PostConstruct
    public void initRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void personBankruptcy(EnumFocusController url_part, String innfl, String fio, String birthDate) throws RestClientException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("innfl", innfl);
        urlParams.put("fio", fio);
        urlParams.put("birthDate", birthDate);
        List<PersonBankruptcy> p = getPersonBankruptcy(getUrlJSON(getUrlParams(urlParams, configProperties.getKey()), url_part));
        personBankruptcy.saveAll(p);
    }

    private List<PersonBankruptcy> getPersonBankruptcy(String url) throws RestClientException {
        log.info("url request to kontr focus: " + url);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(header);

        PersonBankruptcy[] responce;
        try {
            responce = restTemplate.exchange(url, HttpMethod.GET, entity, PersonBankruptcy[].class).getBody();
        } catch (RestClientException e) {
            log.info(e.getMessage());
            throw e;
        }
        return Arrays.asList(responce);
    }

    private String getUrlJSON(String urlParams, EnumFocusController url_part) {
        if (log.isDebugEnabled()) {
            log.debug("input params to method " + FocusClientServiceImpl.class.getName() + ".getUrl: " + "urlParams = " + urlParams + ", url_part = " + url_part);
        }

        return String.format("%s/%s?%s", configProperties.getUrl(), url_part.getValue(), urlParams);
    }
}
