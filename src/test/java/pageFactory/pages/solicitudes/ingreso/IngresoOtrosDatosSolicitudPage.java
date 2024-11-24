package pageFactory.pages.solicitudes.ingreso;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.dao.solicitudes.ingreso.IngresoAntecedentesLaboralesPrevisionalesDao;
import pageFactory.dao.solicitudes.ingreso.IngresoInformacionSolicitanteDao;
import pageFactory.dao.solicitudes.ingreso.IngresoOtrosDatosSolicitudDao;
import utils.Data;
import utils.PageFunctions;

import java.time.Duration;
import java.util.Random;

public class IngresoOtrosDatosSolicitudPage extends PageFunctions {

    private final IngresoAntecedentesLaboralesPrevisionalesDao ingresoAntecedentesLaboralesPrevisionalesDao;
    private final IngresoInformacionSolicitanteDao ingresoInformacionSolicitanteDao;
    private final IngresoOtrosDatosSolicitudDao ingresoOtrosDatosSolicitudDao;
    private final Random random;
    IngresoOtrosDatosSolicitudDao dao = new IngresoOtrosDatosSolicitudDao();

    @FindBy(id = "fechaRecepcionCapri")
    WebElement dateFechaCapri;

    @FindBy(xpath = "")
    WebElement rdbtnBeneficiarioAfiliadoCMSi;

    @FindBy(xpath = "")
    WebElement rdbtnBeneficiarioAfiliadoCMNo;

    @FindBy(xpath = "//*[@id=\"quienVerificaIncapacidad\"]/label[1]/span[1]/input")
    WebElement rdbtnCertificadoMedico;

    @FindBy(xpath = "//*[@id=\"quienVerificaIncapacidad\"]/label[2]/span[1]/input")
    WebElement rdbtnVisita;

    @FindBy(id = "nombreVerificaIdentidad")
    WebElement textNombrePersonaVerificoIdentidad;

    @FindBy(xpath = "//*[@id=\"isTercero\"]/label[1]/span[1]/input")
    WebElement rdbtnRequiereTerceroSi;

    @FindBy(xpath = "//*[@id=\"isTercero\"]/label[2]/span[1]/input")
    WebElement rdbtnRequiereTerceroNo;

    @FindBy(id = "entidadAdmSeguro")
    WebElement textEntidadAdministradora;

    @FindBy(xpath = "//*[@id=\"isAccidenteTrabajo\"]/label[1]/span[1]/input")
    WebElement rdbtnhaSufridoAccidenteEnfermedadSi;

    @FindBy(xpath = "//*[@id=\"isAccidenteTrabajo\"]/label[2]/span[1]/input")
    WebElement rdbtnhaSufridoAccidenteEnfermedadNo;

    @FindBy(id = "enfermedadSolicitante")
    WebElement textPrincipalAfeccionEnfermedad;

    @FindBy(xpath = "//*[@id=\"porcentCargoAfiliado\"]/label[1]/span[1]/input")
    WebElement rdbtn0;

    @FindBy(xpath = "//*[@id=\"porcentCargoAfiliado\"]/label[2]/span[1]/input")
    WebElement rdbtn10;

    @FindBy(xpath = "//*[@id=\"porcentCargoAfiliado\"]/label[3]/span[1]/input")
    WebElement rdbtn20;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn ant-btn-primary ant-btn-block btn-form')]")
    WebElement btnContinuar;

    public IngresoOtrosDatosSolicitudPage(WebDriver driver) {
        super(driver);
        this.ingresoAntecedentesLaboralesPrevisionalesDao = new IngresoAntecedentesLaboralesPrevisionalesDao();
        this.ingresoInformacionSolicitanteDao = new IngresoInformacionSolicitanteDao();
        this.ingresoOtrosDatosSolicitudDao = new IngresoOtrosDatosSolicitudDao();
        this.random = new Random();
        PageFactory.initElements(driver, this);
    }

    public IngresoOtrosDatosSolicitudDao getIngresoOtrosDatosSolicitudDao() {
        return ingresoOtrosDatosSolicitudDao;
    }

    private int indiceActual = 0;

