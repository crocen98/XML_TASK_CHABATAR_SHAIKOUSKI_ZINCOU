package com.javatr.controller.command.impl;

import com.javatr.controller.command.Command;
import com.javatr.entity.gem.Gem;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.factory.EntityBuilderFactory;
import com.javatr.service.factory.ParserType;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ParseGem implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ParseGem.class);

    @Override
    public String parse(String pathToXML, ParserType parserType) {
        EntityBuilderFactory factory = EntityBuilderFactory.getInstance();
        Builder<Gem> gemBuilderFactory = factory.getGemBuilder(parserType);
        List<Gem> gems;
        try {
            gems = gemBuilderFactory.build(new XMLValidatorByXSD("resources/xsd/gems.xsd"), pathToXML) ;
        } catch (IOServiceException | XMLParserServiceException e) {
            LOGGER.error(e);
            return e.getMessage();
        }

        return gems.toString();

    }
}
