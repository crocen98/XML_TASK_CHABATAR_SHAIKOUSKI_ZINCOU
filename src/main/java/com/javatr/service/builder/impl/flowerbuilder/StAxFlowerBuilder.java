package com.javatr.service.builder.impl.flowerbuilder;

import com.javatr.entity.flower.Flower;
import com.javatr.service.builder.Builder;
import com.javatr.service.validation.Validator;

import java.util.List;

public class StAxFlowerBuilder implements Builder<Flower> {
    @Override
    public List<Flower> build(Validator validator, String pathToFile) {
        return null;
    }
}
