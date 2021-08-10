package TestCases;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
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

import java.util.List;


public class Auth_Entidades {

    private final WebDriver driver;
    private DataStore globalData = DataStoreFactory.getSpecDataStore();

    public Auth_Entidades() {
        driver = Driver.getDriver();
    }

    @Step("Given que el usuario quiere loguearse en <>")
    public void navegar_Sitio(String url) {
        System.out.println(url);
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
        Table tableFields = (Table) globalData.get(Constants.TABLE+ "userRegistro");
        List<TableRow> rows = tableFields.getTableRows();
        List<String> columnNames = tableFields.getColumnNames();
        String tdoc=null, doc=null, mesExp=null, diaExp=null, anoExp=null, pnom=null,snom=null,papell=null, sapell=null, telefono=null, pais=null, departamento=null, municipio=null, direccion=null,email=null;
        for(TableRow tcampo: rows){
            if(tcampo.getCell(columnNames.get(0)).equals("Tipo de Documento")){
                tdoc=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Documento de Identidad")){
                doc=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Mes de Expedicion")){
                mesExp=tcampo.getCell(columnNames.get(1));
                System.out.println(mesExp);
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Dia de Expedicion")){
                diaExp=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Año de Expedicion")){
                anoExp=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Primer Nombre")){
                pnom=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Email")){
                email=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Segundo Nombre")){
                snom=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Primer Apellido")){
                papell=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Segundo Apellido")){
                sapell=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Telefono")){
                telefono=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Pais")){
                pais=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Departamento")){
                departamento=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Municipio")){
                municipio=tcampo.getCell(columnNames.get(1));
            }
            if(tcampo.getCell(columnNames.get(0)).equals("Direccion")){
                direccion=tcampo.getCell(columnNames.get(1));
            }
        }
        System.out.println("Valido existencia campos");
        WebElement selectElement = driver.findElement(By.id("selectTipoDocumento"));
        selectElement.click();
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue(tdoc);
        driver.findElement(By.id("Input_Identificacion")).sendKeys(doc);
        driver.findElement(By.id("Input_FechaExpedicion")).click();
        WebElement selectMes = driver.findElement(By.className("flatpickr-monthDropdown-months"));
        selectMes.click();
        Select soMes = new Select(selectMes);
        soMes.selectByVisibleText(mesExp);
//        driver.findElement(By.className("numInput cur-year")).sendKeys(anoExp);
        driver.findElement(By.xpath("//div[@class='flatpickr-days']//div[@class='dayContainer']//span[text()='"+diaExp+"']")).click();

        driver.findElement(By.id("Input_Email")).sendKeys(email);
        driver.findElement(By.id("Input_PrimerNombre")).sendKeys(pnom);
        driver.findElement(By.id("Input_SegundoNombre")).sendKeys(snom);
        driver.findElement(By.id("Input_PrimerApellido")).sendKeys(papell);
        driver.findElement(By.id("Input_SegundoApellido")).sendKeys(sapell);
        driver.findElement(By.id("Telefono")).sendKeys(telefono);

//        WebElement selectElementDpto = driver.findElement(By.xpath("//select[@id='Input_Departamento']"));
//        selectElementDpto.click();
//        Select selectObjectDpto = new Select(selectElementDpto);
//        selectObjectDpto.selectByValue(departamento);
//
//        WebElement selectElementMcip= driver.findElement(By.xpath("//select[@id='Input_Municipio']"));
//        selectElementMcip.click();
//        Select selectObjectMcip = new Select(selectElementMcip);
//        selectObjectMcip.selectByValue(municipio);

        driver.findElement(By.id("Input_Direccion")).sendKeys(direccion);
        driver.findElement(By.xpath("//button[contains(text(),'Siguiente')]")).click();
    }
}
