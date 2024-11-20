package pageFactory.pages.dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import utils.Data;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/header/div[1]/div/div[1]/h3")
    WebElement labelSAGCOM;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/header/div[1]/div/div[2]/ul/li[1]/span")
    WebElement itemInicio;

    @FindBy(xpath = "")
    WebElement itemSolicitudes;

    @FindBy(xpath = "")
    WebElement itemNotificaciones;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/header/div[1]/div/div[2]/ul/li[2]/span")
    WebElement itemMiTrabajoAdministrativo;

    @FindBy(xpath = "")
    WebElement itemExpedientesTareas;

    @FindBy(xpath = "")
    WebElement itemTresPuntos;

    @FindBy(xpath = "")
    WebElement itemOrdenesAtencion;

    @FindBy(xpath = "")
    WebElement itemSesiones;

    @FindBy(xpath = "")
    WebElement itemMantenedores;

    @FindBy(xpath = "")
    WebElement itemMantenedoresAgendaMedicos;

    @FindBy(xpath = "")
    WebElement itemMonitoreo;

    @FindBy(xpath = "")
    WebElement itemReporteria;

    @FindBy(xpath = "")
    WebElement itemNominasCorreo;

    @FindBy(xpath = "//div[contains(@class, 'ant-col mobile-column ant-col-xs-2 ant-col-md-3 ant-col-lg-5')]")
    WebElement itemMenu;

    @FindBy(xpath = "//li[contains(@class, 'ant-dropdown-menu-item ant-dropdown-menu-item-only-child')]")
    WebElement btnMiPerfil;

    @FindBy(xpath = "//a[contains(text(),'Cerrar sesión')]")
    WebElement btnCerrarSesion;

    @FindBy(id = "rut")
    WebElement textRutSolicitud;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/main/div/div/section/div/div/div/div/div[2]/div/div[2]/form/div/div[2]/button")
    WebElement btnComenzar;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void seleccionarItemMiTrabajoAdministrativo() {
        try {
            Thread.sleep(1000);
            itemMiTrabajoAdministrativo.click();
            Thread.sleep(1000);
            System.out.println("Sesión cerrada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión: " + e.getMessage());
        }

    }

    public void ingresarRutSolicitud() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(textRutSolicitud));
            String rutAleatorioSolicitante = Data.generateValidRut();
            textRutSolicitud.click();
            textRutSolicitud.sendKeys(rutAleatorioSolicitante);
        } catch (Exception e) {
            System.err.println("Error al ingresar el RUT o hacer clic en el botón Comenzar: " + e.getMessage());
        }
    }

    public void clickBtnComenzar() {
        try {
            btnComenzar.click();
        } catch (Exception e) {
            System.err.println("Error al ingresar el RUT o hacer clic en el botón Comenzar: " + e.getMessage());
        }
    }

    public void ingresarRutNuevaSolicitud() {
        ingresarRutSolicitud();
        clickBtnComenzar();
    }

    public void cerrarSesion() {
        try {
            itemInicio.click();
            Thread.sleep(1000);
            itemMenu.click();
            Thread.sleep(1000);
            btnCerrarSesion.click();
            Thread.sleep(1000);
            System.out.println("Sesión cerrada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión: " + e.getMessage());
        }
    }
}

