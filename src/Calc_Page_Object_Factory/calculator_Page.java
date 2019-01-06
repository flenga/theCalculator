package src.Calc_Page_Object_Factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import src.Calc_Utilities.Com_functions;
import src.Calc_Utilities.TestBase;

public class calculator_Page extends TestBase
{
	Com_functions comfunc = new Com_functions();
	public WebDriver driver;
		
	@FindBy(how = How.XPATH, using= "//div[@class='cwcd cwbbc']")
	public WebElement RightDiv;
	
	@FindBy(how = How.XPATH, using= "//div[@class='cwcd cwsbc']")
	public WebElement LeftDiv;
	
	
	@FindBy(how = How.XPATH, using= "//div[@class='cwtlotc']")
	public WebElement Result_screen;

	/*
	 * This function get a digit from the test file and add it to Xpath phrase
	 */
	public void clickDigit(int digit) throws Exception {
		if (digit < 0 || digit > 9) throw new IllegalArgumentException("Need a number between 0-9");
		{
			WebElement digElement = RightDiv.findElement(By.xpath("//span[@class='BVPfqc'][contains(text(),'"+digit+"')]"));
			comfunc.ClickOnElement(digElement);
		}
	}

	/*
	 * This function get an operator from the test file and add it to Xpath phrase
	 */
	public void clickOperator(String operator) throws Exception {
		WebElement operatorElement = RightDiv.findElement(By.xpath("//span[@class='BVPfqc'][contains(text(),'"+operator+"')]"));
		System.out.println(operatorElement.getText());
		comfunc.ClickOnElement(operatorElement);
	}

	/*
	 * This function return the result of the Arithmetic exercise that we get. 
	 */
	public WebElement CheckResult()  {
		comfunc.waitToElement(Result_screen);
		return Result_screen;
	}
	
}
