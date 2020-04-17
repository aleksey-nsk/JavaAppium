package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

  static {
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
    SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
    SEARCH_RESULT_BY_SUBSTRING_TEMPLATE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING}')]";
    SEARCH_CANCEL_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/search_close_btn']";
    SEARCH_FIELD = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']";
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
    SEARCH_RESULTS_LIST = "xpath://*[@resource-id='org.wikipedia:id/search_results_container']/*[@resource-id='org.wikipedia:id/search_results_list']";
    SEARCH_RESULTS_LIST_ITEM_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TEMPLATE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']/../*[@resource-id='org.wikipedia:id/page_list_item_description'][contains(@text, '{DESCRIPTION}')]";
  }

  public AndroidSearchPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
