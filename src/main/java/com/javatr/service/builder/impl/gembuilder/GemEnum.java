package com.javatr.service.builder.impl.gembuilder;

public enum GemEnum {
    GEMS("gems"),
    GEM("gem"),
    NAME("name"),
    PRECIOUSNESS("preciousness"),
    ORIGIN("origin"),
    VALUE("value"),
    COLOR("color"),
    TRANSPARENCY("transparency"),
    CUTTINGMETHOD("cuttingMethod"),
    VISUALPARAMETERS("visualParameters");
    private String value;
    GemEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
