package TestCases;

import com.thoughtworks.gauge.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Driver;

public class loginCCD {
    private final WebDriver driver;

    public loginCCD() {
        driver = Driver.getDriver();
    }

    @Step("Given que el usuario quiere loguearse en CCD")
    public void que_el_usuario_usuario_quiere_loguearse_en_ccd() {
        driver.get("https://qacarpetaciudadana.and.gov.co/");
        WebElement btnLogin = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn-std mr-2']")));
    }

    @Step("When el activa la opcion de ingresar")
    public void el_activa_la_opcion_de_ingresar() {
        driver.findElement(By.xpath("//button[@class='btn-std mr-2']")).click();

    }

    @Step("And el complementa formulario con <>")
    public void el_complementa_formulario_con_ad(String usuario) {
        // Write code here that turns the phrase above into concrete actions
        WebElement selectElement = driver.findElement(By.id("Input_TipoIdentificacion"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue("CC");
        driver.findElement(By.id("Input_Identificacion")).sendKeys(usuario);
        driver.findElement(By.xpath("//button[@class='btn btn-round btn-primary']")).click();
    }

    @Step("And el ingresa sus credenciales <>")
    public void el_ingresa_sus(String credenciales) {
        driver.findElement(By.id("Password")).sendKeys(credenciales);
        driver.findElement(By.id("loginbutton")).click();
    }

    @Step("Then el usuario <> se logea y observa las opciones de CCD")
    public void el_se_logea_y_observa_las_opciones_de_ccd(String usuario) {
        // Write code here that turns the phrase above into concrete actions
        WebElement textCC = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='CC." + usuario + "']")));

    }

    @Step("Then el usuario observa mensaje de usuario no registrado")
    public void el_observa_mensaje_de_usuario_no_registrado() {
        WebElement alertFaild = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='El ID Usuario no se encuentra registrado']")));
    }
}


