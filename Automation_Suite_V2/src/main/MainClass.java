package main;

import operations.TestReport;
import testcase.CreateNewAccount;
import testcase.ProvideService;

public class MainClass
{
	public static void main(String [] args)
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
