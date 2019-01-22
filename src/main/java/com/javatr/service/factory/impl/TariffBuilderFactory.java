package com.javatr.service.factory.impl;

import com.javatr.entity.tariff.Tariff;
import com.javatr.service.builder.Builder;

import com.javatr.service.builder.impl.tariffbuilder.DOMTariffBuilder;
import com.javatr.service.builder.impl.tariffbuilder.SAXTariffBuilder;
import com.javatr.service.builder.impl.tariffbuilder.StAXTariffBuilder;
import com.javatr.service.factory.BuilderTypeFactory;
import com.javatr.service.factory.ParserType;

public class TariffBuilderFactory implements BuilderTypeFactory<Tariff> {

    private final Builder<Tariff> domBuilder = new DOMTariffBuilder();
    private final Builder<Tariff> saxBuilder = new SAXTariffBuilder();
    private final Builder<Tariff> stAXBuilder = new StAXTariffBuilder();


    @Override
    public Builder<Tariff> getBuilder(ParserType type) {
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
