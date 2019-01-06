package src.Calc_Utilities;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.sikuli.script.Screen;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import src.Calc_Page_Object_Factory.calculator_Page;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.apache.log4j.BasicConfigurator;
import java.time.Instant;
public class TestBase 
{
	public static WebDriver driver;
	public static ExtentReports extent;//
	public static ExtentTest test;
	static Instant instant = Instant.now();
	static long localDate = instant.getEpochSecond();
	public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(DateFormat.getInstance().getCalendar().getTime());	
	public static Screen screen;
	public static final Logger logger=Logger.getLogger(TestBase.class);
	public static String qaws;
	public static calculator_Page cm;
	public void loadlog4j()
	{
		String log4jConfPath=System.getProperty("log4j.properties");
		PropertyConfigurator.configure(log4jConfPath);
		BasicConfigurator.configure();
	}

	//Reading and Connection to XML file
	public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File("External_Files/XML/ConfigurationFile.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile); 
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
	}

	//Reports function
	public static void  InstanceReports() throws ParserConfigurationException, SAXException, IOException
	{
		extent= new ExtentReports(getData("ReportFilePath") + timeStamp +"-"+localDate+getData("Reporfilename"),true);
	}
	public static void initReportTest(String testName,String testDescription)
	{
		test = extent.startTest(testName, testDescription);
	}
	public static void FinalizedreportTest()
	{
		extent.endTest(test);
	}
	public static void FinalizeExtentReport()
	{
		extent.flush();
		extent.close();
	}
	//end Report function 

	public String getscreenshot() throws Exception 
	{
		String SsPath=getData("ReportFilePath")+ timeStamp +"-"+localDate+".jpg";	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(SsPath));
		return SsPath;
	}

	public static void BrowserSwitch(String BrowserType) throws ParserConfigurationException, SAXException, IOException
	{
		switch(BrowserType.toLowerCase())
		{
		case "chrome":
			driver = ChromeDriver();
			break;
		case "firefox":
			driver =  initFFDriver();
			break;
		case "ie":
			driver = initIEDriver();
			break;
		}
		logger.info("Open brwoser :"+BrowserType);
		driver.manage().window().maximize();
		driver.get(getData("URL"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		screen = new Screen();
	}	
	public static WebDriver ChromeDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.chrome.driver",getData("ChromeDriverPath"));
		WebDriver driverChrome= new ChromeDriver();
		return driverChrome;

	}
	public static WebDriver initFFDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.gecko.driver", getData("FFDriverPath")); 
		WebDriver driverFF= new FirefoxDriver();
		return driverFF;
	}
	public static WebDriver initIEDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.ie.driver",getData("IEDriverPath"));
		WebDriver driverIE= new InternetExplorerDriver();
		return driverIE;

	}

//*****************************Annotations from Test class********************************************
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		BrowserSwitch(getData("BrowserType"));
		InstanceReports();
		src.Calc_Page_Object_Factory.managePages.init();
 	}
	
	@Before
	public void setUp() throws Exception 
	{
		
	}
	@After
	public void doAfterTest()
	{
		FinalizedreportTest();
	
	}
	@AfterClass
	public static void setUpAfterClass()
	{
		FinalizeExtentReport();
		driver.quit();
	}
	//*******************************************************************************
}


