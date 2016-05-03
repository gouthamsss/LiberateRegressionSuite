package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class ProvideService 
{
	TestReport report = new TestReport("ProvideService", "Reports\\");
	
    TestAction ta = new TestAction(Operations.getdriver());
	
	boolean NewCustomer 	= false;
	String AccountNumber	= "240004430000";
	String department		= "BGSAL";
	String site				= "BUSG";
    String ServicePackage	= "ETFTESTING";
	
    boolean passed;
    String xpath;
	String screenName;
    
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
	        
	        passed = testStep_2();
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
			
			xpath = "//*[text()[contains(.,'Status:')]]";
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
			
			xpath = "//*[text()[contains(.,'Number of Services')]]";
			passed = ta.waitUntilElementnotExist(xpath, 5);
		}
		
		return passed;
	}
	
	private boolean testStep_2()
	{
		while(true)
		{
			xpath = "(//*[@class='hdrMid'])[1]";
			ta.waitUntil(xpath, 10);
			
			screenName = ta.getDatafromPage(xpath);
			if(screenName.equals("Pricing Plans"))
				pricingPlanScreen();
			else if(screenName.equals("Service Product"))
				serviceProducts();
			else if(screenName.equals("ISP Fields"))
				ISPFields();
			else
				break;
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
	
	//Method to handle Pricing Plan screen
	private boolean pricingPlanScreen()
	{
		xpath = "//*[text()='Pricing Plans']";
		passed = ta.waitUntil(xpath, 5);
		
		//TODO Handle Pricing plan screen
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		xpath = "//*[text()='Pricing Plans']";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}
	
	
	//Method to handle service products screen
	private boolean serviceProducts()
	{
		xpath = "//*[text()='Service Product']";
		passed = ta.waitUntil(xpath, 5);
		
		//TODO Handle Service Products screen
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		xpath = "//*[text()='Service Product']";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}
	
	private boolean ISPFields()
	{
		xpath = "//*[text()='ISP Fields']";
		passed = ta.waitUntil(xpath, 5);
		
		//TODO Handle ISP Fields screen
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		xpath = "//*[text()='ISP Fields']";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}
}
