package pageFactory.pages.solicitudes.ingreso;


import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.dao.solicitudes.ingreso.IngresoAntecedentesLaboralesPrevisionalesDao;
import pageFactory.dao.solicitudes.ingreso.IngresoInformacionSolicitanteDao;
import pageFactory.dao.solicitudes.ingreso.IngresoAdjuntarAntecedentesMedicosDao;
import utils.PageFunctions;

import java.time.Duration;
import java.util.Random;

public class IngresoAdjuntarAntecedentesMedicosPage extends PageFunctions {

    private IngresoAdjuntarAntecedentesMedicosDao ingresoAdjuntarAntecedentesMedicosDao;
    private IngresoAdjuntarAntecedentesMedicosDao dao;
    Random random = new Random();

    @FindBy(xpath = "//*[@id=\"isAcompanaAntecedentesMedicos\"]/label[1]/span[1]/input")
    WebElement rdbtnIncorporarSi;

    @FindBy(xpath = "//*[@id=\"isAcompanaAntecedentesMedicos\"]/label[2]/span[1]/input")
    WebElement rdbtnIncorporarNo;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn ant-btn-primary ant-btn-block btn-form')]")
    WebElement btnContinuar;


    public IngresoAdjuntarAntecedentesMedicosPage(WebDriver driver) {
        super(driver);
        this.ingresoAdjuntarAntecedentesMedicosDao = new IngresoAdjuntarAntecedentesMedicosDao();
        this.dao = new IngresoAdjuntarAntecedentesMedicosDao();
        PageFactory.initElements(driver, this);
    }

    public IngresoAdjuntarAntecedentesMedicosDao getIngresoAdjuntarAntecedentesMedicosDao() {
        if (ingresoAdjuntarAntecedentesMedicosDao == null) {
            ingresoAdjuntarAntecedentesMedicosDao = new IngresoAdjuntarAntecedentesMedicosDao();
        }
        return ingresoAdjuntarAntecedentesMedicosDao;
    }

    private int indiceActual = 0;

    public void validarDatosCsv() {

        try {
            if (indiceActual >= dao.obtenerNumeroFilas()) {
                System.err.println("El índice actual está fuera de los límites del archivo CSV.");
                return;
            }
            String situacionLaboral = dao.getDato("csvOpcionAntecedentes", indiceActual);
            String fechaAfiliacion = dao.getDato("csvIncorporarOtrosAntecedentes", indiceActual);
            System.out.println("*****************************************************************************");
            System.out.println("Datos del caso actual (fila " + (indiceActual + 1) + "):");
            System.out.println("  Opcion desde el csv: " + situacionLaboral);
            System.out.println("  Incorpora otros antecedentes: " + fechaAfiliacion);
            System.out.println("*****************************************************************************");
        } catch (Exception e) {
            System.err.println("Error al leer el dato del caso actual: " + e.getMessage());
        }
    }

    public void esperarElementoHabilitado(WebElement element, int tiempoEspera) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tiempoEspera));
        try {
            wait.until(driver -> element.isEnabled());
            System.out.println("El elemento ahora está habilitado.");
        } catch (Exception e) {
            System.err.println("El elemento no se habilitó en el tiempo esperado: " + e.getMessage());
        }
    }

    public void seleccionarOpcionIncorporarAntecedentesMedicos() {
        try {
            String valorIncorporarOtrosAntecedentes = dao.getDato("csvIncorporarOtrosAntecedentes", 0).trim();
            System.out.println("Cargando datos si va a incorporar otros antecedentes desde CSV: " + valorIncorporarOtrosAntecedentes);
            if ("si".equalsIgnoreCase(valorIncorporarOtrosAntecedentes)) {
                esperarElementoHabilitado(rdbtnIncorporarSi, 20);
                rdbtnIncorporarSi.click();
                System.out.println("Opción si va a incorporar otros antecedentes: " + valorIncorporarOtrosAntecedentes);
            } else if ("no".equalsIgnoreCase(valorIncorporarOtrosAntecedentes)) {
                esperarElementoHabilitado(rdbtnIncorporarNo, 20);
                rdbtnIncorporarNo.click();
                System.out.println("Opción si va a incorporar otros antecedentes: " + valorIncorporarOtrosAntecedentes);
            } else {
                System.err.println("Valor si va a incorporar otros antecedentes: " + valorIncorporarOtrosAntecedentes);
            }
        } catch (Exception e) {
            System.err.println("Error al seleccionar si va a incorporar otros antecedentes: " + e.getMessage());
        }
    }

    public void clickBtnContinuar() {
        try {
            System.out.println("Se ha finalizado el ingreso de datos del formulario: Antecedentes Laborales");
            System.out.println("Esperando que el botón Continuar esté habilitado...");
            wait.until(ExpectedConditions.elementToBeClickable(btnContinuar));
            System.out.println("El botón Continuar está habilitado. Procediendo a hacer clic...");
            btnContinuar.click();
            System.out.println("El Formulario ha sido completado de manera satisfactoria");
            System.out.println("Se inicia el registro del formulario: Otros datos de la solicitud");
            pausaParaPruebas();
        } catch (TimeoutException e) {
            System.err.println("El botón Continuar no se habilitó en el tiempo esperado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al hacer clic en el botón Continuar: " + e.getMessage());
        }
    }

    public void ingresandoAntecedentes() throws InterruptedException {
        validarDatosCsv();
        seleccionarOpcionIncorporarAntecedentesMedicos();
        clickBtnContinuar();
    }
}