    public void validarDatosCsv() {

        try {
            if (indiceActual >= dao.obtenerNumeroFilas()) {
                System.err.println("El índice actual está fuera de los límites del archivo CSV.");
                return;
            }
            String seleccionOtrosDocumentosPorCsv = dao.getDato("csvSeleccion", indiceActual);
            String fechaRecepcionCapriPorCsv = dao.getDato("csvFechaRecepcionCapri", indiceActual);
            String quienVerificaPorCsv = dao.getDato("csvQuienVerifica", indiceActual);
            String opcionRequiereTerceroPorCsv = dao.getDato("csvRequiereTercero", indiceActual);
            String opcionAccidenteEnfermedadPorCsv = dao.getDato("csvHaSufridoAccidenteEnfermedad", indiceActual);
            String opcionPorcentajeCargoAfiliadoPorCsv = dao.getDato("csvPorcentajeCargoAfiliado", indiceActual);
            System.out.println("*****************************************************************************");
            System.out.println("Datos del caso actual (fila " + (indiceActual + 1) + "):");
            System.out.println("  Selección                         : " + seleccionOtrosDocumentosPorCsv);
            System.out.println("  Fecha Capri                       : " + fechaRecepcionCapriPorCsv);
            System.out.println("  Quien verifico                    : " + quienVerificaPorCsv);
            System.out.println("  Requiere Tercero                  : " + opcionRequiereTerceroPorCsv);
            System.out.println("  Sufrio Accidente o Enfermedad     : " + opcionAccidenteEnfermedadPorCsv);
            System.out.println("  Porcentaje Cargo Afiliado         : " + opcionPorcentajeCargoAfiliadoPorCsv);
            System.out.println("*****************************************************************************");
        } catch (Exception e) {
            System.err.println("Error al leer el dato del caso actual: " + e.getMessage());
        }
    }

