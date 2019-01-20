package com.javatr.service.builder.impl.flowerbuilder;
import com.javatr.entity.flower.Flower;
import com.javatr.service.builder.AbstractSAXBuilder;
import com.javatr.service.builder.AbstractSAXHandler;


public class SAXFlowerBuilder extends AbstractSAXBuilder<Flower> {
       private AbstractSAXHandler<Flower> handler = new FlowerHandler();

        @Override
        public AbstractSAXHandler<Flower> getEntityHandler() {
                return handler;
        }
}
