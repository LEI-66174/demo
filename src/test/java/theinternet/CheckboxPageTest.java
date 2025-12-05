package theinternet;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckboxPageTest {
    private WebDriver driver;
    private CheckboxPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        mainPage = new CheckboxPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Check if checkbox works")
    public void checkCheckbox() {
        mainPage.checkbox1.click();  // clica a primeira
        mainPage.checkbox2.click();  // desmarca a segund

        assertTrue(mainPage.checkbox1.isSelected());
        assertFalse(mainPage.checkbox2.isSelected());
    }

}