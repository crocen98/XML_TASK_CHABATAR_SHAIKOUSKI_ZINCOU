package com.javatr.service.builder.impl.flowerbuilder;

import com.javatr.entity.flower.Flower;
import com.javatr.entity.flower.Generation;
import com.javatr.entity.flower.Soil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class FlowerHandler  extends DefaultHandler {
    private List<Flower> flowers = new ArrayList<>();
    private Flower current;
    private FlowerEnum currentEnum;
    private EnumSet<FlowerEnum> flowerEnumEnumSet = EnumSet.range(FlowerEnum.NAME,FlowerEnum.WATERING);

    public List<Flower> getFlowers(){
        return flowers;
    }


    @Override
    public void startDocument()  {
        flowers = new ArrayList<>();
        current = null;
        currentEnum = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(FlowerEnum.FLOWER.tagName.equals(localName)){
            current = new Flower();
            current.setId(attributes.getValue(0));
            String photophilousInString = attributes.getValue(1);
            boolean isPhotophilous = Boolean.parseBoolean(photophilousInString);
            current.setPhotophilous(isPhotophilous);
        } else {
            FlowerEnum temp = FlowerEnum.valueOf(localName.toUpperCase());
            if(flowerEnumEnumSet.contains(temp)){
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(FlowerEnum.FLOWER.tagName.equals(localName)){
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
                    current.setGeneration(Generation.valueOf(tagContent.toUpperCase()));
                    break;
                case SOIL:
                    current.setSoil(Soil.valueOf(tagContent.toUpperCase()));
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
