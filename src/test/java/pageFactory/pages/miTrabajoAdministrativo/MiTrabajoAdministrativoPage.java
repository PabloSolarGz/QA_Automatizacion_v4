package pageFactory.pages.miTrabajoAdministrativo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MiTrabajoAdministrativoPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/main/div/div/section/div/div/div/div[1]/div[1]/span/span")
    WebElement itemAnalisisAdmisibilidad;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/main/div/div/section/div/div/div/div[1]/div[2]/div/div/section/div[1]/div[1]/div/label[1]/span[2]/small")
    WebElement btnEnfermoTerminal;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/main/div/div/section/div/div/div/div[1]/div[2]/div/div/section/div[1]/div[1]/div/label[2]/span[2]/small")
    WebElement btnRegular;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/main/div/div/section/div/div/div/div[1]/div[2]/div/div/section/div[2]/div/div/div/div/div/div/div/table/tbody/tr[1]/td[9]/button/span")
    WebElement btnVerMas;

    public MiTrabajoAdministrativoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void seleccionarItemAnalisisAdmisibilidad() {
        try {
            Thread.sleep(1000);
            itemAnalisisAdmisibilidad.click();
            Thread.sleep(1000);
            System.out.println("Se ha seleccionado la opción de Analisis de Admisibilidad");
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión: " + e.getMessage());
        }
    }

    public void seleccionarBtnEnfermoTerminal() {
        try {
            Thread.sleep(1000);
            btnEnfermoTerminal.click();
            Thread.sleep(1000);
            System.out.println("Se ha seleccionado la opción de Analisis de Admisibilidad");
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión: " + e.getMessage());
        }
    }

    public void seleccionarBtnRegular() {
        try {
            Thread.sleep(1000);
            btnRegular.click();
            Thread.sleep(1000);
            System.out.println("Se ha seleccionado la opción de Analisis de Admisibilidad");
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión: " + e.getMessage());
        }
    }

    public void seleccionarBtnVerMas() {
        try {
            Thread.sleep(1000);
            btnVerMas.click();
            Thread.sleep(1000);
            System.out.println("Se ha seleccionado la opción de Analisis de Admisibilidad");
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión: " + e.getMessage());
        }
    }

    public void seleccionarSolicitudEnfermoTerminal() {
        seleccionarBtnEnfermoTerminal();
        seleccionarBtnVerMas();
    }

    public void seleccionarSolicitudRegular() {
        seleccionarBtnRegular();
        seleccionarBtnVerMas();
    }
}
