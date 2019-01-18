package com.javatr.entity.flower;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Flower implements Serializable {

    private String id;
    private String name;
    private Set<String> origins = new HashSet<>();
    private Generation generation;
    private Soil soil;

    private String stemColor;
    private String colorLeaves;
    private int averageSize;
    private double temperature;
    private boolean photophilous;
    private int watering;

    public Flower(){}

    public Flower(String id, String name, Set<String> origins,
        Generation generation, Soil soil, String stemColor, String colorLeaves, int averageSize,
        double temperature, boolean photophilous, int watering) {
        this.id = id;
        this.name = name;
        this.origins = origins;
        this.generation = generation;
        this.soil = soil;
        this.stemColor = stemColor;
        this.colorLeaves = colorLeaves;
        this.averageSize = averageSize;
        this.temperature = temperature;
        this.photophilous = photophilous;
        this.watering = watering;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origins.add(origin);
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
        return averageSize == flower.averageSize &&
                Double.compare(flower.temperature, temperature) == 0 &&
                photophilous == flower.photophilous &&
                watering == flower.watering &&
                Objects.equals(id, flower.id) &&
                Objects.equals(name, flower.name) &&
                Objects.equals(origins, flower.origins) &&
                generation == flower.generation &&
                soil == flower.soil &&
                Objects.equals(stemColor, flower.stemColor) &&
                Objects.equals(colorLeaves, flower.colorLeaves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, origins, generation, soil, stemColor, colorLeaves, averageSize, temperature, photophilous, watering);
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", origin=" + origins +
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
