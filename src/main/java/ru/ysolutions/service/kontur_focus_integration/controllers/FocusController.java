package ru.ysolutions.service.kontur_focus_integration.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ru.ysolutions.service.kontur_focus_integration.configs.ConfigProperties;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;
import ru.ysolutions.service.kontur_focus_integration.services.FocusClientService;
import ru.ysolutions.service.kontur_focus_integration.services.FocusClientServiceNew;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.http.MediaType.APPLICATION_XML;


@RestController
@RequestMapping(path = "/api/v2")
@Api("FocusController")
public class FocusController {
    private static final Logger log = LoggerFactory.getLogger(FocusController.class);

    private final FocusClientServiceNew focusClientServiceNew;

    private final ConfigProperties configProperties;

    @Autowired
    public FocusController(FocusClientServiceNew focusClientServiceNew, ConfigProperties configProperties) {
        this.focusClientServiceNew = focusClientServiceNew;
        this.configProperties = configProperties;
    }

    @GetMapping(path = "/{url_part}")
    @ApiOperation(value = "Services", httpMethod = "GET")
    public ResponseEntity<?> getInfo(@PathVariable @ApiParam("Part URL for url request to Kontur Focus") String url_part
            , @RequestParam(required = false) @ApiParam("Param_name: key - key for connection to Kontur Focus") String key
            , @RequestParam(required = false) @ApiParam("Param_name: ogrn - OGRN of client UL") String ogrn
            , @RequestParam(required = false) @ApiParam("Param_name: inn - INN of client UL") String inn
            , @RequestParam(required = false) @ApiParam("Param_name: q - string of search") String q
            , @RequestParam(required = false) @ApiParam("Param_name: date - date of publication") String date
            , @RequestParam(required = false) @ApiParam("Param_name: dateBirth - BirthDate of person") String birthDate
            , @RequestParam(required = false) @ApiParam("Param_name: fio - FIO of person or public Dolzontnih Lic") String fio
            , @RequestParam(required = false) @ApiParam("Param_name: innfl - INN of person") String innfl
            , @RequestParam(required = false) @ApiParam("Param_name: passportNumber - List of numbers passports for chek on valid") String passportNumber
            , @RequestParam(required = false) @ApiParam("Param_name: nza - NZA of foreign representatives") String nza
            , @RequestParam(required = false) @ApiParam("Param_name: PDF - flag get PDF") boolean pdf
    ) {
        Map<String, String> paramsLog = new HashMap<>();
        paramsLog.put("url_part", url_part);
        paramsLog.put("key", key);
        paramsLog.put("ogrn", ogrn);
        paramsLog.put("inn", inn);
        paramsLog.put("q", q);
        paramsLog.put("date", date);
        paramsLog.put("birthDate", birthDate);
        paramsLog.put("fio", fio);
        paramsLog.put("innfl", innfl);
        paramsLog.put("passportNumber", passportNumber);
        paramsLog.put("nza", nza);
        paramsLog.put("pdf flag", Boolean.valueOf(pdf).toString());
        log.info("Input params request for gate: " + paramsLog);

        //Проверяем что к нам стучатся по нашему ключу
        if (!key.equals(configProperties.getKey())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Param of key is not valid");
        }

        try {
            EnumFocusController enumFocusController = EnumFocusController.getByValue(url_part);
            switch (enumFocusController) {
                case PERSON_BANKRUPTCY: {
                    focusClientServiceNew.personBankruptcy(enumFocusController, innfl, fio, birthDate);
                    return ResponseEntity.status(HttpStatus.OK).body("OK");
                }
                default:
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request url not found: " + url_part);
            }
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
