package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

  private static final String
      SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
      SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
      SEARCH_RESULT_BY_SUBSTRING_TEMPLATE = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
      SEARCH_CANCEL_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']",
      SEARCH_FIELD = "//*[@resource-id='org.wikipedia:id/search_src_text']";

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
    this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Can not find and send keys into 'Search…' input", 5);
  }

  public void waitForSearchResult(String substring) {
    System.out.println("\nWait For Search Result");
    String searchResultXpath = getSearchResultElement(substring);
    this.waitForElementPresent(By.xpath(searchResultXpath), "Can not find search result with substring: '" + substring + "'", 15);
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
}
