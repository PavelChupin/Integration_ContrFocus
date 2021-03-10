package ru.ysolutions.service.kontur_focus_integration.services;

import org.springframework.web.client.RestClientException;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

public interface FocusClientService {

    String getInfoUL(EnumFocusController url_part, String ogrn, String inn) throws RestClientException;

    byte[] getFilePDF(EnumFocusController url_part, String ogrn, String inn) throws RestClientException;

    String findMessBankrFiz(EnumFocusController url_part, String q, String date) throws RestClientException;

    String getInfoMessBankrFiz(EnumFocusController url_part, String innfl, String fio, String birthDate) throws RestClientException;

    String isInvalidPassports(EnumFocusController url_part, String passportNumber) throws RestClientException;

    String findPublicDolLic(EnumFocusController url_part, String fio) throws RestClientException;

    String getForeignRepresen(EnumFocusController url_part, String inn, String nza) throws RestClientException;

    byte[] getFilePDFBriefReport(EnumFocusController url_part, String ogrn, String inn, boolean pdf) throws RestClientException;

    void personBankruptcy(EnumFocusController url_part, String innfl, String fio, String birthDate) throws RestClientException;

}
