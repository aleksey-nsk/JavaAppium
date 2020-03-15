import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    WebElement searchField = waitForElementPresent(
        By.id("org.wikipedia:id/search_src_text"),
        "Can not find search field"
    );

    checkTextInElement(searchField, "Search…");

    sendKeysInElement(searchField, "Java");

    waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by 'Java'",
        15
    );
  }

  private WebElement waitForElementPresent(By locator, String errorMessage) {
    return waitForElementPresent(locator, errorMessage, 5);
  }

  private WebElement waitForElementPresent(By locator, String errorMessage, long timeoutInSeconds) {
    System.out.println("Дождаться элемент: locator = '" + locator + "', таймаут = " + timeoutInSeconds + " секунд");
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(errorMessage + "\n");
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    return element;
  }

  private WebElement waitForElementAndClick(By locator, String errorMessage, long timeoutInSeconds) {
    System.out.println("Дождаться элемент, и кликнуть по нему");
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
    System.out.println("Кликнуть по элементу");
    element.click();
    return element;
  }

  private WebElement waitForElementAndSendKeys(By locator, String value, String error_message, long timeoutInSeconds) {
    System.out.println("Дождаться элемент, и ввести в него значение");
    WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
    System.out.println("Ввести в элемент значение: '" + value + "'");
    element.sendKeys(value);
    return element;
  }

  private void sendKeysInElement(WebElement element, String value) {
    System.out.println("Ввести в элемент значение: '" + value + "'");
    element.sendKeys(value);
  }

  private void checkTextInElement(WebElement element, String expectedTextInElement) {
    System.out.println("Проверить наличие в элементе текста: '" + expectedTextInElement + "'");
    String actualTextInElement = element.getAttribute("text");
    Assert.assertEquals("Элемент содержит неверный текст", expectedTextInElement, actualTextInElement);
  }
}
