package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

  private static final String nameOfFolder = "Learning programming";
  private static final String login = "Testlogin2020";
  private static final String password = "test_password_2020";

  @Test
  public void testSaveFirstArticleToMyList() throws InterruptedException {
    System.out.print("\n\n***** Тестовый метод testSaveFirstArticleToMyList() *****\n");

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    final String articleTitle = articlePageObject.getArticleTitle();

    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToNewList(nameOfFolder);
    } else {
      articlePageObject.addArticlesToMySaved();
    }

    if (Platform.getInstance().isMobileWeb()) {
      AuthorisationPageObject auth = new AuthorisationPageObject(driver);
      auth.clickAuthButton();
      auth.enterLoginData(login, password);
      auth.submitForm();

      articlePageObject.waitForTitleElement();
      assertEquals("We are not on the same page after login", articleTitle, articlePageObject.getArticleTitle());
      articlePageObject.addArticlesToMySaved();
    }

    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIFactory.get(driver);
    navigationUI.openNavigation();
    navigationUI.clickMyList();

    MyListsPageObject listsPageObject = MyListsPageObjectFactory.get(driver);
    if (Platform.getInstance().isAndroid()) {
      listsPageObject.openFolderByName(nameOfFolder);
    }
    listsPageObject.swipeByArticleToDelete(articleTitle);
  }

  @Test
  public void testSaveTwoArticles() throws InterruptedException {
    System.out.print("\n\n***** Тестовый метод testSaveTwoArticles() *****\n");

    final String searchLine1 = "Java";
    final String searchLine2 = "Appium";
    final String nameOfFolder = "Learning programming";

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchLine1);
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    String article_title_1 = articlePageObject.getArticleTitle();
    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToNewList(nameOfFolder);
    } else {
      articlePageObject.addArticlesToMySaved();
    }
    articlePageObject.closeArticle();

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchLine2);
    searchPageObject.clickByArticleWithSubstring("Appium");

    String article_title_2;
    if (Platform.getInstance().isAndroid()) {
      articlePageObject.waitForTitleElement();
      article_title_2 = articlePageObject.getArticleTitle();
    } else {
      articlePageObject.waitForIOSTitle("Appium");
      article_title_2 = articlePageObject.getArticleIOSTitle("Appium");
    }

    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToExistingList(nameOfFolder);
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
    listsPageObject.swipeByArticleToDelete(article_title_1);

    listsPageObject.openArticleByTitle(article_title_2);

    String article_title_2_after_delete;
    if (Platform.getInstance().isAndroid()) {
      article_title_2_after_delete = articlePageObject.getArticleTitle();
    } else {
      article_title_2_after_delete = articlePageObject.getArticleIOSTitle("Appium");
    }

    assertEquals("Article title has been changed", article_title_2, article_title_2_after_delete);
  }
}
