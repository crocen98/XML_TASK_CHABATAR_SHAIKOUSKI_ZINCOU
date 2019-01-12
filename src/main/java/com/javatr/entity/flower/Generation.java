package com.javatr.entity.flower;

public enum Generation {
    LEAVES("leaves"), CUTTINGS("cuttings"), SEEDS("seeds");


    String generationType;
    Generation(String generationType) {
        this.generationType = generationType;
    }
}
