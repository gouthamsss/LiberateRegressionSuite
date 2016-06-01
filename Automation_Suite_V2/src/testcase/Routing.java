package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class Routing 
{
	public String ServiceOrder 		= "XD00203";
	public String Department		= "BILDM";
	public String ExchnageID		= "BOT";
	public String PlantItemType		= "FDP";
	public String PlantItemNum		= "FDP60";
	String RoutingType;
	
    TestAction ta 		= new TestAction(Operations.getdriver());
    TestReport report;
    
    String xpath	="";
    boolean passed = true;
    
    public Routing(TestReport report, String RoutingType)
    {
		this.report = report;
		this.RoutingType = RoutingType;
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
		    if(RoutingType.contains("Release"))
		    	break;
		    
		    passed = testStep_4();
   	        if (!passed)
   	        	break;
   	        
		    passed = testStep_5();
   	        if (!passed)
   	        	break;
   	        
		    passed = testStep_6();
   	        if (!passed)
   	        	break;
   	        
		    break;
	    }
		
		return passed;
    }
    
    private boolean testStep_1()
    {
		xpath = "//*[text()='Orders']";
		passed = ta.waitUntil(xpath, 5);
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();
		
		xpath = "//*[text()='Route Maintenance']";
		passed = ta.waitUntil(xpath, 5);
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();

		xpath = "//*[text()='Allocate Route (Service Order)']";
		passed = ta.waitUntil(xpath, 5);
		passed = ta.clickOn(xpath);

		report.takeScreenshot();

    	return passed;
    }
    
    private boolean testStep_2()
    {
    	xpath = "(//*[text()='Department:']/./following::select[@disabled='disabled'])[1]";
    	passed = ta.waitUntil(xpath, 5);
    	
    	String SelectedDepartment = ta.getValueFromSelect(xpath);
    	SelectedDepartment = SelectedDepartment.split("-")[0];
    	
    	if(!Department.equals(SelectedDepartment))
    	{
    		xpath = "//input[@value='Change']";
    		passed = ta.clickOn(xpath);
    		
        	xpath = "(//*[text()='Department:']/./following::select[@disabled='disabled'])[1]";
        	if(ta.elementExist(xpath))
        	{
        		ta.waitUntilElementnotExist(xpath, 5);
        	}
        	
        	xpath = "(//*[text()='Department:']/./following::select)[1]";
        	passed = ta.selectBy(xpath, Department);
        	
        	xpath = "(//*[text()='Department:']/./following::select[@disabled='disabled'])[1]";
        	passed = ta.waitUntil(xpath, 5);
    	}
    	 		
    	xpath = "(//*[text()='Service Order:']/./following::input)[1]";
    	passed = ta.sendDatatoField(xpath, ServiceOrder);
    	
    	xpath = "//input[@value='Search']";
    	passed = ta.clickOn(xpath);
    	
    	report.takeScreenshot();
    	
    	return passed;
    }
    
    private boolean testStep_3()
    {
    	xpath = "//*[text()[contains(.,'Line Details')]]";
		passed=ta.waitUntil(xpath, 3);
		
		//Check if any service order line is selected
		xpath = "//tr[contains(@class,'ui-datatable-even ui-selected ui-state-active')]";
		if(!ta.elementExist(xpath))
		{
			//selecting first row
			xpath = "//tr[contains(@class,'ui-datatable-even')][1]";
			passed=ta.clickOn(xpath);
		}
		
		//Wait until service order line is selected
		xpath = "//tr[contains(@class,'ui-datatable-even ui-selected ui-state-active')]";
		passed=ta.waitUntil(xpath, 3);
		
		//Check if service is already routed
		xpath = "//*[text()='Release Route']";
		if(ta.elementExist(xpath))
		{
			//Release route
			if(RoutingType.contains("Release"))
			{
				passed = releaseRoute(true);
				return passed;
			}
			else
			{
				passed = releaseRoute(false);
			}
		}
		
		ta.scrollUp();
		
		xpath = "//*[text()[contains(.,'Allocate Auto')]]";
		passed=ta.clickOn(xpath);
		
		report.takeScreenshot();
		
		return passed;
    }
    
    private boolean testStep_4()
    {
		xpath = "(//*[text()[contains(.,'Exchange Area :')]]/./following::select)[1]";
		passed=ta.waitUntil(xpath, 3);
		passed=ta.selectBy(xpath, ExchnageID);
		
		ta.waitFor(500);
		
		xpath = "(//*[text()[contains(.,'Plant Item Type:')]]/./following::select)[1]";
		passed=ta.selectBy(xpath, PlantItemType);
		
		ta.waitFor(500);
		
		xpath = "(//*[text()[contains(.,'Plant Item No:')]]/./following::input)[1]";
		passed=ta.sendDatatoField(xpath, PlantItemNum);
		
		xpath = "//input[@value='Accept']";
		passed = ta.clickOn(xpath);
		 
		report.takeScreenshot();
		
    	return passed;
    }
    
    private boolean testStep_5()
    {
		xpath = "//*[text()='"+PlantItemNum+"']";
		passed = ta.waitUntil(xpath, 5);
		
		xpath = "//input[@value='Accept']";
		passed = ta.clickOn(xpath);
    	
		report.takeScreenshot();
		
    	return passed;
    }
    
    private boolean testStep_6()   
    {
		xpath = "//input[@value='OK']";
		passed = ta.waitUntil(xpath, 5);
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();
		
		passed = ta.waitUntilElementnotExist(xpath, 4);
		
		xpath = "(//*[text()='Allocated']/./following::td)[5]";
		String routingStatus = ta.getDatafromPage(xpath);
		ta.log("Routing Status : " + routingStatus);
		
		if(!routingStatus.equals("Y"))
		{
			ta.log("ERROR : Routing Status not 'Y'. Please check backend Database for route status.");
			passed = false;
		}
		
		ta.scrollUp();
		
		report.takeScreenshot();

    	return passed;
    }
    
    private boolean releaseRoute(boolean takescreenshot)
    {
		//Releasing route
		xpath = "//*[text()='Release Route']";
		passed=ta.clickOn(xpath);
		
		if(takescreenshot)
			report.takeScreenshot();
		
		xpath = "//input[@value='Release']";
		passed=ta.waitUntil(xpath, 3);
		passed=ta.clickOn(xpath);
		
		if(takescreenshot)
			report.takeScreenshot();
		
		xpath = "//input[@value='Yes']";
		passed=ta.waitUntil(xpath, 3);
		passed=ta.clickOn(xpath);
		
		if(takescreenshot)
			report.takeScreenshot();
		
		xpath = "//input[@value='OK']";
		passed=ta.waitUntil(xpath, 3);
		passed=ta.clickOn(xpath);
		
		if(takescreenshot)
			report.takeScreenshot();
		
		ta.waitFor(2000);
		
		return passed;
    }
}

