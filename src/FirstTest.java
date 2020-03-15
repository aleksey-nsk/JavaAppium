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
import java.util.List;

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

  @Test
  public void testCancelSearch() {
    System.out.print("\n\n***** Внутри метода testCancelSearch() *****\n\n");

    waitForElementAndClick(
        By.id("org.wikipedia:id/search_container"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        "Java",
        "Can not find 'Search…' input",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']/*[@resource-id='org.wikipedia:id/search_results_list']"),
        "Не дождались элемента, содержащего список результатов поиска"
    );

    checkResultAmountOfElements(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']")
    );

    waitForElementAndClick(
        By.id("org.wikipedia:id/search_close_btn"),
        "Can not find 'X' to cancel search",
        5
    );

    waitForElementNotPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']/*[@resource-id='org.wikipedia:id/search_results_list']"),
        "Элемент, содержащий список результатов поиска, всё ещё присутствует на странице",
        10
    );
  }

  @Test
  public void testCheckWordInSearch() {
    System.out.print("\n\n***** Внутри метода testCheckWordInSearch() *****\n\n");

    waitForElementAndClick(
        By.id("org.wikipedia:id/search_container"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchWord = "Java";

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchWord,
        "Can not find 'Search…' input",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']/*[@resource-id='org.wikipedia:id/search_results_list']"),
        "Не дождались элемента, содержащего список результатов поиска"
    );

    checkWordInSearch(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
        searchWord
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

  private WebElement waitForElementAndSendKeys(By locator, String value, String errorMessage, long timeoutInSeconds) {
    System.out.println("Дождаться элемент, и ввести в него значение");
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
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

  private void checkResultAmountOfElements(By locator) {
    System.out.println("Убедимся, что найдено несколько элементов по локатору: '" + locator + "'");
    List<WebElement> elements = driver.findElements(locator);
    int resultAmount = elements.size();
    System.out.println("resultAmount: " + resultAmount);
    Assert.assertTrue("Не удалось получить несколько элементов", resultAmount > 1);
  }

  private void checkWordInSearch(By locator, String word) {
    System.out.println("Убедимся, что во всех элементах, найденных по локатору: '" + locator + "', присутствует слово: '" + word + "'");
    List<WebElement> elements = driver.findElements(locator);
    for (WebElement element : elements) {
      String textInElement = element.getAttribute("text");
      System.out.println("  textInElement: " + textInElement);
      if (!textInElement.contains(word)) {
        throw new AssertionError("Текст текущего элемента не содержит слово '" + word + "'");
      }
    }
  }

  private boolean waitForElementNotPresent(By locator, String errorMessage, long timeoutInSeconds) {
    System.out.println("Дождёмся отсутствия на странице элемента: locator = '" + locator + "', таймаут = " + timeoutInSeconds + " секунд");
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(errorMessage + "\n");
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }
}
