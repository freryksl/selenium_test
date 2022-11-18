package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SecondPage extends ContextPage {
    By secondPageContent =  By.cssSelector("div[data-page='2']");
    By discountedItems = By.className("product__discountPercent");
    By checkoutPage = By.cssSelector(".header__basketModal.-checkout");
    By enabledSizes = By.cssSelector("div.product__size>label:not(.-disabled)");
    By discountedItemParent = By.xpath("../../..");
    By actualPrice = By.cssSelector(".product__price.-actual");
    public SecondPage(WebDriver driver) {
        super(driver);
    }
    private WebElement hoverDiscounted(WebDriverWait wait) {
        // Hover first discounted item
        WebElement secondPageItems = find(secondPageContent);
        List<WebElement> items = secondPageItems.findElements(discountedItems);
        wait.until(ExpectedConditions.visibilityOfElementLocated(discountedItems));
        WebElement selectedItem = items.get(0).findElement(discountedItemParent);
        setData(selectedItem.findElement(actualPrice).getText());
        moveToElement(selectedItem);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return selectedItem;
    }
    private void selectDiscounted(WebDriverWait wait) {
        // Select random size of product
        List<WebElement> productSizes = hoverDiscounted(wait).findElements(enabledSizes);
        WebElement selectedRandomSize = productSizes.get(new Random().nextInt(productSizes.size()));
        wait.until(ExpectedConditions.elementToBeClickable(selectedRandomSize));
        setData(selectedRandomSize.getText());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        selectedRandomSize.click();
    }
    public void addToCart() {
        // Add selected size to cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        selectDiscounted(wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPage));
        click(checkoutPage);
    }
}
