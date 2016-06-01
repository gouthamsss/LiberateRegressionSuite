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
	
	public QueryManagement (TestReport report)
	{
		this.report = report;
	}
	
    public boolean execute()
    {
    	while(true)
    	{
	    	passed = testStep_1();
	        if (!passed)
	        	break;
	        
	        
	        
    		break;
    	}
		return passed;
    }
    
    private boolean testStep_1()
    {
    	xpath = "//*[text()='Maintain Query']";
    	passed = ta.waitUntil(xpath, 5);
    	passed = ta.clickOn(xpath);
    	report.takeScreenshot();
    	
    	return passed;
    }
    
    private boolean testStep_2()
    {
    	xpath = "(//*[text()='Search By:']/./following::select)[1]";
    	passed = ta.waitUntil(xpath, 5);
    	passed = ta.selectBy(xpath, "ACC");
    	
    	
    	
    	return passed;
    }
}
