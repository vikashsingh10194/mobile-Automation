package com.via.pageobjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;

/**
 * 
 * @author Yash Gupta
 *
 */
public class HomePageElements extends PageHandler {

    private RepositoryParser repositoryParser;
    private final String     PAGE_NAME = "homePage";

    protected HomePageElements(String testId, WebDriver driver, RepositoryParser repositoryParser) {
        super(testId, driver);
        this.repositoryParser = repositoryParser;
    }

    protected WebElement openNavigationDrawer() {
        return findElement(repositoryParser, PAGE_NAME, "openNavigationDrawer");
    }

    protected WebElement crossHomepageKey(){
        return findElement(repositoryParser, PAGE_NAME, "crossHomepageKey");
        }
    
    protected WebElement languageSelectBox() {
        return findElement(repositoryParser, PAGE_NAME, "languageSelectBox");
    }

    /* Login Options */
    protected WebElement currUserName() {
        return findElement(repositoryParser, PAGE_NAME, "currUserName");
    }

    protected WebElement loginDrawerOption() {
        return findElement(repositoryParser, PAGE_NAME, "loginDrawerOption");
    }

    protected WebElement logoutDrawerOption() {
        return findElement(repositoryParser, PAGE_NAME, "logoutDrawerOption");
    }

    protected WebElement username() {
        return findElement(repositoryParser, PAGE_NAME, "username");
    }

    protected WebElement password() {
        return findElement(repositoryParser, PAGE_NAME, "password");
    }

    protected WebElement loginButton() {
        return findElement(repositoryParser, PAGE_NAME, "login");
    }

    public WebElement crossPopup() {
        return findElement(repositoryParser, PAGE_NAME, "crossPopup");
    }
    
}
