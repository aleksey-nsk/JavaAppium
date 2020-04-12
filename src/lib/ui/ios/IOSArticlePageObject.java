package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

  static {
    TITLE = "id:Java (programming language)";
    FOOTER_ELEMENT = "id:View article in browser";
    OPTION_ADD_ARTICLE_TO_LIST="xpath://XCUIElementTypeButton[@name='Save for later']";
    CLOSE_ARTICLE_BUTTON="id:Back";
  }

  public IOSArticlePageObject(AppiumDriver driver) {
    super(driver);
  }
}
