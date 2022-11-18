import log.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pages.*;

public class test_process extends BaseTest {
    HomePage homePage;
    Logger log = new Logger();
    @Test
    @Order(1)
    public void searchPageContent() {
        homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        // Wait url and check
        homePage.waitUrl(url);
        Assertions.assertEquals(url, driver.getCurrentUrl(), "Wrong url!");
        searchPage.searchKeyword("ceket");
        searchPage.scrollDownAndClick();
    }
    @Test
    @Order(2)
    public void secondPageContent() {
        SecondPage secondPage = new SecondPage(driver);
        // Wait url and check
        homePage.waitUrl("page=2");
        // Hovers on first discounted item on second page.
        Assertions.assertTrue(driver.getCurrentUrl().contains("page=2"), "You are not on second page.");
        secondPage.addToCart();
    }
    @Test
    @Order(3)
    public void cartPageContent() {
        CartPage cartPage = new CartPage(driver);
        // Wait url and check
        homePage.waitUrl("/cart");
        Assertions.assertTrue(cartPage.checkDataExist(), "An error occured while fetching size and price data.");
        if(cartPage.checkValue()) {
            log.info("Size and prices are same.");
        } else {
            log.error("Size or price is different!");
        }
        if(cartPage.comparePrice()) {
            log.info("Current price is smaller than old price.");
        } else {
            log.error("Current price is not smaller than old one.");
        }
        cartPage.checkout();
    }
    @Test
    @Order(4)
    public void loginPageContent() {
        LoginPage loginPage = new LoginPage(driver);
        // Wait url and check
        homePage.waitUrl("/login");
        Assertions.assertTrue(loginPage.checkLoginButton(), "Login button does not exist.");
        loginPage.signIn();
    }
    @Test
    @Order(5)
    public void homePageContent() {
        homePage.returnHome();
        homePage.openCart();
        homePage.discardItem();
    }
}
