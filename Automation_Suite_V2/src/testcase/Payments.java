package testcase;

import main.MainClass;
import operations.Operations;
import operations.TestAction;
import operations.TestData;
import operations.TestReport;

public class Payments
{
	String CashDrawerS06	= "CASH";
	String CashDrawerS10	= "CASH1";
	String CashDrawer		= "";
	String OpenDrawer		= "AQSAL";
	String PaymentType		= "";
	
	int numberOfPayments	= 3;
	int totalPayment		= numberOfPayments * 100;
	String[] AccountNumbers = new String[numberOfPayments];
	
	TestReport report;
    TestAction ta 		= new TestAction(Operations.getdriver());
    
    boolean passed;
    String xpath;
    
    public Payments(TestReport report, String PaymentType)
    {
    	this.report		= report;
    	this.PaymentType= PaymentType;
    }
    
    public void execute()
	{		
	    while (true)
	    {
	    	passed = testStep_1();
	        if (!passed)
	        	break;
			if(this.PaymentType.contains("CashDrawer"))
				break;
			
	        passed = testStep_2();
   	        if (!passed)
   	        	break;
		    break;
	    }
	}
	
	private boolean testStep_1()
	{
		if(this.PaymentType.contains("CashDrawer"))
			CashDrawer = OpenDrawer;
		else if(MainClass.Environment.equals("S10"))
			CashDrawer = CashDrawerS10;
		else if (MainClass.Environment.equals("S06"))
			CashDrawer = CashDrawerS06;
		else
		{
			ta.log("CashDrawer not set up for the Environment. Please contact support with logs.");
		}
		//Select Cash drawer Section
		xpath	= "//*[text()='Orders']";
		passed	= ta.waitUntil(xpath, 5);
		passed	= ta.clickOn(xpath);
		
		xpath = "(//*[text()='Department:']/./following::select)[1]";
		if(!ta.getValueFromSelect(xpath).contains(CashDrawer))
		{
			xpath = "//input[@value = 'Change']";
			passed = ta.clickOn(xpath);
			
			xpath = "(//*[text()='Department:']/./following::select[@disabled='disabled'])[1]";
			if(ta.elementExist(xpath))
			{
				passed = ta.waitUntilElementnotExist(xpath, 4);
			}
			
			xpath = "(//*[text()='Department:']/./following::select)[1]";
			passed = ta.selectBy(xpath, CashDrawer);
			
			xpath = "(//*[text()='Department:']/./following::select[@disabled='disabled'])[1]";
			if(!ta.elementExist(xpath))
			{
				passed = ta.waitUntil(xpath, 4);
			}
		}
		report.takeScreenshot();
		
		xpath	= "//*[text()='Payments']";
		passed	= ta.waitUntil(xpath, 5);
		passed	= ta.clickOn(xpath);
		
		xpath	= "//*[text()[contains(.,'Payments>> Payments>> Single Payment>>')]]";
		ta.waitUntil(xpath, 5);
		
		xpath = "//*[text()='Account Number:']";
		if(!ta.elementExist(xpath))
		{
			xpath = "//*[text()[contains(.,'Cashdrawer already open in another office.')]]";
			if(this.PaymentType.contains("CashDrawer"))
			{
				passed = ta.waitUntil(xpath, 4);
				report.takeScreenshot();
				passed = ta.elementExist(xpath);
			}
			else
			{
				changeCashDrawer(CashDrawer);
				testStep_1();
			}
		}
		return passed;
	}
	
	private boolean testStep_2()
	{
		if(this.PaymentType.equalsIgnoreCase("Batch"))
		{
			passed = doBatchPayment();
		}
		
		return passed;
	}
	
	private boolean doBatchPayment()
	{
		populateAccountnumbers();
		
		xpath = "//*[text()='Batch Payment']";
		passed = ta.clickOn(xpath);
		
		xpath	= "//*[text()[contains(.,'Payments>> Payments>> Batch Payment>>')]]";
		passed = ta.waitUntil(xpath, 4);
		
		xpath = "//*[text()='Cash drawers for this user require settlement.']";
		if(ta.elementExist(xpath))
		{
			xpath = "//input[@value='Proceed']";
			passed = ta.clickOn(xpath);
		}
		
		xpath = "(//*[text()='Batch No:']/./following::input)[1]";
		passed = ta.waitUntil(xpath, 4);
		passed = ta.sendDatatoField(xpath, TestData.randomNumeric());
		
		xpath = "(//*[text()='No of Entries:']/./following::input)[1]";
		passed = ta.sendDatatoField(xpath, Integer.toString(numberOfPayments));
		
		xpath = "(//*[text()='Total Amount:']/./following::input)[1]";
		passed = ta.clearInputField(xpath);
		passed = ta.sendDatatoField(xpath, Integer.toString(totalPayment));
		
		xpath = "(//*[text()='Payment Method:']/./following::select)[1]";
		passed = ta.selectBy(xpath, "C");
		
		for(int i = 0; i < numberOfPayments; i++)
		{
			xpath = "//input[@id='batchPaymentForm:batchPaymentTable:"+i+":accountNo' and @disabled='true']";
			if(ta.elementExist(xpath))
				passed = ta.waitUntilElementnotExist(xpath, 2);
			
			xpath = "//input[@id='batchPaymentForm:batchPaymentTable:"+i+":accountNo']";
			passed = ta.sendDatatoField(xpath, AccountNumbers[i]);
			
			xpath = "//input[@value='Show Account Names']";
			if(!ta.elementExist(xpath))
			{
				passed = ta.waitUntil(xpath, 4);
			}
			passed = ta.clickOn(xpath);
			ta.waitFor(1500);
			
			xpath = "//input[@id='batchPaymentForm:batchPaymentTable:"+i+":accountAmountChange']";
			passed = ta.sendDatatoField(xpath, "100");
			ta.waitFor(1000);
		}
		report.takeScreenshot();
		ta.waitFor(1000);

		xpath = "//input[@value='OK']";
		passed = ta.clickOn(xpath);
		
		xpath = "//*[text()='Batch payment submitted successfully.']";
		passed = ta.waitUntil(xpath, 10);
		
		passed = ta.elementExist(xpath);
		report.takeScreenshot();
		
		ta.closeOKpopup();
		report.takeScreenshot();
		
		return passed;
	}

	
	private void populateAccountnumbers()
	{
		//TODO implement retrieval from db file
		AccountNumbers[0] = "240004430000";
		AccountNumbers[1] = "250005560000";
		AccountNumbers[2] = "250005570000";
	}
	
	private boolean changeCashDrawer(String CashDrawer)
	{
		return passed;
	}
}
