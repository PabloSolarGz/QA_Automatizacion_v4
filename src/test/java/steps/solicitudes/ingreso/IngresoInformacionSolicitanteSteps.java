package steps.solicitudes.ingreso;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pageFactory.dao.solicitudes.ingreso.IngresoInformacionSolicitanteDao;
import pageFactory.pages.solicitudes.ingreso.IngresoInformacionSolicitantePage;
import utils.Browsers;

public class IngresoInformacionSolicitanteSteps {
    WebDriver driver;
    IngresoInformacionSolicitantePage ingresoInformacionSolicitantePage;

    public IngresoInformacionSolicitanteSteps() {
        this.driver = Browsers.getDriver("chrome");
        this.ingresoInformacionSolicitantePage = new IngresoInformacionSolicitantePage(driver);
    }

    @And("Ingreso los datos de la Solicitud")
    public void ingresarDatosSolicitud() throws InterruptedException {
        IngresoInformacionSolicitanteDao dao = ingresoInformacionSolicitantePage.getIngresoInformacionSolicitanteDao();
        boolean datosValidos = false;
        for (int i = 0; i < dao.obtenerNumeroFilas(); i++) {
            if (dao.validarDatosPorTipo(i)) {
                System.out.println("Procesando fila válida: " + i);
                ingresoInformacionSolicitantePage.ingresandoDatos();
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