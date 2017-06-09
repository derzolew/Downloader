package com.maxvi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static Map<String, List<String>> mArguments = new HashMap<>();

    public static void main(final String[] args) {
        ArgsUtil argsUtil = new ArgsUtil();
        argsUtil.parseArgs(args);
        mArguments = argsUtil.getArgsMap();
        for (Map.Entry<String, List<String>> entry : mArguments.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
