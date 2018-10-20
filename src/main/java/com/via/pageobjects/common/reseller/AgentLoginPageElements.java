package com.via.pageobjects.common.reseller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;

public class AgentLoginPageElements extends PageHandler {

    private RepositoryParser repositoryParser;
    private final String     PAGE_NAME = "agentLoginPage";

    protected AgentLoginPageElements(String testId, WebDriver driver, RepositoryParser repositoryParser) {
        super(testId, driver);
        this.repositoryParser = repositoryParser;
    }

    protected WebElement adminRadio() {
        return findElement(repositoryParser, PAGE_NAME, "admin");
    }

    protected WebElement deskUserRadio() {
        return findElement(repositoryParser, PAGE_NAME, "user");
    }

    protected WebElement username() {
        return findElement(repositoryParser, PAGE_NAME, "userId");
    }

    protected WebElement deskId() {
        return findElement(repositoryParser, PAGE_NAME, "deskId");
    }

    protected WebElement password() {
        return findElement(repositoryParser, PAGE_NAME, "password");
    }

    protected WebElement loginButton() {
        return findElement(repositoryParser, PAGE_NAME, "login");
    }
}
