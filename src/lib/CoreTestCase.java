package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

  private static final String PLATFORM_IOS = "ios";
  private static final String PLATFORM_ANDROID = "android";

  protected AppiumDriver driver;
  private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

  @Override
  protected void setUp() throws Exception {
    System.out.print("\n\n***** Метод setUp() *****\n\n");

    super.setUp();

    DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
    driver = getDriverByPlatformEnv(capabilities);

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

  private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
    System.out.println("Get Capabilities By Platform Env");

    String platform = System.getenv("PLATFORM");
    System.out.println("  platform: '" + platform + "'");

    DesiredCapabilities capabilities = new DesiredCapabilities();

    if (platform.equalsIgnoreCase(PLATFORM_ANDROID)) {
      System.out.println("  Set capabilities for android");

      final String path = new File("apks/org.wikipedia.apk").getAbsolutePath();
      System.out.println("  Путь до тестируемого приложения: '" + path + "'");

      capabilities.setCapability("platformName", "Android");
      capabilities.setCapability("deviceName", "AndroidTestDevice");
      capabilities.setCapability("platformVersion", "6.0");
      capabilities.setCapability("automationName", "Appium");
      capabilities.setCapability("appPackage", "org.wikipedia");
      capabilities.setCapability("appActivity", ".main.MainActivity");
      capabilities.setCapability("app", path);
    } else if (platform.equalsIgnoreCase(PLATFORM_IOS)) {
      System.out.println("  Set capabilities for ios");

      capabilities.setCapability("platformName", "iOS");
      capabilities.setCapability("deviceName", "iPhone SE");
      capabilities.setCapability("platformVersion", "11.3");
      capabilities.setCapability("app", "/Users/alexz/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
    } else {
      throw new Exception("Cannot get run platform from env variable. Platform value: '" + platform + "'");
    }

    return capabilities;
  }

  private AppiumDriver getDriverByPlatformEnv(DesiredCapabilities capabilities) throws Exception {
    System.out.println("\nGet Driver By Platform Env");

    String platform = System.getenv("PLATFORM");
    System.out.println("  platform: '" + platform + "'");

    AppiumDriver driver;

    if (platform.equalsIgnoreCase(PLATFORM_ANDROID)) {
      System.out.println("  Create android driver");
      driver = new AndroidDriver(new URL(AppiumURL), capabilities);
    } else if (platform.equalsIgnoreCase(PLATFORM_IOS)) {
      System.out.println("  Create ios driver");
      driver = new IOSDriver(new URL(AppiumURL), capabilities);
    } else {
      throw new Exception("Cannot create driver for this platform. Platform value: '" + platform + "'");
    }

    return driver;
  }
}
