package main;

import operations.Operations;
import operations.TestReport;
import testcase.CreateNewAccount;
import testcase.CustomerSearch;
import testcase.LoginLogout;
import testcase.CS_MaintainAccountDeposit;
import testcase.Payments;
import testcase.ProvideService;
import testcase.CS_QueryManagement;
import testcase.Routing;
import app_ui.MainWindow;

public class TestCase 
{
	public static String ReportLocation =  "Reports\\";// = "Reports\\"+TestData.buildNumber+"\\";
	
	static Operations op = new Operations();
	
	public static void PELExistingCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PELExistingCustomer", ReportLocation);

		ProvideService provideService	= new ProvideService(report, "240004430000");
		provideService.ServiceType		= "PEL";
		provideService.ServicePackage	= "ETFTESTING";
		
		Boolean TestStatus = provideService.execute();
		MainWindow.PELExistingCustomerStatus.setText(op.testStatus(TestStatus));
		
		report.createScreenshotDocument();
	}
	
	public static void PDLExistingCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PDLExistingCustomer", ReportLocation);

		ProvideService provideService	= new ProvideService(report, "240004430000");
		provideService.ServiceType		= "PDL";
		provideService.ServicePackage	= "ADSL";
		Boolean TestStatus = provideService.execute();
		MainWindow.PDLExistingCustomerStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void PELPDLNewCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PELPDLNewCustomer", ReportLocation);
		
		CreateNewAccount createNewAccount	= new CreateNewAccount(report, "fixedResidential","ETFTESTING");
		createNewAccount.ServiceType		= "PEL";
		createNewAccount.ServicePackage		= "ETFTESTING";
		Boolean TestStatus = createNewAccount.execute();
		
		if(TestStatus == false)
		{
			MainWindow.PELPDLNewCustomerStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		ProvideService provideService		= new ProvideService(report, createNewAccount.AccountNumber);
		provideService.NewCustomer			= true;
		provideService.PELPDLProvisioning	= true;
		provideService.ServiceType			= createNewAccount.ServiceType;
		provideService.ServicePackage		= createNewAccount.ServicePackage;
		TestStatus = provideService.execute();
		MainWindow.PELPDLNewCustomerStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void PCLExistingCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PCLExistingCustomer", ReportLocation);
		
		ProvideService provideService	= new ProvideService(report, "240004430000");
		provideService.NewCustomer		= false;
		provideService.ServiceType		= "PCL";
		provideService.ServicePackage	= "PCL_Res";
		Boolean TestStatus = provideService.execute();
		MainWindow.PCLExistingCustomerStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void PCLNewCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PCLNewCustomer", ReportLocation);
		
		CreateNewAccount createNewAccount	= new CreateNewAccount(report, "fixedResidential","PCL_Res");
		createNewAccount.ServiceType		= "PCL";
		createNewAccount.ServicePackage		= "PCL_Res";
		Boolean TestStatus = createNewAccount.execute();
		if(TestStatus == false)
		{
			MainWindow.PCLNewCustomerStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		ProvideService provideService		= new ProvideService(report, createNewAccount.AccountNumber);
		provideService.NewCustomer			= true;
		provideService.ServiceType			= createNewAccount.ServiceType;
		provideService.ServicePackage		= createNewAccount.ServicePackage;
		TestStatus = provideService.execute();
		MainWindow.PCLNewCustomerStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void PCLNewCustomerwithCreditLimit()
	{
		MainClass.AUTURL		= "http://172.21.73.80:8081/liberate-LONI01-S10/";
		MainClass.Environment	= "S10";
				
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PCLNewCustomerwithCreditLimit", ReportLocation);
		
		CreateNewAccount createNewAccount	= new CreateNewAccount(report, "fixedResidential","PCL_Res");
		createNewAccount.ServiceType		= "PCL";
		createNewAccount.ServicePackage		= "PCL_Res";
		Boolean TestStatus = createNewAccount.execute();
		if(TestStatus == false)
		{
			MainWindow.PCLNewCustomerwithCreditLimitStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		ProvideService provideService		= new ProvideService(report, createNewAccount.AccountNumber);
		provideService.NewCustomer			= true;
		provideService.ServiceType			= createNewAccount.ServiceType;
		provideService.ServicePackage		= createNewAccount.ServicePackage;
		provideService.createCreditLimit	= true;
		TestStatus = provideService.execute();
		MainWindow.PCLNewCustomerwithCreditLimitStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}

	
	public static void PTVExisting()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PTVExisting", ReportLocation);

		ProvideService provideService		= new ProvideService(report, "240004430000");
		provideService.ServiceType			= "PTV";
		provideService.ServicePackage		= "PTV_RES";
		Boolean TestStatus = provideService.execute();
		MainWindow.PTVExistingStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void PTIExisting()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PTIExisting", ReportLocation);

		ProvideService provideService	= new ProvideService(report, "260000550000");
		provideService.ServiceType		= "PTI";
		provideService.ServicePackage	= "IPTVBasicS";
		Boolean TestStatus = provideService.execute();
		MainWindow.PTIExistingStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void PELAddMorePDL()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PELAddMorePDL", ReportLocation);

		ProvideService providePELService	= new ProvideService(report, "240004430000");
		providePELService.ServiceType		= "PEL";
		providePELService.ServicePackage	= "ETFTESTING";
		providePELService.AddMore			= "PDL";
		Boolean TestStatus = providePELService.execute();
		if(TestStatus == false)
		{
			MainWindow.PELAddMorePDLStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		ProvideService providePDLService	= new ProvideService(report, "240004430000");
		providePDLService.ServiceType		= "PDL";
		providePDLService.ServicePackage	= "ADSL";
		providePDLService.AddMoreFlow		= true;
		providePDLService.runningPDL		= true;
		TestStatus = providePDLService.execute();
		MainWindow.PELAddMorePDLStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void PELAddMorePCL()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PELAddMorePCL", ReportLocation);

		ProvideService providePELService	= new ProvideService(report, "240004430000");
		providePELService.ServiceType		= "PEL";
		providePELService.ServicePackage	= "ETFTESTING";
		providePELService.AddMore			= "PCL";
		Boolean TestStatus = providePELService.execute();
		if(TestStatus == false)
		{
			MainWindow.PELAddMorePCLStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		ProvideService providePDLService	= new ProvideService(report, "240004430000");
		providePDLService.ServiceType		= "PCL";
		providePDLService.ServicePackage	= "PCL_Res";
		providePDLService.AddMoreFlow		= true;
		TestStatus = providePDLService.execute();
		MainWindow.PELAddMorePCLStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void PCLComverseExistingCustomer()
	{
		String BackUpEnvironment = MainClass.Environment;
		String BackupAUTURL = MainClass.AUTURL;

		
		if(!MainClass.Environment.equals("S06"))
		{						
			MainClass.Environment	= "S06";
			MainClass.AUTURL		= "http://172.21.73.80:8083/liberate-LONI02-S06/"; 
		}
		
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PCLComverseExistingCustomer", ReportLocation);
		
		ProvideService provideService	= new ProvideService(report, "240004430000");
		provideService.NewCustomer		= false;
		provideService.ServiceType		= "PCL";
		provideService.ServicePackage	= "LIME_PCL_Y";
		provideService.comverseOne		= true;
		provideService.comOneOffer		= "51";
		Boolean TestStatus = provideService.execute();
		MainWindow.PCLComverseExistingCustomerStatus.setText(op.testStatus(TestStatus));

		MainClass.Environment	= BackUpEnvironment;
		MainClass.AUTURL		= BackupAUTURL;
		
		report.createScreenshotDocument();
	}
	
	public static void BatchPayment()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report	= new TestReport("BatchPayment", ReportLocation);
		
		Payments payment	= new Payments(report, "Batch");
		Boolean TestStatus = payment.execute();
		MainWindow.BatchPaymentStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void SinglePayment()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report			= new TestReport("SinglePayment", ReportLocation);
		
		Payments payment			= new Payments(report, "SinglePayment");
		payment.AccountNumber		= "210010230000";
		payment.SinglePaymentType	= "Standard";
		@SuppressWarnings("unused")
		Boolean TestStatus = payment.execute();
//		TODO TempMainWindow.SinglePayments.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void VerifyCashdrawer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report	= new TestReport("VerifyCashdrawer", ReportLocation);
		
		Payments payment	= new Payments(report, "CashDrawer");
		Boolean TestStatus = payment.execute();
		MainWindow.VerifyCashdrawerStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void CreateRefundDepositReason()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report 				= new TestReport("CreateRefundDepositReason", ReportLocation);
		
		CustomerSearch customerSearch	= new CustomerSearch(report);
		customerSearch.AccountNumber 	= "240004430000";
		Boolean TestStatus = customerSearch.execute();
		if(TestStatus == false)
		{
			MainWindow.CreateRefundDepositReasonStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		CS_MaintainAccountDeposit deposit = new CS_MaintainAccountDeposit(report);
		deposit.operation = "Create";
		TestStatus = deposit.execute();
		if(TestStatus == false)
		{
			MainWindow.CreateRefundDepositReasonStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		Payments depositpayment			= new Payments(report, "SinglePayment");
		depositpayment.AccountNumber	= customerSearch.AccountNumber;
		depositpayment.SinglePaymentType= "Deposit";
		TestStatus = depositpayment.execute();
		if(TestStatus == false)
		{
			MainWindow.CreateRefundDepositReasonStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		deposit.operation= "Refund";
		
		TestStatus = customerSearch.execute();
		if(TestStatus == false)
		{
			MainWindow.CreateRefundDepositReasonStatus.setText(op.testStatus(TestStatus));
			return;
		}
		
		TestStatus = deposit.execute();
		MainWindow.CreateRefundDepositReasonStatus.setText(op.testStatus(TestStatus));
		
		report.createScreenshotDocument();
	}
	
	public static void FibreRouting()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report		= new TestReport("FibreRouting", ReportLocation);
		
		Routing routing			= new Routing(report, "Auto");
		routing.ServiceOrder	= "XD00203";
		routing.Department		= "BILDM";
		routing.PlantItemType	= "FDP";
		routing.PlantItemNum	= "FDP20";
		routing.ExchnageID		= "BOT";
		Boolean TestStatus = routing.execute();
		MainWindow.FibreRoutingStatus.setText(op.testStatus(TestStatus));

		report.createScreenshotDocument();
	}
	
	public static void MaintainQueryAccountNumber()
	{
		LoginLogout.login(MainClass.AUTURL);
		
		TestReport report = new TestReport("MaintainQueryAccountNumber", ReportLocation);
		CS_QueryManagement querymanagement = new CS_QueryManagement(report);
		
		CustomerSearch customerSearch	= new CustomerSearch(report);
		customerSearch.AccountNumber 	= "240004430000";
		@SuppressWarnings("unused")
		Boolean TestStatus = customerSearch.execute();
		
		querymanagement.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void MaintainQueryServiceOrder()
	{
		LoginLogout.login(MainClass.AUTURL);
		
		TestReport report = new TestReport("MaintainQueryServiceOrder", ReportLocation);
		CS_QueryManagement querymanagement = new CS_QueryManagement(report);
		
		CustomerSearch customerSearch	= new CustomerSearch(report);
		customerSearch.AccountNumber 	= "240004430000";
		@SuppressWarnings("unused")
		Boolean TestStatus = customerSearch.execute();
		
		querymanagement.RaiseforOrder = true;
		querymanagement.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void MaintainQueryServiceNumber()
	{
		LoginLogout.login(MainClass.AUTURL);
		
		TestReport report = new TestReport("MaintainQueryServiceNumber", ReportLocation);
		CS_QueryManagement querymanagement = new CS_QueryManagement(report);
		
		CustomerSearch customerSearch	= new CustomerSearch(report);
		customerSearch.AccountNumber 	= "240004430000";
		@SuppressWarnings("unused")
		Boolean TestStatus = customerSearch.execute();
		
		querymanagement.RaiseforService = true;
		//TODO provide service number
		querymanagement.ServiceNumber	= "";
		querymanagement.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void testCase()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("testCase", ReportLocation);
		CreateNewAccount createNewAccount = new CreateNewAccount(report, "fixedResidential","ETFTESTING");
		createNewAccount.ServiceType = "PEL";
		createNewAccount.ServicePackage = "ETFTESTING";
		createNewAccount.execute();
		
		report.createScreenshotDocument();
	}
	
	
}
