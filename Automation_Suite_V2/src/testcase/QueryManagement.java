package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class QueryManagement
{
	TestAction ta = new TestAction(Operations.getdriver());
	TestReport report;
	boolean passed;
	String xpath;
	
	public boolean RaiseforAccount	= false;
	public boolean RaiseforOrder	= false;
	public boolean RaiseforService	= false;
	
	public String ServiceNumber;
	public String ServiceOrder;
	
	public QueryManagement (TestReport report)
	{
		this.report = report;
	}
	
    public boolean execute()
    {
    	while(true)
    	{
	    	passed = RaiseQuery();
	        if (!passed)
	        	break;	        
	        
	        passed = QueryAmend();
	        if(!passed)
	        	break;
	        
	        passed = QueryNotes();
	        if(!passed)
	        	break;
	        
	        passed = QueryProgress();
	        if(!passed)
	        	break;
	        
	        passed = QuerySignOff();
	        if(!passed)
	        	break;
	        
    		break;
    	}
		return passed;
    }
    
    public boolean RaiseQuery()
    {
    	xpath = "//*[text()='Queries']";
    	passed = ta.waitUntil(xpath, 5);
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();
    	
    	xpath = "//*[text()[contains(.,'New Query-Account')]]";
    	passed = ta.waitUntil(xpath, 5);
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();
    	
    	xpath = "(//*[text()='Query Type1:']/./following::select)[1]";
    	passed = ta.waitUntil(xpath, 5);
    	
    	if(RaiseforOrder)
    	{
    		xpath = "(//*[text()='Service Order Number:']/./following::select)[1]";
    		passed = ta.selectBy(xpath, 1);
    		
    		ta.waitFor(2000);
    		
    		ServiceOrder = ta.getValueFromSelect(xpath);
    		
    		report.takeScreenshot();
    	}
    	if(RaiseforService)
    	{
    		xpath = "(//*[text()='Service Number:']/./following::input)[1]";
    		passed = ta.sendDatatoField(xpath, ServiceNumber);
    		
    		xpath = "//*[text()='Service Number:']";
    		ta.clickOn(xpath);
        	ta.waitFor(2000);
        	
        	report.takeScreenshot();
    	}
    	
    	xpath = "(//*[text()='Query Type1:']/./following::select)[1]";
    	passed = ta.selectBy(xpath, 12);
    	
    	xpath = "(//*[text()='Query Type2:']/./following::select[@disabled='disabled'])[1]";
    	passed = ta.waitUntilElementnotExist(xpath, 3);
    	
    	xpath = "(//*[text()='Query Type2:']/./following::select)[1]";
    	passed = ta.selectBy(xpath, 1);
    	
    	xpath = "(//*[text()='Query Type3:']/./following::select[@disabled='disabled'])[1]";
    	passed = ta.waitUntilElementnotExist(xpath, 3);
    	
    	xpath = "(//*[text()='Notes:']/./following::textarea)[1]";
    	passed = ta.sendDatatoField(xpath, "Test Automation");
    	
    	report.takeScreenshot();
    	
    	xpath = "//input[@value='Accept']";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()[contains(.,'Query Details')]]";
    	passed = ta.waitUntil(xpath, 5);
    	
    	return passed;
    }

    public boolean QueryAmend()
    {
    	ta.scrollUp();
    	
    	//Wait till overlay disappears
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	if(ta.elementExist(xpath))
    		ta.waitUntil(xpath, 4);
    	
    	xpath = "//*[text()='Amend']";
    	passed = ta.waitUntil(xpath, 4);
    	passed = ta.elementExist(xpath);
    	if(!passed)
    		return false;
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();
    	
    	xpath = "(//*[text()='Query Type1:']/./following::select)[1]";
    	passed = ta.waitUntil(xpath, 4);
    	passed = ta.selectBy(xpath, 15);
    	
    	xpath = "(//*[text()='Query Type2:']/./following::select[@disabled='disabled'])[1]";
    	passed = ta.waitUntilElementnotExist(xpath, 3);
    	
    	//Wait till overlay disappears
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	passed = ta.waitUntil(xpath, 2);
    	if(ta.elementExist(xpath))
    	{
    		ta.waitUntil(xpath, 4);
    	}
    	
    	ta.waitFor(2000);
    	xpath = "(//*[text()='Query Type2:']/./following::select)[1]";
    	passed = ta.selectBy(xpath, 2);
    	//Wait till overlay disappears
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	passed = ta.waitUntil(xpath, 2);
    	if(ta.elementExist(xpath))
    		ta.waitUntil(xpath, 4);
    	
    	xpath = "(//*[text()='Query Type3:']/./following::select[@disabled='disabled'])[1]";
    	passed = ta.waitUntilElementnotExist(xpath, 3);
    	
    	report.takeScreenshot();
    	
    	xpath = "//input[@value='Accept']";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Query Details']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	report.takeScreenshot();
    	
    	return passed;
    }
    
    public boolean QueryNotes()
    {
    	ta.scrollUp();

    	//Wait till overlay disappears
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	if(ta.elementExist(xpath))
    		ta.waitUntil(xpath, 4);
    	
    	xpath = "//*[text()='Notes']";
    	passed = ta.waitUntil(xpath, 4);
    	passed = ta.elementExist(xpath);
    	if(!passed)
    		return false;
    	
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();
    	
    	xpath = "(//*[text()='Notes:']/./following::textarea)[1]";
    	passed = ta.waitUntil(xpath, 2);
    	passed = ta.sendDatatoField(xpath, "Test Automation 2");
    	
    	xpath = "//input[@value='Add']";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Test Automation 2']";
    	passed = ta.waitUntil(xpath, 4);
    	
    	xpath = "//input[contains(@value,'Back')]";
    	passed = ta.waitUntil(xpath, 2);
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();
    	
    	xpath = "//*[text()='Query Details']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	report.takeScreenshot();
    	
    	return passed;    	
    }
    
    public boolean QueryProgress()
    {
    	ta.scrollUp();
    	
    	//Wait till overlay disappears
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	if(ta.elementExist(xpath))
    		ta.waitUntil(xpath, 4);
    	
    	xpath = "//*[text()='Progress']";
    	passed = ta.waitUntil(xpath, 4);
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();
    	
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	ta.waitUntil(xpath, 2);
    	if(ta.elementExist(xpath))
    		ta.waitUntil(xpath, 4);
    	
    	xpath = "(//*[text()[contains(.,'Department:')]]/./following::select)[1]";
    	//TODO If want to include department change
    	
    	xpath = "(//*[text()[contains(.,'Status:')]]/./following::select)[last()]";
    	passed = ta.waitUntil(xpath, 4);
    	passed = ta.selectBy(xpath, "RE");
    	
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	ta.waitUntil(xpath, 2);
    	if(ta.elementExist(xpath))
    		ta.waitUntil(xpath, 4);
    	
    	report.takeScreenshot();
    	
    	xpath = "//input[@value='Accept']";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Query Details']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	report.takeScreenshot();
    	
    	return passed;
    }
    
    public boolean QuerySignOff()
    {
    	ta.scrollUp();
    	
    	//Wait till overlay disappears
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	ta.waitUntil(xpath, 2);
    	if(ta.elementExist(xpath))
    		ta.waitUntil(xpath, 4);
    	
    	xpath = "//*[text()='Sign Off']";
    	passed = ta.waitUntil(xpath, 4);
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();
    	
    	xpath = "(//*[text()='Resolution:']/./following::select)[last()]";
    	passed = ta.waitUntil(xpath, 4);
    	passed = ta.selectBy(xpath, 1);
    	
    	xpath = "//iframe[@class='ice-blockui-overlay']";
    	ta.waitUntil(xpath, 2);
    	if(ta.elementExist(xpath))
    		ta.waitUntil(xpath, 4);
    	
    	report.takeScreenshot();
    	
    	xpath = "//input[@value='Accept']";
    	passed = ta.clickOn(xpath);
    	
    	xpath = "//*[text()='Query Details']";
    	passed = ta.waitUntil(xpath, 5);
    	
    	report.takeScreenshot();
    	
    	return passed;
    }
}
