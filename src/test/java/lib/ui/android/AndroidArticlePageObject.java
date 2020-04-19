package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

  static {
    TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']";
    FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
    OPTION_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageView']";
    CONTAINER_WITH_MENU_ITEMS = "xpath://*[@class='android.widget.FrameLayout']/*[@class='android.widget.ListView']";
    OPTION_ADD_ARTICLE_TO_LIST = "xpath://*[@text='Add to reading list']";
    ADD_TO_LIST_OVERLAY = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button']";
    LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']";
    LIST_OK_BUTTON = "xpath://*[@text='OK']";
    CLOSE_ARTICLE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']/*[@class='android.widget.ImageButton']";
    LISTS_CONTAINER = "xpath://*[@resource-id='org.wikipedia:id/lists_container']";
    FOLDER_BY_NAME_TEMPLATE = "xpath://*[@text='{FOLDER_NAME}']";
    EDIT_ARTICLE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/view_page_header_edit_pencil']";
  }

  public AndroidArticlePageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
