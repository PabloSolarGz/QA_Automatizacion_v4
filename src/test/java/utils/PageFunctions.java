package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageFunctions {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageFunctions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void scrollToElementWithActions(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            System.out.println("Desplazado al elemento usando Actions.");
        } catch (Exception e) {
            System.out.println("Error al desplazarse usando Actions: " + e.getMessage());
        }
    }

    public static void pausaParaPruebas() throws InterruptedException {
        System.out.println("************************************************************************************");
        System.out.println("Se inicia una pausa para probar de 5 segundos");
        Thread.sleep(1000);
        System.out.println("Quedan 4 segundos");
        Thread.sleep(1000);
        System.out.println("Quedan 3 segundos");
        Thread.sleep(1000);
        System.out.println("Quedan 2 segundos");
        Thread.sleep(1000);
        System.out.println("Quedan 1 segundos");
        Thread.sleep(1000);
        System.out.println("a terminado la pausa");
        System.out.println("************************************************************************************");
    }

    public static WebDriverWait getWait(WebDriver driver, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public static WebDriverWait getDefaultWait(WebDriver driver) {
        return getWait(driver, 10);
    }

    public void waitForElementToBeVisibleAndClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (element.getRect().getWidth() > 0 && element.getRect().getHeight() > 0) {
                System.out.println("El elemento está visible y clickeable.");
            } else {
                System.out.println("El elemento está presente pero no es visible completamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al esperar que el elemento sea visible y clickeable: " + e.getMessage());
        }
    }

}
