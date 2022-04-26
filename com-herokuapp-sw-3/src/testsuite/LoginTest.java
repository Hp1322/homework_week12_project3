package testsuite;

import broserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setup (){
        openBrowser(baseUrl);
    }
    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials(){
      //Find username field element

        sendTextToElement(By.id("username"), "tomsmith");

        //Find password field element

        sendTextToElement(By.name("password"), "SuperSecretPassword!");

        //Find log in button and click on it
        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));

        //Verify the text "Secure Area"
        //This is from requirement
        String expectedMessage = "Secure Area";

        //Actual result
        String actualMessage = getTextFromElement(By.xpath("//body/div[2]/div[1]/div[1]/h2[1]"));

        //Validate expected message and actual message
        Assert.assertEquals("unable to navigate on log in page", expectedMessage, actualMessage);

    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //Find username field
        sendTextToElement(By.name("username"), "tomsmith1");

        //Find password field
        sendTextToElement(By.id("password"), "password");

        //Find login button and click on it
        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));

        //Verify the error message “Your username is invalid!”
        //This is from requirement
        String expectedMessage1 = "Your username is invalid!\n" +
                "×";

        //Actual result
        String actualMessage1 = getTextFromElement(By.xpath("//div[@id='flash']"));

        //Validate expected message and actual message
       Assert.assertEquals("not navigate to invalid page", expectedMessage1, actualMessage1);
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        //Find username field

        sendTextToElement(By.name("username"), "tomsmith");

        //Find password field
        sendTextToElement(By.id("password"), "SuperSecretPassword");

        //Find login button and click on it

        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));

        //Verify the error message “ Your password is invalid!”
        //This is from requirement
        String expectedMessage2 = "Your password is invalid!\n" +
                "×";

        //Actual result

        String actualMessage2 = getTextFromElement(By.xpath("//div[@id='flash']"));

        //Validate expected message and actual message
        Assert.assertEquals("not navigate to invalid password page", expectedMessage2, actualMessage2);
    }
    //Closing browser
    @After
    public void tearDown(){
        closeBrowser();
    }

}
