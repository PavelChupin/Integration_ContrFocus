package ru.ysolutions.service.kontur_focus_integration.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ysolutions.service.kontur_focus_integration.objects.Analytic;
import ru.ysolutions.service.kontur_focus_integration.objects.Analytics;

import javax.annotation.PostConstruct;

@Service
public class FocusClientServiceService implements FocusClientServiceImpl {
    private RestTemplate restTemplate;

    @PostConstruct
    public void initRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String req(String key,String ogrn, String inn) {
        String url = String.format("https://focus-api.kontur.ru/api3/analytics?key=%s&inn=%s&ogrn=%s",key,inn,ogrn);
        Analytics analytics = restTemplate.getForObject(url, Analytics.class);

        return analytics.toString();
    }

    @Override
    public String egrDetails() {
        return null;
    }

    @Override
    public String analytics() {
        return null;
    }
}
