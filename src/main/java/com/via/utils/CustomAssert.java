package com.via.utils;

import org.apache.commons.lang.ObjectUtils;
import org.testng.Assert;

public class CustomAssert {

    public static void assertTrue(String testId, boolean value, String successMsg, String failureMsg) {

        if (value) {
            Log.info(testId, successMsg);
        } else {
            Log.error(testId, failureMsg);
        }
        Assert.assertTrue(value, failureMsg);
    }

    public static void assertTrue(String testId, boolean value, String failureMsg) {
        if (!value) {
            Log.error(testId, failureMsg);
        }
        Assert.assertTrue(value, failureMsg);
    }

    public static void assertTrue(boolean value, String failureMsg) {
        if (!value) {
            Log.error(failureMsg);
        }
        Assert.assertTrue(value, failureMsg);
    }

    public static void assertEquals(String testId, Object expectedObject, Object actualObject, String successMsg,
            String failureMsg) {

        if (ObjectUtils.equals(expectedObject, actualObject)) {
            Log.info(testId, successMsg);
        } else {
            Log.error(testId, failureMsg);
            printError(testId, expectedObject, actualObject);
        }
        Assert.assertEquals(expectedObject, actualObject);
    }

    public static void assertEquals(String testId, Object expectedObject, Object actualObject, String failureMsg) {

        if (!ObjectUtils.equals(expectedObject, actualObject)) {
            Log.error(testId, failureMsg);
            printError(testId, expectedObject, actualObject);
        }
        Assert.assertEquals(expectedObject, actualObject);
    }

    public static void assertEquals(Object expectedObject, Object actualObject, String failureMsg) {

        if (!ObjectUtils.equals(expectedObject, actualObject)) {
            Log.error(failureMsg);
        }
        Assert.assertEquals(expectedObject, actualObject);
    }

    public static void assertVerify(String testId, Object expectedValue, Object actualValue, String successMsg,
            String failureMsg) {
        if (ObjectUtils.equals(expectedValue, actualValue)) {
            Log.info(testId, successMsg);
        } else {
            Log.error(testId, failureMsg);
        }
    }

    public static void assertVerify(String testId, boolean value, String failureMsg) {
        if (!value) {
            Log.error(testId, failureMsg);
        }
    }

    public static void assertVerify(Object expectedValue, Object actualValue, String successMsg, String failureMsg) {
        if (ObjectUtils.equals(expectedValue, actualValue)) {
            Log.info(successMsg);
        } else {
            Log.error(failureMsg);
        }
    }

    public static void assertFail(String testId, String failMsg) {
        Log.error(testId, failMsg);
        Assert.fail();
    }

    public static void assertFail(String failMsg) {
        Log.error(failMsg);
        Assert.fail();
    }

    private static void printError(String testId, Object expectedObject, Object actualObject) {
        Log.info(testId, "Expected :: [" + expectedObject.toString() + "] , Found :: [" + actualObject.toString() + "]");
    }
}
