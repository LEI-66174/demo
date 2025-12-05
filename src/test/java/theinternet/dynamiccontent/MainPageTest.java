package theinternet.dynamiccontent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private WebDriver driver;
    private theinternet.dynamiccontent.MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_content");

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    //test if main table is loaded
    @Test
    public void isContainerLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement container = wait.until(ExpectedConditions.visibilityOf(mainPage.table));

        assertTrue(container.isDisplayed());
    }

    //test if all 3 images appear independently of what images they are
    @Test
    public void areImagesLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By imgsBy = By.cssSelector("div.large-2.columns img");

        // Wait until there are 3 images AND all 3 are displayed
        List<WebElement> imgs = wait.until(d -> {
            List<WebElement> list = d.findElements(imgsBy);
            if (list.size() != 3) return null;
            return list.stream().allMatch(WebElement::isDisplayed) ? list : null;
        });

        Assertions.assertNotNull(imgs);
        assertEquals(3, imgs.size());

    }
}
