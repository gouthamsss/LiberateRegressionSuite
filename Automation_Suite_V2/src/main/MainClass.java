package main;

import operations.Operations;
import operations.TestData;
import app_ui.TempMainWindow;


public class MainClass implements Runnable
{
//	public static String Environment 	= "S10";
//	public static String AUTURL			= "http://172.21.73.80:8081/liberate-LONI01-S10/";
	public static String Environment 	= "S06";
	public static String AUTURL			= "http://172.21.73.80:8083/liberate-LONI02-S06/";
	
	public static void main(String [] args)
	{
		int urlSelected = TempMainWindow.AUTSelection.getSelectedIndex();
		
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
		
		if(TempMainWindow.PELExistingCustomer.isSelected())
			TestCase.PELExistingCustomer();
		if(TempMainWindow.PDLExistingCustomer.isSelected())
			TestCase.PDLExistingCustomer();
		if(TempMainWindow.PELPDLNewCustomer.isSelected())
			TestCase.PELPDLNewCustomer();
		if(TempMainWindow.PCLExistingCustomer.isSelected())
			TestCase.PCLExistingCustomer();
		if(TempMainWindow.PCLNewCustomer.isSelected())
			TestCase.PCLNewCustomer();
		if(TempMainWindow.PCLNewCustomerwithCreditLimit.isSelected())
			TestCase.PCLNewCustomerwithCreditLimit();
		if(TempMainWindow.PTVExisting.isSelected())
			TestCase.PTVExisting();
		if(TempMainWindow.PELAddMorePDL.isSelected())
			TestCase.PELAddMorePDL();
		if(TempMainWindow.PELAddMorePCL.isSelected())
			TestCase.PELAddMorePCL();
		if(TempMainWindow.PTIExisting.isSelected())
			TestCase.PTIExisting();
		if(TempMainWindow.PCLComverseExistingCustomer.isSelected())
			TestCase.PCLComverseExistingCustomer();
		if(TempMainWindow.BatchPayment.isSelected())
			TestCase.BatchPayment();
		if(TempMainWindow.CreateRefundDepositReason.isSelected())
			TestCase.CreateRefundDepositReason();
		if(TempMainWindow.VerifyCashdrawer.isSelected())
			TestCase.VerifyCashdrawer();
		if(TempMainWindow.FibreRouting.isSelected())
			TestCase.FibreRouting();
	
		Operations.exitClose();
		
	}
	
	@Override
	public void run() 
	{
		main(null);
	}
}
