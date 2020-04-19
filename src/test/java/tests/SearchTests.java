package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

  @Test
  public void testSearch() {
    System.out.print("\n\n***** Тестовый метод testSearch() *****\n");
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForSearchResult("bject-oriented programming language");
  }

  @Test
  public void testCancelSearch() {
    System.out.print("\n\n***** Тестовый метод testCancelSearch() *****\n");
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clearSearchField();
    searchPageObject.waitForCancelButtonToAppear();
    searchPageObject.clickCancelButton();
    searchPageObject.waitForCancelButtonToDisappear();
  }

  @Test
  public void testAmountOfNotEmptySearch() {
    System.out.print("\n\n***** Тестовый метод testAmountOfNotEmptySearch() *****\n");
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    final String searchLine = "Linkin park discography";
    searchPageObject.typeSearchLine(searchLine);
    int amountOfFoundArticles = searchPageObject.getAmountOfFoundArticles();
    assertTrue("We found too few results", amountOfFoundArticles > 0);
  }

  @Test
  public void testAmountOfEmptySearch() {
    System.out.print("\n\n***** Тестовый метод testAmountOfEmptySearch() *****\n");
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    final String searchLine = "kflkdjjklfnhj";
    searchPageObject.typeSearchLine(searchLine);
    searchPageObject.waitForEmptyResultLabel();
    searchPageObject.assertThereIsNoResultOfSearch();
  }

  @Test
  public void testCheckWordInSearch() {
    System.out.print("\n\n***** Тестовый метод testCheckWordInSearch() *****\n");
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    final String searchWord = "Java";
    searchPageObject.typeSearchLine(searchWord);
    searchPageObject.waitForSearchResultsList();
    searchPageObject.checkWordInSearchList(searchWord);
  }

  @Test
  public void testSearchByTitleAndDescription() throws InterruptedException {
    System.out.print("\n\n***** Тестовый метод testSearchByTitleAndDescription() *****\n");

    final String searchLine = "Java";

    final String title1 = "Java";
    final String description1 = "sland of Indonesia";

    final String title2 = "JavaScript";
    final String description2 = "rogramming language";

    final String title3 = "Java (programming language)";
    final String description3 = "bject-oriented programming language";

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchLine);

    // Тесты для mobile web работают слишком быстро.
    // Ставлю паузу чтобы успеть увидеть "глазами"
    if (Platform.getInstance().isMobileWeb()) {
      System.out.println("\nПауза для mobile web 5 секунд ...");
      Thread.sleep(5_000L);
    }

    searchPageObject.waitForElementByTitleAndDescription(title1, description1);
    searchPageObject.waitForElementByTitleAndDescription(title2, description2);
    searchPageObject.waitForElementByTitleAndDescription(title3, description3);
  }
}
