package com.javatr.service.builder.impl.flowerbuilder;

public enum FlowerEnum {
    PHOTOPHILOUS("photophilous"),
    ID("id"),
    FLOWERS("flowers"),
    FLOWER("flower"),
    NAME("name"),
    ORIGIN("origin"),
    GENERATION("generation"),
    SOIL("soil"),
    STEMCOLOR("stemColor"),
    COLORLEAVES("colorLeaves"),
    AVERAGESIZE("averageSize"),
    TEMPERATURE("temperature"),
    VISUALPARAMETRS("visualparametrs"),
    GROWINGTIPS("growingtips"),
    WATERING("watering");


    String tagName;

    FlowerEnum(String tagName){
        this.tagName = tagName;
    }


    public String getTagName(){
        return tagName;
    }
}
