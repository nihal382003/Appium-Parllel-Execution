import org.testng.annotations.Test;

public class TestCase1 extends BaseTest{
    @Test
     public void testcase1() throws Exception{
         sampleTest test=new sampleTest(getDriver());
         test.login();

     }
}
