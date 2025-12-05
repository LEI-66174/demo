package theinternet.complexelement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://the-internet.herokuapp.com/
public class MainPage {

    @FindBy( css = "a[href='/upload']" )
    public WebElement linkFileUpload;

    @FindBy( css = "a[href='/download']" )
    public WebElement linkFileDownload;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
