package pageFactory.pages.solicitudes.ingreso;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PageFunctions;

import java.util.Random;

public class IngresoConfirmacionPage extends PageFunctions {

    Random random = new Random();

    @FindBy(xpath = "//button[contains(@class, 'ant-btn ant-btn-primary ant-btn-block btn-form mt-4')]")
    WebElement btnFinalizar;

    public IngresoConfirmacionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickBtnFinalizar() {
        try{
            btnFinalizar.click();
            System.out.println("Se ha creado el expediente");
            pausaParaPruebas();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void ingresandoDatos() throws InterruptedException {
        System.out.println("Pasa por Ingresando datos");
        clickBtnFinalizar();
    }
}
