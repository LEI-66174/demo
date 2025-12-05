package theinternet.dynamicloading;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(css = "#start > button")
    public WebElement startButton;

    @FindBy(id = "finish")
    public WebElement loadedObjective;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
