package TestCases;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Driver;


public class Auth_Entidades {

    private final WebDriver driver;

    public Auth_Entidades() {
        driver = Driver.getDriver();
    }

    @Step("Given que el usuario quiere loguearse en <>")
    public void navegar_Sitio(String url) {
        driver.get(url);
        WebElement frmLogin = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("external-account")));
    }

    @Step("When el usuario realice el ciclo de registro en el nivel de confianza <>")
    public void realizar_Registro(String confianza) {
        WebElement frmLogin = driver.findElement(By.id("external-account"));
        frmLogin.findElement(By.xpath("//button[contains(text(),'Entrar')]")).click();
        WebElement selectDoc = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='Input_TipoIdentificacion']")));
        WebElement txtUs = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Input_Identificacion']")));
        driver.findElement(By.xpath("//button[contains(text(),'Registrar')]")).click();
        if(confianza.equals("medio")){
            driver.findElement(By.id("2")).click();
        }else if(confianza.equals("bajo")){
            driver.findElement(By.id("1")).click();
        }
        driver.findElement(By.xpath("//button[contains(text(),'Siguiente')]")).click();
        WebElement btnVolver = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Volver')]")));
        driver.findElement(By.xpath("//div[@class='card']//button[@value='register']//i[contains(text(),'AND')]")).click();
        diligenciar_Formulario(confianza);
    }

    @Step("Then el usuario diligencia registro correctamente")
    public void validar_Registro() {
        WebElement labelInfor = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Crear cuenta')]")));

    }

    public void diligenciar_Formulario(String confianza){
        if(confianza.equals("medio")){
            diligenciar_media();
        }else if(confianza.equals("bajo")){
            System.out.println("Aca va el diligenciamiento de confianza baja");
        }
    }

    public void diligenciar_media(){
        System.out.println("Valido existencia campos");
        WebElement selectElement = driver.findElement(By.id("Input_TipoIdentificacion"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue("CC");

        driver.findElement(By.id("Input_Identificacion")).sendKeys("1053820712");
        driver.findElement(By.id("Input_FechaExpedicion")).click();
        WebElement selectMes = driver.findElement(By.className("flatpickr-monthDropdown-months"));
        Select soMes = new Select(selectMes);
        soMes.selectByVisibleText("January");
//        driver.findElement(By.className("numInput cur-year")).sendKeys("2010");
        driver.findElement(By.xpath("//div[@class='flatpickr-days']//div[@class='dayContainer']//span[text()='1']")).click();

        driver.findElement(By.id("Input_Email")).sendKeys("usertest@and.gov.co");
        driver.findElement(By.id("Input_PrimerNombre")).sendKeys("N1test");
        driver.findElement(By.id("Input_SegundoNombre")).sendKeys("N2test");
        driver.findElement(By.id("Input_PrimerApellido")).sendKeys("A1test");
        driver.findElement(By.id("Input_SegundoApellido")).sendKeys("A2test");
        driver.findElement(By.id("Telefono")).sendKeys("3206280998");
        driver.findElement(By.id("Input_Direccion")).sendKeys("Avenida siempre viva");
        driver.findElement(By.xpath("//button[contains(text(),'Siguiente')]")).click();
    }
}
