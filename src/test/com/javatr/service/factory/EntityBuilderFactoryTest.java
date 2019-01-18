package com.javatr.service.factory;

import com.javatr.entity.flower.Flower;
import com.javatr.entity.gem.Gem;
import com.javatr.entity.tariff.Tariff;
import com.javatr.service.builder.Builder;
import com.javatr.service.builder.impl.flowerbuilder.SAXFlowerBuilder;
import com.javatr.service.builder.impl.gembuilder.StAXGemBuilder;
import com.javatr.service.builder.impl.tariffbuilder.DOMTariffBuilder;
import org.junit.Assert;
import org.junit.Test;


public class EntityBuilderFactoryTest {

    private final EntityBuilderFactory factory = EntityBuilderFactory.getInstance();

    @Test
    public void methodGetFlowerBuilderShouldGetSAXAndReturnClassEqualsSaxFlowerBuilder(){

        Class<SAXFlowerBuilder> flowerBuilderClass = SAXFlowerBuilder.class;
        Builder<Flower>  flowerBuilder = factory.getFlowerBuilder(ParserType.SAX);
        Assert.assertEquals(flowerBuilderClass,flowerBuilder.getClass());

    }

    @Test
    public void methodGetGemBuilderShouldGetStaXAndReturnClassEqualsStAXGemBuilder(){
        Class<StAXGemBuilder> gemBuilderClass = StAXGemBuilder.class;
        Builder<Gem> gemBuilder = factory.getGemBuilder(ParserType.STAX);
        Assert.assertEquals(gemBuilderClass,gemBuilder.getClass());

    }


    @Test
    public void methodGetTariffBuilderShouldGetDOMAndReturnClassEqualsDOMTariffBuilder(){
        Class<DOMTariffBuilder> tariffBuilderClass = DOMTariffBuilder.class;
        Builder<Tariff> gemBuilder = factory.getTarifBuilder(ParserType.DOM);
        Assert.assertEquals(tariffBuilderClass,gemBuilder.getClass());

    }



}