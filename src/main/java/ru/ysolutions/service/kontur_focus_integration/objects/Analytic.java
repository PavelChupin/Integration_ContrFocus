package ru.ysolutions.service.kontur_focus_integration.objects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Analytic {
    private String inn;
    private String ogrn;
    private String focusHref;
    private Map<String,Object> analytics;
}
