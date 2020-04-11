package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

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
