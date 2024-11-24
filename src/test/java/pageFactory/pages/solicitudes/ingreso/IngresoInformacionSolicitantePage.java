package pageFactory.pages.solicitudes.ingreso;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Comisiones;
import utils.Data;
import utils.PageFunctions;
import pageFactory.dao.solicitudes.ingreso.IngresoInformacionSolicitanteDao;

import java.time.Duration;
import java.util.Random;

public class IngresoInformacionSolicitantePage extends PageFunctions {

    private IngresoInformacionSolicitanteDao ingresoInformacionSolicitanteDao;
    Random random = new Random();

    @FindBy(xpath = "//h3[text()='Antecedentes del solicitante']")
    WebElement labelAntecedentesPersonales;

    @FindBy(id = "tipoExpediente")
    WebElement selTipoSolicitud;

    @FindBy(id = "numeroInterno")
    WebElement textNumeroInternoInstitucion;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn ant-btn-primary ant-input-search-button')]")
    WebElement btnBuscar;

    @FindBy(xpath = "//div[@id='isEnfermoTerminal']//input[@type='radio' and @value='true']")
    WebElement rdbtnEnfermoTerminalSi;

    @FindBy(xpath = "//div[@id='isEnfermoTerminal']//input[@type='radio' and @value='false']")
    WebElement rdbtnEnfermoTerminalNo;

    @FindBy(xpath = "//input[@placeholder='Seleccionar fecha']")
    WebElement dateFechaRecepcionSolicitudAfp;

    @FindBy(id = "nombre")
    WebElement textNombreSolicitante;

    @FindBy(id = "apellidoPaterno")
    WebElement textPrimerApellidoSolicitante;

    @FindBy(id = "apellidoMaterno")
    WebElement textSegundoApellidoSolicitante;

    @FindBy(xpath = "//input[@id='fechaNacimiento']")
    WebElement dateFechaNacimiento;

    @FindBy(id = "sexo")
    WebElement selSexo;

    @FindBy(id = "estadoCivil")
    WebElement selEstadoCivil;

    @FindBy(id = "profesionActividad")
    WebElement textProfesionActividad;

    @FindBy(xpath = "//*[@id=\"isNoTieneEmail\"]/label[1]/span[1]/input")
    WebElement rdbtnCorreoSi;

    @FindBy(xpath = "//*[@id=\"isNoTieneEmail\"]/label[2]/span[1]/input")
    WebElement rdbtnCorreoNo;

    @FindBy(id = "email")
    WebElement textCorreoElectronico;

    @FindBy(id = "email2")
    WebElement textCorreoElectronicoConfirmar;

    @FindBy(xpath = "//*[@id=\"tipoCorreo\"]/label[1]/span[1]/input")
    WebElement rdbtnCorreoPersonal;

    @FindBy(xpath = "//*[@id=\"tipoCorreo\"]/label[2]/span[1]/input")
    WebElement rdbtnCorreoTercero;

    @FindBy(xpath = "//*[@id=\"isNoTieneTelefonoMovil\"]")
    WebElement checkNoTelefonoMovil;

    @FindBy(xpath = "//*[@id=\"isNoTieneTelefonoFijo\"]")
    WebElement checkNoTelefonoFijo;

    @FindBy(id = "celular")
    WebElement textTelefonoMovil;

    @FindBy(id = "telefono")
    WebElement textTelefonoFijo;

    @FindBy(id = "nivelEducacional")
    WebElement selNivelEducacional;

    @FindBy(id = "region")
    WebElement selRegionSolicitante;

    @FindBy(id = "ciudad")
    WebElement selProvinciaSolicitante;

    @FindBy(id = "comuna")
    WebElement selComunaSolicitante;

    @FindBy(id = "calle")
    WebElement textCalleSolicitante;

    @FindBy(id = "numero")
    WebElement textNumeroSolicitante;

    @FindBy(id = "departamento")
    WebElement textCasaDepartamentoSolicitante;

    @FindBy(id = "block")
    WebElement textBlockSolicitante;

    @FindBy(id = "codigoPostal")
    WebElement textCodigoPostalSolicitante;

