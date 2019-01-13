package com.javatr.entity.flower;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Flower implements Serializable {

    private String id;
    private String name;
    private Set<String> origin = new HashSet<>();
    private Generation generation;
    private Soil soil;

    private String stemColor;
    private String colorLeaves;
    private int averageSize;
    private double temperature;
    private boolean photophilous;
    private int watering;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(Set<String> origin) {
        this.origin = origin;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public void setColorLeaves(String colorLeaves) {
        this.colorLeaves = colorLeaves;
    }

    public void setAverageSize(int averageSize) {
        this.averageSize = averageSize;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setPhotophilous(boolean photophilous) {
        this.photophilous = photophilous;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return id == flower.id &&
                averageSize == flower.averageSize &&
                Double.compare(flower.temperature, temperature) == 0 &&
                photophilous == flower.photophilous &&
                watering == flower.watering &&
                Objects.equals(name, flower.name) &&
                Objects.equals(origin, flower.origin) &&
                generation == flower.generation &&
                soil == flower.soil &&
                Objects.equals(stemColor, flower.stemColor) &&
                Objects.equals(colorLeaves, flower.colorLeaves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, origin, generation, soil, stemColor, colorLeaves, averageSize, temperature, photophilous, watering);
    }


    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", origin=" + origin +
                ", generation=" + generation +
                ", soil=" + soil +
                ", stemColor='" + stemColor + '\'' +
                ", colorLeaves='" + colorLeaves + '\'' +
                ", averageSize=" + averageSize +
                ", temperature=" + temperature +
                ", photophilous=" + photophilous +
                ", watering=" + watering +
                '}';
    }
}
