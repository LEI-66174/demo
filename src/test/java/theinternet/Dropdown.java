package theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dropdown {

    @FindBy(css = "select[id='dropdown']")
    public WebElement dropdown;


    public Dropdown(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
