package com.maxvi.utilities;

import com.maxvi.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ArgsUtil {

    public static Map<String, List<String>> parseArgs(final String[] args) {
        final Map<String, List<String>> argsMap = new HashMap<>();
        List<String> argValues = null;
        for (final String arg : args) {
            if (checkProperArg(arg, argsMap)) {
                argValues = new ArrayList<>();
                argsMap.put(arg, argValues);
            } else {
                if (argValues != null && !arg.startsWith(Constants.ARG_START)) {
                    argValues.add(arg);
                }
            }
        }
        return argsMap;
    }

    private static boolean checkProperArg(final String pArg, final Map<String, List<String>> pArgsMap) {
        return (pArg.equals(Constants.ARG_LINK) ||
                pArg.equals(Constants.ARG_PATH) ||
                pArg.equals(Constants.ARG_THREADS) ||
                pArg.equals(Constants.ARG_FILE) ||
                pArg.equals(Constants.ARG_FILE_NAME)) && !pArgsMap.containsKey(pArg);
    }

    public static boolean checkIfFromLink(final Map<String, List<String>> pArgsMap) {
        return pArgsMap.containsKey(Constants.ARG_LINK) && pArgsMap.containsKey(Constants.ARG_PATH)
                && pArgsMap.containsKey(Constants.ARG_FILE_NAME)
                && !pArgsMap.containsKey(Constants.ARG_FILE);
    }

    public static boolean checkIfFromFile(final Map<String, List<String>> pArgsMap) {
        return pArgsMap.containsKey(Constants.ARG_FILE) && pArgsMap.containsKey(Constants.ARG_PATH)
                && !pArgsMap.containsKey(Constants.ARG_LINK)
                && !pArgsMap.containsKey(Constants.ARG_FILE_NAME);
    }

}
