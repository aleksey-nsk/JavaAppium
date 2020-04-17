package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

  protected static String CONTAINER_WITH_LISTS;
  protected static String FOLDER_BY_NAME_TEMPLATE;
  protected static String ARTICLE_BY_TITLE_TEMPLATE;

  public MyListsPageObject(RemoteWebDriver driver) {
    super(driver);
  }

  // ----------------------- TEMPLATE METHODS -----------------------

  private static String getFolderXpathByName(String nameOfFolder) {
    return FOLDER_BY_NAME_TEMPLATE.replace("{FOLDER_NAME}", nameOfFolder);
  }

  private static String getSavedArticleXpathByTitle(String articleTitle) {
    return ARTICLE_BY_TITLE_TEMPLATE.replace("{TITLE}", articleTitle);
  }

  // ----------------------- TEMPLATE METHODS -----------------------

  public void openFolderByName(String nameOfFolder) {
    System.out.println("\nOpen Folder By Name");
    System.out.println("  nameOfFolder: '" + nameOfFolder + "'");
    this.waitForElementPresent(CONTAINER_WITH_LISTS, "Can not find container, which includes folders with articles", 5);
    String folderLocatorWithType = getFolderXpathByName(nameOfFolder);
    this.waitForElementAndClick(folderLocatorWithType, "Can not find folder by name: '" + nameOfFolder + "'", 5);
  }

  public void waitForArticleToAppearByTitle(String articleTitle) {
    System.out.println("\nWait For Article To Appear By Title");
    System.out.println("  articleTitle: '" + articleTitle + "'");
    String articleLocatorWithType = getSavedArticleXpathByTitle(articleTitle);
    this.waitForElementPresent(articleLocatorWithType, "Can not find saved article with title '" + articleTitle + "'", 10);
  }

  public void waitForArticleToDisappearByTitle(String articleTitle) {
    System.out.println("\nWait For Article To Disappear By Title");
    System.out.println("  articleTitle: '" + articleTitle + "'");
    String articleLocatorWithType = getSavedArticleXpathByTitle(articleTitle);
    this.waitForElementNotPresent(articleLocatorWithType, "Saved article with title '" + articleTitle + "' still present", 10);
  }

  public void swipeByArticleToDelete(String articleTitle) {
    System.out.println("\nSwipe By Article To Delete");
    System.out.println("  articleTitle: '" + articleTitle + "'");
    this.waitForArticleToAppearByTitle(articleTitle);
    String articleLocatorWithType = getSavedArticleXpathByTitle(articleTitle);
    this.swipeElementToLeft(articleLocatorWithType, "Can not find saved article");
    if (Platform.getInstance().isIOS()) {
      this.clickElementToTheRightUpperCorner(articleLocatorWithType, "Can not find saved article");
    }
    this.waitForArticleToDisappearByTitle(articleTitle);
  }

  public void openArticleByTitle(String articleTitle) {
    System.out.println("\nOpen Article By Title");
    System.out.println("  articleTitle: '" + articleTitle + "'");
    this.waitForArticleToAppearByTitle(articleTitle);
    String articleLocatorWithType = getSavedArticleXpathByTitle(articleTitle);
    System.out.println();
    this.waitForElementAndClick(articleLocatorWithType, "Can not click on article with title '" + articleTitle + "'", 5);
  }
}
