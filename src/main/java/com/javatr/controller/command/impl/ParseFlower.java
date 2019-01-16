package com.javatr.controller.command.impl;

import com.javatr.controller.command.Command;
import com.javatr.service.factory.ParserType;


public class ParseFlower implements Command {
    @Override
    public String parse(String pathToXML, ParserType parserType) {
        // AbstractFactory factory = AbstractFactory.getInstance();
        // BuilderTypeFactory<Flower> flowerBuilderFactory = factory.FlowerFaType);
        //        // List<Flowers> flowers = saxBuilder.build(Validator, pathToXML) ;ctory();
        // Builder<Flower> saxBuilder = flowerBuilderFactory.getBuilder(parser
        // return flowers.toString();
        throw new RuntimeException("Metod Not Ended");
    }
}
