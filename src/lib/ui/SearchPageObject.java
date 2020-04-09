package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

  private static final String SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]";
  private static final String SEARCH_INPUT = "//*[contains(@text, 'Search…')]";
  private static final String SEARCH_RESULT_BY_SUBSTRING_TEMPLATE = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
  private static final String SEARCH_CANCEL_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']";
  private static final String SEARCH_FIELD = "//*[@resource-id='org.wikipedia:id/search_src_text']";
  private static final String SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
  private static final String SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";
  private static final String SEARCH_RESULTS_LIST = "//*[@resource-id='org.wikipedia:id/search_results_container']/*[@resource-id='org.wikipedia:id/search_results_list']";
  private static final String SEARCH_RESULTS_LIST_ITEM_TITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']";

  public SearchPageObject(AppiumDriver driver) {
    super(driver);
  }

  // ----------------------- TEMPLATE METHODS -----------------------

  private static String getSearchResultElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TEMPLATE.replace("{SUBSTRING}", substring);
  }

  // ----------------------- TEMPLATE METHODS -----------------------

  public void initSearchInput() {
    System.out.println("\nInit Search Input");
    this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Can not find 'Search Wikipedia' input", 5);
    this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Can not find 'Search…' input", 5);
  }

  public void typeSearchLine(String searchLine) {
    System.out.println("\nType Search Line");
    System.out.println("  searchLine: '" + searchLine + "'");
    this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Can not find and send keys into 'Search…' input", 5);
  }

  public void waitForSearchResult(String substring) {
    System.out.println("\nWait For Search Result");
    String searchResultXpath = getSearchResultElement(substring);
    this.waitForElementPresent(By.xpath(searchResultXpath), "Can not find search result with substring: '" + substring + "'", 15);
  }

  public void clickByArticleWithSubstring(String substring) {
    System.out.println("\nClick By Article With Substring");
    System.out.println("  substring: '" + substring + "'");
    String searchResultXpath = getSearchResultElement(substring);
    this.waitForElementAndClick(By.xpath(searchResultXpath), "Can not find and click search result with substring: '" + substring + "'", 10);
  }

  public void clearSearchField() {
    System.out.println("\nClear Search Field");
    this.waitForElementAndClear(By.xpath(SEARCH_FIELD), "Can not find search field", 5);
  }

  public void waitForCancelButtonToAppear() {
    System.out.println("\nWait For Cancel Button To Appear");
    this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Can not find 'X' to cancel search", 5);
  }

  public void waitForCancelButtonToDisappear() {
    System.out.println("\nWait For Cancel Button To Disappear");
    this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Button 'X' is still present on the page", 10);
  }

  public void clickCancelButton() {
    System.out.println("\nClick Cancel Button");
    this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Can not find and click search cancel button", 5);
  }

  public int getAmountOfFoundArticles() {
    System.out.println("\nGet Amount Of Found Articles");
    this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Can not find anything by the request", 15);
    int amountOfFoundArticles = this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    System.out.println("  amountOfFoundArticles: " + amountOfFoundArticles);
    return amountOfFoundArticles;
  }

  public void waitForEmptyResultLabel() {
    System.out.println("\nWait For Empty Result Label");
    this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Can not find empty result element", 15);
  }

  public void assertThereIsNoResultOfSearch() {
    System.out.println("\nAssert There Is No Result Of Search");
    this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We have found some results");
  }

  public void waitForSearchResultsList() {
    System.out.println("\nWait For Search Results List");
    this.waitForElementPresent(By.xpath(SEARCH_RESULTS_LIST), "Can not find element, with search results list", 5);
  }

  public void checkWordInSearchList(String word) {
    System.out.println("\nCheck Word In Search List");
    System.out.println("  word: '" + word + "'");
    this.checkWordInSearch(By.xpath(SEARCH_RESULTS_LIST_ITEM_TITLE), word);
  }
}
