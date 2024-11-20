package pageFactory.pages.miTrabajoAdministrativo;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PageFunctions;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ExpedientePage {
    WebDriver driver;
    WebDriverWait wait;

    Random random = new Random();

    @FindBy(xpath = "//div[contains(@class, 'ant-select-item-option-content') and text()='Aceptar']")
    WebElement rdbtnAdmisibilidadSolicitudAceptar;

    @FindBy(xpath = "//div[contains(@class, 'ant-select-item-option-content') and text()='Rechazar y devolver']")
    WebElement rdbtnAdmisibilidadSolicitudRechazarVolver;

    @FindBy(xpath = "//div[contains(@class, 'ant-col ant-col-xs-24 ant-col-md-5')]")
    WebElement btnConfirmar;

    @FindBy(xpath = "//div[contains(@id, 'documento')]")
    List<WebElement> listaDocumentos;

    @FindBy(xpath = "//input[@type='radio' and @value='ACEPTADO']")
    List<WebElement> listaBotonesAprobarPorValor;

    @FindBy(xpath = "//input[@type='radio' and @value='RECHAZADO']")
    List<WebElement> listaBotonesRechazar;

    @FindBy(css = "textarea[id*='motivo']")
    List<WebElement> listaTextMotivoRechazo;

    @FindBy(id = "motivoObservacion")
    WebElement selMotivoRechazo;

    @FindBy(id = "descripcionMotivo")
    WebElement textMotivoRechazo;

    public ExpedientePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean validarAdmisibilidadCaso() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement admisibilidadDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("admissibility")));

            if (admisibilidadDiv != null && admisibilidadDiv.isDisplayed()) {
                System.out.println("Admisibilidad del caso verificada.");
                return true;
            } else {
                System.out.println("Admisibilidad no encontrada o no válida.");
                return false;
            }
        } catch (TimeoutException e) {
            System.out.println("El elemento de admisibilidad no se encontró a tiempo: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al verificar la admisibilidad: " + e.getMessage());
            return false;
        }
    }

    public void aprobarDocumentosConAdmisibilidad() {
        if (!validarAdmisibilidadCaso()) {
            System.out.println("Admisibilidad del caso no verificada. No se pueden aprobar documentos.");
            return;
        }

        if (validarDocumentosCargados()) {
            System.out.println("Aprobando documentos...");
            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (WebElement botonAprobar : listaBotonesAprobarPorValor) {
                try {
                    PageFunctions.scrollToElementWithActions(driver, botonAprobar);
                    js.executeScript("arguments[0].click();", botonAprobar);

                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement radioSi = wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath(".//input[@type='radio' and @value='ACEPTADO']")
                    ));

                    if (radioSi.isSelected()) {
                        System.out.println("El documento ya está aprobado.");
                    } else {
                        js.executeScript("arguments[0].click();", radioSi);
                        System.out.println("Documento aprobado.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("No se encontró el radio button para aprobar el documento: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error al aprobar el documento: " + e.getMessage());
                }
            }
        } else {
            System.out.println("No hay documentos para aprobar.");
        }
    }

    public void rechazarDocumentosConAdmisibilidad() {
        if (!validarAdmisibilidadCaso()) {
            System.out.println("Admisibilidad del caso no verificada. No se pueden rechazar documentos.");
            return;
        }
        if (validarDocumentosCargados()) {
            System.out.println("Verificando y rechazando documentos...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            for (int i = 0; i < listaBotonesRechazar.size(); i++) {
                try {
                    WebElement botonRechazar = listaBotonesRechazar.get(i);
                    PageFunctions.scrollToElementWithActions(driver, botonRechazar);
                    WebElement radioRechazado = driver.findElement(By.xpath(".//input[@type='radio' and @value='RECHAZADO']"));
                    if (radioRechazado.isSelected()) {
                        System.out.println("Documento " + (i + 1) + " ya está marcado como rechazado.");
                    } else {
                        js.executeScript("arguments[0].click();", botonRechazar);
                        System.out.println("Documento " + (i + 1) + " marcado como rechazado.");
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar el documento " + (i + 1) + ": " + e.getMessage());
                }
            }
            System.out.println("Ingresando motivos de rechazo...");

            for (int i = 0; i < listaBotonesRechazar.size(); i++) {
                try {
                    WebElement motivoRechazo = driver.findElement(By.xpath("//textarea[contains(@id, 'motivo')]"));
                    String motivoActual = motivoRechazo.getAttribute("value");
                    if (motivoActual != null && !motivoActual.isEmpty()) {
                        System.out.println("Motivo ya ingresado para el documento " + (i + 1) + ": " + motivoActual);
                    } else {
                        String nuevoMotivo = "Se rechaza el documento por motivos internos";
                        js.executeScript("arguments[0].value='" + nuevoMotivo + "';", motivoRechazo);
                        System.out.println("Motivo ingresado para el documento " + (i + 1) + ": " + nuevoMotivo);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("No se encontró el campo de motivo para el documento " + (i + 1));
                } catch (Exception e) {
                    System.out.println("Error al ingresar el motivo para el documento " + (i + 1) + ": " + e.getMessage());
                }
            }
            System.out.println("Proceso de rechazo de documentos completado.");
        } else {
            System.out.println("No hay documentos para rechazar.");
        }
    }

    public void seleccionarOpcionAdmisibilidadSi() {
        try {
            WebElement opcionAceptar = driver.findElement(By.xpath("//label[.//span[text()='Aceptar']]//input[@type='radio' and @value='true']"));
            if (opcionAceptar.isSelected()) {
                System.out.println("La opción 'Aceptar' ya está seleccionada.");
            } else {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", opcionAceptar);
                System.out.println("Se ha seleccionado la opción 'Aceptar'.");
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar la opción 'Aceptar': " + e.getMessage());
        }
    }

    public void seleccionarOpcionAdmisibilidadNo() {
        try {
            WebElement opcionRechazar = driver.findElement(By.xpath("//label[.//span[text()='Rechazar y devolver']]//input[@type='radio' and @value='false']"));
            if (opcionRechazar.isSelected()) {
                System.out.println("La opción 'No, Rechazar y devolver' ya está seleccionada.");
            } else {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", opcionRechazar);
                System.out.println("Se ha seleccionado la opción 'No, Rechazar y devolver'.");
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar la opción 'No, Rechazar y devolver': " + e.getMessage());
        }
    }

    public boolean validarDocumentosCargados() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(listaDocumentos));
            return !listaDocumentos.isEmpty();
        } catch (Exception e) {
            System.out.println("Error al validar documentos cargados: " + e.getMessage());
            return false;
        }
    }

    public void aprobarTodosLosDocumentos() {
        if (validarDocumentosCargados()) {
            System.out.println("Aprobando documentos...");
            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (WebElement botonAprobar : listaBotonesAprobarPorValor) {
                try {
                    PageFunctions.scrollToElementWithActions(driver, botonAprobar);
                    js.executeScript("arguments[0].click();", botonAprobar);
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement radioSi = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//input[@type='radio' and @value='ACEPTADO']")));
                    if (radioSi.isSelected()) {
                        System.out.println("El documento ya está aprobado.");
                    } else {
                        js.executeScript("arguments[0].click();", radioSi);
                        System.out.println("Documento aprobado.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("No se encontró el radio button para aprobar el documento: " + e.getMessage());
                } catch (ElementNotInteractableException e) {
                    System.out.println("El elemento no es interactuable en este momento: " + e.getMessage());
                } catch (TimeoutException e) {
                    System.out.println("Tiempo de espera agotado para encontrar el elemento: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error inesperado al aprobar el documento: " + e.getMessage());
                }
            }
        } else {
            System.out.println("No hay documentos para aprobar.");
        }
    }

    public void procesarAprobacionDeDocumentos() {
        if (validarDocumentosCargados()) {
            System.out.println("Documentos cargados encontrados. Procediendo a aprobar.");
            aprobarTodosLosDocumentos();
            System.out.println("Proceso de aprobación completado.");
        } else {
            System.out.println("No hay documentos para aprobar.");
        }
    }

    public void seleccionarMotivoRechazoGeneralAleatorio() {
        try {
            PageFunctions.scrollToElementWithActions(driver, selMotivoRechazo);
            selMotivoRechazo.click();
            List<WebElement> opcionesMotivoRechazoAleatorio = driver.findElements(By.xpath("//div[@class='ant-select-item-option-content']"));
            int indiceAleatorio = random.nextInt(opcionesMotivoRechazoAleatorio.size());
            opcionesMotivoRechazoAleatorio.get(indiceAleatorio).click();
            System.out.println("Motivo de rechazo seleccionado: " + opcionesMotivoRechazoAleatorio.get(indiceAleatorio).getText());
        } catch (Exception e) {
            System.out.println("Error al seleccionar motivo de rechazo: " + e.getMessage());
        }
    }

    public void ingresarMotivoRechazoGeneral() {
        try{
            String motivoRechazoGeneral;
            motivoRechazoGeneral = "Motivo Aleatorio";
            textMotivoRechazo.click();
            textMotivoRechazo.clear();
            textMotivoRechazo.sendKeys(motivoRechazoGeneral);
            System.out.println("Se ha ingresado el motivo de rechazo: " + motivoRechazoGeneral);
        }catch (Exception e){
            System.out.println("Error en el flujo: " + e.getMessage());
        }
    }

    public void rechazarDocumentosConMotivo() {
        if (!validarDocumentosCargados()) {
            System.out.println("No hay documentos para rechazar.");
            return;
        }

        System.out.println("Rechazando documentos...");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < listaBotonesRechazar.size(); i++) {
            try {
                WebElement botonRechazar = listaBotonesRechazar.get(i);

                if (botonRechazar.isSelected()) {
                    System.out.println("El documento " + (i + 1) + " ya estaba marcado como 'Rechazado'. Procediendo a ingresar el motivo.");
                } else {
                    PageFunctions.scrollToElementWithActions(driver, botonRechazar);
                    js.executeScript("arguments[0].scrollIntoView(true);", botonRechazar);
                    wait.until(ExpectedConditions.elementToBeClickable(botonRechazar));
                    js.executeScript("arguments[0].click();", botonRechazar);
                    System.out.println("Documento " + (i + 1) + " marcado como rechazado.");
                }
                WebElement motivoRechazo = esperarCampoMotivo();
                if (motivoRechazo != null) {
                    ingresarMotivo(motivoRechazo, "Se rechaza el documento por motivos internos");
                    System.out.println("Motivo de rechazo ingresado para el documento " + (i + 1));
                } else {
                    System.out.println("No se pudo encontrar el campo de motivo para el documento " + (i + 1));
                }
            } catch (Exception e) {
                System.out.println("Error al rechazar el documento " + (i + 1) + " o al ingresar el motivo: " + e.getMessage());
            }
        }
    }

    private WebElement esperarCampoMotivo() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@id, 'motivo')]")));
        } catch (Exception e) {
            System.out.println("Error al esperar el campo de motivo: " + e.getMessage());
            return null;
        }
    }

    private void ingresarMotivo(WebElement motivoRechazo, String motivo) {
        try {
            PageFunctions.scrollToElementWithActions(driver, motivoRechazo);
            motivoRechazo.clear();
            motivoRechazo.sendKeys(motivo);
            String textoIngresado = motivoRechazo.getAttribute("value");
            if (!motivo.equals(textoIngresado)) {
                System.out.println("Reintentando ingresar el motivo de rechazo...");
                motivoRechazo.clear();
                motivoRechazo.sendKeys(motivo);
            }
        } catch (Exception e) {
            System.out.println("Error al ingresar el motivo: " + e.getMessage());
        }
    }

    public void clickBtnContinuar() {
        try {
            Thread.sleep(1000);
            btnConfirmar.click();
            System.out.println("Se ha hecho click en el botón Continuar");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error en el flujo: " + e.getMessage());
        }
    }

    public void seleccionarAceptarAdmisibilidadConAprobacionDeDcoumentos(){
        try {
            aprobarDocumentosConAdmisibilidad();
            Thread.sleep(2000);
            seleccionarOpcionAdmisibilidadSi();
            Thread.sleep(2000);
            clickBtnContinuar();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error en el flujo: " + e.getMessage());
        }
    }

    public void seleccionarAceptarAdmisibilidadConRechazoDeDcoumentos(){
        try {
            rechazarDocumentosConAdmisibilidad();
            Thread.sleep(2000);
            seleccionarOpcionAdmisibilidadSi();
            Thread.sleep(5000);
            clickBtnContinuar();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error en el flujo: " + e.getMessage());
        }
    }

    public void seleccionarRechazarAdmisibilidadConAprobacionDeDcoumentos(){
        try {
            seleccionarOpcionAdmisibilidadNo();
            Thread.sleep(2000);
            procesarAprobacionDeDocumentos();
            Thread.sleep(5000);
            seleccionarMotivoRechazoGeneralAleatorio();
            Thread.sleep(2000);
            ingresarMotivoRechazoGeneral();
            Thread.sleep(2000);
            clickBtnContinuar();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error en el flujo: " + e.getMessage());
        }
    }

    public void seleccionarRechazarAdmisibilidadConRechazoDeDcoumentos(){
        try {
            seleccionarOpcionAdmisibilidadNo();
            Thread.sleep(2000);
            rechazarDocumentosConMotivo();
            Thread.sleep(5000);
            seleccionarMotivoRechazoGeneralAleatorio();
            Thread.sleep(2000);
            ingresarMotivoRechazoGeneral();
            Thread.sleep(2000);
            clickBtnContinuar();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error en el flujo: " + e.getMessage());
        }
    }
}
