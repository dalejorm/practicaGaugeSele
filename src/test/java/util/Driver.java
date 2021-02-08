package util;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    //    private static final String DEFAULT_BROWSER = "firefox";
    //    private static final String DEFAULT_BROWSER = "chrome";
    private static WebDriver driver;

    @BeforeSuite
    public void setupSuite() {
        if(driver!=null){
            createNavigator();
        }else{
            System.out.println("Ya creado");
        }

    }
    @BeforeScenario()
    public void setupSuite2(){
        if(driver!=null){
            System.out.println("Abierto");
        }else{
            System.out.println("Cerrado");
            createNavigator();
        }
    }

    @AfterScenario
    public void tearDown2(){
        System.out.println("Cerrado Escenario");
        driver.close();
        driver=null;
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
