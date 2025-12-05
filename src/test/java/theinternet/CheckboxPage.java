package theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckboxPage {

    @FindBy( css = "input[type='checkbox' ")
    public WebElement checkbox1;

    @FindBy( css = "input[type='checkbox' checked='']" )
    public WebElement checkbox2;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


}
