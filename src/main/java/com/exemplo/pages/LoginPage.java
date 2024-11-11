package com.exemplo.pages;

import com.exemplo.utils.SelectorsLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private SelectorsLoader selectorsLoader;

    // Elementos da página
    private By usernameField;
    private By passwordField;
    private By loginButton;
    private String inventoryPageUrl;

    // Construtor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.selectorsLoader = new SelectorsLoader();

        // Carrega os seletores do JSON usando o SelectorsLoader
        this.usernameField = By.cssSelector(selectorsLoader.getSelector("LoginPage", "usernameField"));
        this.passwordField = By.cssSelector(selectorsLoader.getSelector("LoginPage", "passwordField"));
        this.loginButton = By.cssSelector(selectorsLoader.getSelector("LoginPage", "loginButton"));
        this.inventoryPageUrl = selectorsLoader.getSelector("LoginPage", "inventoryPageUrl");
    }

    // Comandos (alteram o estado)
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Método que encapsula o processo de login
    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Consultas (não alteram o estado)
    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().equals(inventoryPageUrl);
    }
}

