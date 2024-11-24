package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.InputStream;
import java.util.Properties;

public class Browsers {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
                ChromeOptions options = getChromeOptions();
                driver = new ChromeDriver(options);
            } else {
                throw new IllegalArgumentException("Navegador no soportado: " + browser);
            }
        }
        return driver;
    }

    private static String getChromeDriverPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String driverPathKey = os.contains("win") ? "chromedriver.win"
                : os.contains("mac") ? "chromedriver.mac"
                : os.contains("nix") || os.contains("nux") ? "chromedriver.linux"
                : null;

        if (driverPathKey == null) {
            throw new UnsupportedOperationException("Sistema operativo no soportado: " + os);
        }

        try (InputStream input = Browsers.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String driverPath = prop.getProperty(driverPathKey);
            if (driverPath == null || driverPath.isEmpty()) {
                throw new RuntimeException("Ruta del ChromeDriver no definida en config.properties para " + driverPathKey);
            }
            return driverPath;
        } catch (Exception e) {
            throw new RuntimeException("Error cargando archivo de configuración", e);
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            options.addArguments("--start-maximized");
        } else if (os.contains("mac")) {
            options.addArguments("--start-maximized");
        } else if (os.contains("nix") || os.contains("nux")) {
            options.addArguments("--disable-dev-shm-usage", "--no-sandbox");
        }

        options.addArguments("--disable-notifications");
        return options;
    }

    public static void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
