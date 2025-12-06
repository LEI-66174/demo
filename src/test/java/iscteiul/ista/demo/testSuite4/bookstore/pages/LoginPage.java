package iscteiul.ista.demo.testSuite4.bookstore.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;

public class LoginPage {

    @FindBy(id = "vaadinLoginUsername")
    private SelenideElement usernameField;   // <vaadin-text-field ...>

    @FindBy(id = "vaadinLoginPassword")
    private SelenideElement passwordField;   // <vaadin-password-field ...>

    // NÃO usamos mais o id="button" por causa do <g id="button">
    // Vamos clicar no vaadin-button pelo texto "Log in"
    public LoginPage() {
        Selenide.page(this);
    }

    public InventoryPage login(String username, String password) {
        // 1) Escrever no <input> interno dos componentes Vaadin
        usernameField.shouldBe(visible).$("input").setValue(username);
        passwordField.shouldBe(visible).$("input").setValue(password);

        // 2) Clicar no botão "Log in" (vaadin-button host, não o <button> interno)
        $$("vaadin-button").findBy(text("Log in")).click();

        return new InventoryPage();
    }

    public InventoryPage loginAsAdmin() {
        return login("admin", "admin");
    }
}
