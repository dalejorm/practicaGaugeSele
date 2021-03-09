package util;

import com.thoughtworks.gauge.*;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.FileManager;
import util.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Driver {
    //    private static final String DEFAULT_BROWSER = "firefox";
    //    private static final String DEFAULT_BROWSER = "chrome";
    private static WebDriver driver;
    FileManager fileManager = new FileManager();

    @BeforeSuite
    public void setupSuite() throws IOException {
        String isREST = fileManager.readProperties();
        if(driver!=null){
            if(isREST.equals("False")){
                createNavigator();
            }
        }else{
            System.out.println("Ya creado");
        }
    }

    @BeforeScenario()
    public void setupSuite2() throws IOException {
        String isREST = fileManager.readProperties();
        if(driver!=null){
            System.out.println("Abierto");
        }else{
            System.out.println("Cerrado");
            System.out.println(isREST);
            if(isREST.equals("False")){
                createNavigator();
            }
        }
    }

    @AfterScenario
    public void tearDown2(){
        System.out.println("Cerrado Escenario");
        if(driver == null){
            System.out.println("ya esta cerrado");
            driver=null;
        } else{
            driver.close();
            driver=null;
        }
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        if(driver == null){
            System.out.println("cerrado 2");
        }else{
            System.out.println("Abierto Fin Spec");
            driver.close();
            driver=null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void createNavigator(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
