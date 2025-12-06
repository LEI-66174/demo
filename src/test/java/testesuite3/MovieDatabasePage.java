package testesuite3;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = https://vaadin-database-example.demo.vaadin.com/

public class MovieDatabasePage
{
    public SelenideElement filterField = $("vaadin-grid-cell-content");
    private SelenideElement grid = $("vaadin-grid");

    /**
     * Localiza uma célula que contém exatamente o título fornecido.
     * @param expectedTitle O título (variável String) a procurar.
     * @return O SelenideElement da célula.
     */
    public SelenideElement findCellByText(String expectedTitle) {
        // Usar contains(., ...) em vez de text()=
        String xpathSelector = "//vaadin-grid-cell-content[contains(text(), '" + expectedTitle + "')]";
        // $x( ) tenta localizar o elemento imediatamente.
        return $x(xpathSelector).shouldBe(visible);
    }

    public SelenideElement findCellByExactTextCSS(String expectedTitle) {

        String cssSelector = "vaadin-grid-cell-content";
        //  Seleciona TODOS os elementos vaadin-grid-cell-content (coleção $$)
        //  Filtra a coleção: Encontra o PRIMEIRO elemento que tem o texto visível 'expectedTitle'
        //  Esta função de filtragem é mais eficaz para penetrar no texto renderizado em Shadow DOM
        SelenideElement cellElement = $$(cssSelector)
                .find(text(expectedTitle))
                .shouldBe(visible);

        return cellElement;
    }

    public record MovieRow(SelenideElement title,
                           SelenideElement year,
                           SelenideElement director,
                           String link) {}

    public MovieRow findInfoAboutMovie(String expectedTitle) {
        SelenideElement cellElement = findCellByText(expectedTitle);
        SelenideElement yearRelease = cellElement.$x("following-sibling::vaadin-grid-cell-content[1]");
        SelenideElement Director = cellElement.$x("following-sibling::vaadin-grid-cell-content[2]");
        String IMBDLink = cellElement.$x("following-sibling::vaadin-grid-cell-content[3]").$("a").getAttribute( "href" );

        return new MovieRow(cellElement, yearRelease, Director, IMBDLink);
    }

    public String findLinkByText(String expectedTitle) {
        SelenideElement cellElement = findCellByText(expectedTitle);
//        System.out.println("Found movie: " + cellElement.getText());
//       SelenideElement thirdSibling = cellElement.$x("following::vaadin-grid-cell-content[3]");
        SelenideElement thirdSibling = cellElement.$x("following-sibling::vaadin-grid-cell-content[3]");
//        System.out.println("Found link element: " + thirdSibling.getText());

        // seletor relativo CSS
        SelenideElement link = thirdSibling.$("a");
//        System.out.println(link.getText());
        String href = link.getAttribute("href");

        return href;
    }
}
