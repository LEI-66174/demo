package iscteiul.ista.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/

public class MainPage {
    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    @FindBy(xpath = "//*[@data-test='suggestion-link']")
    public WebElement findYourToolsButton;

    @FindBy(xpath = "//div[@data-test='main-menu-item' and @data-test-marker = 'Developer Tools']/button")
    public WebElement toolsMenu;

    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    @FindBy( css = "button[class='ch2-btn ch2-deny-all-btn ch2-btn-primary']" )
    public WebElement buttondenyall;

    @FindBy( css = "input[data-test*='inner']" )
    public WebElement inputSearch;

    @FindBy( css = "div[data-test='main-submenu']" )
    public WebElement menuPopup;

    @FindBy( css = "html > body > div:nth-of-type(2) > div:nth-of-type(1) > div:nth-of-type(3) > header > div > div > div:nth-of-type(2) > div:nth-of-type(1) > div > nav > div:nth-of-type(2) > div > div" )
    public WebElement divMainSubmenu;


    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
