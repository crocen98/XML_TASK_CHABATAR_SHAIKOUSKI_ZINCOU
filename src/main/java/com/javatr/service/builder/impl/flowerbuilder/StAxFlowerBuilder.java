package com.javatr.service.builder.impl.flowerbuilder;

import com.javatr.entity.flower.Flower;
import com.javatr.entity.flower.Generation;
import com.javatr.entity.flower.Soil;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StAxFlowerBuilder implements Builder<Flower> {
    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    @Override
    public List<Flower> build(XMLValidator validator, String pathToFile) throws IOServiceException, XMLParserServiceException {
        List<Flower> flowers = new ArrayList<>();
        validator.validate(pathToFile);

        try (FileInputStream inputStream = new FileInputStream(new File(pathToFile))){
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            addAllFlowersToList(reader,flowers);
        } catch (IOException e) {
            throw new IOServiceException(e);
        } catch (XMLStreamException e) {
            throw new XMLParserServiceException(e);
        }
        return flowers;
    }


    private void addAllFlowersToList(XMLStreamReader reader, List<Flower> flowers) throws XMLStreamException {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                if (FlowerEnum.valueOf(name.toUpperCase()) == FlowerEnum.FLOWER) {
                    Flower flower = buildFlower(reader);
                    flowers.add(flower);
                }
            }
        }
    }

    private Flower buildFlower(XMLStreamReader reader) throws XMLStreamException {
        Flower flower = new Flower();
        String id = reader.getAttributeValue(null, FlowerEnum.ID.getTagName());
        flower.setId(id);
        String photophilousString = reader.getAttributeValue(null, FlowerEnum.PHOTOPHILOUS.getTagName());
        if (photophilousString != null) {
            boolean photophilous = Boolean.parseBoolean(photophilousString);
            flower.setPhotophilous(photophilous);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            flower.setName(getXMLText(reader));
                            break;

                        case SOIL:
                            name = getXMLText(reader);
                            Soil soilType = Soil.valueOf(name.toUpperCase());
                            flower.setSoil(soilType);
                            break;
                        case ORIGIN:
                            name = getXMLText(reader);
                            flower.setOrigin(name);
                            break;
                        case GENERATION:
                            name = getXMLText(reader);
                            Generation generationType = Generation.valueOf(name.toUpperCase());
                            flower.setGeneration(generationType);
                            break;
                        case VISUALPARAMETRS:
                            initVisualParametrs(reader,flower);
                            break;
                        case GROWINGTIPS:
                            initGrowingTips(reader,flower);
                            break;
                            default:
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (isStringEqualsFlowerEnum(name,FlowerEnum.FLOWER)) {
                        return flower;
                    }
                    break;
                    default:
            }
        }
        throw  new XMLStreamException("Unknown element in tag Student");
    }

    private void initVisualParametrs(XMLStreamReader reader,Flower flower) throws XMLStreamException{
        String name;
        int type;

        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case STEMCOLOR:
                            name = getXMLText(reader);
                            flower.setStemColor(name);
                            break;
                        case COLORLEAVES:
                            name = getXMLText(reader);
                            flower.setColorLeaves(name);
                            break;
                        case AVERAGESIZE:
                            name = getXMLText(reader);
                            int averageSize = Integer.parseInt(name);
                            flower.setAverageSize(averageSize);
                            break;
                            default:
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (isStringEqualsFlowerEnum(name,FlowerEnum.VISUALPARAMETRS)) {
                        return;
                    }
                    break;
                    default:
            }
        }
    }



    private void initGrowingTips(XMLStreamReader reader,Flower flower) throws XMLStreamException{
        String name;
        int type;

        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case TEMPERATURE:
                            name = getXMLText(reader);
                            double temperature = Double.parseDouble(name);
                            flower.setTemperature(temperature);
                            break;
                        case WATERING:
                            name = getXMLText(reader);
                            int watering = Integer.parseInt(name);
                            flower.setWatering(watering);
                            break;
                            default:
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (isStringEqualsFlowerEnum(name,FlowerEnum.GROWINGTIPS)) {
                        return;
                    }
                    break;
                    default:
            }
        }
    }

    private boolean isStringEqualsFlowerEnum(String tagName , FlowerEnum flowerEnum){
        FlowerEnum enumFromString = FlowerEnum.valueOf(tagName.toUpperCase());
        return  enumFromString == flowerEnum;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = "";
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
            text = text.trim();
        }
        return text;
    }


    public static void main(String ... args) throws IOException, XMLParserServiceException {
        StAxFlowerBuilder builder1 = new StAxFlowerBuilder();
        List<Flower> l1 = builder1.build(new XMLValidatorByXSD("resources/xsd/Flowers.xsd"),"resources/xml/flowers_five.xml");

        DOMFlowerBuilder builder2 = new DOMFlowerBuilder();
        List<Flower> l2 = builder2.build(new XMLValidatorByXSD("resources/xsd/Flowers.xsd"),"resources/xml/flowers_five.xml");

        SAXFlowerBuilder builder3 = new SAXFlowerBuilder();
        List<Flower> l3 = builder3.build(new XMLValidatorByXSD("resources/xsd/Flowers.xsd"),"resources/xml/flowers_five.xml");


        System.out.println(l3.equals(l2));
        System.out.println(l1.equals(l3));
        System.out.println(l1.equals(l2));

    }

}
