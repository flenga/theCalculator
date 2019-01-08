package src.Calc_Utilities;

import java.awt.Robot;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.LogStatus;

import jxl.Sheet;
import jxl.Workbook;

public class Com_functions extends TestBase
{

	HSSFWorkbook wb;
	HSSFSheet sheet;
	static Robot robot;
	public  Sheet wrksheet;
	public static Workbook wrkbook = null;
	Logger logger=Logger.getLogger(Com_functions.class);

	//Assertion equal
		public static void asserequal(String Expected,String Actual) throws Exception
		{
			try
			{
				Assert.assertEquals(Expected, Actual);
			//	logger.info("Assertion was succed ");
				test.log(LogStatus.PASS, "Assertion was succed");
			}
			catch(AssertionError e)
			{
				test.log(LogStatus.FAIL,"assertion failed the two values: "+ Expected +" and second one is: "+Actual+": "+ e.getMessage());//+" "+ test.addScreenCapture(getscreenshot()));
			//	logger.error("Something went wrong while trying to Assert the two values: "+ Expected +" and second one is: "+Actual);
			}
		}
	
	public void waitToElement(WebElement ElementToWait)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(ElementToWait)); 

	}

	public void ClickOnElement(WebElement ElementToClick) throws Exception 
	{
		try
		{
			ElementToClick.click();
			logger.info("Clicked on the Element ("+ElementToClick.getAttribute("name")+") with success ");
			test.log(LogStatus.PASS, "Clicked on the Element ("+ElementToClick.getAttribute("name")+") with success ");
		}
		catch(Exception e)
		{
			logger.error("Failed to click on the element: "+ElementToClick.getAttribute("name")+"  !! see screenshot: "+e.getMessage() );
			test.log(LogStatus.FAIL,"Failed to click on the element: "+ElementToClick.getAttribute("name")+" !! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}


	}

	


}



