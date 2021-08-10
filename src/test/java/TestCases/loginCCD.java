package TestCases;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Driver;
import util.Constants;

public class loginCCD {
    private final WebDriver driver;
    private DataStore globalData = DataStoreFactory.getSpecDataStore();

    public loginCCD() {
        driver = Driver.getDriver();
    }

    @Step("Given que el usuario quiere loguearse en CCD")
    public void que_el_usuario_usuario_quiere_loguearse_en_ccd() {
        driver.get(globalData.get(Constants.entornoWeb).toString());
        WebElement btnLogin = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-round btn-high']")));
    }

    @Step("When el activa la opcion de ingresar")
    public void el_activa_la_opcion_de_ingresar() {
        driver.findElement(By.xpath("//button[@class='btn btn-round btn-high']")).click();

    }

    @Step("And el complementa formulario con <>")
    public void el_complementa_formulario_con_ad(String usuario) {
        // Write code here that turns the phrase above into concrete actions
        WebElement selectElement = driver.findElement(By.id("idType"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue("CC");
        driver.findElement(By.id("idInput")).sendKeys(usuario);
        driver.findElement(By.id("loginbutton")).click();
    }

    @Step("And el ingresa sus credenciales <>")
    public void el_ingresa_sus(String credenciales) {
        driver.findElement(By.id("Password")).sendKeys(credenciales);
        driver.findElement(By.id("loginbutton")).click();
    }

    @Step("Then el usuario <> se logea y observa las opciones de CCD")
    public void el_se_logea_y_observa_las_opciones_de_ccd(String usuario) {
        // Write code here that turns the phrase above into concrete actions
        WebElement divtxt = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='dropdownBasic2']//div[@class='text-left text-user']")));
        String txtdiv = driver.findElement(By.xpath("//button[@id='dropdownBasic2']//div[@class='text-left text-user']")).getText();
        System.out.println("texto div " + txtdiv );
        if(txtdiv.contains("CC."+usuario)){
            System.out.println("Login usuario correcto");
        }else{
            System.out.println("Login usuario incorrecto");
        }

    }

    @Step("Then el usuario observa mensaje de usuario no registrado")
    public void el_observa_mensaje_de_usuario_no_registrado() {
        WebElement alertFaild = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='alertLogin']//div[@class='alert-line alert-line-error']")));
        String textalert = driver.findElement(By.xpath("//div[@id='alertLogin']//div[@class='alert-line alert-line-error']")).getText();
        System.out.println("texto alerta " + textalert );
        if(textalert.contains("El usuario no se encuentra registrado")){
            System.out.println("Alerta correcta, login no realizado");
        }else{
            System.out.println("Alerta incorrecta");
        }
    }
}


