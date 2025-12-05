package theinternet.dynamicloading;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private WebDriver driver;
    private theinternet.dynamicloading.MainPage mainPage;


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        mainPage = new theinternet.dynamicloading.MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    //test if main table is loaded
    @Test
    public void isStartButtonLoadedAndClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement button = wait.until(ExpectedConditions.visibilityOf(mainPage.startButton));

        assertTrue(button.isDisplayed());
    }

    @Test
    public void isFinalTestRenderedAfterLoading() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOf(mainPage.startButton));

        button.click();

        WebElement objective = wait.until(ExpectedConditions.visibilityOf(mainPage.loadedObjective));

        assertTrue(objective.isDisplayed());
    }
}
