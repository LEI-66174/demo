package theinternet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorizontalSliderTest {
    private WebDriver driver;
    private HorizontalSliderPage sliderPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/horizontal_slider");

        sliderPage = new HorizontalSliderPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testMoveSliderTo4() {
        sliderPage.moveSliderTo(4.0);

        String displayed = sliderPage.valueLabel.getText();

        assertEquals("4", displayed, "O slider não se moveu para o valor 4.0 corretamente.");
    }

    @Test
    public void testMoveSliderTo2Point5() {
        sliderPage.moveSliderTo(2.5);

        String displayed = sliderPage.valueLabel.getText();

        assertEquals("2.5", displayed, "O slider não se moveu para o valor 2.5 corretamente.");
    }
}