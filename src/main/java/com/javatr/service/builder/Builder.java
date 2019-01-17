package com.javatr.service.builder;

import com.javatr.service.validation.XMLValidator;

import java.util.List;

public interface Builder<T> {
    List<T> build(XMLValidator validator , String pathToFile);
}
