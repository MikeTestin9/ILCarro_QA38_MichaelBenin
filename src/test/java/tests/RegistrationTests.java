package tests;

import manager.TestNgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestNgListener.class)

public class RegistrationTests extends TestBase {

    @BeforeTest
    public void precondition(){
        if(app.getUser().isLogged()) app.getUser().logout();
    }

    @Test
    public void registrationPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("Mike")
                .withLastName("Ben")
                .withEmail("mike_" + i + "@gmail.com")
                .withPassword("Mb12345$");

        app.getUser().openRegistrationForm();
        logger.info("openRegistrationForm invoked");
        app.getUser().fillRegistrationForm(user);
        logger.info("fillRegistrationForm invoked");
        app.getUser().submitLogin();
        logger.info("submitLogin invoked");
        logger.info("registrationPositive starts with credentials: login -> " + user.getEmail() + " and password -> " + user.getPassword());
        Assert.assertTrue(app.getUser().isLoggedSuccess());
        app.getUser().passLoggedInWindow();
        app.getUser().pause(3000);

    }

    @Test
    public void registrationNegativeWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("Mike")
                .withLastName("Ben")
                .withEmail("mike_" + i + "gmail.com")
                .withPassword("Mb12345$");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isWrongEmail());
        logger.info("registration failed - wrong email");
    }
    @Test
    public void registrationNegativeWrongEmailUpperLetter(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("Mike")
                .withLastName("Ben")
                .withEmail("Mike_" + i + "@gmail.com")
                .withPassword("Mb12345$");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isWrongEmail());
        logger.info("registration failed - wrong email - email has an upper letter");
    }
    @Test
    public void registrationNegativeWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("Mike")
                .withLastName("Ben")
                .withEmail("mike_" + i + "@gmail.com")
                .withPassword("Mb123455");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isWrongPassword());
        logger.info("registration failed - wrong password");
    }

    @AfterMethod
    public void postcondition(){
        app.getUser().refreshPage();
        if(app.getUser().isLogged()) app.getUser().logout();
    }
}