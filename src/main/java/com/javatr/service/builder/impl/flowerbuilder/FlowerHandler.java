package com.javatr.service.builder.impl.flowerbuilder;

import com.javatr.entity.flower.Flower;
import com.javatr.entity.flower.Generation;
import com.javatr.entity.flower.Soil;
import com.javatr.service.builder.AbstractSAXHandler;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class FlowerHandler  extends AbstractSAXHandler<Flower> {
    private List<Flower> flowers;
    private Flower current;
    private FlowerEnum currentEnum;
    private EnumSet<FlowerEnum> flowerEnumEnumSet = EnumSet.range(FlowerEnum.NAME,FlowerEnum.WATERING);

    @Override
    public List<Flower> getEntityList(){
        return flowers;
    }


    @Override
    public void startDocument()  {
        flowers = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(FlowerEnum.FLOWER.getTagName().equals(localName)){
            current = new Flower();
            initAttributes(attributes);
        } else {
            FlowerEnum temp = FlowerEnum.valueOf(localName.toUpperCase());
            if(flowerEnumEnumSet.contains(temp)){
                currentEnum = temp;
            }
        }
    }

    private void initAttributes(Attributes attributes){
        current.setId(attributes.getValue(0));
        if(attributes.getLength() ==2) {
            String photophilousInString = attributes.getValue(1);
            boolean isPhotophilous = Boolean.parseBoolean(photophilousInString);
            current.setPhotophilous(isPhotophilous);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(FlowerEnum.FLOWER.getTagName().equals(localName)){
            flowers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String tagContent = new String(ch,start,length);
        tagContent = tagContent.trim();

        if(currentEnum !=null){
            switch (currentEnum){
                case NAME:
                    current.setName(tagContent);
                    break;
                case ORIGIN:
                    current.setOrigin(tagContent);
                    break;
                case GENERATION:
                    Generation generation = Generation.valueOf(tagContent.toUpperCase());
                    current.setGeneration(generation);
                    break;
                case SOIL:
                    Soil soil = Soil.valueOf(tagContent.toUpperCase());
                    current.setSoil(soil);
                    break;
                case STEMCOLOR:
                    current.setStemColor(tagContent);
                    break;
                case COLORLEAVES:
                    current.setColorLeaves(tagContent);
                    break;
                case AVERAGESIZE:
                    current.setAverageSize(Integer.parseInt(tagContent));
                    break;
                case TEMPERATURE:
                    current.setTemperature(Double.parseDouble(tagContent));
                    break;
                case VISUALPARAMETRS:
                    break;
                case GROWINGTIPS:
                    break;
                case WATERING:
                    current.setWatering(Integer.parseInt(tagContent));
                    break;
                    default:
                        throw new EnumConstantNotPresentException(
                                currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
