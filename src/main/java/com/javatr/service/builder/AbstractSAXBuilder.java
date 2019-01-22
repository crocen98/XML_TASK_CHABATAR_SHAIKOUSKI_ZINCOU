package com.javatr.service.builder;

import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public abstract class AbstractSAXBuilder<T> implements Builder<T> {



     public abstract AbstractSAXHandler<T> getEntityHandler();
    @Override
    public List<T> build(XMLValidator validator, String pathToFile) throws IOServiceException, XMLParserServiceException {
        validator.validate(pathToFile);
        AbstractSAXHandler<T> entityHandler = getEntityHandler();
        try {
            XMLReader reader;
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(entityHandler);
            reader.parse(pathToFile);
        } catch (SAXException e) {
            throw new XMLParserServiceException(e);
        } catch (IOException e) {
            throw new IOServiceException(e);
        }
        return entityHandler.getEntityList();
    }
}

