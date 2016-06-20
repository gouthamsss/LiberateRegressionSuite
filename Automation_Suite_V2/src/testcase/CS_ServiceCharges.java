package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class CS_ServiceCharges
{
	TestAction ta = new TestAction(Operations.getdriver());
	TestReport report;
	boolean passed;
	String xpath;
	
	public CS_ServiceCharges (TestReport report)
	{
		this.report = report;
	}
	
	public boolean execute()
	{
		return passed;
	}
	
	public boolean navigateToServiceCharges()
	{
		xpath = "//*[text()='Service Charges']";
		passed = ta.waitUntil(xpath, 4);
		passed = ta.clickOn(xpath);
		
		xpath = "//*[text()[contains(.,'Service Charge Details')]]";
		passed = ta.waitUntil(xpath, 4);
		
		return passed;
	}
	
	public boolean raiseNewServiceCharge()
	{
		xpath = "//*[text()='New']";
		passed = ta.waitUntil(xpath, 4);
		if(!passed)
			return passed;
		
		passed = ta.clickOn(xpath);
		
		
		
		return passed;
	}
}
