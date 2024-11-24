package steps.sesion;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageFactory.dao.sesion.LoginDao;
import pageFactory.pages.sesion.LoginPage;
import utils.Browsers;
import utils.Ambientes;

import java.util.Map;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private Map<String, String> data;

    @Given("Ingreso Sagcom2 con los siguientes datos del archivo csv")
    public void abrirNavegadorDesdeCSV() {
        String perfil = "Ejecutivo AFP";
        String comision = "Sin Comisión";
        data = LoginDao.cargarDatosSesion(perfil, comision);

        if (data == null || data.isEmpty()) {
            throw new RuntimeException("No se encontraron datos disponibles en el archivo CSV.");
        }

        String navegador = data.get("csvBrowser");
        String ambiente = data.get("csvAmbiente");

        if (navegador == null || ambiente == null) {
            throw new IllegalArgumentException("Datos de navegador o ambiente faltantes.");
        }

        driver = Browsers.getDriver(navegador);
        String url = Ambientes.seleccionarUrl(ambiente);
        driver.get(url);

        loginPage = new LoginPage(driver);
        System.out.println("Página de login cargada exitosamente.");
    }

    @When("Ingreso usuario y contraseña validos")
    public void ingresarCredenciales() {
        String usuario = data.get("csvUsuario");
        String password = data.get("csvPass");

        if (usuario == null || password == null) {
            throw new IllegalArgumentException("Los datos de usuario o contraseña no están presentes.");
        }

        loginPage.ingresarCredenciales(usuario, password);
        loginPage.clickIniciarSesion();
    }

    @Then("Cierro el navegador")
    public void cerrarNavegador() {
        Browsers.cerrarNavegador();
        System.out.println("Navegador cerrado exitosamente.");
    }
}
