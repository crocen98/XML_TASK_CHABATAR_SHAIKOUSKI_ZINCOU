package com.javatr.service.factory;

import com.javatr.entity.flower.Flower;
import com.javatr.entity.gem.Gem;
import com.javatr.entity.tariff.Tariff;
import com.javatr.service.builder.Builder;
import com.javatr.service.factory.impl.FlowerBuilderFactory;
import com.javatr.service.factory.impl.GemBuilderFactory;
import com.javatr.service.factory.impl.TariffBuilderFactory;

public class EntityBuilderFactory {
    private static EntityBuilderFactory ourInstance = new EntityBuilderFactory();

    public static EntityBuilderFactory getInstance() {
        return ourInstance;
    }

    private final BuilderTypeFactory<Flower> flowerBuilderTypeFactory = new FlowerBuilderFactory();
    private final BuilderTypeFactory<Gem> gemBuilderTypeFactory = new GemBuilderFactory();
    private final BuilderTypeFactory<Tariff> tariffBuilderTypeFactory = new TariffBuilderFactory();

    private EntityBuilderFactory() {
    }

    public Builder<Flower> getFlowerBuilder(ParserType type){
        return flowerBuilderTypeFactory.getBuilder(type);
    }

    public Builder<Gem> getGemBuilder(ParserType type){
        return gemBuilderTypeFactory.getBuilder(type);
    }
    public Builder<Tariff> getTarifBuilder(ParserType type){
        return tariffBuilderTypeFactory.getBuilder(type);
    }
}
