package pageFactory.pages.solicitudes.ingreso;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.dao.solicitudes.ingreso.IngresoAntecedentesLaboralesPrevisionalesDao;
import pageFactory.dao.solicitudes.ingreso.IngresoInformacionSolicitanteDao;
import utils.Comisiones;
import utils.Data;
import utils.PageFunctions;

import java.time.Duration;
import java.util.Random;

public class IngresoAntecedentesLaboralesPage extends PageFunctions {

    private IngresoAntecedentesLaboralesPrevisionalesDao ingresoAntecedentesLaboralesPrevisionalesDao;
    private IngresoInformacionSolicitanteDao ingresoInformacionSolicitanteDao;
    private IngresoAntecedentesLaboralesPrevisionalesDao dao;
    Random random = new Random();

    @FindBy(id = "tipoTrabajador")
    WebElement selSituacionLaboral;

    @FindBy(id = "rutEmpleador")
    WebElement textRutEmpleador;

    @FindBy(id = "razonSocial")
    WebElement textRazonSocial;

    @FindBy(id = "regionEmpleador")
    WebElement selRegionEmpleador;

    @FindBy(id = "ciudadEmpleador")
    WebElement selProvinciaEmpleador;

    @FindBy(id = "comunaEmpleador")
    WebElement selComunaEmpleador;

    @FindBy(id = "calleEmpleador")
    WebElement textCalleEmpleador;

    @FindBy(id = "numeroEmpleador")
    WebElement textNumeroEmpleador;

    @FindBy(id = "departamentoEmpleador")
    WebElement textDepartamentoCasaEmpleador;

    @FindBy(id = "blockEmpleador")
    WebElement textBlockEmpleador;

    @FindBy(id = "telefonoEmpleador")
    WebElement textTelefonoFijoEmpleador;

    @FindBy(id = "emailEmpleador")
    WebElement textCorreoEmpleador;

    @FindBy(id = "institucionSalud")
    WebElement selInstitucionSalud;

    @FindBy(id = "fechaAfiliacionAfp")
    WebElement dateFechaAfiliacionAfp;

    @FindBy(xpath = "//*[@id=\"cambioAfiliacion\"]/label[1]/span[1]/input")
    WebElement rdbtnCambioAfpSi;

    @FindBy(xpath = "//*[@id=\"cambioAfiliacion\"]/label[2]/span[1]/input")
    WebElement rdbtnCambioAfpNo;

    @FindBy(xpath = "//*[@id=\"mutualidad\"]/label[1]/span[1]/input")
    WebElement rdbtnPensionadoLey16744Si;

    @FindBy(xpath = "//*[@id=\"mutualidad\"]/label[2]/span[1]/input")
    WebElement rdbtnPensionadoLey16744No;

    @FindBy(xpath = "")
    WebElement rdbtnCoberturaSISSi;

    @FindBy(xpath = "")
    WebElement rdbtnCoberturaSISNo;

    @FindBy(id = "companiaAseguradora")
    WebElement selCompaniaAseguradora;

    @FindBy(id = "companiaAseguradoraOtro")
    WebElement selOtrosCompaniaAseguradora;

    @FindBy(xpath = "//*[@id=\"isLicenciaMedica\"]/label[1]/span[1]/input")
    WebElement rdbtnAcogidoLicenciaMedicaSi;

    @FindBy(xpath = "//*[@id=\"isLicenciaMedica\"]/label[2]/span[1]/input")
    WebElement rdbtnAcogidoLicenciaMedicaNo;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn ant-btn-primary ant-btn-block btn-form')]")
    WebElement btnContinuar;

    public IngresoAntecedentesLaboralesPage(WebDriver driver) {
        super(driver);
        this.ingresoAntecedentesLaboralesPrevisionalesDao = new IngresoAntecedentesLaboralesPrevisionalesDao();
        this.ingresoInformacionSolicitanteDao = new IngresoInformacionSolicitanteDao();
        this.dao = new IngresoAntecedentesLaboralesPrevisionalesDao();
        PageFactory.initElements(driver, this);
    }

    public IngresoAntecedentesLaboralesPrevisionalesDao getIngresoAntecedentesLaboralesPrevisionalesDao() {
        if (ingresoAntecedentesLaboralesPrevisionalesDao == null) {
            ingresoAntecedentesLaboralesPrevisionalesDao = new IngresoAntecedentesLaboralesPrevisionalesDao();
        }
        return ingresoAntecedentesLaboralesPrevisionalesDao;
    }

    private int indiceActual = 0;

