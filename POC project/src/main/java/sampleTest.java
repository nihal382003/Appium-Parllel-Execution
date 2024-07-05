import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sampleTest {
     AppiumDriver driver;
     public sampleTest(AppiumDriver driver){
         this.driver=driver;
     }


    public boolean login() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for a maximum of 10 seconds
        driver.findElement(By.id("com.peoplehum.app:id/btn_skip")).click();


        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='com.peoplehum.app:id/et_login_ph_email_id']")));
        usernameField.sendKeys("gouthamautomation@gmail.com");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='com.peoplehum.app:id/et_login_ph_password']")));
        passwordField.sendKeys("Test@123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@resource-id=\"com.peoplehum.app:id/btn_login_ph_signin\"]")));
        loginButton.click();

        // Return true to indicate that the login method has been successfully executed
        return true;
    }
    public boolean applaunch() throws Exception{

         return true;
    }




}
