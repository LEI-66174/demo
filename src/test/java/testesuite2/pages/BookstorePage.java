package testesuite2.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

/**
 * Page Object para a página Bookstore (testsuite 2)
 * Funcionalidade: Adicionar uma categoria
 * URL: https://vaadin-bookstore-example.demo.vaadin.com/
 */
public class BookstorePage {

    public BookstorePage() {
        PageFactory.initElements((SearchContext) webdriver(), this);
    }

    // Botão "Categories"
    @FindBy(xpath = "//vaadin-button[contains(., 'Categories')]")
    public SelenideElement categoriesButton;

    // Botão "Add category"
    @FindBy(xpath = "//vaadin-button[contains(., 'Add category')]")
    public SelenideElement addCategoryButton;

    // Campo do nome da categoria
    @FindBy(xpath = "//vaadin-text-field[@label='Category name']/input")
    public SelenideElement categoryNameField;

    // Botão "Save"
    @FindBy(xpath = "//vaadin-button[contains(., 'Save')]")
    public SelenideElement saveButton;

    // Lista das categorias (grid)
    @FindBy(tagName = "vaadin-grid")
    public SelenideElement categoriesGrid;


    /**
     * Abre a página inicial da Bookstore.
     */
    public BookstorePage openPage(String url) {
        open(url);
        return this;
    }

    /**
     * Clica no botão "Categories".
     */
    public BookstorePage clickCategories() {
        categoriesButton.shouldBe(visible).click();
        return this;
    }

    /**
     * Clica no botão "Add category".
     */
    public BookstorePage clickAddCategory() {
        addCategoryButton.shouldBe(visible).click();
        return this;
    }

    /**
     * Introduz o nome da categoria.
     */
    public BookstorePage enterCategoryName(String name) {
        categoryNameField.shouldBe(visible).setValue(name);
        return this;
    }

    /**
     * Clica no botão "Save".
     */
    public BookstorePage clickSave() {
        saveButton.shouldBe(visible).click();
        return this;
    }

    /**
     * Verifica se a categoria foi adicionada ao grid.
     */
    public boolean isCategoryPresent(String categoryName) {
        return categoriesGrid
                .$x(".//vaadin-grid-cell-content[contains(text(), '" + categoryName + "')]")
                .shouldBe(visible)
                .exists();
    }
}
