package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import lib.Platform;

public class MainPageObject {

  protected RemoteWebDriver driver;

  public MainPageObject(RemoteWebDriver driver) {
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
    System.out.println("\n  Дождаться элемент, и кликнуть по нему");
    WebElement element = waitForElementPresent(locatorWithType, errorMessage, timeoutInSeconds);
    System.out.println("  Кликнуть по элементу");
    element.click();
    return element;
  }

  public void clickAddArticleToListForMobileWeb(String locatorWithType, String errorMessage, long timeoutInSeconds) throws InterruptedException {
    System.out.println("\nClick Add Article To List For Mobile Web");
    if (Platform.getInstance().isMobileWeb()) {
      Thread.sleep(2_000L);
      WebElement element = waitForElementPresent(locatorWithType, errorMessage, timeoutInSeconds);
      System.out.println("\n  Кликнуть по кнопке 'Добавить статью в список'");
      element.click();
      Thread.sleep(2_000L);
    } else {
      System.out.println("  Method clickAddArticleToListForMobileWeb() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
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

    final String attribute;
    if (Platform.getInstance().isMobileWeb()) {
      attribute = "textContent";
    } else {
      attribute = "text";
    }

    final String wordInLowerCase = word.toLowerCase();
    System.out.println("\nУбедимся, что во всех элементах, найденных по локатору: '" + locator + "', присутствует слово: '" + wordInLowerCase + "'");
    List<WebElement> elements = driver.findElements(locator);
    for (WebElement element : elements) {
      String textInElementInLowerCase = element.getAttribute(attribute).toLowerCase();
      System.out.println("  text in element in lower case: '" + textInElementInLowerCase + "'");
      if (!textInElementInLowerCase.contains(wordInLowerCase)) {
        throw new AssertionError("Текст текущего элемента не содержит слово '" + wordInLowerCase + "'");
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
    if (driver instanceof AppiumDriver) {
      TouchAction action = new TouchAction((AppiumDriver) driver);
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
    } else {
      System.out.println("Method swipeUp() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  public void swipeUpQuick() {
    System.out.println("  Метод для быстрого свайпа вверх");
    swipeUp(200);
  }

  public void scrollWebPageUp() {
    System.out.println("\nScroll Web Page Up");
    if (Platform.getInstance().isMobileWeb()) {
      System.out.println("\nЭмулировать scroll в web при помощи JavaScript");
      JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
      jsExecutor.executeScript("window.scrollBy(0, 250)");
    } else {
      System.out.println("\nMethod scrollWebPageUp() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  public void scrollWebPageTillElementVisible(String locatorWithType, String errorMessage, int maxSwipes) throws InterruptedException {
    System.out.println("\nScroll Web Page Till Element Visible");
    int alreadySwiped = 0;
    WebElement element = this.waitForElementPresent(locatorWithType, errorMessage);

    while (!this.isElementLocatedOnTheScreen(locatorWithType)) {
      Thread.sleep(500L); // пауза чтобы увидеть свайп страницы глазами
      scrollWebPageUp();
      alreadySwiped++;
      if (alreadySwiped > maxSwipes) {
        Assert.assertTrue(errorMessage, element.isDisplayed());
      }
    }
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
    System.out.println("\nIs Element Located On The Screen");

    int elementLocationByY = this.waitForElementPresent(locatorWithType, "Cannot find element by locator", 5).getLocation().getY();
    if (Platform.getInstance().isMobileWeb()) {
      JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
      Object jsResult = jsExecutor.executeScript("return window.pageYOffset");
      int jsResultInt = Integer.parseInt(jsResult.toString());
      System.out.println("\njsResultInt: " + jsResultInt);
      elementLocationByY -= jsResultInt;
    }
    System.out.println("\nelementLocationByY: " + elementLocationByY);

    final int screenSizeByY = driver.manage().window().getSize().getHeight();
    System.out.println("screenSizeByY: " + screenSizeByY);

    final boolean elementLocatedOnTheScreen = elementLocationByY < screenSizeByY;
    System.out.println("element located on the screen: " + elementLocatedOnTheScreen);
    return elementLocatedOnTheScreen;
  }

  public void clickElementToTheRightUpperCorner(String locatorWithType, String errorMessage) {
    System.out.println("\nClick Element To The Right Upper Corner");

    if (driver instanceof AppiumDriver) {
      WebElement element = this.waitForElementPresent(locatorWithType + "/..", errorMessage);
      System.out.println("  Элемент получен. Вычисляем необходимые координаты");
      int right_x = element.getLocation().getX();
      int upper_y = element.getLocation().getY();
      int lower_y = upper_y + element.getSize().getHeight();
      int middle_y = (upper_y + lower_y) / 2;
      int width = element.getSize().getWidth();

      int point_to_click_x = (right_x + width) - 3;
      int point_to_ckick_y = middle_y;
      TouchAction action = new TouchAction((AppiumDriver) driver);
      action.tap(PointOption.point(point_to_click_x, point_to_ckick_y)).perform();
    } else {
      System.out.println("Method clickElementToTheRightUpperCorner() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  public void swipeElementToLeft(String locatorWithType, String errorMessage) {
    System.out.println("\nSwipe Element To Left");

    if (driver instanceof AppiumDriver) {
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

      TouchAction action = new TouchAction((AppiumDriver) driver);
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
    } else {
      System.out.println("Method swipeElementToLeft() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  public int getAmountOfElements(String locatorWithType) {
    By locator = this.getLocatorByString(locatorWithType);
    System.out.println("  Определим количество элементов, найденных по локатору: '" + locator + "'");
    List elements = driver.findElements(locator);
    final int amountOfElements = elements.size();
    System.out.println("  Количество найденных элементов: " + amountOfElements);
    return amountOfElements;
  }

  public boolean isElementPresent(String locatorWithType) {
    System.out.println("  Присутствует ли элемент на странице?");
    System.out.println("    локатор: '" + locatorWithType + "'");
    final boolean is_element_present = getAmountOfElements(locatorWithType) > 0;
    System.out.println("  Присутствует ли элемент на странице? --> " + is_element_present);
    return is_element_present;
  }

  public void tryClickElementWithFewAttempts(String locatorWithType, String errorMessage, int amountOfAttempts) {
    System.out.println("\nTry Click Element With Few Attempts");
    int currentAttempts = 0;
    boolean needMoreAttempts = true;

    while (needMoreAttempts) {
      try {
        this.waitForElementAndClick(locatorWithType, errorMessage, 1);
        needMoreAttempts = false;
      } catch (Exception exception) {
        System.out.println("  ПЕРЕХВАЧЕНО ИСКЛЮЧЕНИЕ: " + exception.getMessage());
        if (currentAttempts > amountOfAttempts) {
          this.waitForElementAndClick(locatorWithType, errorMessage, 1);
          needMoreAttempts = false;
        }
      }
      currentAttempts++;
    }
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
    } else if (type.equalsIgnoreCase("css")) {
      return By.cssSelector(locator);
    } else {
      throw new IllegalArgumentException("Cannot get type of locator. Illegal type: '" + type + "'");
    }
  }
}
