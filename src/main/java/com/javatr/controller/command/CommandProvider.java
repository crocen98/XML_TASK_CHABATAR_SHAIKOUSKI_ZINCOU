package com.javatr.controller.command;

import com.javatr.controller.command.impl.ParseFlower;
import com.javatr.controller.command.impl.WrongRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.PARSE_FLOWER, new ParseFlower());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }


    public Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            //write log
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}

