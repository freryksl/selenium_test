package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends ContextPage {
    By checkoutButton = By.cssSelector("button[data-goto-checkout]");
    By sizeSelector = By.cssSelector(".cartItem__attr.-size>.cartItem__attrValue");
    By priceSelector = By.cssSelector(".cartItem__prices>.-sale");
    By oldPriceSelector = By.cssSelector(".cartItem__prices>.-old");
    By pricesParent = By.cssSelector(".cartItem__prices");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    private float formatString(String s) {
        String newString = s.replaceAll("[TL]", "").replaceAll("[.,]","").trim();
        return Float.parseFloat(newString);
    }
    public Boolean checkDataExist() {
        // Checking size and price elements exist or not.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sizeSelector));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pricesParent));
        if(checkExist(pricesParent) && checkExist(sizeSelector)) {
            return true;
        }
        return false;
    }
    public Boolean checkValue() {
        // Checking size and price values are same with on previous page or not.
        List<String> data = getData();
        if(formatString(getText(priceSelector)) == formatString(data.get(0)) &&
                getText(sizeSelector).equals(data.get(1))) {
            return true;
        }
        return false;
    }
    public Boolean comparePrice() {
        // Compare old and current prices.
        if(formatString(getText(priceSelector)) < formatString(getText(oldPriceSelector))) {
            return true;
        }
        return false;
    }
    public void checkout() {
        // Redirect to checkout page.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        click(checkoutButton);
    }
}
