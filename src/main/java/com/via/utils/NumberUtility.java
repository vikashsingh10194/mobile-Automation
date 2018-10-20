package com.via.utils;

import org.apache.commons.lang.StringUtils;

import com.via.utils.Constant.Country;

public class NumberUtility {

    // This string contains amount with comma eg -> 3,337.65
    public static double getAmountFromString(Country country, String str) {
        double value = 0;

        str = str.replaceAll("[^0-9.]", "");
        str = StringUtils.trimToEmpty(str);
        if (StringUtils.isEmpty(str)) {
            return value;
        }
        str = StringUtils.removeEnd(str, Constant.FULLSTOP);

        if (country == Country.INDONESIA) {
            str = StringUtils.remove(str, ".");
            return Double.parseDouble(str);
        }

        return Double.parseDouble(str);
    }

    /**
     * Validates that the amount difference b/w 2 fare is valid according to
     * different countries
     * 
     * @return True if its valid amount diff, else false
     */
    public static boolean validAmountDiff(String testId, Country country, double amount1, double amount2) {
        double diffEligible = 0.0;

        switch (country) {
          case PHILIPPINES:
            diffEligible = 0.05;
            break;
        case SINGAPORE:
            diffEligible = 0.05;
            break;
        case INDONESIA:
            diffEligible = 3;
            break;
        case UAE:
            diffEligible = 0.04;
            break;
        case OMAN:
          diffEligible = 0.05;
          break;
        case SAUDI:
          diffEligible = 0.05;
          break;
        case THAILAND:
            diffEligible = 0.01;
            break;
        case HONGKONG:
          diffEligible = 0.05;
          break;
        default:
            diffEligible = 0.0;
            break;
        }

        if (Math.abs(amount1 - amount2) > diffEligible) {
            Log.info(testId, "Expected :: " + amount1 + " Found :: " + amount2);
            return false;
        }
        return true;
    }
}
