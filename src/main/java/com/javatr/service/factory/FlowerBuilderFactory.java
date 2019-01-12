package com.javatr.service.factory;

import com.javatr.entity.flower.Flower;
import com.javatr.service.builder.Builder;
import com.javatr.service.builder.impl.flowerbuilder.DOMFlowerBuilder;
import com.javatr.service.builder.impl.flowerbuilder.SAXFlowerBuilder;
import com.javatr.service.builder.impl.flowerbuilder.StAxFlowerBuilder;

public final class FlowerBuilderFactory {
    private static FlowerBuilderFactory ourInstance = new FlowerBuilderFactory();

    public static FlowerBuilderFactory getInstance() {
        return ourInstance;
    }

    private final Builder<Flower> domBuilder = new DOMFlowerBuilder();
    private final Builder<Flower> saxBuilder = new SAXFlowerBuilder();
   private final Builder<Flower> stAXBuilder = new StAxFlowerBuilder();
    private FlowerBuilderFactory() {
    }

    public Builder<Flower> getDomBuilder(){
        return domBuilder;
    }

    public Builder<Flower> getStAXBuilder(){
        return stAXBuilder;
    }

    public Builder<Flower> getSAXBuilder(){
        return saxBuilder;
    }



}
