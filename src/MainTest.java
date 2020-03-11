import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

public class MainTest extends CoreTestCase {

  @Before
  public void textStartTest() {
    System.out.println("Start test");
  }

  @After
  public void textFinishTest() {
    System.out.println("Finish test");
  }

  @Test
  public void firstTest() {
    System.out.println("First test");

//    this.assertFail();

//    int expected = 25;
////    int expected = 20;
//    int actual = 5 * 5;
//
////    if (actual != expected) {
////      Assert.fail("5 * 5 != 25");
////    }
//    Assert.assertTrue("5 * 5 != 25", actual == expected);
  }

  @Test
  public void secondTest() {
    System.out.println("Second test");
  }

//  private void assertFail() {
//    Assert.fail("This message will be printed");
//  }






  /*
  public void typeStartMessage() {
//    super.typeStartMessage();
    System.out.println("Current class is MainTest");
  }

//  MathHelper mathHelper = new MathHelper();

  @Test
  public void myFirstTest() {
    this.typeStartMessage();

//    int y = mathHelper.calc(10, 15, '*');
//    System.out.println("y: " + y);

//    System.out.println("First test: Before changing static_int: " + MathHelper.static_int);
//
//    MathHelper.static_int = 8;
//
//    MathHelper mathObject = new MathHelper();
//    System.out.println("First test: Before changing simple_int: " + mathObject.simple_int);
//    mathObject.simple_int = 8;
  }

  @Test
  public void mySecondTest() {
    this.typeStartMessage();

//    System.out.println("Second test: Before changing static_int: " + MathHelper.static_int);
//
//    MathHelper.static_int = 8;
//
//    MathHelper mathObject = new MathHelper();
//    System.out.println("Second test: Before changing simple_int: " + mathObject.simple_int);
//    mathObject.simple_int = 8;
  }

//  public void typeString() {
//    System.out.println("Hello from typeString()!");
//  }
//
//  public int giveMeInt() {
//    return 5;
//  }


   */

}
