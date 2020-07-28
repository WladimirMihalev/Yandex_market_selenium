package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.example.GeneratorCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class LoginTest {
    private static StartPage startPage;
    private static LoginPage loginPage;
    private static ProfilePage profilePage;
    private static String first_tab;
    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final String URI_SCRIPT = "window.open('https://passport.yandex.by/auth?retpath=https%3A%2F%2Fmarket.yande" +
            "x.by%2Fcloser.html%3Fsocial-broker_seed%3D15955038001131e580f%23status%3Dok&retnopopup=&consumer=market&action_if_anonymous=ignore&resul" +
            "t_location=fragment&provider=ya&sid=25&display=popup&origin=market_desktop_header')";


    @BeforeMethod
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", ConfProp.getProperty("chromedriver"));
                       
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        startPage = new StartPage(driver);
        profilePage = new ProfilePage(driver);
        wait = new WebDriverWait(driver, 5);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        first_tab = driver.getWindowHandle();
        driver.get(ConfProp.getProperty("mainpage"));
        ((JavascriptExecutor) driver).executeScript(URI_SCRIPT);
    }


    @Test
    public void openMarketPage() throws IOException{

        switchToAuthorisationTab();

        loginPage.inputLogin(ConfProp.getProperty("login"));
        loginPage.clickLoginBtn();
        loginPage.inputPasswd(ConfProp.getProperty("password"));
        loginPage.clickSecLoginBtn();

        loginPage.closeBtn();

        driver.switchTo().window(first_tab);
        driver.navigate().refresh();
        startPage.openCatalogueBtn();
        List<String> treeHorizontalElements = new ArrayList<>();
        List<String> treeVerticalElements = new ArrayList<>();

        WebElement categoryVerticalTree = driver.findElement(By.cssSelector("div[aria-orientation='vertical']"));
        categoryVerticalTree.findElements(By.cssSelector("div[data-zone-name='category-link']")).forEach(element -> {
            treeVerticalElements.add(element.findElement(By.tagName("span")).getText());
        });

        WebElement categoryHorizontalTree = driver.findElement(By.cssSelector("div[aria-orientation]"));
        categoryHorizontalTree.findElements(By.cssSelector("div[data-zone-name='category-link']")).forEach(element -> {
            if (element.findElement(By.tagName("a")).getAttribute("href").contains("/catalog--")) {
                String elementText = element.findElement(By.tagName("span")).getText();
                if (!elementText.isEmpty()) {
                    treeHorizontalElements.add(elementText);
                }
            }
        });

        GeneratorCSV generator = new GeneratorCSV();
        generator.createCSVFile(treeVerticalElements);

        boolean mistake = false;
        for (String elementName : treeHorizontalElements) {
            if (treeVerticalElements.contains(elementName)) {
            } else {
                mistake = true;
                continue;
            }

        }


        Assert.assertTrue(mistake, "Horizontal catalogue item dissonance");

    }


    @AfterMethod
    public static void tearDown() {
        profilePage.entryMenu();
        profilePage.userLogout();
        driver.quit();
    }

    private void switchToAuthorisationTab() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String next_tab = i1.next();
            if (!first_tab.equalsIgnoreCase(next_tab)) {
                driver.switchTo().window(next_tab);
            }
        }
    }
}




