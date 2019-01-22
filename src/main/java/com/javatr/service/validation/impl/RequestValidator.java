package com.javatr.service.validation.impl;

import com.javatr.controller.command.CommandName;
import com.javatr.service.factory.ParserType;
import com.javatr.service.validation.Validator;

public class RequestValidator implements Validator {
    private static final String REQUEST_REGEX = "\\S+"+ " (" +
            ParserType.STAX.toString() + "|" +
            ParserType.SAX.toString() + "|" +
            ParserType.DOM.toString() + ") ("+
            CommandName.PARSE_FLOWER.toString() + "|" +
            CommandName.PARSE_GEM.toString() + "|" +
            CommandName.PARSE_TARIFF.toString() + ")";

    @Override
    public boolean isValid(String request) {
       return  request.matches(REQUEST_REGEX);
    }


}
