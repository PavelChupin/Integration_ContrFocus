package ru.ysolutions.service.kontur_focus_integration.controllers.enum_controller;

public enum EnumFocusController {
    UNSUPPORTED("unsoported"),
    REQ ("req"),
    ANALYTICS ("analytics"),
    BRIEF_REPORT ("briefReport"),
    CONTACTS ("contacts"),
    EGR_DETAILS ("egrDetails"),
    LICENCES ("licences"),
    EXCERPT ("excerpt"),
    FINAN ("finan"),
    FIZ_BANCKR ("fizBankr"),
    PERSON_BANKRUPTCY ("personBankruptcy"),
    CHECK_PASSPORT ("checkPassport"),
    PEP_SEARCH ("pepSearch"),
    FOREIGN_REPRESENTATIVES ("foreignRepresentatives");

    private String value;

    EnumFocusController(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EnumFocusController getByValue(String value) {
        for (EnumFocusController v: EnumFocusController.values()) {
            if (v.getValue().equals(value)) return v;
        }
        return EnumFocusController.UNSUPPORTED;
    }
}
