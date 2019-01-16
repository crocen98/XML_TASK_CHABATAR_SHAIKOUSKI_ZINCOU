package com.javatr.service.validation.impl;

import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLErrorHandler;
import com.javatr.service.validation.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;


public class XMLValidatorByXSD implements XMLValidator {

    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private final SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);

    private final String pathToXSDScheme;
    public XMLValidatorByXSD(String pathToXSDScheme) {
        this.pathToXSDScheme = pathToXSDScheme;
    }

    @Override
    public void validate(String fileName) throws XMLParserServiceException, IOServiceException {
        Source source = new StreamSource(fileName);

        File schemaLocation = new File(pathToXSDScheme);
        try{
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();

            DefaultHandler errorHandler = XMLErrorHandler.getInstance();
            validator.setErrorHandler(errorHandler);
            validator.validate(source);
        } catch (IOException e){
            throw new IOServiceException(e);
        } catch (SAXException e){
            throw  new XMLParserServiceException(e);
        }
    }

}