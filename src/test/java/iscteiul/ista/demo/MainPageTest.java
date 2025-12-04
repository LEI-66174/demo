package iscteiul.ista.demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach

    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        // --- CÓDIGO PARA ACEITAR COOKIES ---

        // Localizador encontrado na imagem
        By acceptCookiesButtonLocator = By.cssSelector("button.ch2-allow-all-btn");

        try {
            // Usa Espera Explícita para garantir que o banner de cookies apareça e seja clicável.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));

            // Clica no botão "Accept All"
            driver.findElement(acceptCookiesButtonLocator).click();

            System.out.println("Cookies aceitos (Accept All) com sucesso.");

            // Adicione uma pequena pausa (Thread.sleep) ou espera implícita se necessário,
            // para garantir que o banner desapareça completamente antes de prosseguir.
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        } catch (Exception e) {
            System.out.println("O banner de cookies não foi encontrado ou não era clicável. Prosseguindo... " + e.getMessage());
            // Se a exceção ocorrer (por exemplo, o banner já foi dispensado em visitas anteriores),
            // o teste prossegue sem falhar.
        }

        // --- FIM DO CÓDIGO DE COOKIES ---

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click(); // Abre a caixa de pesquisa (pop-up)

        // **CORREÇÃO:** Espera Explícita para o campo de input da pesquisa aparecer no pop-up
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // O campo de input é o primeiro a ser encontrado e preenchido
        WebElement searchField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='search-input']"))
        );
        searchField.sendKeys("Selenium");

        // O botão de submissão da pesquisa (geralmente dentro do pop-up)
        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        // Na página de resultados da pesquisa, verifica o valor do input
        WebElement searchPageField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-test='search-input']"))
        );
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click(); // Clica para abrir o menu suspenso

        // **CORREÇÃO:** Espera Explícita para o menu pop-up ficar VISÍVEL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement menuPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-test='main-submenu']"))
        );

        // Asserção de que o pop-up está visível
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        // 1. Clica no menu principal para abrir o submenu
        mainPage.seeDeveloperToolsButton.click();

        // **CORREÇÃO:** Espera Explícita para garantir que o botão 'Find your tools' esteja clicável
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement findYourTools = wait.until(
                ExpectedConditions.elementToBeClickable(mainPage.findYourToolsButton)
        );

        // **CORREÇÃO:** Usar a classe Actions para clicar e evitar a interceção (mais robusto)
        Actions actions = new Actions(driver);
        actions.click(findYourTools).perform();

        // 3. Espera o carregamento da página de produtos (Verifica a presença do ID)
        WebElement productsList = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("products-page"))
        );

        // Asserções
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
