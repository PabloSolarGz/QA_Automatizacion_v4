package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browsers {
    private static WebDriver driver;

    public static WebDriver getDriver(String navegador) {
        if (driver == null) {
            if ("chrome".equalsIgnoreCase(navegador)) {
                iniciarChrome();
            }
        }
        return driver;
    }

    private static void iniciarChrome() {
        try {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications", "--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.err.println("Error al iniciar Chrome: " + e.getMessage());
        }
    }

    public static void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
