package ru.ysolutions.service.kontur_focus_integration.services;

import org.springframework.web.client.RestClientException;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;

public interface FocusClientServiceNew extends Service{

    void personBankruptcy(EnumFocusController url_part, String innfl, String fio, String birthDate) throws RestClientException;
}
