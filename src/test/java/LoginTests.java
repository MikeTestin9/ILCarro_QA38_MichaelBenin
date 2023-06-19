import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void loginPositive(){
        String email = "mikeben@gmail.com", password = "Mb12345$";
        app.getUser().openLoginForm();       //open login form
        app.getUser().fillLoginForm(email, password);   //fill login form
        app.getUser().submitLogin();     //click on button Yalla
        app.getUser().pause(3000);   //pause for 3 seconds
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[normalize-space()='Ok']")));

    }
//    @Test
//    public void loginPositiveTestBase(){
//        String email = "mb@gmail.com", password = "Mb12345$";
//        app.getUser().openLoginForm();       //open login form
//        app.getUser().fillLoginForm(email, password);   //fill login form
//        app.getUser().submitLogin();     //click on button Login
//        app.getUser().pause(3000);   //pause for 3 seconds
////        Assert.assertTrue(wd.findElements(By.xpath("//button")).size() > 0);
//        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
}