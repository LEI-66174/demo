package testsuite2.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import java.time.Duration;

public class BookstorePage {

    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");

    private final SelenideElement adminLink = $(byText("Admin"));
    private final SelenideElement addCategoryLink = $(byText("Add New Category"));

    private final SelenideElement categoryNameInput = $("vaadin-text-field:last-of-type");

    public BookstorePage() {
    }

    public BookstorePage openPage(String url) {
        open(url);
        return this;
    }

    public BookstorePage loginAsAdmin() {
        if (usernameInput.exists()) {
            usernameInput.shouldBe(visible).setValue("admin");
            passwordInput.shouldBe(visible).setValue("admin").pressEnter();
        }
        return this;
    }

    public BookstorePage clickAdmin() {
        adminLink.shouldBe(visible).click();
        return this;
    }

    public BookstorePage clickAddNewCategory() {
        addCategoryLink.shouldBe(visible, interactable).click();

        categoryNameInput.shouldBe(visible, Duration.ofSeconds(5));

        return this;
    }

    /**
     * Insere o nome da categoria, garantindo que o campo esteja no estado editável,
     * e simula o "deselecionar" (blur) com a tecla TAB para forçar o salvamento no Vaadin.
     */
    public BookstorePage enterCategoryName(String name) {

        categoryNameInput.shouldBe(visible, editable, enabled).setValue(name);

        categoryNameInput.pressTab();

        sleep(1000);

        return this;
    }

    public boolean isCategoryPresent(String name) {
        return $(byText(name)).shouldBe(visible).exists();
    }
}
