package main;

import operations.TestData;


public class MainClass
{
	public static String Environment 	= "S10";
	public static String AUTURL			= "http://172.21.73.80:8083/liberate-LONI02-S06/";
	
	public static void main(String [] args)
	{
		TestData.validateDBFile();
		
//		TestCase.PELExistingCustomer();
//		TestCase.PDLExistingCustomer();
//		TestCase.PELPDLNewCustomer();
//		TestCase.PCLExistingCustomer();
		TestCase.PCLNewCustomer();
	}
}
