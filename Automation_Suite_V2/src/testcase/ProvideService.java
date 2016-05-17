package testcase;

import operations.ErrorHandler;
import operations.Operations;
import operations.TestAction;
import operations.TestConfiguration;
import operations.TestData;
import operations.TestReport;

public class ProvideService 
{
	TestReport report;
	
    TestAction ta = new TestAction(Operations.getdriver());
    SalesSignoff sales; 
    
    //Configuration Variables
	public boolean NewCustomer 		= false;
    public String ServicePackage	= "";
	public String ServiceType		= "";
    public boolean PELPDLProvisioning	= false;
    public String AddMore			= "";
    public boolean AddMoreFlow		= false;
    public boolean runningPDL 		= false;
    public boolean comverseOne		= false;
    public boolean createCreditLimit= false;
    public String comOneOffer		= "";

    
    //Test Data
	String AccountNumber	= "";
	String department		= "BGSAL";
	String site				= "BUSG";
    String PELExchange		= "BOT";
    String PELNumberArea	= "BODD";
    String PCLExchange		= "MSCA";
    String PCLNumberArea	= "SMPO";
    String CreditLimit		= "1200";
    String CallLimit		= "1200";
    
    String salesAccNumber	= "";
    String salesServiceNum	= "";
    String salesOrderNumber	= "";
    String numberAllocation;
    boolean fastTrack 		= false;
    
    String OnScreenError;
    
    //Private Variables
    boolean passed;
    String xpath;
	String oldScreenName = "";
    String newScreenName = "";
    int sameScreenRetry	= 0;
	
	public ProvideService(TestReport report, String AccountNumber)
	{
		this.AccountNumber = AccountNumber;
		this.report 	= report;
		sales 			= new SalesSignoff(this.report);
	}
	
	public void execute()
	{		
	    while (true)
	    {
	    	passed = testStep_1();
	        report.takeScreenshot();
	        if (!passed)
	        	break;
	        
	        passed = testStep_2();
	        if (!passed)
	        	break;
	        
	        passed = testStep_3();
	        if(!passed)
	        	break;
	        
	        passed = testStep_4();
	        if(!passed)
	        	break;
	        
	        passed = testStep_5();
	        if(!passed)
	        	break;
	        
		    break;
	    }
	}
	
