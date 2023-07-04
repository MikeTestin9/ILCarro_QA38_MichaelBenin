import manager.TestNgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)

public class RegistrationTests extends TestBase {

    @BeforeMethod
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
    }
    @Test
    public void registrationNegativeWrongEmailUpperLetter(){ //shows that "Wrong email format" but registration passes.
                                                            //if to try it by hands, submit button is not active
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("Mike")
                .withLastName("Ben")
                .withEmail("Mike_" + i + "@gmail.com")
                .withPassword("Mb12345$");

        app.getUser().openRegistrationForm();

        app.getUser().fillRegistrationForm(user);

        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isWrongEmail());  //writes that test passed, but final window is registered user logged in
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
    }

    @AfterMethod
    public void postcondition(){
        app.getUser().passLoggedInWindow();
    }
}