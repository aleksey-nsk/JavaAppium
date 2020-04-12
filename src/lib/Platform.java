package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;

public class Platform {

  private static final String PLATFORM_IOS = "ios";
  private static final String PLATFORM_ANDROID = "android";
  private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

  private static Platform instance;

  private Platform() {} // приватный конструктор

  public static Platform getInstance() {
    if (instance == null) {
      instance = new Platform();
    }
    return instance;
  }

  public AppiumDriver getDriver() throws Exception {
    System.out.println("Get Driver");

    URL url = new URL(APPIUM_URL);

    if (this.isAndroid()) {
      return new AndroidDriver(url, this.getAndroidDesiredCapabilities());
    } else if (this.isIOS()) {
      return new IOSDriver(url, this.getIOSDesiredCapabilities());
    } else {
      String platform = this.getPlatformVar();
      throw new Exception("Cannot detect type of the driver. Platform value: '" + platform + "'");
    }
  }

  public boolean isAndroid() {
    boolean isPlatformBoolean = isPlatform(PLATFORM_ANDROID);
    System.out.println("  Is Android ? --> " + isPlatformBoolean);
    return isPlatformBoolean;
  }

  public boolean isIOS() {
    boolean isPlatformBoolean = isPlatform(PLATFORM_IOS);
    System.out.println("  Is iOS ? --> " + isPlatformBoolean);
    return isPlatformBoolean;
  }

  private DesiredCapabilities getAndroidDesiredCapabilities() {
    System.out.println("  Get Android Desired Capabilities");

    DesiredCapabilities capabilities = new DesiredCapabilities();

    final String path = new File("apks/org.wikipedia.apk").getAbsolutePath();
    System.out.println("  Путь до тестируемого приложения: '" + path + "'");

    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "AndroidTestDevice");
    capabilities.setCapability("platformVersion", "6.0");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("appPackage", "org.wikipedia");
    capabilities.setCapability("appActivity", ".main.MainActivity");
    capabilities.setCapability("app", path);

    return capabilities;
  }

  private DesiredCapabilities getIOSDesiredCapabilities() {
    System.out.println("  Get iOS Desired Capabilities");

    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName", "iOS");
    capabilities.setCapability("deviceName", "iPhone SE");
    capabilities.setCapability("platformVersion", "11.3");
    capabilities.setCapability("app", "/Users/alexz/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");

    return capabilities;
  }

  private boolean isPlatform(String myPlatform) {
    String platform = this.getPlatformVar();
    System.out.println("  Method isPlatform() --> myPlatform: '" + myPlatform + "', platform: '" + platform + "'");
    boolean isPlatformBoolean = myPlatform.equalsIgnoreCase(platform);
    return isPlatformBoolean;
  }

  private String getPlatformVar() {
    String platform = System.getenv("PLATFORM");
    return platform;
  }
}
