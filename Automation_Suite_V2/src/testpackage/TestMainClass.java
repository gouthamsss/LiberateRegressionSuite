package testpackage;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;
import testcase.LoginLogout;

public class TestMainClass 
{
	static TestAction tsa = new TestAction(Operations.getdriver());
	static TestReport miscreport = new TestReport("MiscReport","");
	
	static String xpath;
	static boolean passed;
	static boolean testStepPassed;

	static String serverDate;
	
	public static void main(String [] args)
	{
		LoginLogout.login("http://172.21.73.80:8081/liberate-LONI01-S10/");
		execute();
		miscreport.createScreenshotDocument("TestDocument");

	}
	
	public static boolean execute()
	{
		for(int i=0;i<1;i++)
		{
			testStepPassed = testStep_1();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_2();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_3();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_4();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_5();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_6();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_7();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_8();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_9();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
			
			testStepPassed = testStep_10();
			miscreport.takeScreenshot();
			if(!testStepPassed)
				break;
		}
		
		return passed;
	}
	
	public static boolean testStep_1()
	{
		tsa.scrollUp();
		
		xpath = "//a[contains(@href,'maintainEmployeeParams.xhtml')]";
		passed = tsa.waitUntil(xpath, tsa.longWait);
		passed = tsa.clickOn(xpath);
		
		return passed;
	}
	
	public static boolean testStep_2()
	{
		xpath = "//*[text()[contains(.,'Withdrawn Items')]]";
		
		passed = tsa.waitUntil(xpath, tsa.longWait);
		passed = tsa.clickOn(xpath);
		
		return passed;
	}
	
	public static boolean testStep_3()
	{
		xpath = "//*[text()[contains(.,'Suspense by Exchange')]]";
		passed = tsa.waitUntil(xpath, tsa.longWait);
		passed = tsa.clickOn(xpath);
		
		return passed;
	}
	
	public static boolean testStep_4()
	{
		xpath = "//select[contains(@id,'exchangeID')]";
		passed = tsa.waitUntil(xpath, tsa.longWait);
		tsa.selectBy(xpath, 3);
		
		xpath = "//select[contains(@id,'suspenseReason')]";
		tsa.selectBy(xpath, "AIMSIS");
		
		xpath = "//input[@value = 'Accept']";
		passed = tsa.clickOn(xpath);
		
		return passed;
	}
	
	public static boolean testStep_5()
	{
		xpath = "//*[text()[contains(.,'uccessfully')]]";
		passed = tsa.waitUntil(xpath, tsa.longWait);

		xpath = "(//*[@class = 'blackNormal'])[2]";
		serverDate = tsa.getDatafromPage(xpath);
	
		serverDate = serverDate.replaceAll("/", "-");
		serverDate = serverDate.replaceAll(",", "");
			
		passed = tsa.waitUntil(xpath, tsa.longWait);
		
		return passed;
	}
	
	public static boolean testStep_6()
	{
		xpath = "//*[text()[contains(.,'Home')]]";
		passed = tsa.clickOn(xpath);
		
		return true;
	}
	
	public static boolean testStep_7()
	{
		xpath = "//*[text()[contains(.,'Reports')]]";
		passed = tsa.clickOn(xpath);
		
		return passed;
	}
	
	public static boolean testStep_8()
	{
		xpath = "//input[contains(@id,'reportno')]";
		passed = tsa.waitUntil(xpath, tsa.longWait);
		passed = tsa.sendDatatoField(xpath, "CIR394G02");
		
		xpath = "//input[@value = 'Search']";
		passed = tsa.clickOn(xpath);
		
		return passed;
	}
	
	public static boolean testStep_9()
	{
		
		xpath = "//tr[@id='browseReports:reportsTable_row_0']";
		passed = tsa.waitUntil(xpath, tsa.longWait);
		passed = tsa.clickOn(xpath);
		
		return passed;
	}
	
	public static boolean testStep_10()
	{
		xpath = "//*[text()[contains(.,'Available Reports')]]";
		passed = tsa.waitUntil(xpath, tsa.longWait);
		
		xpath = "//*[text()[contains(.,'" + serverDate + "')]]";
		
		boolean reportExist = tsa.elementExist(xpath);
		
		if(reportExist)
		{
		xpath = "(//a[contains(@id,'reportsDataTable:')])[1]";
		passed = tsa.clickOn(xpath);
		tsa.waitFor(1000);
				
				//Operations.waitForNumberOfWindowsToEqual(2);//this method is for wait
				
				//NOT MY CODE
//				Set<String> handles = driver.getWindowHandles();
//				 
//				String firstWinHandle = driver.getWindowHandle();
//				handles.remove(firstWinHandle);
//				 
//				Object winHandle=handles.iterator().next();
//				 
//				if (winHandle!=firstWinHandle)
//				{
					//To retrieve the handle of second window, extracting the handle which does not match to first window handle
//					String secondWinHandle = (String) winHandle; //Storing handle of second window handle
					//Switch control to new window
//					driver.switchTo().window(secondWinHandle);
//				}
//				for (String handle : driver.getWindowHandles()) 
//				{
//					driver.switchTo().window(handle);
//				}
//			}
		}
		else
		{
			System.out.println("CIR394G02 Report is not generated..");
			passed = false;
		}
		
		return passed;
	}
}