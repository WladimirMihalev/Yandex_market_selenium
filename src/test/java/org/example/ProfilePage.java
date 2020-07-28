package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    public WebDriver driver;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "/html/body/div[2]/div[2]/noindex/div/div/div[2]/div[2]/div[1]/div[6]/div/div/div/div/div[1]/div/div[1]/button")
    private WebElement userMenu;

    @FindBy(xpath = "/html/body/div[2]/div[2]/noindex/div/div/div[2]/div[2]/div[1]/div[6]/div/div/div/div/div[1]/div/div[2]/div[2]/a[6]/span/span")
    private WebElement logoutBtn;


    public void entryMenu() {
        userMenu.click(); }

    public void userLogout() {
        logoutBtn.click(); } }