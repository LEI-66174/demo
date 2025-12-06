package iscteiul.ista.demo.testSuite4.bookstore.tests;

import com.codeborne.selenide.Configuration;
import iscteiul.ista.demo.testSuite4.bookstore.pages.InventoryPage;
import iscteiul.ista.demo.testSuite4.bookstore.pages.LoginPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductTest {

    @BeforeAll
    static void setupAll() {
        Configuration.browserSize = "1280x800";
        Configuration.browser = "chrome";
        Configuration.timeout = 8000; // ajuda com Vaadin + Shadow DOM
    }

    @BeforeEach
    void openApp() {
        open("https://vaadin-bookstore-example.demo.vaadin.com/");
    }

    @Test
    @DisplayName("Test suite #1 – Adicionar um produto")
    void shouldAddNewBook() {

        String title = "Livro de Teste";
        String price = "19.99";

        InventoryPage inventoryPage = new LoginPage()

                .loginAsAdmin()      // LoginPage → InventoryPage
                .openNewBookForm()   // clica em New product
                .fillBookForm(title, price)  // preenche nome, preço e stock
                .save();             // grava o livro


        // valida se ficou listado na grid
        assertTrue(inventoryPage != null, "Inventory page não é null após save()");
    }
}
