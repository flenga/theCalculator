package src.Calc_TestCases;
import org.junit.Test;
import org.testng.Assert;
import src.Calc_Utilities.TestBase;
public class Run_calculator_SanityTestCases  extends TestBase
{
	/*
	 * This Test case check a calculation in the online Calculator . 
	 * Verify the result of 1+10 is correct SCurrently the function is in the Page itself.
	 */
	@Test
		public void test1_CheckingCalculatorInSimpleMethod() throws Exception 
		{
			initReportTest("First test","Check that the result is correct!");
			cm.clickDigit(1);
			cm.clickOperator("+");//÷, ×,−
			cm.clickDigit(1);
			cm.clickDigit(0);
			cm.clickOperator("=");
			cm.CheckResult();
			Assert.assertEquals(cm.CheckResult().getText(),"11");
		}
}
