package com.via.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.via.utils.Constant.Country;

/**
 * Generated random values
 * 
 * @author Yash Gupta
 *
 */
public class RandomValues {

    private final static String NAME_LEXICON      = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String ALPHA_NUM_LEXICON = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final static Random RAND              = new Random();

    @SuppressWarnings("serial")
    private static List<String> nameList = new ArrayList<String>() {
      {
        add("RIAN");
        add("FAISAL");
        add("LUSI");
        add("RIZKY");
        add("HALIMAH");
        add("NADYA");
        add("CHERLY");
        add("MEI");
        add("MIRA");
        add("IDHAM");
        add("OKY");
        add("VANI");
        add("VINO");
        add("ZAHRA");
        add("MEGA");
        add("FATICH");
        add("MAHESA");
        add("LISTIKA");
        add("SAPTA");
        add("KARWANTO");
        add("REZA");
        add("RENTI");
        add("AZKIA");
        add("CHRISTY");
        add("TIKA");
        add("APRIL");
        add("SHAFA");
        add("JUHENDRA");
        add("AHMAD");
        add("NICO");
      }
    };
    private static Map<String, Boolean> usedNameList;
    
    public static void setUsedNameList() {
      usedNameList = new HashMap<String, Boolean>();
    }
    
    /**
     * Gets the random name
     * 
     * @return The random name
     */
//    public static String getRandomName() {
//        StringBuilder builder = new StringBuilder();
//        int length = RAND.nextInt(5) + 5;
//        for (int i = 0; i < length; i++)
//            builder.append(NAME_LEXICON.charAt(RAND.nextInt(NAME_LEXICON.length())));
//        return builder.toString();
//    }
    
    public static String getRandomName() {
      StringBuilder builder = new StringBuilder();
      int length = RAND.nextInt(5) + 5;
      for (int i = 0; i < length; i++)
        builder.append(NAME_LEXICON.charAt(RAND.nextInt(NAME_LEXICON.length())));
      return builder.toString();
    }

    public static String getRandomName(Country countryCode) {
      String name = null;

      do {
        if (countryCode == Country.INDONESIA) {
          name = nameList.get(RAND.nextInt(30)) + " " + nameList.get(RAND.nextInt(30));
        } else {
          name = getRandomName() + " " + getRandomName();
        }
      } while (usedNameList.get(name) != null);

      usedNameList.put(name, true);

      return name;
    }

    /**
     * Gets the random number between two numbers
     * 
     * @param low The lower number
     * @param high The higher number
     * @return The random number
     */
    public static int getRandomNumberBetween(int low, int high) {
        if (low > high) {
            return -1;
        }
        if (low == high) {
            return low;
        }
        int result = RAND.nextInt(high - low) + low;
        return result;
    }

    /**
     * Gets random passport number
     * 
     * @return The random passport number
     */
    public static String getRandomPassportNumber() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            builder.append(ALPHA_NUM_LEXICON.charAt(RAND.nextInt(ALPHA_NUM_LEXICON.length())));
        }
        return builder.toString();
    }
}
