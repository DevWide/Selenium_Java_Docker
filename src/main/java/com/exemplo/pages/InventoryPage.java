package com.exemplo.pages;

import com.exemplo.utils.SelectorsLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;
    private SelectorsLoader selectorsLoader;

    // Seletores
    private By backpackAddToCartButton;
    private By shoppingCartIcon;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.selectorsLoader = new SelectorsLoader();

        // Carrega os seletores do JSON
        this.backpackAddToCartButton = By.cssSelector(selectorsLoader.getSelector("InventoryPage", "backpackAddToCartButton"));
        this.shoppingCartIcon = By.cssSelector(selectorsLoader.getSelector("InventoryPage", "shoppingCartIcon"));
    }

    // Método para verificar se a página de inventário está carregada
    public boolean isDisplayed() {
        return driver.getCurrentUrl().equals(selectorsLoader.getSelector("LoginPage", "inventoryPageUrl"));
    }

    // Método para adicionar o "Sauce Labs Backpack" ao carrinho
    public void addBackpackToCart() {
        driver.findElement(backpackAddToCartButton).click();
    }

    // Método para abrir o carrinho de compras
    public void openCart() {
        driver.findElement(shoppingCartIcon).click();
    }
}

