package com.javatr.service.validation;

import org.xml.sax.SAXException;

import java.io.IOException;

public interface XMLValidator {

    void validate(String pathToFile) throws SAXException, IOException;
}
