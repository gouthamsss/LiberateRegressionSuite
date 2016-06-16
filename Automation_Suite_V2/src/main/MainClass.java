package main;

import operations.Operations;
import operations.TestData;
import app_ui.MainWindow;


public class MainClass implements Runnable
{
//	public static String Environment 	= "S10";
//	public static String AUTURL			= "http://172.21.73.80:8081/liberate-LONI01-S10/";
	public static String Environment 	= "S06";
	public static String AUTURL			= "http://172.21.73.80:8083/liberate-LONI02-S06/";
	
	public static void main(String [] args)
	{
		int urlSelected = MainWindow.AUTSelection.getSelectedIndex();
		
		if (urlSelected == 0)
		{
			AUTURL = TestData.S06_URL;
			Environment = "S06";
		}
		if (urlSelected == 1)
		{
			AUTURL = TestData.S10_URL;
			Environment = "S10";
		}
		
		TestData.validateDBFile();
		
		if(MainWindow.PELExistingCustomer.isSelected())
			TestCase.PELExistingCustomer();
		if(MainWindow.PDLExistingCustomer.isSelected())
			TestCase.PDLExistingCustomer();
		if(MainWindow.PELPDLNewCustomer.isSelected())
			TestCase.PELPDLNewCustomer();
		if(MainWindow.PCLExistingCustomer.isSelected())
			TestCase.PCLExistingCustomer();
		if(MainWindow.PCLNewCustomer.isSelected())
			TestCase.PCLNewCustomer();
		if(MainWindow.PCLNewCustomerwithCreditLimit.isSelected())
			TestCase.PCLNewCustomerwithCreditLimit();
		if(MainWindow.PTVExisting.isSelected())
			TestCase.PTVExisting();
		if(MainWindow.PELAddMorePDL.isSelected())
			TestCase.PELAddMorePDL();
		if(MainWindow.PELAddMorePCL.isSelected())
			TestCase.PELAddMorePCL();
		if(MainWindow.PTIExisting.isSelected())
			TestCase.PTIExisting();
		if(MainWindow.PCLComverseExistingCustomer.isSelected())
			TestCase.PCLComverseExistingCustomer();
		if(MainWindow.BatchPayment.isSelected())
			TestCase.BatchPayment();
		if(MainWindow.CreateRefundDepositReason.isSelected())
			TestCase.CreateRefundDepositReason();
		if(MainWindow.VerifyCashdrawer.isSelected())
			TestCase.VerifyCashdrawer();
		if(MainWindow.FibreRouting.isSelected())
			TestCase.FibreRouting();
	
		Operations.exitClose();
		
	}
	
	@Override
	public void run() 
	{
		main(null);
	}
}