	private boolean testStep_1()
	{
		if(runningPDL||AddMoreFlow)
		{
			xpath = "(//*[text()='Service Package:']/./following::select)[1]";
			passed = ta.waitUntil(xpath, 2000);
			passed = ta.selectBy(xpath, ServicePackage);
			
			xpath = "//input[contains(@value,'Proceed')]";
			passed = ta.clickOn(xpath);
			
			xpath = "(//*[text()='Service Package:']/./following::select)[1]";
			passed = ta.waitUntilElementnotExist(xpath, 5);
		}
		else
		{
			if(NewCustomer)
			{
				xpath = "(//*[text()[contains(.,'Department:')]]/./following::select)[1]";
				passed = ta.selectBy(xpath, department);
				
				ta.checkElementinSelect(xpath, "");
				
				xpath = "(//*[text()[contains(.,'Site:')]]/./following::select[@disabled = 'disabled'])[1]";
				passed = ta.waitUntilElementnotExist(xpath, 2);
					
				xpath = "(//*[text()[contains(.,'Site:')]]/./following::select)[1]";
				passed = ta.selectBy(xpath, site);
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
				passed = ta.waitUntilElementnotExist(xpath, 2);
					
				xpath = "(//*[text()[contains(.,'Site:')]]/./following::select)[1]";
				passed = ta.selectBy(xpath, site);
					
				xpath = "(//*[text()[contains(.,'Service Type:')]]/./following::select)[1]";
				passed = ta.checkElementinSelect(xpath, ServiceType);
				if(passed)
				{
					ta.log("Service Type '" + ServiceType + "' is Available");
				}
				else
				{
					ta.log("Service Type '" + ServiceType + "' is NOT Available. Please check the BU Param configuration or the user can provision the provided Service Type");
					return false;
				}
				passed = ta.selectBy(xpath, ServiceType);
				ta.waitFor(1000);
					
				xpath = "(//*[text()='Service Package:']/./following::select)[1]";
				passed = ta.selectBy(xpath, ServicePackage);
			}
			
			xpath = "//input[contains(@value,'Proceed')]";
			passed = ta.clickOn(xpath);
			
			xpath = "(//*[text()='Service Package:']/./following::select)[1]";
			passed = ta.waitUntilElementnotExist(xpath, 3);
//			if(ServiceType.equals("PDL"))
//			{
//				xpath = "//*[text()[contains(.,'Telephone Service No')]]";
//				passed = ta.waitUntil(xpath, 3);
//				
//				xpath = "//input[contains(@value,'Proceed')]";
//				passed = ta.clickOn(xpath);
//					
//				report.takeScreenshot();
//					
//				xpath = "//*[text()[contains(.,'Telephone Service No')]]";
//				passed = ta.waitUntilElementnotExist(xpath, 5);
//			}
//			else
//			{
//				xpath = "//*[text()[contains(.,'Number of Services')]]";
//				passed = ta.waitUntil(xpath, 3);
//				
//				xpath = "//input[contains(@value,'Proceed')]";
//				passed = ta.clickOn(xpath);
//				
//				report.takeScreenshot();
//					
//				xpath = "//*[text()[contains(.,'Number of Services')]]";
//				passed = ta.waitUntilElementnotExist(xpath, 5);
//			}
		}
		return passed;
	}
		
	
	private boolean testStep_2()
	{
		while(true)
		{
			//TODO Handle error if new screen and previous screen are same.
			oldScreenName = newScreenName;
			
			xpath = "(//*[@class='hdrMid'])[1]";
			ta.waitUntil(xpath, 10);
			
			newScreenName = ta.getDatafromPage(xpath);
			
			if(oldScreenName.equals(newScreenName))
			{
				xpath = "//*[@class='iceMsgError']";
				
				if(!ta.elementExist(xpath))
				{
					if(sameScreenRetry <= 1)
						sameScreenRetry++;
					else
					{
						ta.log("ERROR : Not able to proceed. Please contact support with log and screenshot");
						break;
					}
				}
				else
				{
					OnScreenError = ta.getDatafromPage(xpath);
					ErrorHandler.handleOnScreenError(OnScreenError);
					
					ta.log("ERROR OCCURED : Please check the on screen error and contact support");
					
					break;
				}
			}
			
			if(newScreenName.equals("Pricing Plans"))
				pricingPlanScreen();
			else if(newScreenName.equals("Service Product"))
				serviceProducts();
			else if(newScreenName.equals("ISP Fields"))
				ISPFields();
			else if (newScreenName.equals("Product Fields"))
				productFields();
			else if (newScreenName.equals("Service Details"))
				serviceDetails();
			else if (newScreenName.equals("Provide IPTV Service (PTI)"))
				PTIServiceDetailsScreen();
			else
			{
				ta.log("ERROR : The screen '"+newScreenName+"' is not handled. Please contact support");
				break;
			}
		}
		
		return passed;	
	}
	
	private boolean testStep_3()
	{
		String max = TestData.randomNumeric();
		
		xpath = "//*[text()='Contract Details']";
		passed = ta.waitUntil(xpath, 10);
		
		xpath = "(//*[text()[contains(.,'Contract Number:')]]/./following::input)[1]";
		passed = ta.sendDatatoField(xpath, max);
		
		xpath = "(//*[text()[contains(.,'Contract Description:')]]/./following::textArea)[1]";
		passed = ta.sendDatatoField(xpath, "Contract Description");
		
		xpath = "(//*[text()[contains(.,'Contract Duration:')]]/./following::select)[1]";
		passed = ta.selectBy(xpath, 2);
		
		xpath = "//input[@value='Submit']";
		passed = ta.clickOn(xpath);
		
		if(ServiceType.equals("PEL"))
		{
			xpath = "//*[text()='Do you want to provide Broadband on the same service order']";
			passed = ta.waitUntil(xpath, 5);
			
			report.takeScreenshot();
			
			if(!PELPDLProvisioning)
			{
				xpath = "//input[@value='No']";
				passed = ta.waitUntil(xpath, 5);
				passed = ta.clickOn(xpath);
			}
			else
			{
				if(!runningPDL)
				{
					xpath = "//input[@value='Yes']";
					passed = ta.waitUntil(xpath, 5);
					passed = ta.clickOn(xpath);
					
					this.ServiceType	= "PDL";
					this.ServicePackage	= "ADSL";
					this.runningPDL		= true;
					this.execute();
				}
			}
		}
		
		return passed;
	}
	
	private boolean testStep_4()
	{
		if(AddMore.equals(""))
			passed = sales.signoff();
		else
			passed = sales.addMore(AddMore);
		
		salesAccNumber	= sales.AccountNumber;
		salesServiceNum	= sales.ServiceNumber;
		salesOrderNumber= sales.ServiceOrder;
		
		if(!AccountNumber.equals(salesAccNumber))
		{
			ta.log("ERROR : Mismatch between the AccountNumber provided and Account number in Sales Sign off");
			passed = false;
		}
		
		return passed;
	}
	
