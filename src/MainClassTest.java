import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainClassTest {

  @Before
  public void textStartTest() {
    System.out.println();
    System.out.println("Start Test");
  }

  @After
  public void textFinishTest() {
    System.out.println("Finish Test");
  }

  @Test
  public void testGetLocalNumber() {
    System.out.println("Get Local Number Test");

    final MainClass mainClassObject = new MainClass();
    final int actualResult = mainClassObject.getLocalNumber();
    final int expectedResult = 14;

    Assert.assertEquals(expectedResult, actualResult);
  }

//  @Test
//  public void testGetClassNumber() {
//    System.out.println("Get Class Number Test");
//
//    final MainClass mainClassObject = new MainClass();
//    final int result = mainClassObject.getClassNumber();
//    System.out.println("result: " + result);
//
//    Assert.assertTrue("Результат не превосходит 45", result > 45);
//  }

  @Test
  public void testGetClassString() {
    System.out.println("Get Class String Test");

    final MainClass mainClassObject = new MainClass();
    final String str = mainClassObject.getClassString();
    System.out.println("str: " + str);

    final boolean substringIsPresent = str.contains("hello") || str.contains("Hello");
    System.out.println("substringIsPresent: " + substringIsPresent);

    Assert.assertTrue("Строка не содержит подстроку 'hello' или 'Hello'", substringIsPresent);
  }
}