    @FindBy(id = "villa")
    WebElement textPoblacionVillaCondominioSolicitante;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn ant-btn-primary ant-btn-block btn-form')]")
    WebElement btnContinuar;

    public IngresoInformacionSolicitantePage(WebDriver driver) {
        super(driver);
        this.ingresoInformacionSolicitanteDao = new IngresoInformacionSolicitanteDao();
        PageFactory.initElements(driver, this);
    }

    public IngresoInformacionSolicitanteDao getIngresoInformacionSolicitanteDao() {
        if (ingresoInformacionSolicitanteDao == null) {
            ingresoInformacionSolicitanteDao = new IngresoInformacionSolicitanteDao();
        }
        return ingresoInformacionSolicitanteDao;
    }

    public void validarFormularioAntecedentesLaborales() {
        waitForElementToBeVisibleAndClickable(labelAntecedentesPersonales);
    }

    public void seleccionarTipoSolicitudPorCsv() throws InterruptedException {
        try {
            String valorTipoSolicitudPorCsv = ingresoInformacionSolicitanteDao.getDato("csvTipoSolicitud", 0); // Primera fila como ejemplo
            System.out.println("Cargando tipo de solicitud desde el CSV: " + valorTipoSolicitudPorCsv);

            if (valorTipoSolicitudPorCsv == null || valorTipoSolicitudPorCsv.isEmpty()) {
                System.err.println("El valor del tipo de solicitud es nulo o vacío.");
                return;
            }

            validarFormularioAntecedentesLaborales();
            selTipoSolicitud.click();

            WebElement valorOpcion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[@title='" + valorTipoSolicitudPorCsv + "']")));
            valorOpcion.click();

            System.out.println("Se ha seleccionado la opción: " + valorTipoSolicitudPorCsv);
        } catch (Exception e) {
            System.err.println("Error al seleccionar el tipo de solicitud: " + e.getMessage());
        }
    }


    public void ingresarNumeroInternoAleatorio() {
        String valorNumeroInternoInstitucion = Data.numeroIntentoInstitucion.generadorIdentificador();
        System.out.println("Generando número interno aleatorio: " + valorNumeroInternoInstitucion);
        try {
            textNumeroInternoInstitucion.click();
            textNumeroInternoInstitucion.sendKeys(valorNumeroInternoInstitucion);
            System.out.println("Se ha ingresado el valor de: " + valorNumeroInternoInstitucion);
        } catch (Exception e) {
            System.err.println("Error al ingresar datos: " + e.getMessage());
        }
    }

    public void clickBuscar() {
        try{
            btnBuscar.click();
            System.out.println("Se ha presionado el botón Buscar");
            System.out.println("Buscando datos...");
        }catch(Exception e){
            System.err.println("Error al ingresar datos: "  + e.getMessage());
        }
    }

