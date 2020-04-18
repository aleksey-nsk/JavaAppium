package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

  static {
    TITLE = "css:main#content h1#section_0";
    FOOTER_ELEMENT = "css:footer.minerva-footer";
    OPTION_ADD_ARTICLE_TO_LIST = "xpath://li[@id='page-actions-watch']/a[@id='ca-watch' and contains(@class, 'mw-ui-icon-wikimedia-star-base20')]";
    OPTION_REMOVE_FROM_LIST_BUTTON = "xpath://li[@id='page-actions-watch']/a[@id='ca-watch' and contains(@class, 'watched')]";
  }

  public MWArticlePageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
