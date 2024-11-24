package steps.solicitudes.ingreso;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pageFactory.dao.solicitudes.ingreso.IngresoOtrosDatosSolicitudDao;
import pageFactory.pages.solicitudes.ingreso.IngresoOtrosDatosSolicitudPage;
import utils.Browsers;

public class IngresoOtrosDatosSolicitudSteps {

    private final WebDriver driver;
    private final IngresoOtrosDatosSolicitudPage ingresoOtrosDatosSolicitudPage;

    public IngresoOtrosDatosSolicitudSteps() {
        this.driver = Browsers.getDriver("chrome");
        this.ingresoOtrosDatosSolicitudPage = new IngresoOtrosDatosSolicitudPage(driver);
    }

    @And("Ingreso Otros datos solicitud")
    public void ingresarOtrosDatosSolicitud() {
        try {
            IngresoOtrosDatosSolicitudDao dao = ingresoOtrosDatosSolicitudPage.getIngresoOtrosDatosSolicitudDao();
            boolean datosValidos = false;

            for (int i = 0; i < dao.obtenerNumeroFilas(); i++) {
                if (dao.validarDatosPorTipo(0)) {
                    System.out.println("Procesando fila válida: " + i);
                    ingresoOtrosDatosSolicitudPage.ingresarDatos();
                    datosValidos = true;
                    break;
                } else {
                    System.err.println("Fila inválida detectada en índice: " + i);
                }
            }

            if (!datosValidos) {
                System.err.println("No se encontraron filas válidas en el CSV.");
            }
        } catch (Exception e) {
            System.err.println("Error procesando datos de solicitud: " + e.getMessage());
        }
    }
}
