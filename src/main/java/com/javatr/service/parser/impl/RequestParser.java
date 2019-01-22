package com.javatr.service.parser.impl;

import com.javatr.controller.command.CommandName;
import com.javatr.service.factory.ParserType;
import com.javatr.service.parser.Parser;

public class RequestParser implements Parser {
    @Override
   public Object[] parse(String request, String separator){
        String[] array = request.split(separator);
        String filePath= array[0];
        ParserType type = ParserType.valueOf(array[1]);
        CommandName commandName = CommandName.valueOf(array[2]);

        Object[] params = new Object[3];
        params[0] = filePath;
        params[1] = type;
        params[2] = commandName;
        return params;
    }
}
