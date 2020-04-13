package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSSearchPageObject;

public class SearchPageObjectFactory {

  public static SearchPageObject get(AppiumDriver driver) {
    System.out.println("\nSearch Page Object Factory --> method get()");
    if (Platform.getInstance().isAndroid()) {
      return new AndroidSearchPageObject(driver);
    } else {
      return new IOSSearchPageObject(driver);
    }
  }
}
