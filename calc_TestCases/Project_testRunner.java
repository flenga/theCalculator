package calc_TestCases;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
public class Project_testRunner 
{
	public static void main(String[] args) 
	{
		JUnitCore.main("calc_TestCases.Run_calculator_SanityTestCases");
	}
}