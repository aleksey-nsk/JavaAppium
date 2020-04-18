package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

  static {
    ARTICLE_BY_TITLE_TEMPLATE = "xpath://ul[contains(@class, 'watchlist')]/li[@title='{TITLE}']/a/h3";
    REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]/li[@title='{TITLE}']/a[contains(@class, 'watched')]";
  }

  public MWMyListsPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
