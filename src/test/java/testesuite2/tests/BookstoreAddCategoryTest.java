package testesuite2.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testesuite2.pages.BookstorePage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Teste da funcionalidade "Adicionar categoria"
 * TestSuite 2 – Vaadin Bookstore
 */
public class BookstoreAddCategoryTest {

    private BookstorePage bookstorePage;

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;

        bookstorePage = new BookstorePage()
                .openPage("https://vaadin-bookstore-example.demo.vaadin.com/");
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    void addCategory() {
        String newCategoryName = "Test Category";

        bookstorePage
                .clickCategories()
                .clickAddCategory()
                .enterCategoryName(newCategoryName)
                .clickSave();

        // Verificação
        boolean exists = bookstorePage.isCategoryPresent(newCategoryName);

        assertTrue(exists, "A categoria não foi encontrada no grid.");
    }
}
