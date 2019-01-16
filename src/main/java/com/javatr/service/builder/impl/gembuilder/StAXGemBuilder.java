package com.javatr.service.builder.impl.gembuilder;

import com.javatr.entity.gem.Gem;
import com.javatr.entity.gem.Preciousness;
import com.javatr.entity.gem.VisualParameters;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StAXGemBuilder implements Builder<Gem> {
    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    @Override
    public List<Gem> build(XMLValidator validator, String pathToFile)  throws IOServiceException, XMLParserServiceException {
        List<Gem> gems = new ArrayList<>();
        validator.validate(pathToFile);

        try (FileInputStream inputStream = new FileInputStream(new File(pathToFile))){

            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            addAllGemToList(reader,gems);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            throw new IOServiceException(e);
        } catch (XMLStreamException e) {
            throw new XMLParserServiceException(e);
        }

        return gems;
    }

    private void addAllGemToList(XMLStreamReader reader, List<Gem> gems)  throws XMLStreamException {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.GEM) {
                    Gem gem = buildGem(reader);
                    gems.add(gem);
                }
            }
        }
    }

    private Gem buildGem(XMLStreamReader reader) throws XMLStreamException {
        Gem gem = new Gem();
        String attributeName = reader.getAttributeValue(null, GemEnum.NAME.getValue());
        gem.setName(attributeName);

        String name;
        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())) {
                        case VALUE:
                            gem.setName(getXMLText(reader));
                            break;

                        case PRECIOUSNESS:
                            name = getXMLText(reader);
                            Preciousness preciousnessType = Preciousness.valueOf(name.toUpperCase());
                            gem.setPreciousness(preciousnessType);
                            break;
                        case ORIGIN:
                            name = getXMLText(reader);
                            gem.setOrigin(name);
                            break;
                        case VISUALPARAMETERS:
                            initVisualParametrs(reader,gem);
                            break;
                        default:
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (isStringEqualsGemEnum(name,GemEnum.GEM)) {
                        return gem;
                    }
                    break;
                default:
            }
        }
        throw  new XMLStreamException("Unknown element in tag Gem");
    }

    private void initVisualParametrs(XMLStreamReader reader, Gem gem) throws XMLStreamException {
        VisualParameters visualParameters = new VisualParameters();
        String name;
        int type;

        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())) {
                        case COLOR:
                            name = getXMLText(reader);
                            visualParameters.setColor(name);
                            break;
                        case TRANSPARENCY:
                            name = getXMLText(reader);
                            int transparency = Integer.parseInt(name);
                            visualParameters.setTransparency(transparency);
                            break;
                        case CUTTINGMETHOD:
                            name = getXMLText(reader);
                            int cuttingMethod = Integer.parseInt(name);
                            visualParameters.setCuttingMethod(cuttingMethod);
                            break;
                        default:
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (isStringEqualsGemEnum(name,GemEnum.VISUALPARAMETERS)) {
                        return;
                    }
                    break;
                default:
            }
        }
    }

    private boolean isStringEqualsGemEnum(String name, GemEnum gemEnum) {
        GemEnum enumFromString = GemEnum.valueOf(name.toUpperCase());
        return  enumFromString == gemEnum;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = "";
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
    public static void main(String ... args) throws IOException, XMLParserServiceException {
        StAXGemBuilder builder = new StAXGemBuilder();
        System.out.println(builder.build(new XMLValidatorByXSD("resources/xsd/Gem.xsd"),"resources/xml/flowers_one.xml"));
        System.out.println(builder.build(new XMLValidatorByXSD("resources/xsd/Gem.xsd"),"resources/xml/flowers_five.xml"));
        System.out.println(2147483647);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
    }
}
