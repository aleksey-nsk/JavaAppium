package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {

  protected static String TITLE;
  protected static String FOOTER_ELEMENT;
  protected static String OPTION_BUTTON;
  protected static String CONTAINER_WITH_MENU_ITEMS;
  protected static String OPTION_ADD_ARTICLE_TO_LIST;
  protected static String ADD_TO_LIST_OVERLAY;
  protected static String LIST_NAME_INPUT;
  protected static String LIST_OK_BUTTON;
  protected static String CLOSE_ARTICLE_BUTTON;
  protected static String LISTS_CONTAINER;
  protected static String FOLDER_BY_NAME_TEMPLATE;

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

  public WebElement waitForIOSTitle(String iosTitle) {
    System.out.println("\nWait For iOS Title");
    System.out.println("  iosTitle: '" + iosTitle + "'");
    WebElement titleElement = this.waitForElementPresent(("id:" + iosTitle), "Can not find article title on page", 15);
    return titleElement;
  }

  public String getArticleTitle() {
    System.out.println("\nGet Article Title");
    WebElement titleElement = waitForTitleElement();
    if (Platform.getInstance().isAndroid()) {
      String articleTitle = titleElement.getAttribute("text");
      System.out.println("\nGet Article Title. Return article title: '" + articleTitle + "'");
      return articleTitle;
    } else {
      return titleElement.getAttribute("name");
    }
  }

  public String getArticleIOSTitle(String iosTitle) {
    System.out.println("\nGet Article iOS Title");
    if (Platform.getInstance().isIOS()) {
      WebElement titleElement = waitForIOSTitle(iosTitle);
      String articleTitle = titleElement .getAttribute("name");
      return articleTitle;
    } else {
      throw new AssertionError("Error: this method is only for iOS");
    }
  }

  public void swipeToFooter() {
    System.out.println("\nSwipe To Footer");
    if (Platform.getInstance().isAndroid()) {
      this.swipeUpToFindElement(FOOTER_ELEMENT, "Can not find the end of the article", 60);
    } else {
      this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Can not find the end of the article", 60);
    }
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

  public void addArticlesToMySaved() {
    System.out.println("\nAdd Articles To My Saved");
    this.waitForElementAndClick(OPTION_ADD_ARTICLE_TO_LIST, "Cannot find option to add article to reading list", 5);
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