package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class ProvideService 
{
	TestReport report = new TestReport("ProvideService", "");
	
    TestAction ta = new TestAction(Operations.getdriver());
	
	boolean NewCustomer = false;
	String AccountNumber= "240004430000";
	String department	= "BGSAL";
	String site			= "BUSG";
    String ServicePackage = "ETFTESTING";
	
    boolean passed;
    String xpath;
	
	public ProvideService(boolean NewCustomer)
	{
		this.NewCustomer = NewCustomer;
	}
	
	public void execute()
	{
		LoginLogout.login("http://172.21.73.80:8081/liberate-LONI01-S10/");
	    //LoginLogout.login("http://172.21.73.80:8083/liberate-LONI02-S06/");

	    while (true)
	    {
	    	passed = testStep_1();
	        report.takeScreenshot();
	        if (!passed)
	        	break;
	        
		    break;
	    }
	}
	
	private boolean testStep_1()
	{
		if(NewCustomer)
		{
			
		}
		else
		{
			passed = navigateToExistingCustomerScreen();
			
			xpath = "(//*[text()[contains(.,'Account No:')]]/./following::input)[1]";
			passed = ta.sendDatatoField(xpath, AccountNumber);
			
			xpath = "//input[@value='Search']";
			passed = ta.clickOn(xpath);
			
			xpath = "//*text()[contains(.,'Status:')]";
			passed = ta.waitUntil(xpath, 3);
			
			xpath = "(//*[text()[contains(.,'Department:')]]/./following::select)[1]";
			passed = ta.selectBy(xpath, department);
			
			xpath = "(//*[text()[contains(.,'Site:')]]/./following::select[@disabled = 'disabled'])[1]";
			passed = ta.waitUntilElementnotExist(xpath, 5);
			
			xpath = "(//*[text()[contains(.,'Site:')]]/./following::select)[1]";
			passed = ta.selectBy(xpath, site);
			
			xpath = "(//*[text()='Service Package:']/./following::select)[1]";
			passed = ta.selectBy(xpath, ServicePackage);
			
			xpath = "//*[text()[contains(.,'Number of Services')]]";
			passed = ta.waitUntil(xpath, 3);
			
			xpath = "//input[contains(@value,'Proceed')]";
			passed = ta.clickOn(xpath);
		}
		
		return passed;
	}
	
	private boolean navigateToExistingCustomerScreen()
	{
    	xpath = "//*[text()[contains(.,'Customer Care')]]";
        passed = ta.waitUntil(xpath, 5);
        passed = ta.clickOn(xpath);
        
    	xpath = "//*[text()[contains(.,'Service Provisioning')]]";
        passed = ta.waitUntil(xpath, 5);
        passed = ta.clickOn(xpath);
        
        ta.closeOKpopup();
        
    	xpath = "//*[text()[contains(.,'Existing Customer')]]";
        passed = ta.waitUntil(xpath, 5);
        passed = ta.clickOn(xpath);
		
		return passed;
	}
}
