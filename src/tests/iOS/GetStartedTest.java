package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

  @Test
  public void testPassThroughWelcome() {
    System.out.print("\n\n***** Тестовый метод testPassThroughWelcome() *****\n");

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