    public void validarDatosCsv() {

        try {
            IngresoAntecedentesLaboralesPrevisionalesDao dao = new IngresoAntecedentesLaboralesPrevisionalesDao();

            if (indiceActual >= dao.obtenerNumeroFilas()) {
                System.err.println("El índice actual está fuera de los límites del archivo CSV.");
                return;
            }
            String situacionLaboral = dao.getDato("csvSituacionLaboral", indiceActual);
            String fechaAfiliacion = dao.getDato("csvFechaAfiliacionAfp", indiceActual);
            String cambioAfp = dao.getDato("csvCambioAfp", indiceActual);
            String pensionado16744 = dao.getDato("csvPensionado16744", indiceActual);
            String licenciaMedica = dao.getDato("csvLicenciaMedica", indiceActual);
            System.out.println("*****************************************************************************");
            System.out.println("Datos del caso actual (fila " + (indiceActual + 1) + "):");
            System.out.println("  Situación Laboral: " + situacionLaboral);
            System.out.println("  Fecha Afiliación AFP: " + fechaAfiliacion);
            System.out.println("  Cambio AFP: " + cambioAfp);
            System.out.println("  Pensionado Ley 16744: " + pensionado16744);
            System.out.println("  Licencia Médica: " + licenciaMedica);
            System.out.println("*****************************************************************************");
        } catch (Exception e) {
            System.err.println("Error al leer el dato del caso actual: " + e.getMessage());
        }
    }

    public void seleccionarSituacionLaboralPorCsv() {
        try {
            String valorSituacionLaboral = ingresoAntecedentesLaboralesPrevisionalesDao.getDato("csvSituacionLaboral", 0).trim();
            System.out.println("Cargando situación laboral desde CSV: " + valorSituacionLaboral);
            if (valorSituacionLaboral.isEmpty()) {
                System.err.println("El valor de la situación laboral es nulo o vacío.");
                return;
            }
            selSituacionLaboral.click();
            WebElement opcion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[@title='" + valorSituacionLaboral + "']")));
            opcion.click();
            System.out.println("Situación laboral seleccionada: " + valorSituacionLaboral);
        } catch (TimeoutException e) {
            System.err.println("No se encontró la opción en el tiempo esperado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al seleccionar la situación laboral: " + e.getMessage());
        }
    }

