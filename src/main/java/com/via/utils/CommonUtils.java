package com.via.utils;

import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.via.utils.Constant.SelectBy;

public class CommonUtils {

  /**
   * Opens the browser in mobile view
   * 
   * @param url The url to open
   * @param browser at which url open
   * @return The driver
   */
  public static WebDriver openBrowser(String url, String browser) {
    WebDriver driver = null;
    String envTestServer = System.getenv("TESTSERVER");
    if (envTestServer != null && envTestServer.equals("jenkinserver")) {
      String path = "/home/jenkins/firebug-2.0.17.xpi";
      if (System.getProperty("firefox.driver") != null) {
        path = System.getProperty("firefox.driver");
      }
      File file = new File(path);
      FirefoxProfile firefoxProfile = new FirefoxProfile();
      try {
        firefoxProfile.addExtension(file);
      } catch (Exception e) {
        Log.error(e.getMessage());
      }

      // Avoid startup screen
      firefoxProfile.setPreference("extensions.firebug.currentVersion", "2.0b8");
      driver = new FirefoxDriver(firefoxProfile);
      System.out.println("browser opened");
    } else if (StringUtils.equalsIgnoreCase(browser, "firefox")) {

      // System.setProperty("webdriver.firefox.marionette",Constant.FIREFOX_GECKODRIVER_PATH);
      // DesiredCapabilities capabilities = DesiredCapabilities.firefox();
      // capabilities.setCapability("marionette", true);
      //
      // FirefoxProfile ffprofile = new FirefoxProfile();
      // ffprofile.setPreference("general.useragent.override", "iPhone");
      //
      // driver=new FirefoxDriver(ffprofile);

      FirefoxProfile ffprofile = new FirefoxProfile();
      ffprofile.setPreference("general.useragent.override", "iPhone");
      driver = new FirefoxDriver(ffprofile);
    } else if (browser.equalsIgnoreCase("chrome")) {
      String osName = System.getProperty("os.name");
      if (StringUtils.containsIgnoreCase(osName, "windows")) {
        System.setProperty("webdriver.chrome.driver", Constant.WINDOWS_CHROMEDRIVER_PATH);
      } else if (StringUtils.containsIgnoreCase(osName, "mac")) {
        System.setProperty("webdriver.chrome.driver", Constant.MAC_CHROMEDRIVER_PATH);
      }

      Map<String, String> mobileEmulation = new HashMap<String, String>();
      mobileEmulation.put("deviceName", "Google Nexus 5");

      Map<String, Object> chromeOptions = new HashMap<String, Object>();
      chromeOptions.put("mobileEmulation", mobileEmulation);
      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
      driver = new ChromeDriver(capabilities);
    }

    setImplicitWaitTime(driver);
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    driver.get(url);

    return driver;
  }

  public static void closeDriver(WebDriver aDriver) {
    sleep(3 * 1000L);
    if (aDriver != null) {
      aDriver.quit();
    }
  }

  public static void sleep(Long milliseconds) {
    try {
      Thread.sleep(3 * 1000l);
    } catch (InterruptedException e) {
      CustomAssert.assertFail("Interrupt occured.");
    }
  }

  public static void setImplicitWaitTime(WebDriver driver, long timeInMilliSeconds) {
    driver.manage().timeouts().implicitlyWait(timeInMilliSeconds, TimeUnit.MILLISECONDS);
  }

  public static void setImplicitWaitTime(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(Constant.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
  }

  public void maximizeScreen(WebDriver driver) {
    java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Point position = new Point(0, 0);
    driver.manage().window().setPosition(position);
    Dimension maximizedScreenSize =
        new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
    driver.manage().window().setSize(maximizedScreenSize);
  }

  public static void click(WebDriver driver, WebElement element) {
    new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element)).click();
  }

  public static void click(WebDriver driver, WebElement element, int waitTimeInSecs) {
    new WebDriverWait(driver, waitTimeInSecs).until(
        ExpectedConditions.elementToBeClickable(element)).click();
  }

  public static void javaScriptExecuterClick(WebDriver driver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true);", element);
    js.executeScript("arguments[0].click();", element);
  }

  /**
   * Modifies the elements locator value Replaces the string "toModify" in the locator value with
   * the list of strings passed.
   * 
   * @param repositoryParser Repository parser
   * @param pageName The objects page name
   * @param objectName The objects name
   * @param modification The modification
   * @return The modified page element
   */
  public static PageElement modifyPageElement(RepositoryParser repositoryParser, String pageName,
      String objectName, String... modificationStrings) {
    PageElement element = repositoryParser.getPageObject(pageName, objectName);
    PageElement modifiedElement = new PageElement(element);
    String locatorValue = modifiedElement.getLocatorValue();

    for (String modification : modificationStrings) {
      locatorValue = StringUtils.replaceOnce(locatorValue, "toModify", modification);
    }
    modifiedElement.setLocatorValue(locatorValue);
    return modifiedElement;
  }

  /**
   * Selects the desrired option in select box
   * 
   * @param selectBox
   * @param selectBy
   * @param desiredValue
   */
  public static void selectBox(WebElement selectBox, SelectBy selectBy, String desiredValue) {
    Select select = new Select(selectBox);
    if (selectBy == SelectBy.INDEX) {
      select.selectByIndex(Integer.parseInt(desiredValue));
    } else if (selectBy == SelectBy.VALUE) {
      select.selectByValue((String) desiredValue);
    } else {
      select.selectByVisibleText((String) desiredValue);
    }
  }

  public static void viewElement(WebDriver driver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true);", element);
  }
}
