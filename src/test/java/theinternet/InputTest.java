package theinternet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTest {
    private WebDriver driver;
    private InputPage inputPage;

    // Texto de teste
    private final String TEST_VALUE = "12345";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/inputs");

        inputPage = new InputPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testEnterValueAndVerify() {
        inputPage.enterValue(TEST_VALUE);

        String actualValue = inputPage.getCurrentValue();
        assertEquals(TEST_VALUE, actualValue, "O valor digitado no campo de input não está correto.");
    }
}