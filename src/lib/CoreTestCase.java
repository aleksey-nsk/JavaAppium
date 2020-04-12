package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import java.time.Duration;

public class CoreTestCase extends TestCase {

  protected AppiumDriver driver;

  @Override
  protected void setUp() throws Exception {
    System.out.print("\n\n***** Метод setUp() *****\n\n");
    super.setUp();
    driver = Platform.getInstance().getDriver();
    this.rotateScreenPortrait();
    this.skipWelcomePageForIOSApp();
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

  private void skipWelcomePageForIOSApp() {
    System.out.println("\nSkip Welcome Page For iOS App");
    if (Platform.getInstance().isIOS()) {
      WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
      welcomePageObject.clickSkip();
    }
  }
}
