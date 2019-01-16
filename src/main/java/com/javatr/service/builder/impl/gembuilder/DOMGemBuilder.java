package com.javatr.service.builder.impl.gembuilder;

import com.javatr.entity.gem.Gem;
import com.javatr.entity.gem.Preciousness;
import com.javatr.entity.gem.VisualParameters;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMGemBuilder implements Builder<Gem> {
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    @Override
    public List<Gem> build(XMLValidator validator, String pathToFile)  throws IOServiceException, XMLParserServiceException {
        List<Gem> gems = new ArrayList<>();
        org.w3c.dom.Document document;
        DocumentBuilder docBuilder;
        validator.validate(pathToFile);
        try {
            docBuilder = factory.newDocumentBuilder();
            document =  docBuilder.parse(pathToFile);
            NodeList studentsList = document.getElementsByTagName("gem");
            for (int i = 0; i < studentsList.getLength(); i++) {
                Element gemElement = (Element) studentsList.item(i);
                Gem gem = buildGem(gemElement);
                gems.add(gem);
            }
        } catch (SAXException | ParserConfigurationException e) {
            throw new XMLParserServiceException(e);
        } catch (IOException e) {
            throw  new IOServiceException(e);
        }


        return null;
    }

    private Gem buildGem(Element gemElement) {
        Gem gem = new Gem();

        gem.setName(gemElement.getAttribute("name"));

        Preciousness preciousness = Preciousness.fromValue(getElementTextContent(gemElement,GemEnum.PRECIOUSNESS));
        gem.setPreciousness(preciousness);

        String value = getElementTextContent(gemElement, GemEnum.VALUE);
        gem.setValue(Double.parseDouble(value));

        NodeList origin = gemElement.getElementsByTagName(GemEnum.ORIGIN.getValue());
        initOrigins(origin,gem);

        initVisualParametrs(gemElement,gem);

        return gem;

    }
    private void initOrigins(NodeList originTagList, Gem gem){
        for(int i = 0; i < originTagList.getLength(); ++i){
            Node flowerNode = originTagList.item(i);
            String origin = flowerNode.getTextContent();
            gem.setOrigin(origin);
        }
    }
    private void initVisualParametrs(Element visualParametrsElement, Gem gem){
        VisualParameters visualParameters = new VisualParameters();

        String color = getElementTextContent(visualParametrsElement,GemEnum.COLOR);
        visualParameters.setColor(color);

        String transparency = getElementTextContent(visualParametrsElement,GemEnum.COLOR);
        visualParameters.setTransparency(Integer.parseInt(transparency));

        String cuttingMethod = getElementTextContent(visualParametrsElement,GemEnum.COLOR);
        visualParameters.setCuttingMethod(Integer.parseInt(cuttingMethod));

        gem.setVisualParameters(visualParameters);
    }
    private static String getElementTextContent(Element element, GemEnum gemEnum) {
        NodeList nList = element.getElementsByTagName(gemEnum.getValue());
        Node node = nList.item(0);
        return node.getTextContent();
    }


}
