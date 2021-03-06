package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

  protected static String SEARCH_INIT_ELEMENT;
  protected static String SEARCH_INPUT;
  protected static String SEARCH_RESULT_BY_SUBSTRING_TEMPLATE;
  protected static String SEARCH_CANCEL_BUTTON;
  protected static String SEARCH_FIELD;
  protected static String CLEAR_SEARCH_FIELD_BUTTON;
  protected static String SEARCH_RESULT_ELEMENT;
  protected static String SEARCH_EMPTY_RESULT_ELEMENT;
  protected static String SEARCH_RESULTS_LIST;
  protected static String SEARCH_RESULTS_LIST_ITEM_TITLE;
  protected static String SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TEMPLATE;

  public SearchPageObject(RemoteWebDriver driver) {
    super(driver);
  }

  // ----------------------- TEMPLATE METHODS -----------------------

  private static String getSearchResultElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TEMPLATE.replace("{SUBSTRING}", substring);
  }

  private static String getSearchResultByTitleAndDescription(String title, String description) {
    return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TEMPLATE.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
  }

  // ----------------------- TEMPLATE METHODS -----------------------

  public void initSearchInput() {
    System.out.println("\nInit Search Input");
    this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can not find 'Search Wikipedia' input", 5);
    this.waitForElementPresent(SEARCH_INPUT, "Can not find 'Search…' input", 5);
  }

  public void typeSearchLine(String searchLine) {
    System.out.println("\nType Search Line");
    System.out.println("  searchLine: '" + searchLine + "'");
    this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Can not find and send keys into 'Search…' input", 5);
  }

  public void waitForSearchResult(String substring) {
    System.out.println("\nWait For Search Result");
    String searchResultLocatorWithType = getSearchResultElement(substring);
    this.waitForElementPresent(searchResultLocatorWithType, "Can not find search result with substring: '" + substring + "'", 15);
  }

  public void clickByArticleWithSubstring(String substring) {
    System.out.println("\nClick By Article With Substring");
    System.out.println("  substring: '" + substring + "'");
    String searchResultLocatorWithType = getSearchResultElement(substring);
    this.waitForElementAndClick(searchResultLocatorWithType, "Can not find and click search result with substring: '" + substring + "'", 10);
  }

  public void clearSearchField() {
    System.out.println("\nClear Search Field");
    if (Platform.getInstance().isMobileWeb()) {
      System.out.println("\nClick X-button");
      this.waitForElementAndClick(CLEAR_SEARCH_FIELD_BUTTON, "Can not find X-button", 5);
    } else {
      System.out.println("\nClear field");
      this.waitForElementAndClear(SEARCH_FIELD, "Can not find search field", 5);
    }
  }

  public void waitForCancelButtonToAppear() {
    System.out.println("\nWait For Cancel Button To Appear");
    this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Can not find 'X' to cancel search", 5);
  }

  public void waitForCancelButtonToDisappear() {
    System.out.println("\nWait For Cancel Button To Disappear");
    this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Button 'X' is still present on the page", 10);
  }

  public void clickCancelButton() {
    System.out.println("\nClick Cancel Button");
    this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Can not find and click search cancel button", 5);
  }

  public int getAmountOfFoundArticles() {
    System.out.println("\nGet Amount Of Found Articles");
    this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Can not find anything by the request", 15);
    int amountOfFoundArticles = this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    System.out.println("  amountOfFoundArticles: " + amountOfFoundArticles);
    return amountOfFoundArticles;
  }

  public void waitForEmptyResultLabel() {
    System.out.println("\nWait For Empty Result Label");
    this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Can not find empty result element", 15);
  }

  public void assertThereIsNoResultOfSearch() {
    System.out.println("\nAssert There Is No Result Of Search");
    this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We have found some results");
  }

  public void waitForSearchResultsList() {
    System.out.println("\nWait For Search Results List");
    this.waitForElementPresent(SEARCH_RESULTS_LIST, "Can not find element, with search results list", 5);
  }

  public void checkWordInSearchList(String word) {
    System.out.println("\nCheck Word In Search List");
    System.out.println("  word: '" + word + "'");
    this.checkWordInSearch(SEARCH_RESULTS_LIST_ITEM_TITLE, word);
  }

  public void waitForElementByTitleAndDescription(String title, String description) {
    System.out.println("\nWait For Element By Title And Description");
    System.out.println("  title: '" + title + "'");
    System.out.println("  description: '" + description + "'");

    String searchResultLocatorWithType = getSearchResultByTitleAndDescription(title, description);
    System.out.println("  searchResultLocatorWithType: '" + searchResultLocatorWithType + "'");

    this.waitForElementPresent(searchResultLocatorWithType, "Can not find search result with title: '" + title + "', and description: '" + description + "'", 15);
  }
}
