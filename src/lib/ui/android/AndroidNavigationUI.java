package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

  static {
    MY_LISTS_LINK = "xpath://*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']";
  }

  public AndroidNavigationUI(AppiumDriver driver) {
    super(driver);
  }
}
