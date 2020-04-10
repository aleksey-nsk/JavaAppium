package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {

  protected AppiumDriver driver;
  private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

  @Override
  protected void setUp() throws Exception {
    System.out.print("\n\n***** Метод setUp() *****\n\n");

    super.setUp();

    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName", "iOS");
    capabilities.setCapability("deviceName", "iPhone SE");
    capabilities.setCapability("platformVersion", "11.3");
    capabilities.setCapability("app", "/Users/alexz/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");

    driver = new IOSDriver(new URL(AppiumURL), capabilities);

    this.rotateScreenPortrait();
  }

  @Override
  protected void tearDown() throws Exception {
    System.out.print("\n\n***** Метод tearDown() *****\n");

    this.rotateScreenPortrait();

    System.out.println("Вызываем метод quit()");
    driver.quit();

    super.tearDown();
  }

  protected void rotateScreenPortrait() {
    System.out.println("\nRotate Screen Portrait");
    driver.rotate(ScreenOrientation.PORTRAIT);
  }

  protected void rotateScreenLandscape() {
    System.out.println("\nRotate Screen Landscape");
    driver.rotate(ScreenOrientation.LANDSCAPE);
  }

  protected void backgroundApp(int seconds) {
    System.out.println("\nBackground App");
    System.out.println("  seconds: " + seconds);
    driver.runAppInBackground(Duration.ofSeconds(seconds));
  }
}
