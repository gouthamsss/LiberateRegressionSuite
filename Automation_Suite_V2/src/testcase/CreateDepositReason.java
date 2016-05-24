package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class CreateDepositReason
{
	public String DepositAmount = "100.00";
	String ReasonForDeposit = "";
	
	TestReport report;
    TestAction ta 		= new TestAction(Operations.getdriver());
    
    boolean passed;
    String xpath;
    
    public CreateDepositReason(TestReport report)
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
   	        
	        passed = testStep_3();
   	        if (!passed)
   	        	break;
   	        
	        passed = testStep_4();
   	        if (!passed)
   	        	break;
   	        
		    break;
	    }
	    return passed;
	}
    
    private boolean testStep_1()
    {
    	xpath = "//*[text()[contains(.,'Customer Account Details')]]";
    	passed = ta.waitUntil(xpath, 5);
    	
    	xpath = "//*[text()='Deposit Requirements']";
    	passed = ta.clickOn(xpath);
    	
    	return passed;
    }
    
    private boolean testStep_2()
    {
    	xpath = "//*[text()='Deposit Summary']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	xpath = "//*[text()='New']";
    	passed = ta.waitUntil(xpath, 5);
    	passed = ta.clickOn(xpath);
    	
    	return passed;
    }
    
    private boolean testStep_3()
    {
    	xpath = "//*[text()='New Deposit Requirements']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	xpath = "(//*[text()='Reason For Deposit:']/./following::select)[1]";
    	passed = ta.selectBy(xpath, 1);
    	ReasonForDeposit = ta.getValueFromSelect(xpath);
    	
    	xpath = "(//*[text()='Amount Required:']/./following::input)[1]";
    	passed = ta.clearInputField(xpath);
    	passed = ta.sendDatatoField(xpath, DepositAmount);
    	
    	xpath = "//input[@value='OK']";
    	passed = ta.clickOn(xpath);
    	
    	return passed;
    }
    
    private boolean testStep_4()
    {
    	xpath = "//*[text()='Deposit details added successfully']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	return passed;
    }
}
