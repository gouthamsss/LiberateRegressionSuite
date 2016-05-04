package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class SalesSignoff 
{
	static String classversion = "SalesSignoff Class 0.0.3 : Added - Screenshot";

	TestAction ta = new TestAction(Operations.getdriver());
	TestReport report;
	
	String xpath;
	boolean passed;
	
	String AccountNumber;
	String ServiceNumber;
	String ServiceOrder;
	
	public SalesSignoff(TestReport report)
	{
		this.report = report;
	}
	
	public boolean signoff()
	{		
		xpath = "//*[text()='Service Order Lines']";
		passed = ta.waitUntil(xpath, 10);
		
		report.takeScreenshot();
		
		ta.closeOKpopup();
		
		xpath = "(//*[text()[contains(.,'Account Number:')]]/./following::*)[1]";
		this.AccountNumber = ta.getDatafromPage(xpath);
		
		xpath = "(//*[text()[contains(.,'Service No:')]]/./following::*)[1]";
		this.ServiceNumber = ta.getDatafromPage(xpath);
		
		xpath = "(//*[text()[contains(.,'Service Order No:')]]/./following::*)[1]";
		this.ServiceOrder = ta.getDatafromPage(xpath);
		
		xpath = "//input[@value='Confirm Payment Amounts' and @disabled='disabled']";
		if(!ta.elementExist(xpath))
		{
			//TODO Confirm Payments
		}
		
		//TODO Handle if service charge needs to be added.
		
		xpath = "//input[@value='Submit Order']";
		passed = ta.clickOn(xpath);
		report.takeScreenshot();
		
		xpath = "//input[@value='OK']";
		passed = ta.waitUntil(xpath, 5);
		
		report.takeScreenshot();
		
		passed = ta.clickOn(xpath);		
		passed = ta.waitUntilElementnotExist(xpath, 5);

		return passed;
	}
}
