package com.exemplo.tests;

import com.exemplo.pages.LoginPage;
import com.exemplo.pages.InventoryPage;
import com.exemplo.utils.ConfigLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Before
    public void setUp() {
        String geckoDriverPath = System.getenv("GECKODRIVER_PATH");
        if (geckoDriverPath == null) {
            geckoDriverPath = "/opt/homebrew/bin/geckodriver"; // Caminho local
        }
        System.setProperty("webdriver.gecko.driver", geckoDriverPath);

        driver = new FirefoxDriver();

        String baseUrl = ConfigLoader.getProperty("baseUrl");
        driver.get(baseUrl);

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void testLoginAndAddToCart() {
        loginPage.loginAs("standard_user", "secret_sauce");

        assertTrue("Login failed: Inventory page is not displayed", inventoryPage.isDisplayed());

        inventoryPage.addBackpackToCart();

        inventoryPage.openCart();

        takeScreenshot("CartPageAfterAddingBackpack");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void takeScreenshot(String fileName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String screenshotDir = "screenshots";
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            directory.mkdir();
        }

        String filePath = screenshotDir + "/" + fileName + "_" + timestamp + ".png";
        try {
            Files.move(screenshotFile.toPath(), Paths.get(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
