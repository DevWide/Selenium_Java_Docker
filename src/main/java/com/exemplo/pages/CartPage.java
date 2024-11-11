package com.exemplo.pages;

import com.exemplo.utils.SelectorsLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private SelectorsLoader selectorsLoader;

    // Seletores
    private By backpackItem;
    private By checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.selectorsLoader = new SelectorsLoader();

        // Carrega os seletores do JSON
        this.backpackItem = By.cssSelector(selectorsLoader.getSelector("CartPage", "backpackItem"));
        this.checkoutButton = By.cssSelector(selectorsLoader.getSelector("CartPage", "checkoutButton"));
    }

    // Método para verificar se o item "Sauce Labs Backpack" está no carrinho
    public boolean isBackpackInCart() {
        return driver.findElement(backpackItem).isDisplayed();
    }

    // Método para verificar se estamos na página do carrinho
    public boolean isCartPageDisplayed() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    // (Opcional) Método para iniciar o processo de checkout
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
