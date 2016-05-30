package operations;

import java.util.Arrays;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Operations 
{
	String operationsClassVersion = "0.1.7";
	
	String provideData;
	String provideField;
	
	String button;
	
	String selectData;
	String selectField;
	
	public Boolean continueStep = true;
	
	Scanner scan = new Scanner(System.in);
	private static WebDriver driver = getdriver();

	TestAction tsa = new TestAction(driver);
	
	//setter for WebDriver driver
	public static WebDriver getdriver()
	{
		if(driver == null)
		{
			driver = initDriver();
		}
		return driver;
	}
	
	//Create new Driver Instance and set diver properties
	private static WebDriver initDriver()
	{
		System.setProperty("webdriver.chrome.driver", "Resources\\chromedriver.exe");

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors","--disable-popup-blocking"));
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public String testStatus(Boolean status)
	{
		String status1 = "";
		if(status == true)
			status1 = "Passed";
		else if (status == false)
			status1 = "Failed";
		else
			status1 = "ERROR : No Status";
		return status1;
	}
	
	public static void exitClose()
	{
		driver.close();
		driver.quit();
		driver = null;
	}
	
}