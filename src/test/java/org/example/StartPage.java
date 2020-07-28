package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

@Test
public class StartPage {
    public WebDriver driver;

    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div[3]/div[2]/noindex/div/div/div[2]/div[2]/div[1]/div[6]/div/div/a")
    private WebElement loginBtn;



    @FindBy(id = "27903767-tab")
    private WebElement catalogueBtn;



    public void clickLoginBtn() {
        loginBtn.click();
        ;}

    public void openCatalogueBtn() {
        catalogueBtn.click();
        ;}

}