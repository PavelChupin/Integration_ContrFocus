package ru.ysolutions.service.kontur_focus_integration.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ysolutions.service.kontur_focus_integration.configs.ConfigProperties;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;
import ru.ysolutions.service.kontur_focus_integration.services.FocusClientServiceImpl;

import java.util.Date;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(path = "/api/v1")
@Api("KonturFocusIntegrationApplication")
public class ULFocusController {
    private FocusClientServiceImpl focusClientService;
    private ConfigProperties configProperties;

    @Autowired
    public ULFocusController(FocusClientServiceImpl focusClientService, ConfigProperties configProperties) {
        this.focusClientService = focusClientService;
        this.configProperties = configProperties;
    }

    @GetMapping(path = "/{url_part}")
    @ApiOperation(value = "Services",httpMethod = "GET")
    public ResponseEntity<?> getInfo(@PathVariable @ApiParam("Part URL for url request to Kontur Focus") String url_part
            , @RequestParam(required = false) @ApiParam("Param_name: key - key for connection to Kontur Focus") String key
            , @RequestParam(required = false) @ApiParam("Param_name: ogrn - OGRN of client UL") String ogrn
            , @RequestParam(required = false) @ApiParam("Param_name: inn - INN of client UL") String inn
            , @RequestParam(required = false) @ApiParam("Param_name: q - string of search") String q
            , @RequestParam(required = false) @ApiParam("Param_name: date - date of publication") Date date
            , @RequestParam(required = false) @ApiParam("Param_name: dateBirth - BirthDate of person") Date birthDate
            , @RequestParam(required = false) @ApiParam("Param_name: fio - FIO of person or public Dolzontnih Lic") String fio
            , @RequestParam(required = false) @ApiParam("Param_name: innfl - INN of person") String innfl
            , @RequestParam(required = false) @ApiParam("Param_name: passportNumber - List of numbers passports for chek on valid") String passportNumber
            , @RequestParam(required = false) @ApiParam("Param_name: nza - NZA of foreign representatives") String nza
    ) {
        //Проверяем что к нам стучатся по нашему ключу
        if (!key.equals(configProperties.getKey())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Param of key is not valid");
        }

        EnumFocusController enumFocusController = EnumFocusController.getByValue(url_part);
        switch (enumFocusController) {
            case REQ:
            case CONTACTS:
            case LICENCES:
            case ANALYTICS:
            case EGR_DETAILS:
            case BRIEF_REPORT: {
                return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.getInfoUL(enumFocusController,  ogrn, inn));
            }
            case FINAN:
            case EXCERPT:{
                return ResponseEntity.ok().contentType(APPLICATION_PDF).body(focusClientService.getFilePDF(enumFocusController,  ogrn, inn));
            }
            case FIZ_BANCKR:{
                return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.findMessBankrFiz(enumFocusController,  q, date));
            }
            case PERSON_BANKRUPTCY:{
                return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.getInfoMessBankrFiz(enumFocusController,  innfl, fio, birthDate));
            }
            case CHECK_PASSPORT:{
                return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.isInvalidPassports(enumFocusController,  passportNumber));
            }
            case PEP_SEARCH:{
                return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.findPublicDolLic(enumFocusController,  fio));
            }
            case FOREIGN_REPRESENTATIVES:{
                return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.getForeignRepresen(enumFocusController,  inn, nza));
            }
            default:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request url not found: " + url_part);
        }
    }
/*
    @GetMapping(path = "/excerpt")
    @ApiOperation("Services")
    public ResponseEntity<?> getExcerpt(@PathVariable String url_part, @RequestParam(required = false) @ApiParam("Request params") Map<String, String> params) {
        params.put("url_part", url_part);
        return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.get(params));
    }

    @GetMapping(path = "/finan")
    @ApiOperation("Services")
    public ResponseEntity<?> getFinan(@PathVariable String url_part, @RequestParam(required = false) @ApiParam("Request params") Map<String, String> params) {
        params.put("url_part", url_part);
        return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.get(params));
    }
*/
    /*
    @GetMapping(path = "/analytics", consumes = APPLICATION_XML_VALUE)
    @ApiOperation("Returns")
    public Publisher<ResponseEntity<AnalyticsList>> getAnalytics(@RequestParam(required = false) @ApiParam("Param_name KEY") String key,
                                                                   @RequestParam(required = false) @ApiParam("Param_name OGRN") String ogrn,
                                                                   @RequestParam(required = false) @ApiParam("Param_name INN") String inn
    ) {
        System.out.println("KEY: " + key);
        System.out.println("OGRN: " + ogrn);
        System.out.println("INN: " + inn);
        //System.out.println(focusClientService.getAnalytics(key,ogrn,inn));
        AnalyticsList analyticsList = new AnalyticsList(focusClientService.getAnalytics(key,ogrn,inn));
        return Mono.just(ResponseEntity.ok().contentType(APPLICATION_XML).body(analyticsList)); //new ResponseEntity<>(focusClientService.getAnalytics(key,ogrn,inn), HttpStatus.OK);
    }*/
/*
    @ExceptionHandler
    public ResponseEntity<?> handleException( exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }*/
}
