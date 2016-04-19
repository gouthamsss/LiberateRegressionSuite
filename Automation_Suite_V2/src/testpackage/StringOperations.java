package testpackage;

import operations.TestCaseExecuter;


public class StringOperations 
{
	String navigation;
	static TestCaseExecuter tse = new TestCaseExecuter();

	public static void main(String [] args)
	{
		StringOperations so = new StringOperations();
		
		so.getNavigation();
	}
	
	
	public String[] getNavigation()
	{
		navigation = tse.readTestStep();
		
		String [] splitNavigation;
		String [] liberateNavigation = new String[3];
		
		splitNavigation = navigation.split(">>");
		
		String [] tempNav = splitNavigation;
		
		for (int i = 0; i <3; i++)
		{	
			//System.out.println(tempNav[i].trim());
			liberateNavigation[i] = "//*[text()[contains(.,'" + tempNav[i].trim() +"')]]";
		}
		
		for (int i = 0; i <3; i++)
		{	
			//System.out.println(liberateNavigation[i]);
		}
		return liberateNavigation;
	}
}
