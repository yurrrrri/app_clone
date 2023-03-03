package com.ll.stage6;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String actionCode;
    private Map<String, String> params;

    public Rq(String command) {
        String[] commandBits = command.split("\\?", 2);
        actionCode = commandBits[0];
        params = new HashMap<>();

        if(commandBits.length == 1) return;

        String[] paramsBits = commandBits[1].split("&");

        for(String paramStr : paramsBits){
            String[] paramStrBits = paramStr.split("=", 2);
            String key = paramStrBits[0];

            if(paramStrBits.length == 1) continue;

            String value = paramStrBits[1];

            params.put(key, value);
        }
    }

    public String getActionCode() {
        return actionCode;
    }

    public String getParam(String name) {
        return params.get(name);
    }

    public int getIntParam(String name, int defaultValue) {
        try {
            return Integer.parseInt(getParam(name));
        } catch (NumberFormatException e) {}
        return defaultValue;
    }
}