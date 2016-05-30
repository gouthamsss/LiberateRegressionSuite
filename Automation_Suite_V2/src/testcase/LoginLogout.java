package testcase;

import main.TestCase;
import operations.Operations;
import operations.TestAction;
import operations.TestData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class LoginLogout 
{
	public static boolean passed;
	static String xpath;
	
	static WebDriver driver = Operations.getdriver();
	static TestAction tsa = new TestAction(driver);
	
	public static boolean login(String autURL)
	{
		try
		{
			driver.get(autURL);
			tsa.log("Navigating to '"+autURL+"'");
		}
		catch(UnreachableBrowserException e)
		{
			System.out.println("Unreachable Browser. Starting new chromedriver instance");
			driver = Operations.getdriver();
			driver.get(autURL);
			tsa = new TestAction(driver);
			tsa.log("Navigating to '"+autURL+"'");
		}
		
		boolean needToLogin = driver.findElements(By.id("login:userName")).size() != 0;
		boolean loggedIn = driver.findElements(By.id("headerForm:headerLogoutLink")).size() != 0;
		
		if (needToLogin)
		{
			driver.findElement(By.id("login:userName")).sendKeys("libadmin");
			driver.findElement(By.id("login:userPassword")).sendKeys("Ic3cr34m!");	
			driver.findElement(By.id("login:login_button")).click();
			tsa.log("Loggin In");

			xpath = "//*[text()[contains(.,'Logout')]]";
			passed = tsa.waitUntil(xpath,30);
			if(passed)
			{
				tsa.log("Logged in");
				
				TestData.buildNumber = driver.findElement(By.xpath("(//span[contains(@id,'headerForm')])[5]")).getText();
				TestData.buildNumber = TestData.buildNumber.substring(34, 37);
				tsa.log("Current Build Number : " + TestData.buildNumber);
				TestCase.ReportLocation = "Reports\\"+TestData.buildNumber+"\\";
				//MainWindow.current_BuildNumber.setText(TestData.currentBuild);
				
				//Operations.initReportLocation();
			}
			else
			{
				System.out.println("Not able to Log in. Please contact support or check again.");
			}
		}
		else if (loggedIn)
		{
			System.out.println("Already logged in");
		}
		else
		{
			System.out.println("Not able to Log in. Please contact support or check again.");
		}
		
		return passed;
	}
}
