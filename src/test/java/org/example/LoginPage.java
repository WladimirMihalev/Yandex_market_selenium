package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "passp-field-login")
    private WebElement loginField;

    @FindBy(id = "closeElem")
    private WebElement closeBtn;


    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div/div/div[3]/div[2]/div/div/div[1]/form/div[3]/button")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div/div/div[3]/div[2]/div/div/form/div[3]/button")
    private WebElement secLoginBtn;


    @FindBy(xpath = "//*[contains(@id, 'passp-field-passwd')]")
    private WebElement passwdField;

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd);
    }

    public void clickLoginBtn() {
        loginBtn.click();
        ;
    }

    public void clickSecLoginBtn() {
        secLoginBtn.click();
        ;
    }


    public void closeBtn() {
        closeBtn.click();
        ;
    }
}

