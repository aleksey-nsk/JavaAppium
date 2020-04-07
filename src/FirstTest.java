import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class FirstTest extends CoreTestCase {

  @Test
  public void testSearch() {
    System.out.print("\n\n***** Внутри метода testSearch() *****\n\n");

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
        15
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

  @Test
  public void testSwipeArticle() {
    System.out.print("\n\n***** Внутри метода testSwipeArticle() *****\n\n");

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        "Appium",
        "Can not find 'Search…' input",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
        "Can not find 'Appium' topic",
        5
    );

    waitForElementPresent(
        By.id("org.wikipedia:id/view_page_title_text"),
        "Can not find article title",
        15
    );

    swipeUpToFindElement(
        By.xpath("//*[@text='View page in browser']"),
        "Can not find the end of the article",
        20
    );
  }

  @Test
  public void testSaveFirstArticleToMyList() {
    System.out.print("\n\n***** Внутри метода testSaveFirstArticleToMyList() *****\n\n");

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        "Java",
        "Can not find 'Search…' input",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by 'Java'",
        5
    );

    waitForElementPresent(
        By.id("org.wikipedia:id/view_page_title_text"),
        "Can not find article title",
        15
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']"),
        "Can not find button to open article options",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']"),
        "Не дождались контейнер, содержащий пункты меню"
    );

    waitForElementAndClick(
        By.xpath("//*[@text='Add to reading list']"),
        "Can not find option to add article to reading list",
        5
    );

    waitForElementAndClick(
        By.id("org.wikipedia:id/onboarding_button"),
        "Can not find 'Got it' tip overlay",
        5
    );

    waitForElementAndClear(
        By.id("org.wikipedia:id/text_input"),
        "Can not find input to set name of articles folder",
        5
    );

    final String nameOfFolder = "Learning programming";

    waitForElementAndSendKeys(
        By.id("org.wikipedia:id/text_input"),
        nameOfFolder,
        "Can not put text into articles folder input",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@text='OK']"),
        "Can not press OK button",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']"),
        "Can not close article, can not find X link",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']"),
        "Can not find navigation button to My list",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_list']"),
        "Не дождались контейнер, содержащий папки с сохранёнными статьями"
    );

    waitForElementAndClick(
        By.xpath("//*[@text='" + nameOfFolder + "']"),
        "Can not find created folder",
        5
    );

    swipeElementToLeft(
        By.xpath("//*[@text='Java (programming language)']"),
        "Can not find saved article"
    );

    waitForElementNotPresent(
        By.xpath("//*[@text='Java (programming language)']"),
        "Can not delete saved article",
        5
    );
  }

  @Test
  public void testAmountOfNotEmptySearch() {
    System.out.print("\n\n***** Внутри метода testAmountOfNotEmptySearch() *****\n\n");

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchLine = "Linkin park discography";

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    final String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

    waitForElementPresent(
        By.xpath(searchResultLocator),
        "Can not find anything by the request: '" + searchLine + "'",
        15
    );

    int amountOfSearchResults = getAmountOfElements(By.xpath(searchResultLocator));

    Assert.assertTrue("We found too few results", amountOfSearchResults > 0);
  }

  @Test
  public void testAmountOfEmptySearch() {
    System.out.print("\n\n***** Внутри метода testAmountOfEmptySearch() *****\n\n");

    final String searchLine = "kflkdjjklfnhj";
    final String emptyResultLabel = "//*[@text='No results found']";
    final String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    waitForElementPresent(
        By.xpath(emptyResultLabel),
        "Can not find empty result label by the request: '" + searchLine + "'",
        15
    );

    assertElementNotPresent(
        By.xpath(searchResultLocator),
        "We have found some results by request: '" + searchLine + "'"
    );
  }

  @Test
  public void testChangeScreenOrientationOnSearchResults() {
    System.out.print("\n\n***** Внутри метода testChangeScreenOrientationOnSearchResults() *****\n\n");

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchLine = "Java";

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by '" + searchLine + "'",
        15
    );

    final String titleBeforeRotation = waitForElementAndGetAttribute(
        By.id("org.wikipedia:id/view_page_title_text"),
        "text",
        "Can not find title of article",
        15
    );

    // Поворачиваем экран телефона
    driver.rotate(ScreenOrientation.LANDSCAPE);

    final String titleAfterRotation = waitForElementAndGetAttribute(
        By.id("org.wikipedia:id/view_page_title_text"),
        "text",
        "Can not find title of article",
        15
    );

    Assert.assertEquals(
        "Article title have been changed after screen rotation",
        titleBeforeRotation,
        titleAfterRotation
    );

    // Поворачиваем экран телефона
    driver.rotate(ScreenOrientation.PORTRAIT);

    final String titleAfterSecondRotation = waitForElementAndGetAttribute(
        By.id("org.wikipedia:id/view_page_title_text"),
        "text",
        "Can not find title of article",
        15
    );

    Assert.assertEquals(
        "Article title have been changed after screen rotation",
        titleBeforeRotation,
        titleAfterSecondRotation
    );
  }

  @Test
  public void testCheckSearchArticleInBackground() {
    System.out.print("\n\n***** Внутри метода testCheckSearchArticleInBackground() *****\n\n");

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchLine = "Java";

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by '" + searchLine + "'",
        5
    );

    // Отослать приложение в background
    driver.runAppInBackground(5);

    waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find article after returning from background",
        5
    );
  }

  @Test
  public void testSaveTwoArticles() {
    System.out.print("\n\n***** Внутри метода testSaveTwoArticles() *****\n\n");

    final String searchLine = "Java";
    final String firstArticleTitle = "Java (programming language)";
    final String secondArticleTitle = "Java (software platform)";
    final String nameOfFolder = "Learning programming";

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + firstArticleTitle + "']"),
        "Can not find 'Object-oriented programming language' topic, searching by '" + searchLine + "'",
        5
    );

    waitForElementPresent(
        By.id("org.wikipedia:id/view_page_title_text"),
        "Can not find article title",
        10
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']"),
        "Can not find button to open article options",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']"),
        "Не дождались контейнер, содержащий пункты меню"
    );

    waitForElementAndClick(
        By.xpath("//*[@text='Add to reading list']"),
        "Can not find option to add article to reading list",
        5
    );

    waitForElementAndClick(
        By.id("org.wikipedia:id/onboarding_button"),
        "Can not find 'Got it' tip overlay",
        5
    );

    waitForElementAndClear(
        By.id("org.wikipedia:id/text_input"),
        "Can not find input to set name of articles folder",
        5
    );

    waitForElementAndSendKeys(
        By.id("org.wikipedia:id/text_input"),
        nameOfFolder,
        "Can not put text into articles folder input",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@text='OK']"),
        "Can not press OK button",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']"),
        "Can not close article, can not find X link",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + secondArticleTitle + "']"),
        "Can not find 'Java (software platform)' topic, searching by '" + searchLine + "'",
        5
    );

    waitForElementPresent(
        By.id("org.wikipedia:id/view_page_title_text"),
        "Can not find article title",
        10
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']"),
        "Can not find button to open article options",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']"),
        "Не дождались контейнер, содержащий пункты меню"
    );

    waitForElementAndClick(
        By.xpath("//*[@text='Add to reading list']"),
        "Can not find option to add article to reading list",
        5
    );

    waitForElementPresent(
        By.id("org.wikipedia:id/lists_container"),
        "Не дождались контейнер, содержащий папки со статьями"
    );

    waitForElementAndClick(
        By.xpath("//*[@text='" + nameOfFolder + "']"),
        "Can not find folder named '" + nameOfFolder + "'",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']"),
        "Can not close article, can not find X link",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']"),
        "Can not find navigation button to My list",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_list']"),
        "Не дождались контейнер, содержащий папки с сохранёнными статьями"
    );

    waitForElementAndClick(
        By.xpath("//*[@text='" + nameOfFolder + "']"),
        "Can not find created folder",
        5
    );

    swipeElementToLeft(
        By.xpath("//*[@text='" + firstArticleTitle + "']"),
        "Can not find saved article"
    );

    waitForElementNotPresent(
        By.xpath("//*[@text='" + firstArticleTitle + "']"),
        "Can not delete saved article",
        5
    );

    waitForElementPresent(
        By.xpath("//*[@text='" + secondArticleTitle + "']"),
        "Can not find second article in the folder",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@text='" + secondArticleTitle + "']"),
        "Can not click on second article",
        5
    );

    final String checkSecondArticleTitle = waitForElementAndGetAttribute(
        By.id("org.wikipedia:id/view_page_title_text"),
        "text",
        "Can not find title of article",
        10
    );

    Assert.assertEquals(
        "Article title have been changed",
        secondArticleTitle,
        checkSecondArticleTitle
    );
  }

  @Test
  public void testAssertTitle() {
    System.out.print("\n\n***** Внутри метода testAssertTitle() *****\n\n");

    waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchLine = "Java";

    waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by '" + searchLine + "'",
        15
    );

    final String titleLocator = "//*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@resource-id='org.wikipedia:id/view_page_title_text']";

    assertElementPresent(
        By.xpath(titleLocator),
        "У статьи отсутствует элемент title"
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

  private WebElement waitForElementAndClear(By locator, String errorMessage, long timeoutInSeconds) {
    System.out.println("Дождаться элемент, и очистить его");
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
    element.clear();
    return element;
  }

  protected void swipeUp(int timeOfSwipe) {
    System.out.println("Простой свайп по экрану снизу вверх");
    TouchAction action = new TouchAction(driver);
    Dimension size = driver.manage().window().getSize(); // определить размеры экрана
    int x = size.width / 2;
    int start_y = (int) (size.height * 0.8);
    int end_y = (int) (size.height * 0.2);
    System.out.println("  x: " + x);
    System.out.println("  start_y: " + start_y);
    System.out.println("  end_y: " + end_y);
    action
        .press(x, start_y)
        .waitAction(timeOfSwipe)
        .moveTo(x, end_y)
        .release()
        .perform();
  }

  protected void swipeUpQuick() {
    System.out.println("Метод для быстрого свайпа вверх");
    swipeUp(200);
  }

  protected void swipeUpToFindElement(By locator, String errorMessage, int maxSwipes) {
    System.out.println("Свайпать, пока не достигнем элемента с локатором: '" + locator + "'");
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

  protected void swipeElementToLeft(By locator, String errorMessage) {
    System.out.println("Свайп по элементу влево");

    WebElement element = waitForElementPresent(locator, errorMessage, 10);
    System.out.println("Элемент получен. Вычисляем необходимые координаты");
    int left_x = element.getLocation().getX();
    int right_x = left_x + element.getSize().getWidth();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y) / 2;
    System.out.println("  left_x: " + left_x);
    System.out.println("  right_x: " + right_x);
    System.out.println("  upper_y: " + upper_y);
    System.out.println("  lower_y: " + lower_y);
    System.out.println("  middle_y: " + middle_y);

    TouchAction action = new TouchAction(driver);
    action
        .press(right_x, middle_y)
        .waitAction(1000)
        .moveTo(left_x, middle_y)
        .release()
        .perform();
  }

  private int getAmountOfElements(By locator) {
    System.out.println("Определим количество элементов, найденных по локатору: '" + locator + "'");
    List elements = driver.findElements(locator);
    final int amountOfElements = elements.size();
    System.out.println("  amountOfElements: " + amountOfElements);
    return amountOfElements;
  }

  private void assertElementNotPresent(By locator, String errorMessage) {
    System.out.println("Убедимся, что элементов нет");
    final int amountOfElements = getAmountOfElements(locator);
    if (amountOfElements > 0) {
      final String defaultMessage = "An element '" + locator.toString() + "' supposed to be not present.";
      throw new AssertionError(defaultMessage + " " + errorMessage);
    } else {
      System.out.println("Проверка успешна. Элементов нет");
    }
  }

  private String waitForElementAndGetAttribute(By locator, String attribute, String errorMessage, long timeoutInSeconds) {
    System.out.println("Найдём элемент, затем получим значение его атрибуты: '" + attribute + "'");
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
    String attributeValue = element.getAttribute(attribute);
    System.out.println("attributeValue: '" + attributeValue + "'");
    return attributeValue;
  }

  private void assertElementPresent(By locator, String errorMessage) {
    System.out.println("Убедимся, что элемент присутствует");
    final int amountOfElements = getAmountOfElements(locator);
    if (amountOfElements > 0) {
      System.out.println("Проверка успешна. Элемент присутствует");
    } else {
      final String defaultMessage = "По локатору '" + locator.toString() + "' элемент не найден.";
      throw new AssertionError(defaultMessage + " " + errorMessage);
    }
  }
}
