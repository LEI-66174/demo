package theinternet.dynamiccontent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(id = "content")
    public WebElement table;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
