package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

  static {
    TITLE = "id:Java (programming language)";
    FOOTER_ELEMENT = "id:View article in browser";
    OPTION_ADD_ARTICLE_TO_LIST="xpath://XCUIElementTypeButton[@name='Save for later']";
    CLOSE_ARTICLE_BUTTON="id:Back";
  }

  public IOSArticlePageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
