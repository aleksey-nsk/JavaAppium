import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;

public class FirstTest extends CoreTestCase {

  private MainPageObject mainPageObject;

  protected void setUp() throws Exception {
    super.setUp();
    mainPageObject = new MainPageObject(driver);
  }

  @Test
  public void testSearch() {
    System.out.print("\n\n***** Тестовый метод testSearch() *****\n");
    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForSearchResult("Object-oriented programming language");
  }

  @Test
  public void testCancelSearch() {
    System.out.print("\n\n***** Тестовый метод testCancelSearch() *****\n");
    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clearSearchField();
    searchPageObject.waitForCancelButtonToAppear();
    searchPageObject.clickCancelButton();
    searchPageObject.waitForCancelButtonToDisappear();
  }

  @Test
  public void testCheckWordInSearch() {
    System.out.print("\n\n***** Тестовый метод testCheckWordInSearch() *****\n");
    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    final String searchWord = "Java";
    searchPageObject.typeSearchLine(searchWord);
    searchPageObject.waitForSearchResultsList();
    searchPageObject.checkWordInSearchList(searchWord);
  }

  @Test
  public void testCompareArticleTitle() {
    System.out.print("\n\n***** Тестовый метод testCompareArticleTitle() *****\n");

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    String articleTitle = articlePageObject.getArticleTitle();

    String expectedTitle = "Java (programming language)";
    Assert.assertEquals("We see unexpected title", expectedTitle, articleTitle);
  }

  @Test
  public void testSwipeArticle() {
    System.out.print("\n\n***** Тестовый метод testSwipeArticle() *****\n");

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Appium");
    searchPageObject.clickByArticleWithSubstring("Appium");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.waitForTitleElement();
    articlePageObject.swipeToFooter();
  }

  @Test
  public void testSaveFirstArticleToMyList() {
    System.out.print("\n\n***** Тестовый метод testSaveFirstArticleToMyList() *****\n");

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.waitForTitleElement();
    final String articleTitle = articlePageObject.getArticleTitle();
    final String nameOfFolder = "Learning programming";
    articlePageObject.addArticleToNewList(nameOfFolder);
    articlePageObject.closeArticle();

    NavigationUI navigationUI = new NavigationUI(driver);
    navigationUI.clickMyList();

    MyListsPageObject listsPageObject = new MyListsPageObject(driver);
    listsPageObject.openFolderByName(nameOfFolder);
    listsPageObject.swipeByArticleToDelete(articleTitle);
  }

  @Test
  public void testAmountOfNotEmptySearch() {
    System.out.print("\n\n***** Тестовый метод testAmountOfNotEmptySearch() *****\n");
    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    final String searchLine = "Linkin park discography";
    searchPageObject.typeSearchLine(searchLine);
    int amountOfFoundArticles = searchPageObject.getAmountOfFoundArticles();
    Assert.assertTrue("We found too few results", amountOfFoundArticles > 0);
  }

  @Test
  public void testAmountOfEmptySearch() {
    System.out.print("\n\n***** Тестовый метод testAmountOfEmptySearch() *****\n");
    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    final String searchLine = "kflkdjjklfnhj";
    searchPageObject.typeSearchLine(searchLine);
    searchPageObject.waitForEmptyResultLabel();
    searchPageObject.assertThereIsNoResultOfSearch();
  }

  @Test
  public void testChangeScreenOrientationOnSearchResults() {
    System.out.print("\n\n***** Тестовый метод testChangeScreenOrientationOnSearchResults() *****\n");

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    final String titleBeforeRotation = articlePageObject.getArticleTitle();

    this.rotateScreenLandscape();
    final String titleAfterRotation = articlePageObject.getArticleTitle();
    Assert.assertEquals("Article title have been changed after screen rotation", titleBeforeRotation, titleAfterRotation);

    this.rotateScreenPortrait();
    final String titleAfterSecondRotation = articlePageObject.getArticleTitle();
    Assert.assertEquals("Article title have been changed after screen rotation", titleBeforeRotation, titleAfterSecondRotation);
  }

  @Test
  public void testCheckSearchArticleInBackground() {
    System.out.print("\n\n***** Тестовый метод testCheckSearchArticleInBackground() *****\n");
    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForSearchResult("Object-oriented programming language");
    this.backgroundApp(5);
    searchPageObject.waitForSearchResult("Object-oriented programming language");
  }

  @Test
  public void testSaveTwoArticles() {
    System.out.print("\n\n***** Тестовый метод testSaveTwoArticles() *****\n");

    final String searchLine = "Java";
    final String firstArticleTitle = "Java (programming language)";
    final String secondArticleTitle = "Java (software platform)";
    final String nameOfFolder = "Learning programming";

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchLine);
    searchPageObject.clickByArticleWithSubstring(firstArticleTitle);

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.waitForTitleElement();
    articlePageObject.addArticleToNewList(nameOfFolder);
    articlePageObject.closeArticle();

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchLine);
    searchPageObject.clickByArticleWithSubstring(secondArticleTitle);

    articlePageObject.waitForTitleElement();
    articlePageObject.addArticleToExistingList(nameOfFolder);
    articlePageObject.closeArticle();

    NavigationUI navigationUI = new NavigationUI(driver);
    navigationUI.clickMyList();

    MyListsPageObject listsPageObject = new MyListsPageObject(driver);
    listsPageObject.openFolderByName(nameOfFolder);
    listsPageObject.swipeByArticleToDelete(firstArticleTitle);
    listsPageObject.openArticleByTitle(secondArticleTitle);

    final String checkSecondArticleTitle = articlePageObject.getArticleTitle();
    Assert.assertEquals("Article title has been changed", secondArticleTitle, checkSecondArticleTitle);
  }

  @Test
  public void testAssertTitle() {
    System.out.print("\n\n***** Тестовый метод testAssertTitle() *****\n");

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.assertTitlePresentWithoutWait();
  }
}
