package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

  private static final String TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']";
  private static final String FOOTER_ELEMENT = "//*[@text='View page in browser']";
  private static final String OPTION_BUTTON = "//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']";
  private static final String CONTAINER_WITH_MENU_ITEMS = "//*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']"; //"Не дождались контейнер, содержащий пункты меню"
  private static final String OPTION_ADD_ARTICLE_TO_LIST = "//*[@text='Add to reading list']"; //"Can not find option to add article to reading list",
  private static final String ADD_TO_LIST_OVERLAY = "//*[@resource-id='org.wikipedia:id/onboarding_button']"; //"Can not find 'Got it' tip overlay",
  private static final String LIST_NAME_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']"; //"Can not find input to set name of articles folder",
  private static final String LIST_OK_BUTTON = "//*[@text='OK']"; // "Can not press OK button",
  private static final String CLOSE_ARTICLE_BUTTON = "//*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']";

  public ArticlePageObject(AppiumDriver driver) {
    super(driver);
  }

  public WebElement waitForTitleElement() {
    System.out.println("\nWait For Title Element");
    WebElement titleElement = this.waitForElementPresent(By.xpath(TITLE), "Can not find article title on page", 15);
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
    this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Can not find the end of the article", 20);
  }

  public void addArticleToMyList(String nameOfFolder) {
    System.out.println("\nAdd Article To My List");
    System.out.println("  nameOfFolder: '" + nameOfFolder + "'");
    this.waitForElementAndClick(By.xpath(OPTION_BUTTON), "Can not find button to open article options", 5);
    this.waitForElementPresent(By.xpath(CONTAINER_WITH_MENU_ITEMS), "Can not find container with menu items", 5);
    this.waitForElementAndClick(By.xpath(OPTION_ADD_ARTICLE_TO_LIST), "Can not find option to add article to reading list", 5);
    this.waitForElementAndClick(By.xpath(ADD_TO_LIST_OVERLAY), "Can not find 'Got it' tip overlay", 5);
    this.waitForElementAndClear(By.xpath(LIST_NAME_INPUT), "Can not find input to set name of articles folder", 5);
    this.waitForElementAndSendKeys(By.xpath(LIST_NAME_INPUT), nameOfFolder, "Can not put text into articles folder input", 5);
    this.waitForElementAndClick(By.xpath(LIST_OK_BUTTON), "Can not press OK button", 5);
  }

  public void closeArticle() {
    System.out.println("\nClose Article");
    this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON), "Can not close article, can not find X link", 5);
  }
}