	private boolean testStep_5()
	{
		if(!fastTrack)
		{
			xpath = "//*[text()='Service Order List']";
			passed = ta.waitUntil(xpath, 5);
			
			report.takeScreenshot();
			
			xpath = "//*[text()='"+salesOrderNumber+"']";
			passed = ta.elementExist(xpath);
		}
		else
		{
			//TODO FastTrack
		}
		
		if(AddMore.equals(""))
		{
			xpath = "//*[text()='Service Order List']";
			passed = ta.waitUntil(xpath, 5);
			
			report.takeScreenshot();
			
			xpath = "//*[text()='"+salesOrderNumber+"']";
			passed = ta.elementExist(xpath);
		}
		else
		{
			//Nothing
		}
		return passed;
	}
	
	//SUPPORTING FUNCTIONS
	private boolean navigateToExistingCustomerScreen()
	{
    	xpath = "//*[text()[contains(.,'Customer Care')]]";
        passed = ta.waitUntil(xpath, 5);
        passed = ta.clickOn(xpath);
        
        report.takeScreenshot();
        
    	xpath = "//*[text()[contains(.,'Service Provisioning')]]";
        passed = ta.waitUntil(xpath, 5);
        passed = ta.clickOn(xpath);
        
        report.takeScreenshot();
        
        ta.closeOKpopup();
        
    	xpath = "//*[text()[contains(.,'Existing Customer')]]";
        passed = ta.waitUntil(xpath, 5);
        passed = ta.clickOn(xpath);
		
        report.takeScreenshot();
        
		return passed;
	}
	
	//Method to handle Pricing Plan screen
	private boolean pricingPlanScreen()
	{
		xpath = "//*[text()='Pricing Plans']";
		passed = ta.waitUntil(xpath, 5);
		
		//TODO Handle Pricing plan screen
		
		xpath = "//*[text()='Pricing Plan Approval Request']";
		if(ta.elementExist(xpath))
		{
			xpath = "(//*[text()[contains(.,'To be Authorised By:')]]/./following::select)[1]";
			ta.selectBy(xpath, 1);
			ta.waitFor(1000);
		}
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();
		
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
		if(ServiceType.equals("PCL"))
		{
			//Check if needs to select handset
			xpath = "//*[text()='No products selected']";
			if(ta.elementExist(xpath))
			{
				xpath = "//tr[@id='packageProductSelection:availableProductDetails:0']";
				passed = ta.clickOn(xpath);
				
				xpath = "//*[text()='No products selected']";
				passed = ta.waitUntilElementnotExist(xpath, 3);
			}
			if(comverseOne)
			{
				xpath = "(//*[text()[contains(.,'Primary Offers:')]]/./following::select)[1]";
				passed = ta.selectBy(xpath, comOneOffer);
				
				xpath = "//*[text()='Offer : "+comOneOffer+"']";
				passed = ta.waitUntil(xpath, 5);
				
				xpath = "//input[@value = 'Select']";
				passed = ta.clickOn(xpath);
				
				xpath = "//*[text()='Offer : "+comOneOffer+"']";
				if(ta.elementExist(xpath))
					passed = ta.waitUntilElementnotExist(xpath, 3);
			}
		}
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();
		
		xpath = "//*[text()='Service Product']";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}
	
	private boolean ISPFields()
	{
		xpath = "//*[text()='ISP Fields']";
		passed = ta.waitUntil(xpath, 5);
		
		//ISP Field Handling
		xpath = "(//*[text()[contains(.,'Service Order No')]]/./following::span)[1]";
		String email = ta.getDatafromPage(xpath);
				
		xpath = "(//*[text()[contains(.,'Email')]]/./following::input)[1]";
		if(ta.elementExist(xpath))
			passed = ta.sendDatatoField(xpath, email);
		
		xpath = "(//*[text()[contains(.,'Email Address')]]/./following::select)[1]";
		if(ta.elementExist(xpath))
			passed = ta.selectBy(xpath, 1);
		
		xpath = "(//*[text()[contains(.,'Password')]]/./following::input)[2]";
		if(ta.elementExist(xpath))
			passed = ta.sendDatatoField(xpath, "A!2"+TestData.randomString());
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();
		
		xpath = "//*[text()='ISP Fields']";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}
	
	private boolean productFields()
	{
		xpath = "//*[text()='Product Fields']";
		passed = ta.waitUntil(xpath, 5);
		
		xpath = "//input[@class='iceInpTxt MandatoryTextBox']";
		if(ta.elementExist(xpath))
		{
			//TODO If mandatory fields exist. Fill them
			ta.log("Mandatory Fields Exists. Calling stub");
			TestConfiguration.stubFillProductFields();
		}
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();
		
		xpath = "//*[text()='Product Fields']";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}
	
