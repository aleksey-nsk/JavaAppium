package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

  protected static String MY_LISTS_LINK;

  public NavigationUI(RemoteWebDriver driver) {
    super(driver);
  }

  public void clickMyList() {
    System.out.println("\nClick My List");
    this.waitForElementAndClick(MY_LISTS_LINK, "Can not find navigation button to My Lists", 5);
  }
}
