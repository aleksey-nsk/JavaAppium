package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

  protected static String MY_LISTS_LINK;

  public NavigationUI(AppiumDriver driver) {
    super(driver);
  }

  public void clickMyList() {
    System.out.println("\nClick My List");
    this.waitForElementAndClick(MY_LISTS_LINK, "Can not find navigation button to My Lists", 5);
  }
}
