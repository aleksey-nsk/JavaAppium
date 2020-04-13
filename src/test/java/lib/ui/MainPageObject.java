package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import lib.Platform;

public class MainPageObject {

  protected AppiumDriver driver;

  public MainPageObject(AppiumDriver driver) {
    this.driver = driver;
  }

  public WebElement waitForElementPresent(String locatorWithType, String errorMessage) {
    return waitForElementPresent(locatorWithType, errorMessage, 5);
  }

  public WebElement waitForElementPresent(String locatorWithType, String errorMessage, long timeoutInSeconds) {
    By locator = this.getLocatorByString(locatorWithType);
    System.out.println("  Дождаться элемент: locator = '" + locator + "', таймаут = " + timeoutInSeconds + " секунд");
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(errorMessage + "\n");
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    return element;
  }

  public WebElement waitForElementAndClick(String locatorWithType, String errorMessage, long timeoutInSeconds) {
    System.out.println("  Дождаться элемент, и кликнуть по нему");
    WebElement element = waitForElementPresent(locatorWithType, errorMessage, timeoutInSeconds);
    System.out.println("  Кликнуть по элементу");
    element.click();
    return element;
  }

  public WebElement waitForElementAndSendKeys(String locatorWithType, String value, String errorMessage, long timeoutInSeconds) {
    System.out.println("  Дождаться элемент, и ввести в него значение");
    WebElement element = waitForElementPresent(locatorWithType, errorMessage, timeoutInSeconds);
    System.out.println("  Ввести в элемент значение: '" + value + "'");
    element.sendKeys(value);
    return element;
  }

  public void sendKeysInElement(WebElement element, String value) {
    System.out.println("Ввести в элемент значение: '" + value + "'");
    element.sendKeys(value);
  }

  public void checkTextInElement(WebElement element, String expectedTextInElement) {
    System.out.println("Проверить наличие в элементе текста: '" + expectedTextInElement + "'");
    String actualTextInElement = element.getAttribute("text");
    Assert.assertEquals("Элемент содержит неверный текст", expectedTextInElement, actualTextInElement);
  }

  public void checkResultAmountOfElements(String locatorWithType) {
    By locator = this.getLocatorByString(locatorWithType);
    System.out.println("Убедимся, что найдено несколько элементов по локатору: '" + locator + "'");
    List<WebElement> elements = driver.findElements(locator);
    int resultAmount = elements.size();
    System.out.println("resultAmount: " + resultAmount);
    Assert.assertTrue("Не удалось получить несколько элементов", resultAmount > 1);
  }

  public void checkWordInSearch(String locatorWithType, String word) {
    By locator = this.getLocatorByString(locatorWithType);
    System.out.println("  Убедимся, что во всех элементах, найденных по локатору: '" + locator + "', присутствует слово: '" + word + "'");
    List<WebElement> elements = driver.findElements(locator);
    for (WebElement element : elements) {
      String textInElement = element.getAttribute("text");
      System.out.println("    textInElement: '" + textInElement + "'");
      if (!textInElement.contains(word)) {
        throw new AssertionError("Текст текущего элемента не содержит слово '" + word + "'");
      }
    }
  }

