package com.javatr.controller.command.impl;

import com.javatr.controller.command.Command;
import com.javatr.service.factory.ParserType;

public class WrongRequest implements Command {
    @Override
    public String parse(String pathToXML, ParserType parserType) {
        return "Error! you enter incorrect request";
    }
}
