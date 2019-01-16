package com.javatr.service.builder.impl.gembuilder;

import com.javatr.entity.gem.Gem;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SAXGemBuilder implements Builder<Gem> {
    @Override
    public List<Gem> build(XMLValidator validator, String pathToFile) throws IOServiceException, XMLParserServiceException {
        GemHandler gemHandler = new GemHandler();
        validator.validate(pathToFile);
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.parse(pathToFile);
        } catch (SAXException e ){
            throw  new XMLParserServiceException(e);
        }catch (IOException e){
            throw  new IOServiceException(e);
        }
        return gemHandler.getGems();
    }
}
