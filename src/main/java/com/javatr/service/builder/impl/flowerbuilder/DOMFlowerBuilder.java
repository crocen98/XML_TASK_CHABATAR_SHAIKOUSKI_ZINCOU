package com.javatr.service.builder.impl.flowerbuilder;

import com.javatr.entity.flower.Flower;
import com.javatr.entity.flower.Generation;
import com.javatr.entity.flower.Soil;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMFlowerBuilder implements Builder<Flower> {
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    @Override
    public List<Flower> build(XMLValidator validator, String pathToFile) throws XMLParserServiceException, IOServiceException {
        Document doc;
            validator.validate(pathToFile);
        try {
            DocumentBuilder  docBuilder = factory.newDocumentBuilder();
            doc = docBuilder.parse(pathToFile);
        } catch (SAXException | ParserConfigurationException e) {
            throw new XMLParserServiceException(e);
        } catch (IOException e) {
            throw  new IOServiceException(e);
        }
        List<Flower> flowers = new ArrayList<>();
        NodeList flowersList = doc.getElementsByTagName("flower");
        for(int i = 0; i < flowersList.getLength(); ++i){
            Element flowerElement = (Element) flowersList.item(i);
            Flower flower = buildFlower(flowerElement);
            flowers.add(flower);
        }
        return flowers;
    }

    private Flower buildFlower(Element flowerElement){
        Flower flower = new Flower();

        initAttributes(flowerElement,flower);

        String name = getElementTextContent(flowerElement,FlowerEnum.NAME);
        flower.setName(name);

        String generation = getElementTextContent(flowerElement,FlowerEnum.GENERATION);
        flower.setGeneration(Generation.valueOf(generation.toUpperCase()));

        String soil = getElementTextContent(flowerElement,FlowerEnum.SOIL);
        flower.setSoil(Soil.valueOf(soil.toUpperCase()));

        NodeList origins = flowerElement.getElementsByTagName(FlowerEnum.ORIGIN.getTagName());
        initOrigins(origins,flower);

        initChildTags(flowerElement,flower);
        return flower;
    }

    private void initChildTags(Element flowerElement, Flower flower) {
        NodeList visualParametrsTags = flowerElement.getElementsByTagName(FlowerEnum.VISUALPARAMETRS.getTagName());
        Element visualParametrsElement = (Element) visualParametrsTags.item(0);

        NodeList growingTipsTags = flowerElement.getElementsByTagName(FlowerEnum.GROWINGTIPS.getTagName());
        Element growingTipsElement = (Element) growingTipsTags.item(0);

        initVisualParametrs(visualParametrsElement,flower);
        initGrowingTips(growingTipsElement,flower);

    }
    private void initAttributes(Element flowerElement, Flower flower){
        String id = flowerElement.getAttribute(FlowerEnum.ID.getTagName());
        flower.setId(id);
        if(flowerElement.hasAttribute("photophilous")){
            String photophilous = flowerElement.getAttribute(FlowerEnum.PHOTOPHILOUS.getTagName());
            boolean photophilousBoolean = Boolean.parseBoolean(photophilous);
            flower.setPhotophilous(photophilousBoolean);
        }
    }


    private void initOrigins(NodeList originTagList, Flower flower){
        for(int i = 0; i < originTagList.getLength(); ++i){
            Node flowerNode = originTagList.item(i);
            String origin = flowerNode.getTextContent();
            flower.setOrigin(origin);
        }
    }
    private void initGrowingTips(Element growingTipsElement, Flower flower){
        String temperature = getElementTextContent(growingTipsElement,FlowerEnum.TEMPERATURE);
        double doubleTemperature = Double.parseDouble(temperature);
        flower.setTemperature(doubleTemperature);

        String watering = getElementTextContent(growingTipsElement,FlowerEnum.WATERING);
        int wateringInt = Integer.parseInt(watering);
        flower.setWatering(wateringInt);
    }
    private void initVisualParametrs(Element visualParametrsElement, Flower flower){
        String stemColor = getElementTextContent(visualParametrsElement,FlowerEnum.STEMCOLOR);
        flower.setStemColor(stemColor);

        String colorLeaves = getElementTextContent(visualParametrsElement,FlowerEnum.COLORLEAVES);
        flower.setColorLeaves(colorLeaves);

        String averageSize = getElementTextContent(visualParametrsElement,FlowerEnum.AVERAGESIZE);
        int averageSizeInt = Integer.parseInt(averageSize);
        flower.setAverageSize(averageSizeInt);
    }

    private  String getElementTextContent(Element element, FlowerEnum elementName) {
        NodeList nList = element.getElementsByTagName(elementName.getTagName());
        Node node = nList.item(0);
        String text = node.getTextContent();
        text = text.trim();
        return text;

    }

}
