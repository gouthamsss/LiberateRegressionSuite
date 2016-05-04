package main;

import operations.TestReport;
import testcase.ProvideService;

public class MainClass
{
	public static void main(String [] args)
	{
//		CreateNewAccount createNewAccount = new CreateNewAccount("fixedResidential","ETFTESTING");
//		createNewAccount.execute();

		TestReport report = new TestReport("ProvideService", "Reports\\");

		ProvideService provideService = new ProvideService(report, "240004430000");
		provideService.execute();
		report.createScreenshotDocument();

	}
}
