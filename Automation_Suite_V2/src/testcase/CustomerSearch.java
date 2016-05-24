package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class CustomerSearch
{
	public String AccountNumber;
	public String ServiceNumber;
	public String ServiceOrderNumber;
	public String LocalAccountNumber;
	public Boolean onlyCustomerSearch = false;
	
	TestReport report;
    TestAction ta 		= new TestAction(Operations.getdriver());
	
	String xpath;
	boolean passed;
	
	public CustomerSearch(TestReport report)
	{
		this.report = report;
	}
	
	public boolean execute()
	{
	    while (true)
	    {
	    	passed = testStep_1();
	        if (!passed)
	        	break;
			
	        passed = testStep_2();
   	        if (!passed)
   	        	break;
		    
		    if(onlyCustomerSearch)
		    	break;
		    
		    break;
	    }
		
		return passed;
	}
	
	private boolean testStep_1()
	{
		xpath = "//*[text()[contains(.,'Customer Care')]]";
		passed = ta.clickOn(xpath);
		
		xpath = "(//*[text()='Account No.:']/./following::input)[1]";
		passed = ta.waitUntil(xpath, 5);
		
		if(!AccountNumber.equals(""))
		{
			passed = ta.sendDatatoField(xpath, AccountNumber);
		}
		else if(!LocalAccountNumber.equals(""))
		{
			xpath = "(//*[text()='Local Account No.:']/./following::input)[1]";
			passed = ta.sendDatatoField(xpath, LocalAccountNumber);
		}
		else if(!ServiceNumber.equals(""))
		{
			xpath = "(//*[text()='Service No.:']/./following::input)[1]";
			passed = ta.sendDatatoField(xpath, ServiceNumber);
		}
		else if(!ServiceOrderNumber.equals(""))
		{
			xpath = "(//*[text()='Service Order No.:']/./following::input)[1]";
			passed = ta.sendDatatoField(xpath, ServiceOrderNumber);
		}
		else
		{
			ta.log("ERRROR : Search Criteria not provided.");
			passed = false;
			return passed;
		}
		
		xpath = "//input[@value='Search']";
		passed = ta.clickOn(xpath);
		
		return passed;
	}
	
	private boolean testStep_2()
	{
		
		return passed;
	}
}
