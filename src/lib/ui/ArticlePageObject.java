package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

  private static final String TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']";
  private static final String FOOTER_ELEMENT = "//*[@text='View page in browser']";

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
}
