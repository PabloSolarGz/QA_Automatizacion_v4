package steps.dashboard;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageFactory.dao.sesion.LoginDao;
import pageFactory.pages.sesion.LoginPage;
import pageFactory.pages.dashboard.DashboardPage;
import utils.Browsers;
import utils.Ambientes;

import java.util.Map;

public class DashboardSteps {
    private WebDriver driver;
    private DashboardPage dashboardPage;

    public DashboardSteps() {
        this.driver = Browsers.getDriver("chrome");
        this.dashboardPage = new DashboardPage(driver);
    }

    @And("Cerrar sesion")
    public void cerrarSesionActiva() {
        System.out.println("Cerrando sesión...");
        dashboardPage.cerrarSesion();
        System.out.println("Sesión cerrada correctamente.");
    }

    @And("Ingresar nuevo expediente")
    public void ingresarNuevoExpediente() {
        dashboardPage.ingresarRutNuevaSolicitud();
    }

    @And("Seleccionar Mi Trabajo Administrativo")
    public void seleccionarMenuMiTrabajoAdministrativo() {
        System.out.println("Seleccionando Menú...");
        dashboardPage.seleccionarItemMiTrabajoAdministrativo();
        System.out.println("Se ha ingresado al menu de Mi Trabajo Administrativo");
    }
}
