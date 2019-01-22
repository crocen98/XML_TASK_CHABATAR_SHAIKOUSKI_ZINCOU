package com.javatr.service.factory.impl;

import com.javatr.entity.gem.Gem;
import com.javatr.service.builder.Builder;
import com.javatr.service.builder.impl.gembuilder.DOMGemBuilder;
import com.javatr.service.builder.impl.gembuilder.SAXGemBuilder;
import com.javatr.service.builder.impl.gembuilder.StAXGemBuilder;
import com.javatr.service.factory.BuilderTypeFactory;
import com.javatr.service.factory.ParserType;

public class GemBuilderFactory implements BuilderTypeFactory<Gem> {

    private final Builder<Gem> domBuilder = new DOMGemBuilder();
    private final Builder<Gem> saxBuilder = new SAXGemBuilder();
    private final Builder<Gem> stAXBuilder = new StAXGemBuilder();



    @Override
    public Builder<Gem> getBuilder(ParserType type) {
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
