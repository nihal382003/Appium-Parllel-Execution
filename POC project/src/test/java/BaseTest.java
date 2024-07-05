import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    private ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private AppiumDriverLocalService service;

    @BeforeMethod
    @Parameters({"deviceName", "platformVersion", "portNumber"})
    public void initializeDriver(String deviceName, String platformVersion, String portNumber) throws MalformedURLException {
        // Start Appium service
        //startAppiumService(portNumber);

        // Wait for Appium server to start
       // waitForAppiumServer(Integer.parseInt(portNumber));

        // Initialize driver
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.peoplehum.app");
        desiredCapabilities.setCapability("appActivity", "com.peoplehum.app.base.SplashScreenActivity");

        setDriver(new AndroidDriver<>(new URL("http://127.0.0.1:" + portNumber + "/wd/hub"), desiredCapabilities));
    }



    public void startAppiumService(String portNumber) {
        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                        .withIPAddress("127.0.0.1")
                        .usingPort(Integer.parseInt(portNumber))
        );
        service.start();
        System.out.println("Service has been started with port number: " + portNumber);
    }

    public void setDriver(AppiumDriver driver) {
        this.driver.set(driver);
    }

    public AppiumDriver getDriver() {
        return this.driver.get();
    }

    public void waitForAppiumServer(int port) {
        // Wait for Appium server to start
        long startTime = System.currentTimeMillis();
        long timeoutInMillis = 60000; // Timeout in milliseconds (adjust as needed)
        boolean isServerUp = false;
        while (!isServerUp && System.currentTimeMillis() - startTime < timeoutInMillis) {
            try {
                new URL("http://127.0.0.1:" + port + "/wd/hub").openConnection().connect();
                isServerUp = true;
            } catch (Exception e) {
                // Server is not yet up
                try {
                    Thread.sleep(1000); // Check again after 1 second
                } catch (InterruptedException ignored) {
                }
            }
        }
        if (!isServerUp) {
            throw new RuntimeException("Appium server did not start within the timeout period.");
        }
    }
}
