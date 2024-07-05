import org.testng.annotations.Test;


public class Testclass2 extends BaseTest{
    @Test
    public void testcase2() throws Exception{
        sampleTest test=new sampleTest(getDriver());
        test.applaunch();
    }
}
