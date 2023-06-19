package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class HelperUser extends HelperBase{


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        wd.findElement(By.xpath("//a[starts-with(@href,'/login')]")).click();

    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[1]"), email);
        type(By.xpath("//*[@id='password']"), password);
    }

    public void submitLogin(){

        click(By.cssSelector("button[type='submit']"));
    }

}