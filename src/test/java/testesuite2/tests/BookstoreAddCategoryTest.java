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
 */
public class BookstoreAddCategoryTest {

    private BookstorePage bookstorePage;

    @BeforeEach
    public void setUp() {
        // Configurações Selenide
        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
        Configuration.browserSize = "1920x1080";
        Configuration.clickViaJs = true;

        bookstorePage = new BookstorePage()
                .openPage("https://vaadin-bookstore-example.demo.vaadin.com/")
                .loginAsAdmin();
    }

    @AfterEach
    public void tearDown() {
        // Garante o fechamento do navegador após a conclusão do teste.
        closeWebDriver();
    }

    @Test
    void addCategoryTest() {
        // Nome da categoria como "Teste" + timestamp
        String newCategoryName = "Teste " + System.currentTimeMillis();

        bookstorePage
                .clickAdmin()
                .clickAddNewCategory()
                // Este método agora garante que o campo está editável, digita e salva (via TAB)
                .enterCategoryName(newCategoryName);

        // Verificação
        boolean exists = bookstorePage.isCategoryPresent(newCategoryName);
        assertTrue(exists, "A categoria '" + newCategoryName + "' não foi encontrada na lista de categorias.");
    }
}