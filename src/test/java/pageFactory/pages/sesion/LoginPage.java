package pageFactory.pages.sesion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "normal_login_username")
    WebElement textUsuario;

    @FindBy(id = "normal_login_password")
    WebElement textPassword;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn ant-btn-primary ant-btn-block btn-form')]")
    WebElement btnIniciarSesion;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void ingresarCredenciales(String usuario, String password) {
        try {
            textUsuario.click();
            textUsuario.clear();
            textUsuario.sendKeys(usuario);
            textPassword.clear();
            textPassword.sendKeys(password);
        } catch (Exception e) {
            System.out.println("Error al ingresar credenciales: " + e.getMessage());
        }
    }

    public void clickIniciarSesion() {
        try {
            btnIniciarSesion.click();
        } catch (Exception e) {
            System.out.println("Error al hacer clic en iniciar sesión: " + e.getMessage());
        }
    }
}
