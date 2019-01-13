package com.javatr.service.builder;

import com.javatr.service.validation.XMLValidator;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

public interface Builder<T> {
    List<T> build(XMLValidator validator , String pathToFile) throws IOException, SAXException;
}
