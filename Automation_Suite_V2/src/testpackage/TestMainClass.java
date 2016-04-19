package testpackage;

import operations.Operations;
import operations.TestCaseExecuter;

public class TestMainClass 
{
	static TestCaseExecuter tse = new TestCaseExecuter();
	static Operations op = new Operations();
	
	public static void main(String [] args)
	{
		tse.processTestStep(tse.readTestStep());
	}
}