package com.javatr.entity.flower;

public enum Soil {

    GROUNDWATER("groundwater"),
    PODZOLIC("podzolic"),
    SOD_PODZOL("sod-podzol");

    String soilType;
    Soil(String soilType) {
        this.soilType = soilType;
    }

}
