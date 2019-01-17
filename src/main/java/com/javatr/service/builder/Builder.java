package com.javatr.service.builder;

import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;

import java.util.List;

public interface Builder<T> {
    List<T> build(XMLValidator validator , String pathToFile)throws IOServiceException, XMLParserServiceException;
}
