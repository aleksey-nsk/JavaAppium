package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

  static {
    SEARCH_INIT_ELEMENT = "css:button#searchIcon";
    SEARCH_INPUT = "css:form>input[type='search']";
    SEARCH_CANCEL_BUTTON = "css:div.header-action>button.cancel";
    CLEAR_SEARCH_FIELD_BUTTON = "css:button.clear";
    SEARCH_RESULT_BY_SUBSTRING_TEMPLATE = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
    SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
    SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
    SEARCH_RESULTS_LIST = "css:div.results-list-container>ul.page-list";
    SEARCH_RESULTS_LIST_ITEM_TITLE = "css:div.results-list-container>ul.page-list>li>a>h3";
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TEMPLATE = "xpath://li[@title='{TITLE}']/a/div[contains(text(), '{DESCRIPTION}')]";
  }

  public MWSearchPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
