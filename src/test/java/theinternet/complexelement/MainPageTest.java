package theinternet.complexelement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    Path tempDir;

    @BeforeEach
    public void setUp() {
        try
        {
            tempDir = Files.createTempDirectory("selenium_download_test");
        }
        catch (IOException e)
        {
            throw new RuntimeException("Could not create temporary directory for downloads.", e);
        }

        String downloadPath = tempDir.toAbsolutePath().toString();
        System.out.println("Using temporary directory: " + downloadPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "download.default_directory", downloadPath,
                "download.prompt_for_download", false,
                "download.directory_upgrade", true
        ));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        mainPage = new MainPage( driver);
    }

    static void deleteDirectory(File fileOrDirectory) {
        File[] contents = fileOrDirectory.listFiles();
        if (contents != null) {
            for (File file : contents) {
                deleteDirectory(file);
            }
        }
        fileOrDirectory.delete();
    }

    @AfterEach
    public void tearDown() {
        File tmpDirectory = tempDir.toFile();
        deleteDirectory(tmpDirectory);
        driver.quit();
    }

    @Test
    public void upload() {
        mainPage.linkFileUpload.click();

        File uploadFile = new File(tempDir.toAbsolutePath().toString(), "sample.txt");

        try {
            Files.writeString(uploadFile.toPath(), "sample");
            System.out.println("Successfully created file " + uploadFile.getName() + " at:\n" + uploadFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }

        WebElement uploadFormButton = driver.findElement( By.cssSelector("input[id='file-upload']"));
        uploadFormButton.sendKeys(uploadFile.getAbsolutePath());

        WebElement uploadSubmitButton = driver.findElement( By.id("file-submit"));
        uploadSubmitButton.click();

        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( 10));
        WebElement uploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));

        assertEquals(uploadFile.getName(), uploadedFile.getText());
    }

    @Test
    public void download() {
        mainPage.linkFileDownload.click();

        WebElement randomDownload = driver.findElement( By.cssSelector("a[href$='txt']"));
        randomDownload.click();

        String fullDownloadUrl = randomDownload.getAttribute("href");       //  full download link string
        String[] parts = fullDownloadUrl.split("/");                        //  split it by slashes
        String actualFileName = parts[parts.length - 1];                          //

        int timeoutSeconds = 30;
        int checkIntervalMs = 500;
        long startTime = System.currentTimeMillis();

        String fileName = actualFileName;
        File downloadedFile = new File(tempDir.toAbsolutePath().toString(), fileName);

        // Polling loop
        while (System.currentTimeMillis() - startTime < timeoutSeconds * 1000) {
            if (downloadedFile.exists()) {
                System.out.println("File successfully downloaded!");
                break;
            }
            try {
                Thread.sleep(checkIntervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (downloadedFile.exists()) {
            System.out.println("Test Passed: File downloaded and exists.");
            // Optionally assert the file size > 0
            if (downloadedFile.length() > 0) {
                System.out.println("Test Passed: File is not empty.");
            }
        } else {
            // Fail the test if the file was not found
            throw new AssertionError("Test Failed: File was not downloaded within the timeout.");
        }
    }

}
