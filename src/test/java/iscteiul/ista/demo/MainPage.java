package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class MainPage {

    // Elemento para o botão de aceitar cookies (necessário para o setUp)
    @FindBy(xpath = "//button[@data-test='allow-all' or contains(text(), 'Accept All')]")
    public WebElement acceptCookiesButton;

    // Elemento para o botão que abre o campo de pesquisa (A LUPA)
    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    // 🆕 NOVO ELEMENTO: O campo de input de pesquisa que aparece APÓS o clique na lupa.
    // Usamos um seletor robusto (CSS Selector) que verifica o atributo 'placeholder'
    @FindBy(css = "input[type='text'][placeholder^='Search'], input[data-test='search-input']")
    public WebElement searchFieldInput;

    // Elemento para o botão de submissão de pesquisa na modal
    @FindBy(css = "button[data-test='full-search-button']")
    public WebElement submitSearchButton;

    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    // Corrigido anteriormente para evitar ElementClickInterceptedException
    @FindBy(css = "a[data-test='suggestion-link']")
    public WebElement findYourToolsButton;

    @FindBy(xpath = "//div[@data-test='main-menu-item' and @data-test-marker = 'Developer Tools']")
    public WebElement toolsMenu;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        try {
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
                System.out.println("Cookies aceites.");
            }
        } catch (Exception e) {
            System.out.println("Banner de cookies não encontrado ou já aceite.");
        }
    }
}