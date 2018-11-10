package com.es.phoneshop.command;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final String COMMAND = "command";
    private static final String TARGET = " ";
    private static final String REPLACEMENT = "_";
    private static Command command = new DefaultCommand();

    public static Command defineCommand(HttpServletRequest httpServletRequest){
        String action = httpServletRequest.getParameter(COMMAND);
        if (action == null || action.isEmpty()){
            return command;
        }
        return command;
    }
}
