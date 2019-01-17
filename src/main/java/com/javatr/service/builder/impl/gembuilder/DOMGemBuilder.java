package com.javatr.service.builder.impl.gembuilder;

import com.javatr.entity.gem.Gem;
import com.javatr.entity.gem.Preciousness;
import com.javatr.entity.gem.VisualParameters;
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

public class DOMGemBuilder implements Builder<Gem> {
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    @Override
    public List<Gem> build(XMLValidator validator, String pathToFile) throws IOServiceException, XMLParserServiceException{
        List<Gem> gems = new ArrayList<Gem>();
        Document document;
        DocumentBuilder docBuilder;
        validator.validate(pathToFile);
        try {
            docBuilder = factory.newDocumentBuilder();
            document =  docBuilder.parse(pathToFile);
            NodeList gemsList = document.getElementsByTagName("gem");
            for (int i = 0; i < gemsList.getLength(); i++) {
                Element gemElement = (Element) gemsList.item(i);
                Gem gem = buildGem(gemElement);
                gems.add(gem);
            }
        } catch (SAXException | ParserConfigurationException e) {
            throw new XMLParserServiceException(e);
        } catch (IOException e) {
            throw  new IOServiceException(e);
        }


        return gems;
    }

    private Gem buildGem(Element gemElement) {
        Gem gem = new Gem();

        gem.setId(gemElement.getAttribute("id"));

        Preciousness preciousness = Preciousness.fromValue(getElementTextContent(gemElement, GemEnum.PRECIOUSNESS));
        gem.setPreciousness(preciousness);

        String value = getElementTextContent(gemElement, GemEnum.VALUE);
        gem.setValue(Double.parseDouble(value));

        String name = getElementTextContent(gemElement, GemEnum.NAME);
        gem.setName(name);

        NodeList origin = gemElement.getElementsByTagName(GemEnum.ORIGIN.getValue());
        initOrigins(origin,gem);

        initVisualParametrs(gemElement,gem);

        return gem;

    }
    private void initOrigins(NodeList originTagList, Gem gem){
        for(int i = 0; i < originTagList.getLength(); ++i){
            Node gemNode = originTagList.item(i);
            String origin = gemNode.getTextContent();
            gem.setOrigin(origin);
        }
    }
    private void initVisualParametrs(Element visualParametrsElement, Gem gem){
        VisualParameters visualParameters = new VisualParameters();

        String color = getElementTextContent(visualParametrsElement, GemEnum.COLOR);
        visualParameters.setColor(color);

        String transparency = getElementTextContent(visualParametrsElement, GemEnum.TRANSPARENCY);
        visualParameters.setTransparency(Double.parseDouble(transparency));

        String cuttingMethod = getElementTextContent(visualParametrsElement, GemEnum.CUTTINGMETHOD);
        visualParameters.setCuttingMethod(Integer.parseInt(cuttingMethod));

        gem.setVisualParameters(visualParameters);
    }
    private static String getElementTextContent(Element element, GemEnum gemEnum) {
        NodeList nList = element.getElementsByTagName(gemEnum.getValue());
        Node node = nList.item(0);
        return node.getTextContent();
    }
    public static void main(String[] args) throws IOServiceException, XMLParserServiceException {
        DOMGemBuilder saxGemBuilder = new DOMGemBuilder();
        XMLValidator validator = new XMLValidatorByXSD("resources/xsd/gems.xsd");
        List<Gem> gems = saxGemBuilder.build(validator,"resources/xml/gems_one.xml");
        for(Gem gem : gems){
            System.out.println(gem.getName());
        }
    }

}
