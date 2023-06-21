package manager;

import models.User;
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
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//*[@id='password']"), password);
    }
    // overloading
    public void fillLoginForm(User user) {
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//*[@id='password']"), user.getPassword());
    }
        // method signature - type + name + parameters types
    public void submitLogin(){

        wd.findElement(By.xpath("//button[@type='submit']")).submit();
        //click(By.xpath("//button[@type='submit']"));
        //click(By.cssSelector("button[type='submit']"));
    }

    public void logout() {
        click(By.xpath("//*[.=' Logout ']"));
    }
    public boolean isLogged() {

        return isElementPresent(By.xpath("//*[.=' Logout ']"));
    }

    public void passLoggedInWindow() {
     //   click(By.xpath("//button[normalize-space()='Ok']"));
        click(By.xpath("//button[@type='button']"));
    }
}