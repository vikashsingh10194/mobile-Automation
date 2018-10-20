package com.via.utils;

import org.apache.log4j.Logger;

public class Log {
    // Initialize Log4j logs
    private static Logger log = Logger.getLogger(Log.class.getName());

    // This is to print log for the beginning of the test case, as we usually
    // run so many test cases as a test suite
    public static void startTestCase(String sTestCaseName) {
        log.info("************************************************************************************");
        log.info("$$$$$$$$$$$$$$$$$$$              " + sTestCaseName + "           $$$$$$$$$$$$$$$$$$$");
        log.info("************************************************************************************");
        System.out.println("************************************************************************************");
        System.out.println("$$$$$$$$$$$$$$$$$$$              " + sTestCaseName + "           $$$$$$$$$$$$$$$$$$$");
        System.out.println("************************************************************************************");
    }

    // This is to print log for the ending of the test case
    public static void endTestCase(String sTestCaseName) {
        log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
        log.info("                                         X");
        log.info("                                         X");
        log.info("                                         X");
        log.info("                                         X");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("                                         X");
        System.out.println("                                         X");
        System.out.println("                                         X");
        System.out.println("                                         X");
    }

    public static void openPage(String testId, String pageName) {
        log.info(testId + " :::::::::::::::    " + pageName + "    :::::::::::::::");
        System.out.println(testId + " :::::::::::::::    " + pageName + "    :::::::::::::::");
    }

    public static void divider(String testId, String text) {
        log.info(testId + " -----------------    " + text + "    -----------------");
        System.out.println(testId + " -----------------    " + text + "    -----------------");
    }

    public static void divider(String testId) {
        log.info(testId + " -----------------------------------------------------------");
        System.out.println(testId + " -----------------------------------------------------------");
    }

    // Need to create these methods, so that they can be called
    public static void info(String testId, String message) {
        log.info(testId + " " + message);
        System.out.println(testId + " " + message);
    }

    public static void warn(String testId, String message) {
        log.warn(testId + " " + message);
        System.out.println(testId + " " + message);
    }

    public static void error(String testId, String message) {
        log.error(testId + " " + message);
        System.out.println(testId + " " + message);
    }

    public static void fatal(String testId, String message) {
        log.fatal(testId + " " + message);
        System.out.println(testId + " " + message);
    }

    public static void debug(String testId, String message) {
        log.debug(testId + " " + message);
        System.out.println(testId + " " + message);
    }

    public static void info(String message) {
        log.info(message);
        System.out.println(message);
    }

    public static void warn(String message) {
        log.warn(message);
        System.out.println(message);
    }

    public static void error(String message) {
        log.error(message);
        System.out.println(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
        System.out.println(message);
    }

    public static void debug(String message) {
        log.debug(message);
        System.out.println(message);
    }
}
