package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

  private static final String nameOfFolder = "Learning programming";

  @Test
  public void testSaveFirstArticleToMyList() {
    System.out.print("\n\n***** Тестовый метод testSaveFirstArticleToMyList() *****\n");

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    final String articleTitle = articlePageObject.getArticleTitle();
    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToNewList(nameOfFolder);
    } else {
      articlePageObject.addArticlesToMySaved();
    }
    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIFactory.get(driver);
    navigationUI.clickMyList();

    MyListsPageObject listsPageObject = MyListsPageObjectFactory.get(driver);
    if (Platform.getInstance().isAndroid()) {
      listsPageObject.openFolderByName(nameOfFolder);
    }
    listsPageObject.swipeByArticleToDelete(articleTitle);
  }

  @Test
  public void testSaveTwoArticles() {
    System.out.print("\n\n***** Тестовый метод testSaveTwoArticles() *****\n");

    final String searchLine = "Java";
    final String firstArticleTitle = "Java (programming language)";
    final String secondArticleTitle = "Java (software platform)";
    final String nameOfFolder = "Learning programming";

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchLine);
    searchPageObject.clickByArticleWithSubstring(firstArticleTitle);

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    articlePageObject.addArticleToNewList(nameOfFolder);
    articlePageObject.closeArticle();

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchLine);
    searchPageObject.clickByArticleWithSubstring(secondArticleTitle);

    articlePageObject.waitForTitleElement();
    articlePageObject.addArticleToExistingList(nameOfFolder);
    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIFactory.get(driver);
    navigationUI.clickMyList();

    MyListsPageObject listsPageObject = MyListsPageObjectFactory.get(driver);
    listsPageObject.openFolderByName(nameOfFolder);
    listsPageObject.swipeByArticleToDelete(firstArticleTitle);
    listsPageObject.openArticleByTitle(secondArticleTitle);

    final String checkSecondArticleTitle = articlePageObject.getArticleTitle();
    assertEquals("Article title has been changed", secondArticleTitle, checkSecondArticleTitle);
  }
}
