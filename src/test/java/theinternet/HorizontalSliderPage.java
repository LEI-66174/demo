package theinternet;

import org.openqa.selenium.Keys; // Importação necessária
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HorizontalSliderPage {
    @FindBy(css = "input[type='range']")
    public WebElement slider;

    @FindBy(id = "range")
    public WebElement valueLabel;

    public HorizontalSliderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Método para mover o slider para um valor alvo
    public void moveSliderTo(double targetValue) {
        double current = Double.parseDouble(valueLabel.getText());

        while (current < targetValue) {
            slider.sendKeys(Keys.ARROW_RIGHT);
            current = Double.parseDouble(valueLabel.getText());
        }

        while (current > targetValue) {
            slider.sendKeys(Keys.ARROW_LEFT);
            current = Double.parseDouble(valueLabel.getText());
        }

    }
}