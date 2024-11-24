package steps.solicitudes.ingreso;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pageFactory.dao.solicitudes.ingreso.IngresoAdjuntarAntecedentesMedicosDao;
import pageFactory.dao.solicitudes.ingreso.IngresoAntecedentesLaboralesPrevisionalesDao;
import pageFactory.pages.solicitudes.ingreso.IngresoAdjuntarAntecedentesMedicosPage;
import pageFactory.pages.solicitudes.ingreso.IngresoAntecedentesLaboralesPage;
import utils.Browsers;

public class IngresoAdjuntarAntecedentesMedicosSteps {
    WebDriver driver;
    IngresoAdjuntarAntecedentesMedicosPage ingresoAntecedentesLaboralesPrevisionalesPage;

    public IngresoAdjuntarAntecedentesMedicosSteps() {
        this.driver = Browsers.getDriver("chrome");
        this.ingresoAntecedentesLaboralesPrevisionalesPage = new IngresoAdjuntarAntecedentesMedicosPage(driver);
    }

    @And("Ingreso Adjuntar Antecedentes Médicos")
    public void ingresarDatosAntecedentesLaborales() throws InterruptedException {
        IngresoAdjuntarAntecedentesMedicosDao dao = ingresoAntecedentesLaboralesPrevisionalesPage.getIngresoAdjuntarAntecedentesMedicosDao();
        boolean datosValidos = false;
        for (int i = 0; i < dao.obtenerNumeroFilas(); i++) {
            if (dao.validarDatosPorTipo(0)) {
                System.out.println("Procesando fila válida: " + i);
                ingresoAntecedentesLaboralesPrevisionalesPage.ingresandoAntecedentes();
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