    public void ingresarFechaCapriPorCsv() {
        try {
            String fechaAfiliacion = dao.getDato("csvFechaRecepcionCapri", indiceActual);
            if (fechaAfiliacion.isEmpty()) {
                System.err.println("El valor de la fecha capri es nulo o vacío.");
                return;
            }
            System.out.println("Cargando fecha capri desde CSV: " + fechaAfiliacion);
            dateFechaCapri.click();
            dateFechaCapri.sendKeys(fechaAfiliacion);
            dateFechaCapri.sendKeys(Keys.ENTER);
            System.out.println("Fecha de afiliación ingresada: " + fechaAfiliacion);
        } catch (Exception e) {
            System.err.println("Error al ingresar la fecha de afiliación: " + e.getMessage());
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

    public void seleccionarQuienVerificaIncapacidadPorCsv() {
        try {
            String quienVerificaIncapacidad = dao.getDato("csvQuienVerifica", 0).trim();
            System.out.println("Cargando cambio de AFP desde CSV: " + quienVerificaIncapacidad);
            if ("Certificado médico".equalsIgnoreCase(quienVerificaIncapacidad)) {
                esperarElementoHabilitado(rdbtnCertificadoMedico, 20);
                rdbtnCertificadoMedico.click();
                System.out.println("Opción 'Certificado médico' seleccionada Quien verifica incapacidad");
            } else if ("Visita".equalsIgnoreCase(quienVerificaIncapacidad)) {
                esperarElementoHabilitado(rdbtnVisita, 20);
                rdbtnVisita.click();
                System.out.println("Opción 'Visita' seleccionada Quien verifica incapacidad");
            } else {
                System.err.println("Valor no válido para cambio de AFP: " + quienVerificaIncapacidad);
            }
        } catch (Exception e) {
            System.err.println("Error al seleccionar cambio de AFP: " + e.getMessage());
        }
    }

    public void adjuntarCertificadoIncapacidad() {
        try{
            Data.cargarArchivo(driver, "certificadoIncapacidad", "Archivo.pdf");
            System.out.println("Cargando archivo...");
        }catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void ingresarPersonaQueVerificoIdentidadAfiliadoAleatorio() {
        String nombrePersonaIdentificaIndentidad = Data.generarNombreCompuesto();
        System.out.println("Lo registra: " + nombrePersonaIdentificaIndentidad);
        try{
            waitForElementToBeVisibleAndClickable(textNombrePersonaVerificoIdentidad);
            textNombrePersonaVerificoIdentidad.click();
            textNombrePersonaVerificoIdentidad.sendKeys(nombrePersonaIdentificaIndentidad);
            System.out.println("Se ha ingresado: " + nombrePersonaIdentificaIndentidad);
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void seleccionarRequiereTerceroPorCsv() {
        try {
            String requiereTercero = dao.getDato("csvRequiereTercero", 0).trim();
            System.out.println("Cargando datos si requiere tercero desde CSV: " + requiereTercero);
            if ("si".equalsIgnoreCase(requiereTercero)) {
                esperarElementoHabilitado(rdbtnRequiereTerceroSi, 20);
                rdbtnRequiereTerceroSi.click();
                System.out.println("Opción Requiere tercero es: " + requiereTercero);
            } else if ("no".equalsIgnoreCase(requiereTercero)) {
                esperarElementoHabilitado(rdbtnRequiereTerceroNo, 20);
                rdbtnRequiereTerceroNo.click();
                System.out.println("Opción Requiere tercero es: " + requiereTercero);
            } else {
                System.err.println("Valor no válido requiere tercero: " + requiereTercero);
            }
        } catch (Exception e) {
            System.err.println("Error al seleccionar requiere tercero: " + e.getMessage());
        }
    }

    public void ingresarEntidadAdministradoraAleatoria() {
        String nombreEntidadAdministradora = Data.getEntidadesAdministradoras();
        System.out.println("Entidad administradora es: " + nombreEntidadAdministradora);
        try{
            waitForElementToBeVisibleAndClickable(textEntidadAdministradora);
            textEntidadAdministradora.click();
            textEntidadAdministradora.sendKeys(nombreEntidadAdministradora);
            System.out.println("Se ha ingresado: " + nombreEntidadAdministradora);
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void seleccionarHaSufridoAccidenteEnfermedad() {
        try {
            String valorHaSufridoAccidenteEnfermedad = dao.getDato("csvHaSufridoAccidenteEnfermedad", 0).trim();
            System.out.println("Cargando datos si ha sufrido Accidente o Enfermedad desde CSV: " + valorHaSufridoAccidenteEnfermedad);
            if ("si".equalsIgnoreCase(valorHaSufridoAccidenteEnfermedad)) {
                esperarElementoHabilitado(rdbtnhaSufridoAccidenteEnfermedadSi, 20);
                rdbtnhaSufridoAccidenteEnfermedadSi.click();
                System.out.println("Opción si ha sufrido Accidente o Enfermedad: " + valorHaSufridoAccidenteEnfermedad);
            } else if ("no".equalsIgnoreCase(valorHaSufridoAccidenteEnfermedad)) {
                esperarElementoHabilitado(rdbtnhaSufridoAccidenteEnfermedadNo, 20);
                rdbtnhaSufridoAccidenteEnfermedadNo.click();
                System.out.println("Opción si ha sufrido Accidente o Enfermedad: " + valorHaSufridoAccidenteEnfermedad);
            } else {
                System.err.println("Valor no si ha sufrido Accidente o Enfermedad: " + valorHaSufridoAccidenteEnfermedad);
            }
        } catch (Exception e) {
            System.err.println("Error al seleccionar si ha sufrido Accidente o Enfermedad: " + e.getMessage());
        }
    }

    public void ingresarPrincipalAfeccionEnfermedad() {
        String nombrePrincipalAfeccionEnfermedad = Data.getListadoAfeccionesEnfermedades();
        System.out.println("Principal Afección o Enfermedad: " + nombrePrincipalAfeccionEnfermedad);
        try{
            waitForElementToBeVisibleAndClickable(textPrincipalAfeccionEnfermedad);
            textPrincipalAfeccionEnfermedad.click();
            textPrincipalAfeccionEnfermedad.sendKeys(nombrePrincipalAfeccionEnfermedad);
            System.out.println("Se ha ingresado: " + nombrePrincipalAfeccionEnfermedad);
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void seleccionarPorcentajeCargoAfiliadoPorCsv() {
        try {
            String valorPorcentajeCargoAfiliado = dao.getDato("csvPorcentajeCargoAfiliado", 0).trim();
            System.out.println("Cargando porcentaje de cargo afiliado desde CSV: " + valorPorcentajeCargoAfiliado);
            if (valorPorcentajeCargoAfiliado.isEmpty()) {
                System.err.println("El valor del porcentaje de cargo afiliado es nulo o vacío.");
                return;
            }
            switch (valorPorcentajeCargoAfiliado) {
                case "0":
                    esperarElementoHabilitado(rdbtn0, 20);
                    rdbtn0.click();
                    System.out.println("Opción '0%' seleccionada.");
                    break;
                case "10":
                    esperarElementoHabilitado(rdbtn10, 20);
                    rdbtn10.click();
                    System.out.println("Opción '10%' seleccionada.");
                    break;
                case "20":
                    esperarElementoHabilitado(rdbtn20, 20);
                    rdbtn20.click();
                    System.out.println("Opción '20%' seleccionada.");
                    break;
                default:
                    System.err.println("Valor no válido para el porcentaje de cargo afiliado: " + valorPorcentajeCargoAfiliado);
            }
        } catch (TimeoutException e) {
            System.err.println("No se pudo interactuar con los elementos en el tiempo esperado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al seleccionar el porcentaje de cargo afiliado: " + e.getMessage());
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

    public void ingresarDatos() {
        System.out.println("Ingresando datos desde IngresoOtrosDatosSolicitudPage");
        validarDatosCsv();
        ingresarFechaCapriPorCsv();
        seleccionarQuienVerificaIncapacidadPorCsv();
        adjuntarCertificadoIncapacidad();
        ingresarPersonaQueVerificoIdentidadAfiliadoAleatorio();
        seleccionarRequiereTerceroPorCsv();
        ingresarEntidadAdministradoraAleatoria();
        seleccionarHaSufridoAccidenteEnfermedad();
        ingresarPrincipalAfeccionEnfermedad();
        seleccionarPorcentajeCargoAfiliadoPorCsv();
        clickBtnContinuar();
    }
}
