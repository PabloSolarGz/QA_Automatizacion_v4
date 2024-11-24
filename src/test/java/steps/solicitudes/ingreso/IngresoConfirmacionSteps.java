package steps.solicitudes.ingreso;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pageFactory.pages.solicitudes.ingreso.IngresoConfirmacionPage;
import utils.Browsers;

public class IngresoConfirmacionSteps {

    WebDriver driver;
    IngresoConfirmacionPage ingresoConfirmacionPage;

    public IngresoConfirmacionSteps() {
        this.driver = Browsers.getDriver("chrome");
        this.ingresoConfirmacionPage = new IngresoConfirmacionPage(driver);
    }

    @And("Confirmacion creación expediente")
    public void ingresarDatosAntecedentesLaborales() throws InterruptedException {
        ingresoConfirmacionPage.ingresandoDatos();
    }
}
