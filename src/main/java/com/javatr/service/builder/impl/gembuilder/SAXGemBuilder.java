package com.javatr.service.builder.impl.gembuilder;

import com.javatr.entity.gem.Gem;
import com.javatr.service.builder.AbstractSAXBuilder;
import com.javatr.service.builder.AbstractSAXHandler;

public class SAXGemBuilder extends AbstractSAXBuilder<Gem> {

    private AbstractSAXHandler<Gem> handler = new GemHandler();

    @Override
    public AbstractSAXHandler<Gem> getEntityHandler() {
        return handler;
    }
}
