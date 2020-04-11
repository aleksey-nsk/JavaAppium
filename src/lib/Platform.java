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

  public AppiumDriver getDriver() throws Exception {
    System.out.println("Get Driver");

    URL url = new URL(APPIUM_URL);

    if (this.isAndroid()) {
      System.out.println("  Create and return android driver");
      return new AndroidDriver(url, this.getAndroidDesiredCapabilities());
    } else if (this.isIOS()) {
      System.out.println("  Create and return ios driver");
      return new IOSDriver(url, this.getIOSDesiredCapabilities());
    } else {
      String platform = this.getPlatformVar();
      throw new Exception("Cannot detect type of the driver. Platform value: '" + platform + "'");
    }

    /*
    if (platform.equalsIgnoreCase(PLATFORM_ANDROID)) {
      System.out.println("  Create android driver");
      driver = new AndroidDriver(new URL(AppiumURL), capabilities);
    } else if (platform.equalsIgnoreCase(PLATFORM_IOS)) {
      System.out.println("  Create ios driver");
      driver = new IOSDriver(new URL(AppiumURL), capabilities);
    } else {
      throw new Exception("Cannot create driver for this platform. Platform value: '" + platform + "'");
    }
     */
  }

  public boolean isAndroid() {
    System.out.println("Is Android");
    return isPlatform(PLATFORM_ANDROID);
  }

  public boolean isIOS() {
    System.out.println("Is iOS");
    return isPlatform(PLATFORM_IOS);
  }



//  private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
//    System.out.println("Get Capabilities By Platform Env");
//
//    String platform = System.getenv("PLATFORM");
//    System.out.println("  platform: '" + platform + "'");
//
//    DesiredCapabilities capabilities = new DesiredCapabilities();
//
//    if (platform.equalsIgnoreCase(PLATFORM_ANDROID)) {
//      System.out.println("  Set capabilities for android");
//
////      final String path = new File("apks/org.wikipedia.apk").getAbsolutePath();
////      System.out.println("  Путь до тестируемого приложения: '" + path + "'");
////
////      capabilities.setCapability("platformName", "Android");
////      capabilities.setCapability("deviceName", "AndroidTestDevice");
////      capabilities.setCapability("platformVersion", "6.0");
////      capabilities.setCapability("automationName", "Appium");
////      capabilities.setCapability("appPackage", "org.wikipedia");
////      capabilities.setCapability("appActivity", ".main.MainActivity");
////      capabilities.setCapability("app", path);
//    } else if (platform.equalsIgnoreCase(PLATFORM_IOS)) {
//      System.out.println("  Set capabilities for ios");
//
////      capabilities.setCapability("platformName", "iOS");
////      capabilities.setCapability("deviceName", "iPhone SE");
////      capabilities.setCapability("platformVersion", "11.3");
////      capabilities.setCapability("app", "/Users/alexz/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
//    } else {
//      throw new Exception("Cannot get run platform from env variable. Platform value: '" + platform + "'");
//    }
//
//    return capabilities;
//  }

  private DesiredCapabilities getAndroidDesiredCapabilities() {
    System.out.println("Get Android Desired Capabilities");

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
    System.out.println("Get iOS Desired Capabilities");

    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName", "iOS");
    capabilities.setCapability("deviceName", "iPhone SE");
    capabilities.setCapability("platformVersion", "11.3");
    capabilities.setCapability("app", "/Users/alexz/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");

    return capabilities;
  }

  private boolean isPlatform(String myPlatform) {
    System.out.println("Is Platform");
    String platform = this.getPlatformVar();
    boolean isPlatformBoolean = myPlatform.equalsIgnoreCase(platform);
    System.out.println("  isPlatformBoolean: " + isPlatformBoolean);
    return isPlatformBoolean;
  }

  private String getPlatformVar() {
    System.out.println("Get Platform Var");
    String platform = System.getenv("PLATFORM");
    System.out.println("  platform: '" + platform + "'");
    return platform;
  }
}
