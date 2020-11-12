package ru.ysolutions.service.kontur_focus_integration.services;

import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

import java.util.Date;

public interface FocusClientServiceImpl {

    String getInfoUL(EnumFocusController url_part, String ogrn, String inn);

    byte[] getFilePDF(EnumFocusController url_part, String ogrn, String inn);

    String findMessBankrFiz(EnumFocusController url_part, String q, String date);

    String getInfoMessBankrFiz(EnumFocusController url_part, String innfl, String fio, String birthDate);

    String isInvalidPassports(EnumFocusController url_part, String passportNumber);

    String findPublicDolLic(EnumFocusController url_part, String fio);

    String getForeignRepresen(EnumFocusController url_part, String inn, String nza);

    byte[] getFilePDFBriefReport(EnumFocusController url_part, String ogrn, String inn, boolean pdf);
}
