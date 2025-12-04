package iscteiul.ista.demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        closeCookies(driver);

        mainPage = new MainPage(driver);
    }

    public void closeCookies(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By denyAll = By.cssSelector("button.ch2-btn.ch2-deny-all-btn");
        By banner  = By.cssSelector("div.ch2-container");

        try {
            WebElement denyAllButton = wait.until(ExpectedConditions.elementToBeClickable(denyAll));
            denyAllButton.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(banner));
        } catch (TimeoutException ignored) {
            // banner didn't appear – nothing to do
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("[data-test-id='search-input']"));
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test-id='search-input']"));
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage.toolsMenu.click();

        wait.until(d -> d.findElements(By.cssSelector("div[data-test='main-submenu']")).stream()
                .anyMatch(WebElement::isDisplayed));

        WebElement visible = driver.findElements(By.cssSelector("div[data-test='main-submenu']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow();

        assertTrue(visible.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
