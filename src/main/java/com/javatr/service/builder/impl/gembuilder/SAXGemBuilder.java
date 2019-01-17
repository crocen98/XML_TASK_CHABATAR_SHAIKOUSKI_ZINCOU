package com.javatr.service.builder.impl.gembuilder;

import com.javatr.entity.gem.Gem;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SAXGemBuilder implements Builder<Gem> {
    @Override
    public List<Gem> build(XMLValidator validator, String pathToFile) throws IOServiceException, XMLParserServiceException{
        GemHandler gemHandler = new GemHandler();
        validator.validate(pathToFile);
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.parse(pathToFile);
        } catch (SAXException | ParserConfigurationException e ){
            throw  new XMLParserServiceException(e);
        }catch (IOException e){
            throw  new IOServiceException(e);
        }
        return gemHandler.getGems();
    }


}
