package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class HelperUser extends HelperBase{


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        wd.findElement(By.xpath("//a[starts-with(@href,'/login')]")).click();

    }
    public void openRegistrationForm(){
        wd.findElement(By.xpath("//*[.=' Sign up ']")).click();
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
    public void fillRegistrationForm(User user){
        type(By.xpath("//input[@id='name']"), user.getName());
        type(By.xpath("//input[@id='lastName']"), user.getLastName());
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
        clickCheckbox();
    }
    // method signature - type + name + parameters types
    public void clickCheckbox(){
        System.out.println("Clicked checkbox");
        // variant 1
       click(By.cssSelector("label[for='terms-of-use']"));
        // variant 2 ------------------------- if to do this var. wrong password fails cuz there is no click
//        JavascriptExecutor js = (JavascriptExecutor) wd;
//        js.executeScript("document.querySelector('#terms-of-use').click();");
        // variant 3
//        Rectangle rect = wd.findElement(By.cssSelector("div.checkbox-container")).getRect();
//        int x = rect.getX() + 5;
//        int y = rect.getY() + rect.getHeight() / 4;
//        Actions actions = new Actions(wd);
//        actions.moveByOffset(x, y).click().perform();
    }

    public void submitLogin(){

        //wd.findElement(By.xpath("//button[@type='submit']")).submit();
        //click(By.xpath("//button[text()='Y’alla!']"));
       //wd.findElement(By.xpath("//button[text()='Y’alla!']")).submit();
        wd.findElement(By.xpath("//button[text()='Y’alla!']")).click();
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
        click(By.xpath("//button[@type='button']"));
    }

    public boolean isLoggedSuccess() {
        return isElementPresent(By.xpath("//h2[contains(text(),'success')]"));
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submitLogin();
        passLoggedInWindow();
    }

    public boolean isWrongEmail() {
        return isElementPresent(By.xpath("//*[contains(text(),'Wrong email format')]"));
    }
    public boolean isWrongPassword() {
        return isElementPresent(By.xpath("//*[contains(text(),'Password must contain')]"));
    }
}