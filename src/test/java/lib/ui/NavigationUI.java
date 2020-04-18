package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

  protected static String MY_LISTS_LINK;
  protected static String OPEN_NAVIGATION;

  public NavigationUI(RemoteWebDriver driver) {
    super(driver);
  }

  public void openNavigation() {
    System.out.println("\nOpen Navigation");
    if (Platform.getInstance().isMobileWeb()) {
      this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5);
    } else {
      System.out.println("Method openNavigation() does nothing for platform: '" + Platform.getInstance().getPlatformVar() + "'");
    }
  }

  public void clickMyList() {
    System.out.println("\nClick My List");
    if (Platform.getInstance().isMobileWeb()) {
      this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Can not find navigation button to My List", 10);
    } else {
      this.waitForElementAndClick(MY_LISTS_LINK, "Can not find navigation button to My List", 5);
    }
  }
}
