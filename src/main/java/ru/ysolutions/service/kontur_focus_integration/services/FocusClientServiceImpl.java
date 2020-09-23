package ru.ysolutions.service.kontur_focus_integration.services;

import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

import java.util.Map;

public interface FocusClientServiceImpl {
    /*String req(String key, String ogrn, String inn);

    String getEgrDetails(String key, String ogrn, String inn);

    String getAnalytics(String key, String ogrn, String inn);
    */

    String getInfoUL(EnumFocusController url_part, String key, String ogrn, String inn);

    byte[] getFilePDF(EnumFocusController url_part, String key, String ogrn, String inn);
}
