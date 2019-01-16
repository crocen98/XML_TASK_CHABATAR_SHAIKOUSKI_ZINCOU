package com.javatr.service.validation;

import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;

import java.io.IOException;

public interface XMLValidator {

    void validate(String pathToFile) throws IOServiceException, XMLParserServiceException;
}
