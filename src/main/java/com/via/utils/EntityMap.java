package com.via.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.via.utils.Constant.Product;

public class EntityMap {

    // The map store the mapping of the city name visible in excel sheet with
    // the city name in autoComplete box

    @SuppressWarnings("serial")
    private static final Map<String, String> airlinesMap = new HashMap<String, String>() {
                                                             {
                                                                 put("INDIGO", "6E");
                                                                 put("SPICEJET", "SG");
                                                                 put("JETAIRWAYS", "9W");
                                                                 put("GOAIR", "G8");
                                                                 put("AIRINDIA", "AI");
                                                                 put("VISTARA", "UK");
                                                                 put("AIRASIA_INDIA", "I5");
                                                                 put("AIR_COSTA", "LB");
                                                                 put("AIRASIA", "I5");
                                                                 put("AIRCOSTA", "LB");
                                                                 put("AIR_PEGASUS", "OP");
                                                                 put("AIRPEGASUS", "OP");
                                                                 put("TRUEJET", "2T");
                                                                 put("TIGERAIRWAYS", "TR");
                                                                 put("AIRASIAINTL", "AK");
                                                                 put("AIRARABIA", "G9");
                                                                 put("SCOOT", "TZ");
                                                                 put("ZESTAIR", "Z2");
                                                                 put("ETIHAD", "EY");
                                                                 put("THAILION", "SL");
                                                                 put("AIRASIATHAI", "FD");
                                                                 put("CITILINK", "QG");
                                                                 put("SRIWIJAYA", "SJ");
                                                                 put("AIRASIAINDONESIA", "QZ");
                                                                 put("LIONAIRWAYS", "JT");
                                                                 put("BATIKAIR", "ID");
                                                                 put("GARUDA", "GA");
                                                                 put("NAMAIR", "IN");
                                                                 put("WINGSAIR", "IW");
                                                                 put("KALSTAR", "KD");
                                                                 put("CEBUPACIFIC", "5J");
                                                                 put("PHILIPPINEAIRLINES", "PR");
                                                                 put("CEBGO", "DG");
                                                                 put("SKYJET", "M8");
                                                                 put("MALAYSIAAIRLINES", "MH");
                                                                 put("SINGAPOREAIRLINES", "SQ");
                                                                 put("MALINDO", "OD");
                                                                 put("CATHAYPACIFIC", "CX");
                                                                 put("EXPRESSAIR", "XN");
                                                                 put("THAI", "TG");
                                                                 put("FIREFLY", "FY");
                                                                 put("EVAAIRWAYS", "BR");
                                                                 put("ANA", "NH");
                                                                 put("EMIRATES", "EK");
                                                                 put("TURKISHAIR", "TK");
                                                                 put("CHINAEASTERNAIRLINES", "MU");
                                                                 put("CHINASOUTHERNAIRLINES", "CZ");
                                                                 put("XIAMENAIRLINES", "");
                                                                 put("JETSTARASIA", "3K");
                                                                 put("KOREANAIR", "KE");
                                                                 put("VIETNAMAIRLINES", "VN");
                                                                 put("ASIANAAIRLINES", "OZ");
                                                                 put("XIAMENAIRLINES", "MF");
                                                                 put("DELTA", "DL");
                                                                 put("CHINAAIRLINES", "CI");
                                                                 put("CEBGO", "DG");
                                                                 put("FLYDUBAI", "FZ");
                                                                 put("OMANAIR", "WY");
                                                                 put("QATARAIRWAYS", "QR");
                                                                 put("AIRBLUE", "PA");
                                                                 put("ROYALJORDANIAN", "RJ");
                                                                 put("GULFAIR", "GF");
                                                                 put("MYANMAR", "8M");
                                                                 put("BANGKOKAIRWAYS", "PG");
                                                                 put("KLM", "KL");
                                                                 put("NOKAIR", "DD");
                                                                 put("FLYNAS","XY-");
                                                                 put("SALAM","OV");
                                                             }
                                                         };

    public static String getAirlineCode(String airlineName) {
        String airlineCode = airlinesMap.get(StringUtils.upperCase(airlineName));
        if (StringUtils.isBlank(airlineCode)) {
            return airlineName;
        }
        return airlineCode;
    }

    @SuppressWarnings("serial")
    private static Map<String, Map<Product, String>> recipientMap      = new HashMap<String, Map<Product, String>>() {
                                                                           {
                                                                               // uae
                                                                               Map<Product, String> productRecipientMap = new HashMap<Product, String>();
                                                                               productRecipientMap
                                                                                       .put(Product.Flight,
                                                                                               DEFAULT_RECIPIENT
                                                                                                       + ",sweta.kumari@via.com");

                                                                               productRecipientMap
                                                                                       .put(Product.Hotel,
                                                                                               DEFAULT_RECIPIENT
                                                                                                       + ",sweta.kumari@via.com");

                                                                               put("uae", productRecipientMap);

                                                                               // singapore

                                                                               productRecipientMap = new HashMap<Product, String>();

                                                                               productRecipientMap
                                                                                       .put(Product.Flight,
                                                                                               DEFAULT_RECIPIENT
                                                                                                       + ",ankitkumar.t@via.com,saurav.raj@via.com,rinki.kumari@via.com");
                                                                               productRecipientMap
                                                                                       .put(Product.Hotel,
                                                                                               DEFAULT_RECIPIENT
                                                                                                       + ",ankitkumar.t@via.com,saurav.raj@via.com,rinki.kumari@via.com");
                                                                               put("singapore", productRecipientMap);

                                                                               put("thiland", productRecipientMap);

                                                                           }
                                                                       };

    private static final String                      DEFAULT_RECIPIENT = "qatest@via.com,automationreport@via.com";

    public static String getEmailRecipient(String countryName, Product productName) {

        Map<Product, String> productRecipientMap = recipientMap.get(countryName);
        if (productRecipientMap == null) {
            return DEFAULT_RECIPIENT;
        }

        String recipientList = productRecipientMap.get(productName);

        if (recipientList == null) {
            return DEFAULT_RECIPIENT;
        }

        return recipientList;
    }

}
