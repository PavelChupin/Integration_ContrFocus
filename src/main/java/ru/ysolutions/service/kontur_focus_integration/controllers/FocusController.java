package ru.ysolutions.service.kontur_focus_integration.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ysolutions.service.kontur_focus_integration.services.FocusClientServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class FocusController {
    private FocusClientServiceImpl focusClientService;

    @Autowired
    public FocusController(FocusClientServiceImpl focusClientService) {
        this.focusClientService = focusClientService;
    }

    @GetMapping("/req")
    @ApiOperation("Returns")
    public ResponseEntity<?> req(@RequestParam(required = false) @ApiParam("Param_name KEY") String key,
                                 @RequestParam(required = false) @ApiParam("Param_name OGRN") String ogrn,
                                 @RequestParam(required = true) @ApiParam("Param_name INN") String inn
    ) {
        System.out.println("KEY: " + key);
        System.out.println("OGRN: " + ogrn);
        System.out.println("INN: " + inn);
        System.out.println(focusClientService.req(key,ogrn,inn));

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
/*
    @ExceptionHandler
    public ResponseEntity<?> handleException( exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }*/

}
