package com.javatr.service.builder.impl.flowerbuilder;

import com.javatr.entity.flower.Flower;
import com.javatr.service.builder.Builder;
import com.javatr.service.validation.XMLValidator;

import java.util.List;

public class SAXFlowerBuilder implements Builder<Flower> {
@Override
public List<Flower> build(XMLValidator validator, String pathToFile) {
        return null;
        }
}
