package testsuite4_113239.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// page_url = https://vaadin-form-example.demo.vaadin.com/
public class Form {
    private static SelenideElement inputInside(SelenideElement host) {
        SearchContext shadow = host.toWebElement().getShadowRoot();
        WebElement input = shadow.findElement(By.cssSelector("input[part='value']"));
        return $(input);
    }

    private final SelenideElement firstNameInput =
            inputInside($$("vaadin-text-field").get(0));

    private final SelenideElement lastNameInput =
            inputInside($$("vaadin-text-field").get(1));

    private final SelenideElement userHandleInput =
            inputInside($$("vaadin-text-field").get(2));

    private final SelenideElement passwordInput =
            inputInside($$("vaadin-password-field").get(0));

    private final SelenideElement passwordConfirmationInput =
            inputInside($$("vaadin-password-field").get(1));


    private final SelenideElement allowMarketing =
            $$("vaadin-checkbox").findBy(text("Allow Marketing?"));

    private final SelenideElement joinCommunityButton =
            $$("vaadin-button").findBy(text("Join the community"));

    public SelenideElement getFirstNameInput() {
        return firstNameInput;
    }

    public SelenideElement getLastNameInput() {
        return lastNameInput;
    }

    public SelenideElement getUserHandleInput() {
        return userHandleInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getPasswordConfirmationInput() {
        return passwordConfirmationInput;
    }

    public SelenideElement getAllowMarketing() {
        return allowMarketing;
    }

    public SelenideElement getJoinCommunityButton() {
        return joinCommunityButton;
    }

    public SelenideElement getEmailInput() {
        SelenideElement emailHost = $$("vaadin-email-field").first().shouldBe(visible);
        return inputInside(emailHost).shouldBe(visible);
    }

    public SelenideElement getSaveFailedMessage() {
        return $$("span").findBy(text("Saving the data failed, please try again"));
    }
}