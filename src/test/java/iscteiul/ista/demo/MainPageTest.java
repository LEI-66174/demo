package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Inicializa o WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        // 🟢 CORREÇÃO: Usa XPath para procurar pelo atributo 'placeholder' ou 'data-test' como fallback.
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search'] | //input[@data-test='search-input']")
        ));

        searchField.sendKeys("Selenium");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-test='full-search-button']")
        ));
        submitButton.click();

        // No search results page
        WebElement searchPageField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-test='search-input'], input[type='search']")
        ));

        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.toolsMenu)).click();

        // Espera pelo submenu ficar visível
        WebElement menuPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[data-test='main-submenu']")
        ));

        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        // Usa elementToBeClickable para garantir que o elemento não está coberto
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.seeDeveloperToolsButton)).click();

        // Assume-se que o findYourToolsButton foi corrigido no MainPage.java para evitar interceção
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.findYourToolsButton)).click();

        WebElement productsList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("products-page")
        ));

        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}