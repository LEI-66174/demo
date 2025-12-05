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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        wait = new WebDriverWait( driver, Duration.ofSeconds(10));

        mainPage = new MainPage(driver);

        try {
            WebElement denyAllButton = wait.until(
                    ExpectedConditions.elementToBeClickable(mainPage.buttondenyall)
            );
            denyAllButton.click();
            Thread.sleep(500);
        } catch (Exception ignored) {
            System.out.println("Cookie not found.");
        }

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName( "Search \"Selenium\" on JetBrains.com" )
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(
                mainPage.inputSearch
        ));

        searchField.sendKeys("Selenium");
        searchField.click();
        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        //  Check the search results page field element
        WebElement searchResultPageField = driver.findElement(By.cssSelector("input[data-test$='inner']"));
        assertEquals("Selenium", searchResultPageField.getAttribute("value"));
    }

    @Test
    @DisplayName( "Show \"Tools\" menu" )
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        WebElement menuPopupElement = wait.until(
                ExpectedConditions.visibilityOf(mainPage.findYourToolsButton));

        assertTrue(menuPopupElement.isDisplayed());
    }

    @Test
    @DisplayName( "Check JetBrains Tools catalog" )
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}