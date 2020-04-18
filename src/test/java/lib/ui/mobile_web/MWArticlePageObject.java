package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

  static {
    TITLE = "css:main#content h1#section_0";
    FOOTER_ELEMENT = "css:footer.minerva-footer";
    OPTION_ADD_ARTICLE_TO_LIST = "css:#page-actions-watch>#ca-watch";
  }

  public MWArticlePageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
