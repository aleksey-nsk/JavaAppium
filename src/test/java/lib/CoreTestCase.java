package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.time.Duration;

public class CoreTestCase extends TestCase {

  protected RemoteWebDriver driver;

  @Override
  protected void setUp() throws Exception {
    System.out.print("\n\n***** Метод setUp() *****\n\n");
    super.setUp();
    driver = Platform.getInstance().getDriver();
    this.rotateScreenPortrait();
    this.skipWelcomePageForIOSApp();
    this.openWikiWebPageForMobileWeb();
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
    if (driver instanceof AppiumDriver) {
      AppiumDriver driver = (AppiumDriver) this.driver;
      driver.rotate(ScreenOrientation.PORTRAIT);
    } else {
      System.out.println("Method rotateScreenPortrait() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  protected void rotateScreenLandscape() {
    System.out.println("\nRotate Screen Landscape");
    if (driver instanceof AppiumDriver) {
      AppiumDriver driver = (AppiumDriver) this.driver;
      driver.rotate(ScreenOrientation.LANDSCAPE);
    } else {
      System.out.println("Method rotateScreenLandscape() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  protected void backgroundApp(int seconds) {
    System.out.println("\nBackground App");
    System.out.println("  seconds: " + seconds);
    if (driver instanceof AppiumDriver) {
      AppiumDriver driver = (AppiumDriver) this.driver;
      driver.runAppInBackground(Duration.ofSeconds(seconds));
    } else {
      System.out.println("Method backgroundApp() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  protected void openWikiWebPageForMobileWeb() {
    System.out.println("\nOpen Wiki Web Page For Mobile Web");
    if (Platform.getInstance().isMobileWeb()) {
      driver.get("https://en.m.wikipedia.org");
    } else {
      System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  private void skipWelcomePageForIOSApp() {
    System.out.println("\nSkip Welcome Page For iOS App");
    if (Platform.getInstance().isIOS()) {
      AppiumDriver driver = (AppiumDriver) this.driver;
      WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
      welcomePageObject.clickSkip();
    }
  }
}