	private boolean serviceDetails()
	{
		xpath = "//*[text()='Service Details']";
		passed = ta.waitUntil(xpath, 5);
		
		//TODO Handle Service Details screen
		passed = handleServiceDetailsScreen(ServiceType);
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();
		
		xpath = "//*[text()='Service Details']";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}
	
	private boolean handleServiceDetailsScreen(String ServiceType)
	{
		switch (ServiceType)
		{
			case "PEL": passed = PELServiceDetailsScreen();
				break;
			case "PDL": passed = PDLServiceDetailsScreen();
				break;
			case "PCL": passed = PCLServiceDetailsScreen();
				break;
			case "PTV": passed = PELServiceDetailsScreen();
				break;
		}
		
		
		return passed;
	}
	
	private boolean PTIServiceDetailsScreen()
	{
		xpath = "(//*[text()[contains(.,'Service Number Allocation:')]]/./following::select)[1]";
		numberAllocation = ta.getValueFromSelect(xpath);
		
		if(!numberAllocation.equals("Auto"))
		{
			passed = ta.selectBy(xpath, "Auto");
			
			xpath = "//input[@value='Deallocate']";
			passed = ta.waitUntil(xpath, 5);
		}
		
		xpath = "//input[@value='Find' and @disabled='disabled']";
		if(!ta.elementExist(xpath)) 
		{
			xpath = "//input[@value='Find']";
			passed = ta.clickOn(xpath);
			
			xpath = "//input[@value='Deallocate']";
			passed = ta.waitUntil(xpath, 5);
		}
				
		xpath = "(//*[text()[contains(.,'Service to be linked to ADSL/Other Service:')]]/./following::select)[1]";
		
		if(ta.elementExist(xpath))
		{
			if(ta.numberofElementsinSelect(xpath)>1)
			{
				passed = ta.selectBy(xpath, 1);
				ta.waitFor(1000);
			}
			else
			{
				passed = false;
				ta.log("ERROR : No DS/Other service available for allocation. Please re run the test with an accoung having DS or other Compatible service for IPTV");
				return passed;
			}
		}		
		
		xpath = "(//*[text()[contains(.,'Address Type:')]]/./following::select)[1]";
		if(ta.elementExist(xpath))
		{
			xpath = "(//*[text()[contains(.,'Same as Account Address:')]]/./following::input)[1]";
			passed = ta.clickOn(xpath);
			xpath = "(//*[text()[contains(.,'Address Type:')]]/./following::select)[1]";
			passed = ta.waitUntilElementnotExist(xpath, 5);
		}
		
		//TODO Handle Mac Address 
		
		report.takeScreenshot();
		
		xpath = "//input[@value='Proceed']";
		passed = ta.clickOn(xpath);
		
		report.takeScreenshot();
		
		xpath = "(//*[text()[contains(.,'Service Number Allocation:')]]/./following::select)[1]";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}

	
	/**
	 * @return
	 */
	private boolean PELServiceDetailsScreen()
	{
		if(!runningPDL)
		{
			xpath = "(//*[text()[contains(.,'Exchange:')]]/./following::select)[1]";
			passed = ta.selectBy(xpath, PELExchange);
		
			xpath = "(//*[text()[contains(.,'Number Area:')]]/./following::select[@disabled='disabled'])[1]";
			if(ta.elementExist(xpath))
				passed = ta.waitUntilElementnotExist(xpath, 5);
		
			xpath = "(//*[text()[contains(.,'Number Area:')]]/./following::select)[1]";
			passed = ta.selectBy(xpath, PELNumberArea);
		}
		
		xpath = "(//*[text()[contains(.,'Service Number Allocation:')]]/./following::select)[1]";
		numberAllocation = ta.getValueFromSelect(xpath);
		
		if(!numberAllocation.equals("Auto"))
		{
			passed = ta.selectBy(xpath, "Auto");
			
			xpath = "//input[@value='Deallocate']";
			passed = ta.waitUntil(xpath, 5);
		}
		
		xpath = "//input[@value='Find' and @disabled='disabled']";
		if(!ta.elementExist(xpath)) 
		{
			xpath = "//input[@value='Find']";
			passed = ta.clickOn(xpath);
			
			xpath = "//input[@value='Deallocate']";
			passed = ta.waitUntil(xpath, 5);
		}
		
		if(ServiceType.equals("PEL")||ServiceType.equals("PDL"))
		{
			xpath = "(//*[text()[contains(.,'Service Usage:')]]/./following::select)[1]";
			passed = ta.selectBy(xpath, "V");
			
			xpath = "(//*[text()[contains(.,'Charge Option:')]]/./following::select)[1]";
			passed = ta.selectBy(xpath, "2");
			
			xpath = "(//*[text()[contains(.,'Special Instructions:')]]/./following::textArea)[1]";
			passed = ta.sendDatatoField(xpath, "Automation Testing");
		}
		
		if(ServiceType.equals("PDL"))
		{
			//TODO Handle Plant item selection
		}
		
		report.takeScreenshot();
		
		xpath = "(//*[text()[contains(.,'Same as Account Address:')]]/./following::input)[1]";
		passed = ta.clickOn(xpath);
		
		xpath = "(//*[text()[contains(.,'Address Type:')]]/./following::select)[1]";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		return passed;
	}

