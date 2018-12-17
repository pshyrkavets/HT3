package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://github.com/";

    @FindBy(xpath = "//form[@class='logout-form']/input[@type='submit']")
    private WebElement signOutButtonSubmit;

    @FindBy(xpath = "//meta[@name='user-login']")
    private WebElement linkLoggedInUser;

    public LogoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void logout() {
        signOutButtonSubmit.click();
        logger.info("Logout performed");
    }

    public String getLoggedInUserName() {
        return linkLoggedInUser.getAttribute("content");
    }
}
