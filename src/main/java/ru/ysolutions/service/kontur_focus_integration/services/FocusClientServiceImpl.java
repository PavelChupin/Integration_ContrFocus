package ru.ysolutions.service.kontur_focus_integration.services;

import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

import java.util.Date;
import java.util.Map;

public interface FocusClientServiceImpl {
    /*String req(String key, String ogrn, String inn);

    String getEgrDetails(String key, String ogrn, String inn);

    String getAnalytics(String key, String ogrn, String inn);
    */

    String getInfoUL(EnumFocusController url_part,  String ogrn, String inn);

    byte[] getFilePDF(EnumFocusController url_part,  String ogrn, String inn);

    String findMessBankrFiz(EnumFocusController url_part, String q, Date date);

    String getInfoMessBankrFiz(EnumFocusController url_part, String innfl, String fio, Date birthDate);

    String isInvalidPassports(EnumFocusController url_part, String passportNumber);

    String findPublicDolLic(EnumFocusController url_part, String fio);

    String getForeignRepresen(EnumFocusController url_part, String inn, String nza);
}
