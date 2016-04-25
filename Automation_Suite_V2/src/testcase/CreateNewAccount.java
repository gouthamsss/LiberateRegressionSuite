package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestData;

public class CreateNewAccount
{
	String AccountNumber;
	static String AccountFirstName = TestData.customerName();
	static String AccountLastName	= TestData.customerName();
	//String CustomerID;
	static String ApplicationForm	= "fixedResidential";
	static String CustomerType		= "R";
	static String ServiceType		= "PEL";
	static String ServicePackage	= "ETFTESTING";
	static String CustomeIDType		= "NI";
	static boolean SelectServicePackage = true;
	static boolean CustomerIDProvided   = false;
	
	static boolean passed;
	
	static String xpath;
	static String xpath_1;
	static String xpath_2;
	
	static TestAction ta = new TestAction(Operations.getdriver());
	
	public static void execute()
	{
		//LoginLogout.login("http://172.21.73.80:8081/liberate-LONI01-S10/");
		LoginLogout.login("http://172.21.73.80:8083/liberate-LONI02-S06/");
		
		while(true)
		{
			if(!testStep_1())
			{
				break;
			}
			if(!testStep_2())
			{
				break;
			}
			if(!testStep_3())
			{
				break;
			}
			
			break;
		}
	}
	
	private static boolean testStep_1()
	{
		xpath = "//*[text()[contains(.,'Customer Care')]]";
		passed = ta.waitUntil(xpath, 5);
		passed = ta.clickOn(xpath);
		
		return passed;
	}
	
	private static boolean testStep_2()
	{
		xpath = "//*[text()[contains(.,'Service Provisioning')]]";
		passed = ta.waitUntil(xpath, 5);
		passed = ta.clickOn(xpath);
		
		return passed;		
	}
	
	private static boolean testStep_3()
	{
		xpath = "(//*[text()[contains(.,'Application Form:')]]/./following::select)[1]";
		passed = ta.waitUntil(xpath, 5);
		ta.closeOKpopup();
		passed = ta.selectBy(xpath, ApplicationForm);
		
		if(SelectServicePackage)
		{
			xpath = "(//*[text()[contains(.,'Customer Type:')]]/./following::select)[1]";
			passed = ta.selectBy(xpath, CustomerType);

			xpath = "(//*[text()[contains(.,'Service Type:')]]/./following::select)[1]";
			passed = ta.selectBy(xpath, ServiceType);

			xpath = "(//*[text()[contains(.,'Service Package:')]]/./following::select)[1]";
			passed = ta.waitUntilSelectOptionsPopulated(xpath);
			passed = ta.selectBy(xpath, ServicePackage);
		}
		
		xpath = "//input[@value='Proceed >>']";
		passed = ta.waitUntil(xpath, 2);
		passed = ta.clickOn(xpath);
		
		xpath_1 = "(//*[text()[contains(.,'First Name:')]]/./following::select)[1]";
		xpath_2 = "(//*[text()[contains(.,'ID:')]]/./following::select)[1]";
		
		xpath = xpath_1 + " | " + xpath_2;
		passed = ta.waitUntil(xpath, 10);
		
		if(!ta.elementExist(xpath_2))
		{
			xpath = xpath_2;
			passed = ta.selectBy(xpath, CustomeIDType);
			
			xpath = "(//input[contains(@class,'MandatoryTextBox')])[1]";
			passed = ta.waitUntil(xpath, 2);
			passed = ta.sendDatatoField(xpath, AccountFirstName);
			
			xpath = "(//input[@value='Proceed >>'])[last()]";
			passed = ta.clickOn(xpath);
			
			CustomerIDProvided = true;
			
			xpath = "//input[@value='Create new account']";
			passed = ta.waitUntil(xpath, 2);
			passed = ta.clickOn(xpath);
		}
		
		return passed;
	}
}
