package com.via.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Yash Gupta
 *
 */
public class StringUtilities {

    /**
     * Splits the string into trimmed list of strings
     * 
     * @param str The string to be trimmed
     * @param seperator The seperator string
     * @return List of trimmed strings
     */
    public static List<String> split(String str, String seperator) {
        String metaCharacters = Constant.METACHARACTERS;
        if (str == null) {
            return new ArrayList<String>();
        }
        if (metaCharacters.contains(seperator)) {
            seperator = "\\" + seperator;
        }
        str = StringUtils.trimToEmpty(str);
        List<String> splittedList = Arrays.asList(str.split(seperator));
        for (int index = 0; index < splittedList.size(); index++) {
            String splittedString = StringUtils.trimToEmpty(splittedList.get(index));
            splittedList.set(index, splittedString);
        }

        return splittedList;
    }

}
