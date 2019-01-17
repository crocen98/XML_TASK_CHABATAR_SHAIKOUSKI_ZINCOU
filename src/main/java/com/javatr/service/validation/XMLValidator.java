package com.javatr.service.validation;

import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;


public interface XMLValidator {

    void validate(String pathToFile) throws IOServiceException, XMLParserServiceException;

}



