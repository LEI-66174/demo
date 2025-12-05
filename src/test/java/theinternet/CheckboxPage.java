package theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckboxPage {

    @FindBy(css = "input[type='checkbox']:nth-of-type(1)")
    public WebElement checkbox1;

    @FindBy(css = "input[type='checkbox']:nth-of-type(2)")
    public WebElement checkbox2;

    public CheckboxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
