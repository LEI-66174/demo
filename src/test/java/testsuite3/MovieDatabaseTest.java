package testsuite3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieDatabaseTest
{
    private MovieDatabasePage mainPage;

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        mainPage = new MovieDatabasePage();
        open("https://vaadin-database-example.demo.vaadin.com/");
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    void checkMovieInfo()
    {
        String movieName = "Law Abiding Citizen";
        MovieDatabasePage.MovieRow row = mainPage.findInfoAboutMovie(movieName);

        assertEquals("Law Abiding Citizen", row.title().getText(), "Nao encontrou o filme correto");
        assertEquals("2009", row.year().getText(), "O ano do filme não está correto.");
        assertEquals("F. Gardy Gray", row.director().getText(), "O director encontrado não está correto.");
        assertEquals("https://www.imdb.com/title/tt1197624/", row.link(), "Link errado.");

        row.title().shouldBe(Condition.text("Law Abiding Citizen"));
        row.year().shouldBe(Condition.text("2009"));
        row.director().shouldBe(Condition.text("F. Gardy Gray"));
    }
}
