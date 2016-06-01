package main;

import operations.Operations;
import operations.TestData;
import app_ui.TempMainWindow;


public class TEST_MainClass implements Runnable
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
		
		//TODO ADD Test Cases Here
		
		Operations.exitClose();
		
	}
	
	@Override
	public void run() 
	{
		main(null);
	}
}
