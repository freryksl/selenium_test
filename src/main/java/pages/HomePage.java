package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends ContextPage {
    By logoSelector = By.cssSelector(".headerCheckout__logo");
    By cartButtonSelector = By.cssSelector("div[data-header-basket]");
    By discardSelector = By.cssSelector(".header__basketProduct>.header__basketModal.-remove");
    By discardButtonSelector = By.cssSelector("button[data-remove-cart-item]");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void waitUrl(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ExpectedCondition<Boolean> checkPage = arg-> driver.getCurrentUrl().contains(url);
        wait.until(checkPage);
    }
    public void returnHome() {
        click(logoSelector);
    }
    public void openCart() {
        click(cartButtonSelector);
    }
    public void discardItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(discardSelector));
        click(discardSelector);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(discardButtonSelector));
        click(discardButtonSelector);
    }
}