	private boolean PDLServiceDetailsScreen()
	{
		passed = PELServiceDetailsScreen();
		
		return passed;
	}

	private boolean PCLServiceDetailsScreen()
	{
		xpath = "(//*[text()[contains(.,'Exchange:')]]/./following::select)[1]";
		passed = ta.selectBy(xpath, PCLExchange);
	
		xpath = "(//*[text()[contains(.,'Number Area:')]]/./following::select[@disabled='disabled'])[1]";
		if(ta.elementExist(xpath))
			passed = ta.waitUntilElementnotExist(xpath, 5);
		
		xpath = "(//*[text()[contains(.,'Number Area:')]]/./following::select)[1]";
		passed = ta.selectBy(xpath, PCLNumberArea);
		
		xpath = "(//*[text()[contains(.,'Service Number Allocation:')]]/./following::select)[1]";
		numberAllocation = ta.getValueFromSelect(xpath);
		
		if(!numberAllocation.equals("Auto"))
		{
			passed = ta.selectBy(xpath, "Auto");
			
			xpath = "//input[@value='Deallocate']";
			passed = ta.waitUntil(xpath, 5);
		}
		
		xpath = "//input[@value='Find' and @disabled='disabled']";
		if(!ta.elementExist(xpath)) 
		{
			xpath = "//input[@value='Find']";
			passed = ta.clickOn(xpath);
			
			xpath = "//input[@value='Deallocate']";
			passed = ta.waitUntil(xpath, 5);
		}
		
		xpath = "//input[@value='Look Up']";
		passed = ta.clickOn(xpath);
		
		xpath = "//*[text()='More Numbers exist matching the details entered']";
		passed = ta.waitUntil(xpath, 5);
		
		xpath = "(//*[text()[contains(.,'SIM')]]/./following::select)[1]";
		passed = ta.selectBy(xpath, 4);
		
		xpath = "//*[text()='More Numbers exist matching the details entered']";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		xpath = "(//*[text()[contains(.,'Same as Account Address:')]]/./following::input)[1]";
		passed = ta.clickOn(xpath);
		
		xpath = "(//*[text()[contains(.,'Address Type:')]]/./following::select)[1]";
		passed = ta.waitUntilElementnotExist(xpath, 5);
		
		if(createCreditLimit)
		{
			xpath = "//*[text()[contains(.,'Billing Details')]]";
			if(ta.elementExist(xpath))
			{
				xpath = "(//*[text()='Credit Limit:']/./following::input)[1]";
				passed = ta.sendDatatoField(xpath, CreditLimit);
				
				xpath = "(//*[text()='Call Limit:']/./following::input)[1]";
				passed = ta.sendDatatoField(xpath, CallLimit);
				
				xpath = "(//*[text()='Use Customer-Selected Credit Limit:']/./following::input)[1]";
				passed = ta.clickOn(xpath);
				
				xpath = "(//*[text()='Customer-Selected Credit Limit:']/./following::input[@class='iceInpTxt-dis MandatoryTextBox-dis'])[1]";
				if(ta.elementExist(xpath))
					passed = ta.waitUntilElementnotExist(xpath, 5);
				
				xpath = "(//*[text()='Customer-Selected Credit Limit:']/./following::input)[1]";
				passed = ta.sendDatatoField(xpath, CreditLimit);
				
				xpath = "(//*[text()='Customer-Selected Update Type:']/./following::select)[1]";
				passed = ta.selectBy(xpath, 1);
				
				xpath = "(//*[text()='Customer-Selected Bar Type:']/./following::select)[1]";
				passed = ta.selectBy(xpath, 1);
			}
			else
			{
				ta.log("ERROR : Not able to create credit limit. Please select a Post Paid service package");
				return false;
			}
		}
		
		return passed;
	}
}
