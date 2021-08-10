package util;

import com.thoughtworks.gauge.*;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import util.FileManager;
import util.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;
    FileManager fileManager = new FileManager();
    private static DataStore globalData = DataStoreFactory.getSpecDataStore();

    @BeforeSuite
    public void setupSuite() throws IOException {
        fileManager.readProperties();
        boolean isREST = Boolean.valueOf(globalData.get(Constants.ISREST).toString());
        System.out.println("Estado con "+ isREST);
        if(driver!=null){
            if(!isREST){
                createNavigator();
            }
        }else{
            System.out.println("Ya creado");
        }
    }

    @BeforeScenario()
    public void setupSuite2() throws IOException {
        fileManager.readProperties();
        boolean isREST = Boolean.valueOf(globalData.get(Constants.ISREST).toString());
        System.out.println("Estado con "+ isREST);
        if(driver!=null){
            System.out.println("Abierto");
        }else{
            System.out.println("Cerrado");
            System.out.println(isREST);
            if(!isREST){
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
        if(globalData.get(Constants.NAVEGADOR).toString().equals("firefox")){
            System.setProperty("webdriver.gecko.driver","src/test/resources/drivers/firedriver/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-private");
            driver = new FirefoxDriver(options);
        }
        if(globalData.get(Constants.entornoWeb).toString().equals("chrome")){
            System.setProperty("webdriver.gecko.driver","src/test/resources/drivers/chromedriver/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


}
