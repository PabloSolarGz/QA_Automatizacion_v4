package steps.solicitudes.ingreso;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pageFactory.dao.solicitudes.ingreso.IngresoAntecedentesLaboralesPrevisionalesDao;
import pageFactory.pages.solicitudes.ingreso.IngresoAntecedentesLaboralesPage;
import utils.Browsers;

public class IngresoAntecedentesLaboralesSteps {
    WebDriver driver;
    IngresoAntecedentesLaboralesPage ingresoAntecedentesLaboralesPage;

    public IngresoAntecedentesLaboralesSteps() {
        this.driver = Browsers.getDriver("chrome");
        this.ingresoAntecedentesLaboralesPage = new IngresoAntecedentesLaboralesPage(driver);
    }

    @And("Ingreso los datos de Antecedentes Laborales")
    public void ingresarDatosAntecedentesLaborales() throws InterruptedException {
        IngresoAntecedentesLaboralesPrevisionalesDao dao = ingresoAntecedentesLaboralesPage.getIngresoAntecedentesLaboralesPrevisionalesDao();
        boolean datosValidos = false;
        for (int i = 0; i < dao.obtenerNumeroFilas(); i++) {
            if (dao.validarDatosPorTipo(0)) {
                System.out.println("Procesando fila válida: " + i);
                ingresoAntecedentesLaboralesPage.ingresandoAntecedentes();
                datosValidos = true;
                break;
            } else {
                System.err.println("Fila inválida detectada en índice: " + i);
            }
        }
        if (!datosValidos) {
            System.err.println("No se encontraron filas válidas en el CSV.");
        }
    }
}