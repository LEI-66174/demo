package theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InputPage {

    @FindBy(tagName = "input")
    public WebElement inputField;

    public InputPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Limpa o campo e envia um valor.
     * @param value O texto (ou número) a ser digitado.
     */
    public void enterValue(String value) {
        inputField.clear();
        inputField.sendKeys(value);
    }

    /**
     * Obtém o valor atual do campo de input.
     * Para inputs, usamos getAttribute("value") e NÃO getText().
     * @return O valor (texto) contido no campo.
     */
    public String getCurrentValue() {
        return inputField.getAttribute("value");
    }
}