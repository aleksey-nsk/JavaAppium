package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorisationPageObject extends MainPageObject {

  private static final String LOGIN_BUTTON = "xpath://body/div/div/a[text()='Log in']";
  private static final String LOGIN_INPUT = "css:input#wpName1";
  private static final String PASSWORD_INPUT = "css:input#wpPassword1";
  private static final String SUBMIT_BUTTON = "css:button#wpLoginAttempt";

  public AuthorisationPageObject(RemoteWebDriver driver) {
    super(driver);
  }

  public void clickAuthButton() {
    System.out.println("\nClick Auth Button");
    this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
    this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
  }

  public void enterLoginData(String login, String password) throws InterruptedException {
    System.out.println("\nEnter Login Data");
    Thread.sleep(2_000L);
    this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
    this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 5);
    Thread.sleep(2_000L);
  }

  public void submitForm() {
    System.out.println("\nSubmit Form");
    this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
  }
}
