package src.Calc_Page_Object_Factory;

import org.openqa.selenium.support.PageFactory;

import src.Calc_Utilities.TestBase;

public class managePages extends TestBase {
	public static void init()
	{
		cm = PageFactory.initElements(driver, calculator_Page.class);
	}
}
