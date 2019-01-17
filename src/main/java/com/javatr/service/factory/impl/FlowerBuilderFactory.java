package com.javatr.service.factory.impl;

import com.javatr.entity.flower.Flower;
import com.javatr.service.builder.Builder;
import com.javatr.service.builder.impl.flowerbuilder.DOMFlowerBuilder;
import com.javatr.service.builder.impl.flowerbuilder.SAXFlowerBuilder;
import com.javatr.service.builder.impl.flowerbuilder.StAxFlowerBuilder;
import com.javatr.service.factory.BuilderTypeFactory;
import com.javatr.service.factory.ParserType;


public final class FlowerBuilderFactory implements BuilderTypeFactory<Flower> {




    private final Builder<Flower> domBuilder = new DOMFlowerBuilder();
    private final Builder<Flower> saxBuilder = new SAXFlowerBuilder();
    private final Builder<Flower> stAXBuilder = new StAxFlowerBuilder();

    @Override
    public Builder<Flower> getBuilder(ParserType type) {
        switch (type){
            case DOM:
                return domBuilder;
            case SAX:
                return saxBuilder;
            case STAX:
                return stAXBuilder;
                default:
                    throw new UnsupportedOperationException();
        }
    }

}
