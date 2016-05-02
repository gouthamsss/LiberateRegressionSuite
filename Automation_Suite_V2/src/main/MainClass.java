package main;

import testcase.ProvideService;

public class MainClass
{
	public static void main(String [] args)
	{
//		CreateNewAccount createNewAccount = new CreateNewAccount("fixedResidential","ETFTESTING");
//		createNewAccount.execute();
		
		ProvideService provideService = new ProvideService(false);
		provideService.execute();
	}
}
