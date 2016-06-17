package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class CS_MaintainAccountDeposit
{
	public String DepositAmount = "100.00";
	String ReasonForDeposit = "";
	
	TestReport report;
    TestAction ta 		= new TestAction(Operations.getdriver());
    
    public String operation = "";
    
    boolean passed;
    String xpath;
    
    public CS_MaintainAccountDeposit(TestReport report)
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
    	
		report.takeScreenshot();
    	
    	return passed;
    }
    
    private boolean testStep_2()
    {
    	xpath = "//*[text()='Deposit Summary']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	if(operation.equalsIgnoreCase("Create"))
    	{
	    	xpath = "//*[text()='New']";
	    	passed = ta.waitUntil(xpath, 5);
	    	passed = ta.clickOn(xpath);
    	}
    	else if (operation.equalsIgnoreCase("Refund"))
    	{
    		//TODO Refund Deposit
    	}
    	else
    	{
    		ta.log("ERROR : Unknown operation '"+operation+"'. Please contact Support");
    		return false;
    	}
		report.takeScreenshot();
    	
    	return passed;
    }
    
    private boolean testStep_3()
    {
    	if(operation.equalsIgnoreCase("Create"))
    	{
    		passed = createDepositeRequirement();
    	}
    	else if (operation.equalsIgnoreCase("Refund"))
    	{
    		passed = refundDeposit();  
    	}
    	else
    	{
    		ta.log("ERROR : Unknown operation '"+operation+"'. Please contact Support");
    		return false;
    	}
    	
    	return passed;
    }
    
    private boolean testStep_4()
    {

		
		ta.scrollUp();
		
    	return passed;
    }
    
    private boolean createDepositeRequirement()
    {
    	xpath = "//*[text()='New Deposit Requirements']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	xpath = "(//*[text()='Reason For Deposit:']/./following::select)[1]";
    	passed = ta.selectBy(xpath, 1);
    	ReasonForDeposit = ta.getValueFromSelect(xpath);
    	
    	xpath = "(//*[text()='Amount Required:']/./following::input)[1]";
    	passed = ta.clearInputField(xpath);
    	passed = ta.sendDatatoField(xpath, DepositAmount);
    	
		report.takeScreenshot();

    	xpath = "//input[@value='OK']";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Deposit details added successfully']";
    	passed = ta.waitUntil(xpath, 5);
    	
		report.takeScreenshot();
		
    	return passed;
    }
    
    private boolean refundDeposit()
    {
    	xpath = "//*[text()='Refund Deposit']";
    	passed = ta.waitUntil(xpath, 5);
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Deposit Information']";
    	passed = ta.waitUntil(xpath, 4);
    	
    	report.takeScreenshot();
    	
    	xpath = "(//td[contains(@class,'iceDatTblCol') and @scope='row'])[1]";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Refund Method :']";
    	passed = ta.waitUntil(xpath, 3);
    	
    	xpath = "(//*[text()='Service Charges']/./preceding::input)[last()]";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Refund Amount:']";
    	passed = ta.waitUntil(xpath, 4);
    	
    	xpath = "(//*[text()='Refund Amount:']/./following::input)[1]";
    	if(ta.getAttribute(xpath, "value").equals("0.00")||ta.getAttribute(xpath, "value").equals(""))
    	{
    		ta.sendDatatoField(xpath, "1.00");
    	}
    	ta.waitFor(1000);
    	
    	report.takeScreenshot();
    	
    	xpath = "//input[@value='Apply']";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Deposit refunded.']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	xpath = "//td[@class='buttonbg']";
    	passed = ta.waitUntil(xpath, 2);
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();

    	passed = ta.waitUntilElementnotExist(xpath, 4);
    	
    	report.takeScreenshot();
    	
    	return passed;
    }
}
