package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

  @Test
  public void testPassThroughWelcome() {
    System.out.print("\n\n***** Тестовый метод testPassThroughWelcome() *****\n");

    if (Platform.getInstance().isAndroid() || Platform.getInstance().isMobileWeb()) {
      System.out.println("\nSkip this test");
      return;
    }

    WelcomePageObject welcomePage = new WelcomePageObject(driver);

    welcomePage.waitForLearnMoreLink();
    welcomePage.clickNextButton();

    welcomePage.waitForNewWayToExploreText();
    welcomePage.clickNextButton();

    welcomePage.waitForAddOrEditPreferredLangText();
    welcomePage.clickNextButton();

    welcomePage.waitForLearnMoreAboutDataCollectedText();
    welcomePage.clickGetStartedButton();
  }
}
