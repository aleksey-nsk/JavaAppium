package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

  @Test
  public void testChangeScreenOrientationOnSearchResults() {
    System.out.print("\n\n***** Тестовый метод testChangeScreenOrientationOnSearchResults() *****\n");

    if (Platform.getInstance().isMobileWeb()) {
      System.out.println("\nSkip this test");
      return;
    }

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    final String titleBeforeRotation = articlePageObject.getArticleTitle();

    this.rotateScreenLandscape();
    final String titleAfterRotation = articlePageObject.getArticleTitle();
    assertEquals("Article title have been changed after screen rotation", titleBeforeRotation, titleAfterRotation);

    this.rotateScreenPortrait();
    final String titleAfterSecondRotation = articlePageObject.getArticleTitle();
    assertEquals("Article title have been changed after screen rotation", titleBeforeRotation, titleAfterSecondRotation);
  }

  @Test
  public void testCheckSearchArticleInBackground() {
    System.out.print("\n\n***** Тестовый метод testCheckSearchArticleInBackground() *****\n");

    if (Platform.getInstance().isMobileWeb()) {
      System.out.println("\nSkip this test");
      return;
    }

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForSearchResult("Object-oriented programming language");
    this.backgroundApp(5);
    searchPageObject.waitForSearchResult("Object-oriented programming language");
  }
}
