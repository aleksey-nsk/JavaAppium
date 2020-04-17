package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

  private static final String STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia";
  private static final String STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore";
  private static final String STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages";
  private static final String STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected";
  private static final String NEXT_LINK = "id:Next";
  private static final String GET_STARTED_BUTTON = "id:Get started";
  private static final String SKIP = "id:Skip";

  public WelcomePageObject(RemoteWebDriver driver) {
    super(driver);
  }

  public void waitForLearnMoreLink() {
    System.out.println("\nWait For Learn More Link");
    this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
  }

  public void waitForNewWayToExploreText() {
    System.out.println("\nWait For New Way To Explore Text");
    this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' link", 10);
  }

  public void waitForAddOrEditPreferredLangText() {
    System.out.println("\nWait For Add Or Edit Preferred Lang Text");
    this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred languages' link", 10);
  }

  public void waitForLearnMoreAboutDataCollectedText() {
    System.out.println("\nWait For Learn More About Data Collected Text");
    this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link", 10);
  }

  public void clickNextButton() {
    System.out.println("\nClick Next Button");
    this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 10);
  }

  public void clickGetStartedButton() {
    System.out.println("\nClick Get Started Button");
    this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' link", 10);
  }

  public void clickSkip() {
    System.out.println("\nClick Skip");
    this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 5);
  }
}