    public void ingresarDatosPrincipalesEmpleador() {
        String rutEmpleador = Data.generateValidRut();
        String razonSocialNombreEmpleador = Data.getListadoRazonesSociales();
        System.out.println("Se generan los datos del empleador");
        System.out.println("El rut del emeplador: " + rutEmpleador + " y su razón social / nombre empresa es: " + razonSocialNombreEmpleador);
        try{
            waitForElementToBeVisibleAndClickable(textRutEmpleador);
            textRutEmpleador.click();
            textRutEmpleador.sendKeys(rutEmpleador);
            System.out.println("Se ha ingresado: " + rutEmpleador);
            textRazonSocial.click();
            textRazonSocial.sendKeys(razonSocialNombreEmpleador);
            System.out.println("Se ha ingresado: " + rutEmpleador);
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void seleccionarRegionProvinciaComunaEmpleador() {
        try {
            String codigoComision = ingresoInformacionSolicitanteDao.getDato("csvComision", 0).trim();
            System.out.println("Cargando código de comisión desde CSV: " + codigoComision);

            if (codigoComision == null || codigoComision.trim().isEmpty()) {
                System.err.println("El código de comisión es nulo o vacío.");
                return;
            }
            Comisiones comisiones = new Comisiones();
            if (!comisiones.setComisionPorCodigo(codigoComision)) {
                System.err.println("Código de comisión inválido: " + codigoComision);
                return;
            }
            selRegionEmpleador.click();
            String regionEmpleador = comisiones.getRegionComision();
            WebElement opcionRegionEmpleador = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[contains(@title, \"" + regionEmpleador + "\")]")));
            opcionRegionEmpleador.click();
            System.out.println("Región seleccionada: " + regionEmpleador);
            selProvinciaEmpleador.click();
            String provinciaEmpleador = comisiones.getProvinciaComision();
            WebElement opcionProvinciaEmpleador = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[contains(@title, \"" + provinciaEmpleador + "\")]")));
            opcionProvinciaEmpleador.click();
            System.out.println("Provincia seleccionada: " + provinciaEmpleador);
            selComunaEmpleador.click();
            String comunaEmpleador = comisiones.getComunaComision();
            WebElement opcionComunaEmpleador = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[contains(@title, \"" + comunaEmpleador + "\")]")));
            opcionComunaEmpleador.click();
            System.out.println("Comuna seleccionada: " + comunaEmpleador);
        } catch (Exception e) {
            System.err.println("Error al seleccionar región, provincia o comuna: " + e.getMessage());
        }
    }

    public void ingresarAntecedentesEmpleadorAleatorios() {
        String calleEmpleador = Data.getObtenerCalle();
        String numeroEmpleador = Data.getObtenerNumero();
        String departamentoCasa = Data.getObtenerTipoVivienda();
        String blockEmpleador = Data.getObtenerblock();
        String telefonoEmpleador = Data.generarNumeroFijo();
        String correoEmpleador = Data.generarCorreoElectronico();
        System.out.println("La ubicación geográfica se encuentra en la región: " + "" + " en la provincia de: " + "" + " en la comuna de: " +"");
        System.out.println("La dirección del empleador es: " + calleEmpleador + " número: " + numeroEmpleador + " ubicada en un(a): " + departamentoCasa + " en el block: " + blockEmpleador + " cuyo telefono de contacto es: " + telefonoEmpleador + " y su correo es: " + correoEmpleador);
        System.out.println("Ahora se cargaran los datos en el formulario...");
        try{
            textCalleEmpleador.click();
            textCalleEmpleador.sendKeys(calleEmpleador);
            System.out.println("Se ha ingresado: " + calleEmpleador);
            textNumeroEmpleador.click();
            textNumeroEmpleador.sendKeys(numeroEmpleador);
            System.out.println("Se ha ingresado: " + numeroEmpleador);
            textDepartamentoCasaEmpleador.click();
            textDepartamentoCasaEmpleador.sendKeys(departamentoCasa);
            System.out.println("Se ha ingresado: " + departamentoCasa);
            textBlockEmpleador.click();
            textBlockEmpleador.sendKeys(blockEmpleador);
            System.out.println("Se ha ingresado: " + blockEmpleador);
            textTelefonoFijoEmpleador.click();
            textTelefonoFijoEmpleador.sendKeys(telefonoEmpleador);
            System.out.println("Se ha ingresado: " + telefonoEmpleador);
            textCorreoEmpleador.click();
            textCorreoEmpleador.sendKeys(correoEmpleador);
            System.out.println("Se ha ingresado: " + correoEmpleador);
            System.out.println("Se han ingresado los datos del Empleador");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void adjuntarCertificadoEmpleador() {
        try{
            Data.cargarArchivo(driver, "certificadoEmpleador", "Archivo.pdf");
            System.out.println("Cargando archivo...");
        }catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void seleccionarInstitucionSaludAleatoria() {
        try {
            String[] opcionInstitucionSaludAleatorio = {
                    "FONASA",
                    "Isapre Banmedica",
                    "Chuquicamata",
                    "Colmena",
                    "Consalud",
                    "Isapre Cruz Blanca S.A",
                    "Cruz del norte",
                    "Isapre Nueva Masvida"
            };
            int indiceInstitucionSaludAleatorio = random.nextInt(opcionInstitucionSaludAleatorio.length);
            String seleccionInstitucionSalud = opcionInstitucionSaludAleatorio[indiceInstitucionSaludAleatorio];
            System.out.println("La opción seleccionada es: " + seleccionInstitucionSalud);
            selInstitucionSalud.click();
            WebElement valorOpcionInstitucionSaludAleatorio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@title, \"" + seleccionInstitucionSalud + "\")]")));
            valorOpcionInstitucionSaludAleatorio.click();
            System.out.println("Se ha seleccionado la opción: " + seleccionInstitucionSalud);
        } catch (Exception e) {
            System.err.println("Error al seleccionar la Institución Aleatoria: " + e.getMessage());
        }
    }

    public void ingresarFechaAfiliacion() {
        try {
            String fechaAfiliacion = dao.getDato("csvFechaAfiliacionAfp", indiceActual);
            if (fechaAfiliacion.isEmpty()) {
                System.err.println("El valor de la fecha de afiliación es nulo o vacío.");
                return;
            }
            System.out.println("Cargando fecha de afiliación desde CSV: " + fechaAfiliacion);
            dateFechaAfiliacionAfp.click();
            dateFechaAfiliacionAfp.sendKeys(fechaAfiliacion);
            dateFechaAfiliacionAfp.sendKeys(Keys.ENTER);
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

    public void seleccionarCambioAfpAfiliadoPorCsv() {
        try {
            String valorCambioAfp = ingresoAntecedentesLaboralesPrevisionalesDao.getDato("csvCambioAfp", 0).trim();
            System.out.println("Cargando cambio de AFP desde CSV: " + valorCambioAfp);
            if ("si".equalsIgnoreCase(valorCambioAfp)) {
                esperarElementoHabilitado(rdbtnCambioAfpSi, 20);
                rdbtnCambioAfpSi.click();
                System.out.println("Opción 'Sí' seleccionada para cambio de AFP.");
            } else if ("no".equalsIgnoreCase(valorCambioAfp)) {
                esperarElementoHabilitado(rdbtnCambioAfpNo, 20);
                rdbtnCambioAfpNo.click();
                System.out.println("Opción 'No' seleccionada para cambio de AFP.");
            } else {
                System.err.println("Valor no válido para cambio de AFP: " + valorCambioAfp);
            }
        } catch (Exception e) {
            System.err.println("Error al seleccionar cambio de AFP: " + e.getMessage());
        }
    }

    public void seleccionarPensioandoLey16744PorCsv() {
        try {
            String valorPensionadoLey = ingresoAntecedentesLaboralesPrevisionalesDao.getDato("csvPensionado16744", indiceActual).trim();
            System.out.println("Cargando valor de pensionado Ley 16744 desde CSV: " + valorPensionadoLey);
            if ("si".equalsIgnoreCase(valorPensionadoLey)) {
                esperarElementoHabilitado(rdbtnPensionadoLey16744Si, 20);
                rdbtnPensionadoLey16744Si.click();
                System.out.println("Opción 'Sí' seleccionada para Ley 16744.");
            } else if ("no".equalsIgnoreCase(valorPensionadoLey)) {
                esperarElementoHabilitado(rdbtnPensionadoLey16744No, 20);
                rdbtnPensionadoLey16744No.click();
                System.out.println("Opción 'No' seleccionada para Ley 16744.");
            } else {
                System.err.println("Valor no válido para Ley 16744: " + valorPensionadoLey);
            }
        } catch (Exception e) {
            System.err.println("Error al seleccionar opción de Ley 16744: " + e.getMessage());
        }
    }


    public void adjuntarDocumentoCobertura() {
        try{
            Data.cargarArchivo(driver, "documentoCoberturaSIS", "Archivo.pdf");
            System.out.println("Cargando archivo...");
        }catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void seleccionarAcogidoLicenciaMedicaPorCsv() {
        try {
            String valorAcogidoLicenciaMedica = ingresoAntecedentesLaboralesPrevisionalesDao.getDato("csvLicenciaMedica", indiceActual).toLowerCase().trim();
            System.out.println("Cargando valor de Acogido Licencia Médica desde CSV: " + valorAcogidoLicenciaMedica);
            if (valorAcogidoLicenciaMedica.isEmpty()) {
                System.err.println("El valor de Acogido Licencia Médica es nulo o vacío.");
                return;
            }
            if ("si".equalsIgnoreCase(valorAcogidoLicenciaMedica)) {
                esperarElementoHabilitado(rdbtnAcogidoLicenciaMedicaSi, 20);
                rdbtnAcogidoLicenciaMedicaSi.click();
                System.out.println("Opción 'Sí' seleccionada para Acogido Licencia Médica.");
            } else if ("no".equalsIgnoreCase(valorAcogidoLicenciaMedica)) {
                esperarElementoHabilitado(rdbtnAcogidoLicenciaMedicaNo, 20);
                rdbtnAcogidoLicenciaMedicaNo.click();
                System.out.println("Opción 'No' seleccionada para Acogido Licencia Médica.");
            } else {
                System.err.println("Valor no válido para Acogido Licencia Médica: " + valorAcogidoLicenciaMedica);
            }
        } catch (TimeoutException e) {
            System.err.println("No se pudo interactuar con los elementos en el tiempo esperado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al seleccionar la opción de Acogido Licencia Médica: " + e.getMessage());
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
        seleccionarSituacionLaboralPorCsv();
        ingresarDatosPrincipalesEmpleador();
        seleccionarRegionProvinciaComunaEmpleador();
        ingresarAntecedentesEmpleadorAleatorios();
        adjuntarCertificadoEmpleador();
        seleccionarInstitucionSaludAleatoria();
        ingresarFechaAfiliacion();
        seleccionarCambioAfpAfiliadoPorCsv();
        seleccionarPensioandoLey16744PorCsv();
        adjuntarDocumentoCobertura();
        seleccionarAcogidoLicenciaMedicaPorCsv();
        clickBtnContinuar();
    }
}