package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class ContextPage {
    private static List<String> data = new ArrayList<>();;
    WebDriver driver;
    public ContextPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }
    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }
    public void type(By path, String keyword) {
        find(path).sendKeys(keyword);
    }
    public void click(By locator) {
        find(locator).click();
    }
    public void submit(By locator) {
        find(locator).submit();
    }
    public Boolean checkExist(By locator) {
        return !findAll(locator).isEmpty();
    }
    public String getText(By locator) {
        return find(locator).getText();
    }
    public void moveToElement(WebElement moreContentButton) {
        Actions actions = new Actions(driver);
        actions.moveToElement(moreContentButton).perform();
    }
    public void setData(String s) {
        data.add(s);
    }
    public List getData() {
        return data;
    }
}
