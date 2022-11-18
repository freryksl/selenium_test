package pages;

import com.opencsv.CSVReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPage extends ContextPage {
    By emailInput = By.cssSelector("input[name='Email']");
    By passwdInput = By.cssSelector("input[name='Password']");
    By loginButtonSelector = By.cssSelector("form#login");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public Boolean checkLoginButton() {
        return checkExist(loginButtonSelector);
    }
    public void signIn() {
        try {
            String csvFile = new File("login.csv").getAbsolutePath();
            CSVReader readCSV = new CSVReader(new FileReader(csvFile));
            String[] nextLine;
            List<List<String>> lines = new ArrayList();
            while ((nextLine = readCSV.readNext())!= null) {
                String[] cells = nextLine[0].split(";");
                lines.add(Arrays.asList(cells));
            }
            find(emailInput).sendKeys(lines.get(0).get(0).toString());
            find(passwdInput).sendKeys(lines.get(0).get(1).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
