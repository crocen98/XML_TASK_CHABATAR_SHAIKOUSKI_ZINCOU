package com.javatr.controller.command;

import com.javatr.controller.command.impl.ParseFlower;
import com.javatr.controller.command.impl.ParseGem;
import com.javatr.controller.command.impl.ParseTariff;
import com.javatr.controller.command.impl.WrongRequest;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.PARSE_FLOWER, new ParseFlower());
        repository.put(CommandName.PARSE_GEM, new ParseGem());
        repository.put(CommandName.PARSE_TARIFF, new ParseTariff());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(CommandName name) {
        Command command;
        try {
            command = repository.get(name);
        } catch (IllegalArgumentException | NullPointerException e) {
            //write log
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }


}


