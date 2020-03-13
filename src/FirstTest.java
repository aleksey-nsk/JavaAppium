import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;

public class FirstTest {

  private AppiumDriver driver;

  @Before
  public void setUp() throws Exception {
    System.out.print("\n\n***** Внутри метода setUp() *****\n\n");

    final String path = new File("apks/org.wikipedia.apk").getAbsolutePath();
    System.out.println("Путь до тестируемого приложения: " + path);

    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "AndroidTestDevice");
    capabilities.setCapability("platformVersion", "6.0");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("appPackage", "org.wikipedia");
    capabilities.setCapability("appActivity", ".main.MainActivity");
    capabilities.setCapability("app", path);

    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  }

  @After
  public void tearDown() {
    System.out.print("\n\n***** Внутри метода tearDown() *****\n\n");
    driver.quit();
  }

  @Test
  public void firstTest() {
    System.out.print("\n\n***** Внутри метода firstTest() *****\n\n");
    System.out.println("Запускаю первый тест");
  }
}
