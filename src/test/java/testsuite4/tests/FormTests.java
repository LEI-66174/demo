package testsuite4.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testsuite4.pages.Form;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class FormTests {
    private Form formPage;


    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10_000; // Selenide waits automatically up to this
        open("https://vaadin-form-example.demo.vaadin.com/");
        formPage = new Form();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    public void canJoinTheCommunity() {
        formPage.getFirstNameInput().shouldBe(visible);
        formPage.getLastNameInput().shouldBe(visible);
        formPage.getUserHandleInput().shouldBe(visible);
        formPage.getPasswordInput().shouldBe(visible);
        formPage.getPasswordConfirmationInput().shouldBe(visible);
        formPage.getAllowMarketing().shouldBe(visible);
        formPage.getJoinCommunityButton().shouldBe(visible);

        formPage.getFirstNameInput().setValue("Pedro");
        formPage.getFirstNameInput().shouldHave(value("Pedro"));

        formPage.getLastNameInput().setValue("Veloso");
        formPage.getLastNameInput().shouldHave(value("Veloso"));

        formPage.getUserHandleInput().setValue("Peter");
        formPage.getUserHandleInput().shouldHave(value("Peter"));

        formPage.getPasswordInput().setValue("12345678");
        formPage.getPasswordInput().shouldHave(value("12345678"));


        formPage.getPasswordConfirmationInput().setValue("12345678");
        formPage.getPasswordConfirmationInput().shouldHave(value("12345678"));

        formPage.getAllowMarketing().click();
        formPage.getAllowMarketing().shouldHave(attribute("aria-checked", "true"));

        formPage.getEmailInput().shouldBe(visible);
        formPage.getEmailInput().setValue("pedro@testes.com");
        formPage.getEmailInput().shouldHave(value("pedro@testes.com"));

        formPage.getJoinCommunityButton().click();

        if(formPage.getSaveFailedMessage().exists()) {
            formPage.getSaveFailedMessage().shouldBe(visible);
            formPage.getJoinCommunityButton().click();
        }
    }
}
