package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

  @Test
  public void testCompareArticleTitle() {
    System.out.print("\n\n***** Тестовый метод testCompareArticleTitle() *****\n");

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    String articleTitle = articlePageObject.getArticleTitle();

    String expectedTitle = "Java (programming language)";
    assertEquals("We see unexpected title", expectedTitle, articleTitle);
  }

  @Test
  public void testSwipeArticle() {
    System.out.print("\n\n***** Тестовый метод testSwipeArticle() *****\n");

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Appium");
    searchPageObject.clickByArticleWithSubstring("Appium");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.waitForTitleElement();
    articlePageObject.swipeToFooter();
  }

  @Test
  public void testAssertTitle() {
    System.out.print("\n\n***** Тестовый метод testAssertTitle() *****\n");

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.assertTitlePresentWithoutWait();
  }
}
