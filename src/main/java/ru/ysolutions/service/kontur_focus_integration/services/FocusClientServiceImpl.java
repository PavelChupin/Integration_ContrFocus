package ru.ysolutions.service.kontur_focus_integration.services;

public interface FocusClientServiceImpl {
        String req(String key,String ogrn, String inn);
    String egrDetails();
    String analytics();
}
