package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }

    }

    @Test
    public void loginPositiveUserData(){
        User user = new User()
                .withEmail("mikeben@gmail.com")
                .withPassword("Mb12345$")
                ;

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @AfterMethod
    public void postcondition(){


        if(app.getUser().isElementPresent(By.xpath("//*[@role='dialog']"))){
            app.getUser().passLoggedInWindow();
        }
        app.getUser().pause(3000);
        app.getUser().logout();

    }

//    @Test
//    public void loginPositiveUser(){
//        // User user = new User("mikeben@gmail.com","Mb12345$");
//        User user = new User()
//                .withEmail("mikeben@gmail.com")
//                .withPassword("Mb12345$")
//                ;
//
//        app.getUser().openLoginForm();       //open login form
//        app.getUser().fillLoginForm(user.getEmail(), user.getPassword());   //fill login form
//        app.getUser().submitLogin();     //click on button Yalla
//    }
// HW7
//    @Test
//    public void loginPositive(){
//        String email = "mikeben@gmail.com", password = "Mb12345$";
//        app.getUser().openLoginForm();       //open login form
//        app.getUser().fillLoginForm(email, password);   //fill login form
//        app.getUser().submitLogin();     //click on button Yalla
//        app.getUser().pause(3000);   //pause for 3 seconds
//        Assert.assertTrue(app.getUser().isLoggedSuccess());
//    }


}