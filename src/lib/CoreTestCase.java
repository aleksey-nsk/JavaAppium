package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import java.time.Duration;

public class CoreTestCase extends TestCase {

  protected AppiumDriver driver;
  protected Platform platform;

  @Override
  protected void setUp() throws Exception {
    System.out.print("\n\n***** Метод setUp() *****\n\n");

    super.setUp();

    platform = new Platform();
    driver = platform.getDriver();

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
