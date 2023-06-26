package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public HelperUser getUser;
    WebDriver wd;
    HelperUser user;
    HelperCar car;

    public HelperUser getUser() {
        return user;
    }
    public HelperCar getCar() {
        return car;
    }

    @BeforeSuite
    public void init(){
        wd = new ChromeDriver();
        user = new HelperUser(wd);
        car = new HelperCar(wd);
//        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro.web.app/search");
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown(){
 //       wd.quit();
    }

}