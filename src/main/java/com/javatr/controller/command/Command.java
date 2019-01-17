package com.javatr.controller.command;

import com.javatr.service.factory.ParserType;

public interface Command {
     String parse(String pathToXML, ParserType parserType);
}