package theinternet;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class DropdownTest {
    private WebDriver driver;
    private Dropdown mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");

        mainPage = new Dropdown(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Check if Option 1 and 2 is selected")
    public void checkDropdown() {

        // Criar objeto Select usando o elemento <select>
        Select select = new Select(mainPage.dropdown);

        // Selecionar Option 1
        select.selectByVisibleText("Option 1");

        // Verificar
        assertEquals("Option 1", select.getFirstSelectedOption().getText());

        select.selectByValue("2");
        assertEquals("2", select.getFirstSelectedOption().getAttribute("value"));

    }
}
