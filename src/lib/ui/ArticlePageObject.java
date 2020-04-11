package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

  private static final String TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']";
  private static final String FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
  private static final String OPTION_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']";
  private static final String CONTAINER_WITH_MENU_ITEMS = "xpath://*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']";
  private static final String OPTION_ADD_ARTICLE_TO_LIST = "xpath://*[@text='Add to reading list']";
  private static final String ADD_TO_LIST_OVERLAY = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button']";
  private static final String LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']";
  private static final String LIST_OK_BUTTON = "xpath://*[@text='OK']";
  private static final String CLOSE_ARTICLE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']";
  private static final String LISTS_CONTAINER = "xpath://*[@resource-id='org.wikipedia:id/lists_container']";
  private static final String FOLDER_BY_NAME_TEMPLATE = "xpath://*[@text='{FOLDER_NAME}']";

  public ArticlePageObject(AppiumDriver driver) {
    super(driver);
  }

  // ----------------------- TEMPLATE METHODS -----------------------

  private static String getFolderElement(String folderName) {
    return FOLDER_BY_NAME_TEMPLATE.replace("{FOLDER_NAME}", folderName);
  }

  // ----------------------- TEMPLATE METHODS -----------------------

  public WebElement waitForTitleElement() {
    System.out.println("\nWait For Title Element");
    WebElement titleElement = this.waitForElementPresent(TITLE, "Can not find article title on page", 15);
    return titleElement;
  }

  public String getArticleTitle() {
    System.out.println("\nGet Article Title");
    WebElement titleElement = waitForTitleElement();
    String articleTitle = titleElement.getAttribute("text");
    System.out.println("\nGet Article Title. Return article title: '" + articleTitle + "'");
    return articleTitle;
  }

  public void swipeToFooter() {
    System.out.println("\nSwipe To Footer");
    this.swipeUpToFindElement(FOOTER_ELEMENT, "Can not find the end of the article", 20);
  }

  public void addArticleToNewList(String nameOfFolder) {
    System.out.println("\nAdd Article To New List");
    System.out.println("  nameOfFolder: '" + nameOfFolder + "'");

    this.waitForElementAndClick(OPTION_BUTTON, "Can not find button to open article options", 5);
    this.waitForElementPresent(CONTAINER_WITH_MENU_ITEMS, "Can not find container with menu items", 5);
    this.waitForElementAndClick(OPTION_ADD_ARTICLE_TO_LIST, "Can not find option to add article to reading list", 5);

    this.waitForElementAndClick(ADD_TO_LIST_OVERLAY, "Can not find 'Got it' tip overlay", 5);
    this.waitForElementAndClear(LIST_NAME_INPUT, "Can not find input to set name of articles folder", 5);
    this.waitForElementAndSendKeys(LIST_NAME_INPUT, nameOfFolder, "Can not put text into articles folder input", 5);
    this.waitForElementAndClick(LIST_OK_BUTTON, "Can not press OK button", 5);
  }

  public void closeArticle() {
    System.out.println("\nClose Article");
    this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Can not close article, can not find X link", 5);
  }

  public void addArticleToExistingList(String nameOfFolder) {
    System.out.println("\nAdd Article To Existing List");
    System.out.println("  nameOfFolder: '" + nameOfFolder + "'");

    this.waitForElementAndClick(OPTION_BUTTON, "Can not find button to open article options", 5);
    this.waitForElementPresent(CONTAINER_WITH_MENU_ITEMS, "Can not find container with menu items", 5);
    this.waitForElementAndClick(OPTION_ADD_ARTICLE_TO_LIST, "Can not find option to add article to reading list", 5);

    this.waitForElementPresent(LISTS_CONTAINER, "Can not find container, which include folders with articles", 5);
    String folderLocatorWithType = getFolderElement(nameOfFolder);
    this.waitForElementAndClick(folderLocatorWithType, "Can not find folder with name '" + nameOfFolder + "'", 5);
  }

  public void assertTitlePresentWithoutWait() {
    System.out.println("\nAssert Title Present Without Wait");
    this.assertElementPresent(TITLE, "This article has not got element title");
  }
}
