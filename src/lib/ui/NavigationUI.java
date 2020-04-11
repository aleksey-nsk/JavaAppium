package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {

  private static final String MY_LISTS_LINK = "xpath://*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']";

  public NavigationUI(AppiumDriver driver) {
    super(driver);
  }

  public void clickMyList() {
    System.out.println("\nClick My List");
    this.waitForElementAndClick(MY_LISTS_LINK, "Can not find navigation button to My Lists", 5);
  }
}