    public void seleccionarEnfermedadTerminalPorCSV() {
        try {
            String valorEnfermedadTerminal = ingresoInformacionSolicitanteDao.getDato("csvEnfermoTerminal", 0).trim().toLowerCase();
            System.out.println("Valor de Enfermo Terminal desde CSV: " + valorEnfermedadTerminal);

            if ("si".equals(valorEnfermedadTerminal)) {
                esperarElementoHabilitado(rdbtnEnfermoTerminalSi, 20);
                rdbtnEnfermoTerminalSi.click();
                System.out.println("Opción 'Sí' seleccionada");
                adjuntarCertificado();
            } else if ("no".equals(valorEnfermedadTerminal)) {
                esperarElementoHabilitado(rdbtnEnfermoTerminalNo, 20);
                rdbtnEnfermoTerminalNo.click();
                System.out.println("Opción 'No' seleccionada");
            } else {
                System.err.println("Valor no válido en el CSV para Enfermo Terminal: " + valorEnfermedadTerminal);
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Índice fuera de los límites en seleccionarEnfermedadTerminalPorCSV: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al seleccionar Enfermo Terminal: " + e.getMessage());
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

    public void adjuntarCertificado() {
        try{
            Data.cargarArchivo(driver, "certificadoEnfermoTerminal", "Archivo.pdf");
            System.out.println("Cargando archivo...");
        }catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void ingresarFechaRecepcionSolicitudAfpPorCsv() {
        try {
            String valorFechaRecepcionSolicitud = ingresoInformacionSolicitanteDao.getDato("csvFechaRecepcionSolicitud", 0).trim();
            System.out.println("Ingresando Fecha Recepción Solicitud desde CSV: " + valorFechaRecepcionSolicitud);

            if (valorFechaRecepcionSolicitud == null || valorFechaRecepcionSolicitud.isEmpty()) {
                System.err.println("El valor de la fecha de recepción es nulo o vacío.");
                return;
            }

            dateFechaRecepcionSolicitudAfp.click();
            dateFechaRecepcionSolicitudAfp.sendKeys(valorFechaRecepcionSolicitud);
            dateFechaRecepcionSolicitudAfp.sendKeys(Keys.ENTER);
            System.out.println("Fecha ingresada: " + valorFechaRecepcionSolicitud);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Índice fuera de los límites en ingresarFechaRecepcionSolicitudAfpPorCsv: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al ingresar la fecha de recepción: " + e.getMessage());
        }
    }


    public void ingresarNombresSolicitanteAleatorio() {
        try {
            String valorSexoPorCsv = ingresoInformacionSolicitanteDao.getDato("csvSexo", 0); // Primera fila como ejemplo
            System.out.println("Sexo del solicitante desde CSV: " + valorSexoPorCsv);

            if (valorSexoPorCsv == null || valorSexoPorCsv.trim().isEmpty()) {
                System.err.println("El valor del sexo es nulo o vacío. No se puede generar un nombre.");
                return;
            }

            String nombreSolicitante = "Nombre por defecto";
            if ("Masculino".equalsIgnoreCase(valorSexoPorCsv)) {
                nombreSolicitante = Data.getNombresHombres();
            } else if ("Femenino".equalsIgnoreCase(valorSexoPorCsv)) {
                nombreSolicitante = Data.getNombresMujeres();
            } else {
                System.err.println("Sexo inválido en CSV: " + valorSexoPorCsv);
                return;
            }

            textNombreSolicitante.click();
            textNombreSolicitante.clear();
            textNombreSolicitante.sendKeys(nombreSolicitante);

            System.out.println("Se ha ingresado el nombre: " + nombreSolicitante);
        } catch (Exception e) {
            System.err.println("Error al ingresar el nombre del solicitante: " + e.getMessage());
        }
    }


    public void ingresarPrimerApellidoSolicitanteAleatorio () {
        String valorPrimerApellidoSolicitante = Data.obtenerPrimerApellidoAleatorio();
        try{
            textPrimerApellidoSolicitante.click();
            textPrimerApellidoSolicitante.sendKeys(valorPrimerApellidoSolicitante);
            System.out.println("Se ha ingresado el primer apellido: " + valorPrimerApellidoSolicitante);
        }catch(Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void ingresarSegundoApellidoSolicitanteAleatorio () {
        String valorSegundoApellidoSolicitante = Data.obtenerPrimerApellidoAleatorio();
        try{
            textSegundoApellidoSolicitante.click();
            textSegundoApellidoSolicitante.sendKeys(valorSegundoApellidoSolicitante);
            System.out.println("Se ha ingresado el segundo apellido: " + valorSegundoApellidoSolicitante);
        }catch(Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void ingresarFechanNacimientoSolicitantePorCsv() {
        try {
            String valorFechaNacimiento = ingresoInformacionSolicitanteDao.getDato("csvFechaNacimiento", 0).trim();
            System.out.println("Ingresando Fecha Nacimiento desde CSV: " + valorFechaNacimiento);

            if (valorFechaNacimiento == null || valorFechaNacimiento.isEmpty()) {
                System.err.println("El valor de la fecha de nacimiento es nulo o vacío.");
                return;
            }

            dateFechaNacimiento.click();
            dateFechaNacimiento.sendKeys(valorFechaNacimiento);
            dateFechaNacimiento.sendKeys(Keys.ENTER);
            System.out.println("Fecha ingresada: " + valorFechaNacimiento);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Índice fuera de los límites en ingresarFechanNacimientoSolicitantePorCsv: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al ingresar la fecha de nacimiento: " + e.getMessage());
        }
    }


    public void seleccionarSexoPorCsv() {
        try {
            String valorSexo = ingresoInformacionSolicitanteDao.getDato("csvSexo", 0).trim();
            System.out.println("Seleccionando sexo desde CSV: " + valorSexo);

            if (valorSexo == null || valorSexo.isEmpty()) {
                System.err.println("El valor de sexo es nulo o vacío.");
                return;
            }

            selSexo.click();
            WebElement valorOpcion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[@title='" + valorSexo + "']")));
            valorOpcion.click();
            System.out.println("Sexo seleccionado: " + valorSexo);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Índice fuera de los límites en seleccionarSexoPorCsv: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al seleccionar sexo: " + e.getMessage());
        }
    }


    public void seleccionarEstadoCivilAleatorio() {
        try {
            String[] opcionEstadoCivilAleatorio = {
                    "Soltero(a)",
                    "Casado(a)",
                    "Viudo(a)",
                    "Divorciado(a)",
                    "Conviviente Civil"
            };
            int indiceEstadoCivilAleatorio = random.nextInt(opcionEstadoCivilAleatorio.length);
            String estadoCivilSeleccionado = opcionEstadoCivilAleatorio[indiceEstadoCivilAleatorio];
            System.out.println("La opción seleccionada es: " + estadoCivilSeleccionado);
            selEstadoCivil.click();
            WebElement valorOpcionEstadoCivilAleatorio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@title='" + estadoCivilSeleccionado + "']")));
            valorOpcionEstadoCivilAleatorio.click();
            System.out.println("Se ha seleccionado la opción: " + estadoCivilSeleccionado);
        } catch (Exception e) {
            System.err.println("Error al seleccionar el estado civil: " + e.getMessage());
        }
    }

    public void ingresarProfesionActividadAleatorio () {
        String valorProfesionActividad = Data.getObtenerProfesion();
        try{
            textProfesionActividad.click();
            textProfesionActividad.sendKeys(valorProfesionActividad);
            System.out.println("Se ha ingresado la Profesión / Actividad: " + valorProfesionActividad);
        }catch(Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void seleccionarUnaOpcionAleatoriaDeContacto() {
        try {
            String[] opcionUnDatoContacto = {
                    "soloCorreo",
                    "soloTelefonoMóvil",
                    "soloTelefonoFijo"
            };

            int indiceAleatorioUnaOpcionContacto = random.nextInt(opcionUnDatoContacto.length);

            switch (opcionUnDatoContacto[indiceAleatorioUnaOpcionContacto]) {
                case "soloCorreo":
                    ingresarCorreoOpcionSi();
                    seleccionarOpcionTipoCorreo();
                    ingresarSinTelefonoMovil();
                    ingresarSinTelefonoFijo();
                    break;
                case "soloTelefonoMóvil":
                    ingresarTelefonoMovilAleatorio();
                    ingresarCorreoOpcionNo();
                    ingresarSinTelefonoFijo();
                    break;
                case "soloTelefonoFijo":
                    ingresarTelefonoFijoAleatorio();
                    ingresarCorreoOpcionNo();
                    ingresarSinTelefonoMovil();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar una opción de contacto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ingresarCorreoOpcionSi() throws  InterruptedException{
        try{
            rdbtnCorreoSi.click();
            String correoAleatorio = Data.generarCorreoElectronico();
            textCorreoElectronico.click();
            textCorreoElectronico.sendKeys(correoAleatorio);
            textCorreoElectronicoConfirmar.click();
            textCorreoElectronicoConfirmar.sendKeys(correoAleatorio);
            System.out.println("Se ingresa el correo electrónico: " + correoAleatorio + " - Se re ingresa el correo electrónico: "+ correoAleatorio);
            System.out.println("Se a seleccionado la opción de Si ingresar un correo electrónico");
        }
        catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void ingresarCorreoOpcionNo() throws  InterruptedException{
        try{
            rdbtnCorreoNo.click();
            System.out.println("Se a seleccionado la opción de No ingresar un correo electrónico");
        }
        catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void seleccionarCorreoPersonal() throws InterruptedException{
        try{
            rdbtnCorreoPersonal.click();
            System.out.println("Se a seleccionado la opcion de Correo Personal");
        }
        catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void seleccionarCorreoTercero() throws InterruptedException{
        try{
            rdbtnCorreoTercero.click();
            System.out.println("Se a seleccionado la opcion de Correo de Tercero");
        }
        catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void seleccionarOpcionTipoCorreo() throws InterruptedException{
        try {
            String[] opcionesTipoCorreo = {
                    "Personal",
                    "Tercero"
            };

            int indiceAleatorioTipoCorreo = random.nextInt(opcionesTipoCorreo.length);

            switch (opcionesTipoCorreo[indiceAleatorioTipoCorreo]) {
                case "Personal":
                    seleccionarCorreoPersonal();
                    System.out.println("Se ha seleccionado la opción 'Personal'");
                    break;
                case "Tercero":
                    seleccionarCorreoTercero();
                    System.out.println("Se ha seleccionado la opción 'De un tercero'");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } catch (Exception e) {
            System.out.println("Error en Seleccionar un tipo de archivo: " + e.getMessage());
        }
    }

    public void ingresarTelefonoMovilAleatorio() throws  InterruptedException{
        try{
            String telefonoMovil = Data.generarNumeroCelular();
            textTelefonoMovil.click();
            textTelefonoMovil.sendKeys(telefonoMovil);
            System.out.println("Se ha ingresado el Número de Celular: " + telefonoMovil);
        }
        catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void ingresarTelefonoFijoAleatorio() throws  InterruptedException{
        try{
            String telefonoFijo = Data.generarNumeroFijo();
            textTelefonoFijo.click();
            textTelefonoFijo.sendKeys(telefonoFijo);
            System.out.println("Se ha ingresado el Número de Teléfono Fijo: " + telefonoFijo);
        }
        catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void ingresarSinTelefonoFijo() throws  InterruptedException{
        try{
            checkNoTelefonoFijo.click();
            Thread.sleep(500);
        }
        catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void ingresarSinTelefonoMovil() throws  InterruptedException{
        try{
            checkNoTelefonoMovil.click();
            Thread.sleep(500);
        }
        catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
    }

    public void seleccionarNivelEducacional() {
        try {
            String[] opcionNivelEducacionalAleatorio = {
                    "Analfabeto",
                    "Educación Básica",
                    "Educación Media",
                    "Educación Superior"
            };
            int indiceNivelEducacionalAleatorio = random.nextInt(opcionNivelEducacionalAleatorio.length);
            String nivelEducacionalSeleccionado = opcionNivelEducacionalAleatorio[indiceNivelEducacionalAleatorio];
            System.out.println("La opción seleccionada es: " + nivelEducacionalSeleccionado);
            selNivelEducacional.click();
            WebElement valorOpcionEstadoCivilAleatorio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@title='" + nivelEducacionalSeleccionado + "']")));
            valorOpcionEstadoCivilAleatorio.click();
            System.out.println("Se ha seleccionado la opción: " + nivelEducacionalSeleccionado);
        } catch (Exception e) {
            System.err.println("Error al seleccionar el estado civil: " + e.getMessage());
        }
    }

    public void seleccionarRegionProvinciaComunaPorCodigo() {
        try {
            String codigoComision = ingresoInformacionSolicitanteDao.getDato("csvComision", 0); // Primera fila como ejemplo
            System.out.println("Código de comisión desde CSV: " + codigoComision);

            if (codigoComision == null || codigoComision.trim().isEmpty()) {
                System.err.println("El código de comisión es nulo o vacío.");
                return;
            }

            Comisiones comisiones = new Comisiones();
            if (!comisiones.setComisionPorCodigo(codigoComision)) {
                System.err.println("Código de comisión inválido: " + codigoComision);
                return;
            }

            selRegionSolicitante.click();
            String regionSolicitante = comisiones.getRegionComision();
            WebElement opcionRegionSolicitante = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[contains(@title, \"" + regionSolicitante + "\")]")));
            opcionRegionSolicitante.click();
            System.out.println("Región seleccionada: " + regionSolicitante);

            selProvinciaSolicitante.click();
            String provinciaSolicitante = comisiones.getProvinciaComision();
            WebElement opcionProvinciaSolicitante = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[contains(@title, \"" + provinciaSolicitante + "\")]")));
            opcionProvinciaSolicitante.click();
            System.out.println("Provincia seleccionada: " + provinciaSolicitante);

            selComunaSolicitante.click();
            String comunaSolicitante = comisiones.getComunaComision();
            WebElement opcionComunaSolicitante = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//div[contains(@title, \"" + comunaSolicitante + "\")]")));
            opcionComunaSolicitante.click();
            System.out.println("Comuna seleccionada: " + comunaSolicitante);
        } catch (Exception e) {
            System.err.println("Error al seleccionar región, provincia o comuna: " + e.getMessage());
        }
    }

    public void ingresarDireccionSolicitante() {
        String calleSolicitante = Data.getObtenerCalle();
        String numeroSolicitante = Data.getObtenerNumero();
        String tipoViviendaSolicitante = Data.getObtenerTipoVivienda();
        String blockSolicitante = Data.getObtenerblock();
        String codigoPostalSolicitante = Data.getObtenerCodigoPostal();
        String condominioSolicitante = Data.getObtenerCondominio();
        System.out.println("Se obtienen los datos de la direccion: " +calleSolicitante + " del número: " + numeroSolicitante + " de tipo vivienda: " + tipoViviendaSolicitante + " ubicada en el block: " + blockSolicitante + " de código postal: " + codigoPostalSolicitante + " en el condominio: " + condominioSolicitante);
        try{
            textCalleSolicitante.click();
            textCalleSolicitante.sendKeys(calleSolicitante);
            System.out.println("Se registra el valor: " + calleSolicitante);
            textNumeroSolicitante.click();
            textNumeroSolicitante.sendKeys(numeroSolicitante);
            System.out.println("Se registra el valor: " + numeroSolicitante);
            textCasaDepartamentoSolicitante.click();
            textCasaDepartamentoSolicitante.sendKeys(tipoViviendaSolicitante);
            System.out.println("Se registra el valor: " + tipoViviendaSolicitante);
            textBlockSolicitante.click();
            textBlockSolicitante.sendKeys(blockSolicitante);
            System.out.println("Se registra el valor: " + blockSolicitante);
            textCodigoPostalSolicitante.click();
            textCodigoPostalSolicitante.sendKeys(codigoPostalSolicitante);
            System.out.println("Se registra el valor: " + codigoPostalSolicitante);
            textPoblacionVillaCondominioSolicitante.click();
            textPoblacionVillaCondominioSolicitante.sendKeys(condominioSolicitante);
            System.out.println("Se registra el valor: " + condominioSolicitante);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void clickBtnContinuar() {
        try{
            System.out.println("Se ha finalizado el ingreso de datos del formulario: Antecedentes del solicitante");
            System.out.println("Se presionara el botón Continuar para validar los datos registrados");
            btnContinuar.click();
            System.out.println("El Formulario ha sido completado de manera satisfactoria");
            System.out.println("Se inicia el registro del formulario: Antecedentes Laborales y Previsionales ");
            pausaParaPruebas();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void ingresandoDatos() throws InterruptedException {
        System.out.println("Pasa por Ingresando datos");
        seleccionarTipoSolicitudPorCsv();
        ingresarNumeroInternoAleatorio();
        clickBuscar();
        seleccionarEnfermedadTerminalPorCSV();
        ingresarFechaRecepcionSolicitudAfpPorCsv();
        ingresarNombresSolicitanteAleatorio();
        ingresarPrimerApellidoSolicitanteAleatorio();
        ingresarSegundoApellidoSolicitanteAleatorio();
        ingresarFechanNacimientoSolicitantePorCsv();
        seleccionarSexoPorCsv();
        seleccionarEstadoCivilAleatorio();
        ingresarProfesionActividadAleatorio();
        seleccionarUnaOpcionAleatoriaDeContacto();
        seleccionarNivelEducacional();
        seleccionarRegionProvinciaComunaPorCodigo();
        ingresarDireccionSolicitante();
        clickBtnContinuar();
    }
}
