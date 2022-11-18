package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends ContextPage {
    By searcBox = By.id("search");
    By moreContent = By.cssSelector(".productList__moreContent>button");
    By cookieSelector = By.cssSelector("#onetrust-accept-btn-handler");
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    public void searchKeyword(String keyword) {
        if(checkExist(cookieSelector)) {
            find(cookieSelector).click();
        }
        type(searcBox, keyword);
        submit(searcBox);
    }
    public void scrollDownAndClick() {
        WebElement moreContentButton = find(moreContent);
        moveToElement(moreContentButton);
        moreContentButton.click();
    }
}
