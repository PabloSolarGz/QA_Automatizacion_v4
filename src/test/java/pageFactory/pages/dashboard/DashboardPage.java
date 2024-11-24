package pageFactory.pages.dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import utils.Browsers;
import utils.Data;
import utils.PageFunctions;

public class DashboardPage extends PageFunctions {
    private WebDriver driver;
    private WebDriverWait wait;
    PageFunctions pageFunctions;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/header/div[1]/div/div[1]/h3")
    WebElement labelSAGCOM;

    @FindBy(xpath = "//li[contains(@class, 'ant-menu-overflow-item ant-menu-item ant-menu-item-selected ant-menu-item-only-child')]")
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

    @FindBy(xpath = "")
    WebElement itemMenu;

    @FindBy(xpath = "//div[contains(@class, 'ant-col mobile-column ant-col-xs-2 ant-col-md-3 ant-col-lg-5')]")
    WebElement btnMiPerfil;

    @FindBy(xpath = "//a[text()='Cerrar sesión']")
    WebElement btnCerrarSesion;

    @FindBy(id = "rut")
    WebElement textRutSolicitud;

    @FindBy(xpath = "//*[@id=\"root\"]/div/section/main/div/div/section/div/div/div/div/div[2]/div/div[2]/form/div/div[2]/button")
    WebElement btnComenzar;

    public DashboardPage(WebDriver driver) {
        super(driver);
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
            wait.until(ExpectedConditions.elementToBeClickable(btnMiPerfil)).click();
            wait.until(ExpectedConditions.visibilityOf(btnCerrarSesion));
            wait.until(ExpectedConditions.elementToBeClickable(btnCerrarSesion)).click();
            System.out.println("Sesión cerrada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión: " + e.getMessage());
        } finally {
            Browsers.cerrarNavegador();
        }
    }
}