  public boolean waitForElementNotPresent(String locatorWithType, String errorMessage, long timeoutInSeconds) {
    By locator = this.getLocatorByString(locatorWithType);
    System.out.println("  Дождёмся отсутствия на странице элемента: locator = '" + locator + "', таймаут = " + timeoutInSeconds + " секунд");
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(errorMessage + "\n");
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  public WebElement waitForElementAndClear(String locatorWithType, String errorMessage, long timeoutInSeconds) {
    System.out.println("  Дождаться элемент, и очистить его");
    WebElement element = waitForElementPresent(locatorWithType, errorMessage, timeoutInSeconds);
    element.clear();
    return element;
  }

  public void swipeUp(int timeOfSwipe) {
    System.out.println("  Простой свайп по экрану снизу вверх");
    TouchAction action = new TouchAction(driver);
    Dimension size = driver.manage().window().getSize(); // определить размеры экрана
    int x = size.width / 2;
    int start_y = (int) (size.height * 0.8);
    int end_y = (int) (size.height * 0.2);
    System.out.println("    x: " + x);
    System.out.println("    start_y: " + start_y);
    System.out.println("    end_y: " + end_y);
    action
        .press(PointOption.point(x, start_y))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
        .moveTo(PointOption.point(x, end_y))
        .release()
        .perform();
  }

  public void swipeUpQuick() {
    System.out.println("  Метод для быстрого свайпа вверх");
    swipeUp(200);
  }

  public void swipeUpToFindElement(String locatorWithType, String errorMessage, int maxSwipes) {
    By locator = this.getLocatorByString(locatorWithType);
    System.out.println("  Свайпать, пока не достигнем элемента с локатором: '" + locator + "'");
    int alreadySwiped = 0;
    while (driver.findElements(locator).size() == 0) {
      if (alreadySwiped > maxSwipes) {
        waitForElementPresent(locatorWithType, "Can not find element by swipimg up. \n" + errorMessage, 0);
        return;
      }
      swipeUpQuick();
      alreadySwiped++;
    }
  }

  public void swipeUpTillElementAppear(String locatorWithType, String errorMessage, int maxSwipes) {
    System.out.println("\nSwipe Up Till Element Appear");
    int alreadySwiped = 0;
    while (!this.isElementLocatedOnTheScreen(locatorWithType)) {
      if (alreadySwiped > maxSwipes) {
        Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locatorWithType));
      }
      swipeUpQuick();
      alreadySwiped++;
    }
  }

  public boolean isElementLocatedOnTheScreen(String locatorWithType) {
    System.out.println("Is Element Located On The Screen");
    final int elementLocationByY = this.waitForElementPresent(locatorWithType, "Cannot find element by locator", 5).getLocation().getY();
    final int screenSizeByY = driver.manage().window().getSize().getHeight();
    System.out.println("  elementLocationByY: " + elementLocationByY);
    System.out.println("  screenSizeByY: " + screenSizeByY);

    final boolean elementLocatedOnTheScreen = elementLocationByY < screenSizeByY;
    System.out.println("  element located on the screen: " + elementLocatedOnTheScreen);
    return elementLocatedOnTheScreen;
  }

  public void clickElementToTheRightUpperCorner(String locatorWithType, String errorMessage) {
    System.out.println("\nClick Element To The Right Upper Corner");

    WebElement element = this.waitForElementPresent(locatorWithType + "/..", errorMessage);
    System.out.println("  Элемент получен. Вычисляем необходимые координаты");
    int right_x = element.getLocation().getX();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y) / 2;
    int width = element.getSize().getWidth();
    int point_to_click_x = (right_x + width) - 3;
    int point_to_ckick_y = middle_y;

    TouchAction action = new TouchAction(driver);
    action.tap(PointOption.point(point_to_click_x, point_to_ckick_y)).perform();
  }

  public void swipeElementToLeft(String locatorWithType, String errorMessage) {
    System.out.println("\nSwipe Element To Left");

    WebElement element = waitForElementPresent(locatorWithType, errorMessage, 10);
    System.out.println("  Элемент получен. Вычисляем необходимые координаты");
    int left_x = element.getLocation().getX();
    int right_x = left_x + element.getSize().getWidth();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y) / 2;
    System.out.println("    left_x: " + left_x);
    System.out.println("    right_x: " + right_x);
    System.out.println("    upper_y: " + upper_y);
    System.out.println("    lower_y: " + lower_y);
    System.out.println("    middle_y: " + middle_y);

    TouchAction action = new TouchAction(driver);
    action.press(PointOption.point(right_x, middle_y));
    action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)));
    if (Platform.getInstance().isAndroid()) {
      action.moveTo(PointOption.point(left_x, middle_y));
    } else {
      int offsetX = (-1 * element.getSize().getWidth());
      action.moveTo(PointOption.point(offsetX, 0));
    }
    action.release();
    action.perform();
  }

  public int getAmountOfElements(String locatorWithType) {
    By locator = this.getLocatorByString(locatorWithType);
    System.out.println("  Определим количество элементов, найденных по локатору: '" + locator + "'");
    List elements = driver.findElements(locator);
    final int amountOfElements = elements.size();
    System.out.println("  Количество найденных элементов: " + amountOfElements);
    return amountOfElements;
  }

  public void assertElementNotPresent(String locatorWithType, String errorMessage) {
    System.out.println("  Убедимся, что элементов нет");
    final int amountOfElements = getAmountOfElements(locatorWithType);
    if (amountOfElements > 0) {
      final String defaultMessage = "An element '" + locatorWithType + "' supposed to be not present.";
      throw new AssertionError(defaultMessage + " " + errorMessage);
    } else {
      System.out.println("  Проверка успешна. Элементов нет");
    }
  }

  public String waitForElementAndGetAttribute(String locatorWithType, String attribute, String errorMessage, long timeoutInSeconds) {
    System.out.println("Найдём элемент, затем получим значение его атрибуты: '" + attribute + "'");
    WebElement element = waitForElementPresent(locatorWithType, errorMessage, timeoutInSeconds);
    String attributeValue = element.getAttribute(attribute);
    System.out.println("attributeValue: '" + attributeValue + "'");
    return attributeValue;
  }

  public void assertElementPresent(String locatorWithType, String errorMessage) {
    System.out.println("  Убедимся, что элемент присутствует");
    final int amountOfElements = getAmountOfElements(locatorWithType);
    if (amountOfElements > 0) {
      System.out.println("  Проверка успешна. Элемент присутствует");
    } else {
      final String defaultMessage = "По локатору '" + locatorWithType + "' элемент не найден.";
      throw new AssertionError(defaultMessage + " " + errorMessage);
    }
  }

  private By getLocatorByString(String locatorWithType) {
    System.out.println("  Get Locator By String");
    System.out.println("    locatorWithType: '" + locatorWithType + "'");

    String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
    String type = explodedLocator[0];
    String locator = explodedLocator[1];
    System.out.println("    type: '" + type + "'");
    System.out.println("    locator: '" + locator + "'");

    if (type.equalsIgnoreCase("xpath")) {
      return By.xpath(locator);
    } else if (type.equalsIgnoreCase("id")) {
      return By.id(locator);
    } else {
      throw new IllegalArgumentException("Cannot get type of locator. Illegal type: '" + type + "'");
    }
  }
}
