import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

  private MainPageObject mainPageObject;

  protected void setUp() throws Exception {
    super.setUp();
    mainPageObject = new MainPageObject(driver);
  }

  @Test
  public void testSearch() {
    System.out.print("\n\n***** Внутри метода testSearch() *****\n\n");

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    WebElement searchField = mainPageObject.waitForElementPresent(
        By.id("org.wikipedia:id/search_src_text"),
        "Can not find search field"
    );

    mainPageObject.checkTextInElement(searchField, "Search…");

    mainPageObject.sendKeysInElement(searchField, "Java");

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by 'Java'",
        15
    );
  }

  @Test
  public void testCancelSearch() {
    System.out.print("\n\n***** Внутри метода testCancelSearch() *****\n\n");

    mainPageObject.waitForElementAndClick(
        By.id("org.wikipedia:id/search_container"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        "Java",
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']/*[@resource-id='org.wikipedia:id/search_results_list']"),
        "Не дождались элемента, содержащего список результатов поиска"
    );

    mainPageObject.checkResultAmountOfElements(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']")
    );

    mainPageObject.waitForElementAndClick(
        By.id("org.wikipedia:id/search_close_btn"),
        "Can not find 'X' to cancel search",
        5
    );

    mainPageObject.waitForElementNotPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']/*[@resource-id='org.wikipedia:id/search_results_list']"),
        "Элемент, содержащий список результатов поиска, всё ещё присутствует на странице",
        15
    );
  }

  @Test
  public void testCheckWordInSearch() {
    System.out.print("\n\n***** Внутри метода testCheckWordInSearch() *****\n\n");

    mainPageObject.waitForElementAndClick(
        By.id("org.wikipedia:id/search_container"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchWord = "Java";

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchWord,
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']/*[@resource-id='org.wikipedia:id/search_results_list']"),
        "Не дождались элемента, содержащего список результатов поиска"
    );

    mainPageObject.checkWordInSearch(
        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
        searchWord
    );
  }

  @Test
  public void testSwipeArticle() {
    System.out.print("\n\n***** Внутри метода testSwipeArticle() *****\n\n");

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        "Appium",
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
        "Can not find 'Appium' topic",
        5
    );

    mainPageObject.waitForElementPresent(
        By.id("org.wikipedia:id/view_page_title_text"),
        "Can not find article title",
        15
    );

    mainPageObject.swipeUpToFindElement(
        By.xpath("//*[@text='View page in browser']"),
        "Can not find the end of the article",
        20
    );
  }

  @Test
  public void testSaveFirstArticleToMyList() {
    System.out.print("\n\n***** Внутри метода testSaveFirstArticleToMyList() *****\n\n");

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        "Java",
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by 'Java'",
        5
    );

    mainPageObject.waitForElementPresent(
        By.id("org.wikipedia:id/view_page_title_text"),
        "Can not find article title",
        15
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']"),
        "Can not find button to open article options",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']"),
        "Не дождались контейнер, содержащий пункты меню"
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='Add to reading list']"),
        "Can not find option to add article to reading list",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.id("org.wikipedia:id/onboarding_button"),
        "Can not find 'Got it' tip overlay",
        5
    );

    mainPageObject.waitForElementAndClear(
        By.id("org.wikipedia:id/text_input"),
        "Can not find input to set name of articles folder",
        5
    );

    final String nameOfFolder = "Learning programming";

    mainPageObject.waitForElementAndSendKeys(
        By.id("org.wikipedia:id/text_input"),
        nameOfFolder,
        "Can not put text into articles folder input",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='OK']"),
        "Can not press OK button",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']"),
        "Can not close article, can not find X link",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']"),
        "Can not find navigation button to My list",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_list']"),
        "Не дождались контейнер, содержащий папки с сохранёнными статьями"
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='" + nameOfFolder + "']"),
        "Can not find created folder",
        5
    );

    mainPageObject.swipeElementToLeft(
        By.xpath("//*[@text='Java (programming language)']"),
        "Can not find saved article"
    );

    mainPageObject.waitForElementNotPresent(
        By.xpath("//*[@text='Java (programming language)']"),
        "Can not delete saved article",
        5
    );
  }

  @Test
  public void testAmountOfNotEmptySearch() {
    System.out.print("\n\n***** Внутри метода testAmountOfNotEmptySearch() *****\n\n");

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchLine = "Linkin park discography";

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    final String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

    mainPageObject.waitForElementPresent(
        By.xpath(searchResultLocator),
        "Can not find anything by the request: '" + searchLine + "'",
        15
    );

    int amountOfSearchResults = mainPageObject.getAmountOfElements(By.xpath(searchResultLocator));

    Assert.assertTrue("We found too few results", amountOfSearchResults > 0);
  }

  @Test
  public void testAmountOfEmptySearch() {
    System.out.print("\n\n***** Внутри метода testAmountOfEmptySearch() *****\n\n");

    final String searchLine = "kflkdjjklfnhj";
    final String emptyResultLabel = "//*[@text='No results found']";
    final String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath(emptyResultLabel),
        "Can not find empty result label by the request: '" + searchLine + "'",
        15
    );

    mainPageObject.assertElementNotPresent(
        By.xpath(searchResultLocator),
        "We have found some results by request: '" + searchLine + "'"
    );
  }

  @Test
  public void testChangeScreenOrientationOnSearchResults() {
    System.out.print("\n\n***** Внутри метода testChangeScreenOrientationOnSearchResults() *****\n\n");

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchLine = "Java";

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by '" + searchLine + "'",
        15
    );

    final String titleBeforeRotation = mainPageObject.waitForElementAndGetAttribute(
        By.id("org.wikipedia:id/view_page_title_text"),
        "text",
        "Can not find title of article",
        15
    );

    // Поворачиваем экран телефона
    driver.rotate(ScreenOrientation.LANDSCAPE);

    final String titleAfterRotation = mainPageObject.waitForElementAndGetAttribute(
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

    final String titleAfterSecondRotation = mainPageObject.waitForElementAndGetAttribute(
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

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchLine = "Java";

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by '" + searchLine + "'",
        5
    );

    // Отослать приложение в background
    driver.runAppInBackground(5);

    mainPageObject.waitForElementPresent(
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

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + firstArticleTitle + "']"),
        "Can not find 'Object-oriented programming language' topic, searching by '" + searchLine + "'",
        5
    );

    mainPageObject.waitForElementPresent(
        By.id("org.wikipedia:id/view_page_title_text"),
        "Can not find article title",
        10
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']"),
        "Can not find button to open article options",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']"),
        "Не дождались контейнер, содержащий пункты меню"
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='Add to reading list']"),
        "Can not find option to add article to reading list",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.id("org.wikipedia:id/onboarding_button"),
        "Can not find 'Got it' tip overlay",
        5
    );

    mainPageObject.waitForElementAndClear(
        By.id("org.wikipedia:id/text_input"),
        "Can not find input to set name of articles folder",
        5
    );

    mainPageObject.waitForElementAndSendKeys(
        By.id("org.wikipedia:id/text_input"),
        nameOfFolder,
        "Can not put text into articles folder input",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='OK']"),
        "Can not press OK button",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']"),
        "Can not close article, can not find X link",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + secondArticleTitle + "']"),
        "Can not find 'Java (software platform)' topic, searching by '" + searchLine + "'",
        5
    );

    mainPageObject.waitForElementPresent(
        By.id("org.wikipedia:id/view_page_title_text"),
        "Can not find article title",
        10
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']"),
        "Can not find button to open article options",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']"),
        "Не дождались контейнер, содержащий пункты меню"
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='Add to reading list']"),
        "Can not find option to add article to reading list",
        5
    );

    mainPageObject.waitForElementPresent(
        By.id("org.wikipedia:id/lists_container"),
        "Не дождались контейнер, содержащий папки со статьями"
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='" + nameOfFolder + "']"),
        "Can not find folder named '" + nameOfFolder + "'",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']"),
        "Can not close article, can not find X link",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']"),
        "Can not find navigation button to My list",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_list']"),
        "Не дождались контейнер, содержащий папки с сохранёнными статьями"
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='" + nameOfFolder + "']"),
        "Can not find created folder",
        5
    );

    mainPageObject.swipeElementToLeft(
        By.xpath("//*[@text='" + firstArticleTitle + "']"),
        "Can not find saved article"
    );

    mainPageObject.waitForElementNotPresent(
        By.xpath("//*[@text='" + firstArticleTitle + "']"),
        "Can not delete saved article",
        5
    );

    mainPageObject.waitForElementPresent(
        By.xpath("//*[@text='" + secondArticleTitle + "']"),
        "Can not find second article in the folder",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@text='" + secondArticleTitle + "']"),
        "Can not click on second article",
        5
    );

    final String checkSecondArticleTitle = mainPageObject.waitForElementAndGetAttribute(
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

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
        "Can not find 'Search Wikipedia' input",
        5
    );

    final String searchLine = "Java";

    mainPageObject.waitForElementAndSendKeys(
        By.xpath("//*[contains(@text, 'Search…')]"),
        searchLine,
        "Can not find 'Search…' input",
        5
    );

    mainPageObject.waitForElementAndClick(
        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
        "Can not find 'Object-oriented programming language' topic, searching by '" + searchLine + "'",
        15
    );

    final String titleLocator = "//*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@resource-id='org.wikipedia:id/view_page_title_text']";

    mainPageObject.assertElementPresent(
        By.xpath(titleLocator),
        "У статьи отсутствует элемент title"
    );
  }
}
