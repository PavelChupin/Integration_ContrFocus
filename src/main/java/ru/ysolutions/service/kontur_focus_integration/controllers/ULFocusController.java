package ru.ysolutions.service.kontur_focus_integration.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller.EnumFocusController;
import ru.ysolutions.service.kontur_focus_integration.services.FocusClientServiceImpl;

import static org.springframework.http.MediaType.APPLICATION_XML;

@RestController
@RequestMapping(path = "/api/v1")
public class ULFocusController {
    private FocusClientServiceImpl focusClientService;

    @Autowired
    public ULFocusController(FocusClientServiceImpl focusClientService) {
        this.focusClientService = focusClientService;
    }

    @GetMapping(path = "/{url_part}")
    @ApiOperation("Services")
    public ResponseEntity<?> get(@PathVariable String url_part
            , @RequestParam(required = false) @ApiParam("Param_name KEY") String key
            , @RequestParam(required = false) @ApiParam("Param_name OGRN") String ogrn
            , @RequestParam(required = false) @ApiParam("Param_name INN") String inn
    ) {
        EnumFocusController enumFocusController = EnumFocusController.getByValue(url_part);
        switch (enumFocusController) {
            case REQ:
            case CONTACTS:
            case LICENCES:
            case ANALYTICS:
            case EGR_DETAILS:
            case BRIEF_REPORT: {
                return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.get(enumFocusController, key, ogrn, inn));
            }
            case FINAN:
            case EXCERPT:{
                return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.get(enumFocusController, key, ogrn, inn));
            }
            default:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request url not found: " + url_part);
        }
        /*try {

            EnumFocusController e = EnumFocusController.valueOf(url_part.toUpperCase());
            return ResponseEntity.ok().contentType(APPLICATION_XML).body(focusClientService.get(e, key, ogrn, inn));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request url not found: " + url_part);
        }*/
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


    //req (Актуализация реквизитов)
    //briefReport (Экспресс-отчет по контрагенту)
    //contacts (Контакты из Контур.Справочника)
    //analytics (Расширенная аналитика)
    //egrDetails (Расширенные сведения на основе ЕГРЮЛ/ЕГРИП)
    //licences (Информация о лицензиях)


    //excerpt (Выписка из ЕГРЮЛ/ЕГРИП)
    //finan (Финансовый анализ)


    //fizBankr (Поиск сообщений о банкротстве физлиц)

    //personBankruptcy (Информация о банкротстве физлица)

    //checkPassport (Проверка недействительных паспортов)

    //pepSearch (Поиск публичных должностных лиц)

    //foreignRepresentatives (Иностранные представительства и филиалы)


}
