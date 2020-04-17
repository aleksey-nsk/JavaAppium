package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

  static {
    MY_LISTS_LINK = "xpath://*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']";
  }

  public AndroidNavigationUI(RemoteWebDriver driver) {
    super(driver);
  }
}
