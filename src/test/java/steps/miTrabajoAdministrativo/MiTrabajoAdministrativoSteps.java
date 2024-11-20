package steps.miTrabajoAdministrativo;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pageFactory.pages.dashboard.DashboardPage;
import pageFactory.pages.miTrabajoAdministrativo.MiTrabajoAdministrativoPage;
import utils.Browsers;

public class MiTrabajoAdministrativoSteps {
    private WebDriver driver;
    private MiTrabajoAdministrativoPage miTrabajoAdministrativoPage;

    public MiTrabajoAdministrativoSteps() {
        this.driver = Browsers.getDriver("chrome");
        this.miTrabajoAdministrativoPage = new MiTrabajoAdministrativoPage(driver);
    }

    @And("Seleccionando Análisis de Admisibilidad")
    public void seleccionarAnalisisAdmisibilidadEnfermoTerminal() {
        System.out.println("Seleccionanado Item...");
        miTrabajoAdministrativoPage.seleccionarItemAnalisisAdmisibilidad();
        System.out.println("Seleccionanado Analisis...");
        miTrabajoAdministrativoPage.seleccionarSolicitudEnfermoTerminal();
        System.out.println("Se ha seleccionado la primera solicitud");
    }
}
