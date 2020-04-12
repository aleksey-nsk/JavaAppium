package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

  @Test
  public void testSaveFirstArticleToMyList() {
    System.out.print("\n\n***** Тестовый метод testSaveFirstArticleToMyList() *****\n");

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
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
    assertEquals("Article title has been changed", secondArticleTitle, checkSecondArticleTitle);
  }
}
