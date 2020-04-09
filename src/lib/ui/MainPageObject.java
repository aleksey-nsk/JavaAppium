package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class MainPageObject {

  protected AppiumDriver driver;

  public MainPageObject(AppiumDriver driver) {
    this.driver = driver;
  }

  public WebElement waitForElementPresent(By locator, String errorMessage) {
    return waitForElementPresent(locator, errorMessage, 5);
  }

  public WebElement waitForElementPresent(By locator, String errorMessage, long timeoutInSeconds) {
    System.out.println("  Дождаться элемент: locator = '" + locator + "', таймаут = " + timeoutInSeconds + " секунд");
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(errorMessage + "\n");
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    return element;
  }

  public WebElement waitForElementAndClick(By locator, String errorMessage, long timeoutInSeconds) {
    System.out.println("  Дождаться элемент, и кликнуть по нему");
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
    System.out.println("  Кликнуть по элементу");
    element.click();
    return element;
  }

  public WebElement waitForElementAndSendKeys(By locator, String value, String errorMessage, long timeoutInSeconds) {
    System.out.println("  Дождаться элемент, и ввести в него значение");
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
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

  public void checkResultAmountOfElements(By locator) {
    System.out.println("Убедимся, что найдено несколько элементов по локатору: '" + locator + "'");
    List<WebElement> elements = driver.findElements(locator);
    int resultAmount = elements.size();
    System.out.println("resultAmount: " + resultAmount);
    Assert.assertTrue("Не удалось получить несколько элементов", resultAmount > 1);
  }

  public void checkWordInSearch(By locator, String word) {
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

  public boolean waitForElementNotPresent(By locator, String errorMessage, long timeoutInSeconds) {
    System.out.println("  Дождёмся отсутствия на странице элемента: locator = '" + locator + "', таймаут = " + timeoutInSeconds + " секунд");
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(errorMessage + "\n");
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  public WebElement waitForElementAndClear(By locator, String errorMessage, long timeoutInSeconds) {
    System.out.println("  Дождаться элемент, и очистить его");
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
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
        .press(x, start_y)
        .waitAction(timeOfSwipe)
        .moveTo(x, end_y)
        .release()
        .perform();
  }

  public void swipeUpQuick() {
    System.out.println("  Метод для быстрого свайпа вверх");
    swipeUp(200);
  }

  public void swipeUpToFindElement(By locator, String errorMessage, int maxSwipes) {
    System.out.println("  Свайпать, пока не достигнем элемента с локатором: '" + locator + "'");
    int alreadySwiped = 0;
    while (driver.findElements(locator).size() == 0) {
      if (alreadySwiped > maxSwipes) {
        waitForElementPresent(locator, "Can not find element by swipimg up. \n" + errorMessage, 0);
        return;
      }
      swipeUpQuick();
      alreadySwiped++;
    }
  }

  public void swipeElementToLeft(By locator, String errorMessage) {
    System.out.println("\nSwipe Element To Left");

    WebElement element = waitForElementPresent(locator, errorMessage, 10);
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
    action
        .press(right_x, middle_y)
        .waitAction(1000)
        .moveTo(left_x, middle_y)
        .release()
        .perform();
  }

  public int getAmountOfElements(By locator) {
    System.out.println("  Определим количество элементов, найденных по локатору: '" + locator + "'");
    List elements = driver.findElements(locator);
    final int amountOfElements = elements.size();
    System.out.println("  Количество найденных элементов: " + amountOfElements);
    return amountOfElements;
  }

  public void assertElementNotPresent(By locator, String errorMessage) {
    System.out.println("  Убедимся, что элементов нет");
    final int amountOfElements = getAmountOfElements(locator);
    if (amountOfElements > 0) {
      final String defaultMessage = "An element '" + locator.toString() + "' supposed to be not present.";
      throw new AssertionError(defaultMessage + " " + errorMessage);
    } else {
      System.out.println("  Проверка успешна. Элементов нет");
    }
  }

  public String waitForElementAndGetAttribute(By locator, String attribute, String errorMessage, long timeoutInSeconds) {
    System.out.println("Найдём элемент, затем получим значение его атрибуты: '" + attribute + "'");
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
    String attributeValue = element.getAttribute(attribute);
    System.out.println("attributeValue: '" + attributeValue + "'");
    return attributeValue;
  }

  public void assertElementPresent(By locator, String errorMessage) {
    System.out.println("  Убедимся, что элемент присутствует");
    final int amountOfElements = getAmountOfElements(locator);
    if (amountOfElements > 0) {
      System.out.println("  Проверка успешна. Элемент присутствует");
    } else {
      final String defaultMessage = "По локатору '" + locator.toString() + "' элемент не найден.";
      throw new AssertionError(defaultMessage + " " + errorMessage);
    }
  }
}
