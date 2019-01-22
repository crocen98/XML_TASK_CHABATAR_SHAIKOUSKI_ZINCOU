package com.javatr.controller;

import com.javatr.controller.command.Command;
import com.javatr.controller.command.CommandName;
import com.javatr.controller.command.CommandProvider;
import com.javatr.service.factory.ParserType;
import com.javatr.service.parser.impl.RequestParser;
import com.javatr.service.validation.impl.RequestValidator;

public class ConsoleController implements Controller {
    private static final String SEPARATOR = " ";
    private final CommandProvider provider = new CommandProvider();
    private final RequestValidator validator = new RequestValidator();

    @Override
    public String parseEntity(String request) {
        if(validator.isValid(request)){
            RequestParser parser = new RequestParser();
            Object[] params =  parser.parse(request,SEPARATOR);

            String filePath = (String) params[0];
            ParserType type= (ParserType) params[1];
            CommandName commandName = (CommandName) params[2];

            Command command = provider.getCommand(commandName);
            return command.parse(filePath,type);
        }
        return provider.getCommand(CommandName.WRONG_REQUEST).parse(null,null);

    }


}


