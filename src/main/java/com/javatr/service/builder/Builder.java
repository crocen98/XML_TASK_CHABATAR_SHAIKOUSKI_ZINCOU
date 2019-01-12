package com.javatr.service.builder;

import com.javatr.service.validation.Validator;

import java.util.List;

public interface Builder<T> {
    List<T> build(Validator validator , String pathToFile);
}
