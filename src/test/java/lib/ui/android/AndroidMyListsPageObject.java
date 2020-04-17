package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

  static {
    CONTAINER_WITH_LISTS = "xpath://*[@resource-id='org.wikipedia:id/reading_list_list']";
    FOLDER_BY_NAME_TEMPLATE = "xpath://*[@text='{FOLDER_NAME}']";
    ARTICLE_BY_TITLE_TEMPLATE = "xpath://*[@text='{TITLE}']";
  }

  public AndroidMyListsPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
