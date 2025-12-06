package iscteiul.ista.demo.testSuite4.bookstore.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {

    // --- helpers de localização ---

    private SelenideElement productForm() {
        // o formulário do lado direito
        return $("vaadin-vertical-layout.product-form-content")
                .shouldBe(Condition.visible);
    }

    /**
     * Helper: recebe o host (vaadin-text-field / vaadin-integer-field)
     * e devolve o <input part="value"> lá dentro do shadowRoot.
     */
    private static SelenideElement inputInside(SelenideElement host) {
        SearchContext shadow = host.toWebElement().getShadowRoot();
        WebElement input = shadow.findElement(By.cssSelector("input[part='value']"));
        return $(input);
    }

    // --- acções da página ---

    /**
     * Escreve texto no filtro "Filter name, availability or category"
     * por cima da grelha.
     */
    public InventoryPage filterBy(String text) {
        // Depois do login, antes de abrir o formulário, o ÚNICO vaadin-text-field
        // na página é o do filtro da grelha.
        SelenideElement filterHost = $$("vaadin-text-field")
                .first()
                .shouldBe(Condition.visible);

        inputInside(filterHost).setValue(text);
        return this;
    }

    public InventoryPage openNewBookForm() {
        // botão "New product" no topo direito
        $$("vaadin-button").findBy(Condition.text("New product"))
                .shouldBe(Condition.visible)
                .click();

        // espera que o formulário apareça
        productForm();
        return this;
    }

    public InventoryPage fillBookForm(String title, String price) {
        SelenideElement form = productForm();

        // 1.º vaadin-text-field = Product name
        SelenideElement nameFieldHost = form.$$("vaadin-text-field").get(0);
        inputInside(nameFieldHost).setValue(title);

        // 2.º vaadin-text-field = Price
        SelenideElement priceFieldHost = form.$$("vaadin-text-field").get(1);
        inputInside(priceFieldHost).setValue(price);

        // campo "In stock" é um vaadin-integer-field
        SelenideElement stockFieldHost = form.$("vaadin-integer-field");
        inputInside(stockFieldHost).setValue("1");

        return this;
    }

    public InventoryPage save() {
        $$("vaadin-button").findBy(Condition.text("Save"))
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    /**
     * Assume que a grelha já está filtrada pelo título.
     * Procura o título nas células actualmente visíveis.
     */
    public boolean gridContainsBook(String title) {
        return $$("vaadin-grid-cell-content")
                .findBy(Condition.text(title))
                .shouldBe(Condition.visible)   // espera até ficar visível
                .exists();
    }
}
