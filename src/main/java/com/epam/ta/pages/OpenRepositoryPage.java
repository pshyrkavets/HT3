package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenRepositoryPage extends AbstractPage {
    private final String BASE_URL = "https://github.com/" + getLoggedInUserName() + "?tab=repositories";
    private final Logger logger = LogManager.getRootLogger();
    private String nameOfWantedRepository;

    @FindBy(xpath = "//meta[@name='user-login']")
    private WebElement linkLoggedInUser;

    public OpenRepositoryPage(String nameOfWantedRepository, WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.nameOfWantedRepository = nameOfWantedRepository;
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Login page opened");
    }

    public void seekAndOpenWantedRepository() {
        WebElement listOfRepositories = driver.findElement(By.id("user-repositories-list"));
        listOfRepositories.findElement(By.linkText(nameOfWantedRepository)).click();
    }

    public String getLoggedInUserName() {
        return linkLoggedInUser.getAttribute("content");
    }

    public String getUrlOfPage() {
        return driver.getCurrentUrl();
    }
}
