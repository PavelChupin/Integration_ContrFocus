package ru.ysolutions.service.kontur_focus_integration.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public interface Service {
    Logger log = LoggerFactory.getLogger(Service.class);

    default String getUrlParams(Map<String, String> params, String k) {
        if (log.isDebugEnabled()) {
            log.debug("input params to method getUrlParams: " + params);
        }

        StringBuilder urlParams = new StringBuilder(String.format("key=%s", k));

        params.forEach((key, value) -> {
            if (value != null) {
                urlParams.append(String.format("&%s=%s", key, value));
            }
        });
        return urlParams.toString();
    }
}
