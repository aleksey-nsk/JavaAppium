package lib.ui.factories;

import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMyListsPageObject;
import lib.ui.ios.IOSMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {

  public static MyListsPageObject get(RemoteWebDriver driver) {
    System.out.println("\nMy Lists Page Object Factory --> method get()");
    if (Platform.getInstance().isAndroid()) {
      return new AndroidMyListsPageObject(driver);
    } else {
      return new IOSMyListsPageObject(driver);
    }
  }
}
