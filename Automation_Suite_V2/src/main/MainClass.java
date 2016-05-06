package main;

import operations.TestData;


public class MainClass
{
	public static String Environment = "S10";
		
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
