package main;

import operations.TestReport;
import testcase.CreateNewAccount;
import testcase.ProvideService;

public class TestCase 
{
	public static void PELExistingCustomer()
	{
		TestReport report = new TestReport("PELExistingCustomer", "Reports\\");

		ProvideService provideService = new ProvideService(report, "240004430000");
		provideService.ServiceType = "PEL";
		provideService.ServicePackage = "ETFTESTING";
		provideService.execute();
		
		report.createScreenshotDocument();
	}
	public static void PDLExistingCustomer()
	{
		TestReport report = new TestReport("PDLExistingCustomer", "Reports\\");

		ProvideService provideService = new ProvideService(report, "240004430000");
		provideService.ServiceType = "PDL";
		provideService.ServicePackage = "ADSL";
		provideService.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void PELPDLNewCustomer()
	{
		TestReport report = new TestReport("PELPDLNewCustomer", "Reports\\");
		CreateNewAccount createNewAccount = new CreateNewAccount(report, "fixedResidential","ETFTESTING");
		createNewAccount.ServiceType = "PEL";
		createNewAccount.ServicePackage = "ETFTESTING";
		createNewAccount.execute();

		ProvideService provideService		= new ProvideService(report, createNewAccount.AccountNumber);
		provideService.NewCustomer			= true;
		provideService.PELPDLProvisioning	= true;
		provideService.ServiceType = createNewAccount.ServiceType;
		provideService.ServicePackage = createNewAccount.ServicePackage;
		provideService.execute();
		
		report.createScreenshotDocument();
	}
	
	public void testCase()
	{
		TestReport report = new TestReport("ProvideService", "Reports\\");
		CreateNewAccount createNewAccount = new CreateNewAccount(report, "fixedResidential","ETFTESTING");
		createNewAccount.ServiceType = "PEL";
		createNewAccount.ServicePackage = "ETFTESTING";
		createNewAccount.execute();

		ProvideService provideService = new ProvideService(report, createNewAccount.AccountNumber);
		provideService.NewCustomer = true;
		provideService.ServiceType = createNewAccount.ServiceType;
		provideService.ServicePackage = createNewAccount.ServicePackage;
		provideService.execute();
		
		report.createScreenshotDocument();
	}
}
