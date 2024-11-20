package steps.miTrabajoAdministrativo;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageFactory.dao.sesion.LoginDao;
import pageFactory.pages.sesion.LoginPage;
import pageFactory.pages.dashboard.DashboardPage;
import pageFactory.pages.miTrabajoAdministrativo.ExpedientePage;
import utils.Browsers;
import utils.Ambientes;

import java.util.Map;

public class ExpedienteSteps {
    private WebDriver driver;
    private ExpedientePage expedientePage;

    public ExpedienteSteps() {
        this.driver = Browsers.getDriver("chrome");
        this.expedientePage = new ExpedientePage(driver);
    }

    @And("Aceptar Adminisibilidad de la solicitud con Aprobación de Documentos")
    public void seleccionarAceptarAdmisibilidadSolicitudConAprobacionDeDcoumentos() {
        expedientePage.seleccionarAceptarAdmisibilidadConAprobacionDeDcoumentos();
    }

    @And("Aceptar Adminisibilidad de la solicitud con Rechazo de Documentos")
    public void seleccionarAceptarAdmisibilidadSolicitudConRechazoDeDcoumentos() {
        expedientePage.seleccionarAceptarAdmisibilidadConRechazoDeDcoumentos();
    }

    @And("Rechazar Adminisibilidad de la solicitud con Aprobación de Documentos")
    public void seleccionarRechazarAdmisibilidadSolicitudConAprobacionDeDcoumentos() {
        expedientePage.seleccionarRechazarAdmisibilidadConAprobacionDeDcoumentos();
    }

    @And("Rechazar Adminisibilidad de la solicitud con Rechazo de Documentos")
    public void seleccionarRechazarAdmisibilidadSolicitudConRechazoDeDcoumentos() {
        expedientePage.seleccionarRechazarAdmisibilidadConRechazoDeDcoumentos();
    }
}
