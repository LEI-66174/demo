package testesuite2.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import java.time.Duration;

public class BookstorePage {

    // --- Elementos de Login ---
    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");

    // --- Elementos de Navegação e Categoria ---
    private final SelenideElement adminLink = $(byText("Admin"));
    private final SelenideElement addCategoryLink = $(byText("Add New Category"));

    // CORREÇÃO ESSENCIAL: Selecionar o ÚLTIMO vaadin-text-field, que é o campo recém-criado
    // na parte inferior da lista de categorias.
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

        // Espera crucial: Garante que o campo foi criado e está visível.
        categoryNameInput.shouldBe(visible, Duration.ofSeconds(5));

        return this;
    }

    /**
     * Insere o nome da categoria, garantindo que o campo esteja no estado editável,
     * e simula o "deselecionar" (blur) com a tecla TAB para forçar o salvamento no Vaadin.
     */
    public BookstorePage enterCategoryName(String name) {
        // CORREÇÃO CRÍTICA: Espera que o campo esteja visível, editável E habilitado,
        // resolvendo o InvalidElementStateException.
        categoryNameInput.shouldBe(visible, editable, enabled).setValue(name);

        // Simula o 'deselecionar' (TAB) para sair do campo, o que salva a nova categoria.
        categoryNameInput.pressTab();

        // Pequena espera para o Vaadin finalizar o processo de salvamento.
        sleep(1000);

        return this;
    }

    public boolean isCategoryPresent(String name) {
        return $(byText(name)).shouldBe(visible).exists();
    }
}