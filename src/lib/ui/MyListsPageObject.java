package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

  private static final String CONTAINER_WITH_LISTS = "//*[@resource-id='org.wikipedia:id/reading_list_list']";
  private static final String FOLDER_BY_NAME_TEMPLATE = "//*[@text='{FOLDER_NAME}']";
  private static final String ARTICLE_BY_TITLE_TEMPLATE = "//*[@text='{TITLE}']";

  public MyListsPageObject(AppiumDriver driver) {
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
    this.waitForElementPresent(By.xpath(CONTAINER_WITH_LISTS), "Can not find container, which includes folders with articles", 5);
    String folderNameXpath = getFolderXpathByName(nameOfFolder);
    this.waitForElementAndClick(By.xpath(folderNameXpath), "Can not find folder by name: '" + nameOfFolder + "'", 5);
  }

  public void waitForArticleToAppearByTitle(String articleTitle) {
    System.out.println("\nWait For Article To Appear By Title");
    System.out.println("  articleTitle: '" + articleTitle + "'");
    String articleXpath = getSavedArticleXpathByTitle(articleTitle);
    this.waitForElementPresent(By.xpath(articleXpath), "Can not find saved article with title '" + articleTitle + "'", 10);
  }

  public void waitForArticleToDisappearByTitle(String articleTitle) {
    System.out.println("\nWait For Article To Disappear By Title");
    System.out.println("  articleTitle: '" + articleTitle + "'");
    String articleXpath = getSavedArticleXpathByTitle(articleTitle);
    this.waitForElementNotPresent(By.xpath(articleXpath), "Saved article with title '" + articleTitle + "' still present", 10);
  }

  public void swipeByArticleToDelete(String articleTitle) {
    System.out.println("\nSwipe By Article To Delete");
    System.out.println("  articleTitle: '" + articleTitle + "'");
    this.waitForArticleToAppearByTitle(articleTitle);
    String articleXpath = getSavedArticleXpathByTitle(articleTitle);
    this.swipeElementToLeft(By.xpath(articleXpath), "Can not find saved article");
    this.waitForArticleToDisappearByTitle(articleTitle);
  }

  public void openArticleByTitle(String articleTitle) {
    System.out.println("\nOpen Article By Title");
    System.out.println("  articleTitle: '" + articleTitle + "'");
    this.waitForArticleToAppearByTitle(articleTitle);
    String articleXpath = getSavedArticleXpathByTitle(articleTitle);
    System.out.println();
    this.waitForElementAndClick(By.xpath(articleXpath), "Can not click on article with title '" + articleTitle + "'", 5);
  }
}
