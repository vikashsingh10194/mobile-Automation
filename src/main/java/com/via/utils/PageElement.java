package com.via.utils;

public class PageElement {

    private String name;
    private String type;
    private String locatorType;
    private String locatorValue;

    public PageElement() {

    }

    public PageElement(PageElement pageElement) {
        this.name = pageElement.name;
        this.type = pageElement.type;
        this.locatorType = pageElement.locatorType;
        this.locatorValue = pageElement.locatorValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public String getLocatorValue() {
        return locatorValue;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }
}
