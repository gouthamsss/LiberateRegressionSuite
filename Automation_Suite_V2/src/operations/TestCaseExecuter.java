package operations;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

public class TestCaseExecuter 
{
	String ClassVersion = "0.2.1";
	
	String provideData;
	String provideField;
	
	String button;
	
	String selectData;
	String selectField;
	
	public Boolean continueStep = true;
	
	Scanner scan = new Scanner(System.in);
	
	private WebDriver driver = Operations.getdriver();

	TestAction tsa = new TestAction(driver);
	
	//Read next String (whole line) from console
	public String readTestStep()
	{
		String testStep;
		
		System.out.print("Test Step :");
		testStep = scan.nextLine();
		
		processTestStep(testStep);
		
		return testStep;
	}

	//Function to process the test step in order to call/execute appropriate functions
	public void processTestStep(String testStep)
	{
		String [] splitTestStep;
		
		splitTestStep = testStep.split(" ");
		
		if (splitTestStep[0].equalsIgnoreCase("Navigate"))
		{
			navigate(testStep);
		}
		else if (splitTestStep[0].equalsIgnoreCase("Select"))
		{
			select(testStep);
		}
		else if (splitTestStep[0].equalsIgnoreCase("Provide"))
		{
			provideData(testStep);
		}
		else if (splitTestStep[0].equalsIgnoreCase("Verify"))
		{
			//Call Verify function
		}
		else if (splitTestStep[0].equalsIgnoreCase("Click"))
		{
			clickOn(testStep);
		}
		else if (splitTestStep[0].equalsIgnoreCase("Goto")|| splitTestStep[0].equalsIgnoreCase("Go"))
		{
			gotoURL(testStep);
		}
		else if (splitTestStep[0].equalsIgnoreCase("Exit"))
		{
			continueStep = false;
		}
	}

	//Function to navigate to a particular screen in Liberate
	private void navigate(String step)
	{
		String testStep = step;
		String [] splitTestStep;
		
		splitTestStep = testStep.split(" ");
		
		if (splitTestStep[0].equalsIgnoreCase("Navigate"))
		{
			testStep = testStep.replace("Navigate", "");
			testStep = testStep.replace("navigate", "");
			testStep = testStep.trim();
		}
		
		splitTestStep = testStep.split(" ");
		if (splitTestStep[0].equalsIgnoreCase("To"))
		{
			testStep = testStep.replace("To", "");
			testStep = testStep.replace("to", "");
			testStep = testStep.trim();
		}
		
		System.out.println(testStep);
		
		String [] splitNavigation;
		String [] liberateNavigation = new String[3];

		splitNavigation = testStep.split(">>");
		String [] tempNav = splitNavigation;
		
		for (int i = 0; i <3; i++)
		{	
			//System.out.println(tempNav[i].trim());
			liberateNavigation[i] = "//*[text()[contains(.,'" + tempNav[i].trim() +"')]]";
		}
		
		tsa.waitUntil(liberateNavigation[0], 20);
		tsa.clickOn(liberateNavigation[0]);
		tsa.waitUntil(liberateNavigation[1], 20);
		tsa.clickOn(liberateNavigation[1]);
		tsa.waitUntil(liberateNavigation[2], 20);
		tsa.clickOn(liberateNavigation[2]);
	}
	
	//Function to navigate to a particular URL
	private void gotoURL(String step)
	{
		String testStep = step;
		String [] splitTestStep;
		
		splitTestStep = testStep.split(" ");
		
		if (splitTestStep[0].equalsIgnoreCase("Goto") || splitTestStep[0].equals("Go"))
		{
			testStep = testStep.replace("Goto", "");
			testStep = testStep.replace("goto", "");
			testStep = testStep.trim();
		}
		
		splitTestStep = testStep.split(" ");
		if (splitTestStep[0].equalsIgnoreCase("To"))
		{
			testStep = testStep.replace("To", "");
			testStep = testStep.replace("to", "");
			testStep = testStep.trim();
		}
		
		System.out.println(testStep);
		
		driver.get(testStep);
	}
	
	//Function to provide data to given field
	private void provideData(String step)
	{
		String testStep = step;
		String [] splitTestStep;
		
		splitTestStep = testStep.split(" ");
		
		if (splitTestStep[0].equalsIgnoreCase("Provide"))
		{
			testStep = testStep.replace("Provide", "");
			testStep = testStep.replace("provide", "");
			testStep = testStep.trim();
		}
		
		splitTestStep = testStep.split(" ");
		{
			provideData = splitTestStep[0];
			testStep = testStep.replace(provideData, "");
			testStep = testStep.trim();
		}
		
		splitTestStep = testStep.split(" ");
		if (splitTestStep[0].equalsIgnoreCase("To"))
		{
			testStep = testStep.replace("To", "");
			testStep = testStep.replace("to", "");
			testStep = testStep.trim();
		}
		
		provideField = testStep;
		
		provideField = "(//*[text()[contains(.,'" + provideField + ":')]]/./following::input)[1]";
		tsa.waitUntil(provideField, 20);
		tsa.sendDatatoField(provideField, provideData);	
	}

	//Function to Click on Button
	private void clickOn(String step)
	{
		String testStep = step;
		String [] splitTestStep;
		
		splitTestStep = testStep.split(" ");
		
		if (splitTestStep[0].equalsIgnoreCase("Click"))
		{
			testStep = testStep.replace("Click", "");
			testStep = testStep.replace("click", "");
			testStep = testStep.trim();
		}
		
		splitTestStep = testStep.split(" ");
		if (splitTestStep[0].equalsIgnoreCase("on"))
		{
			testStep = testStep.replace("On", "");
			testStep = testStep.replace("on", "");
			testStep = testStep.trim();
		}
		
		button = testStep.replace("button", "");
		button = testStep.replace("Button", "");
		button = button.trim();
		
		button = "//input[@value='" + button + "']";
		tsa.waitUntil(provideField, 20);
		tsa.clickOn(button);
	}

	private void select(String step)
	{
		String testStep = step;
		String [] splitTestStep;
		
		splitTestStep = testStep.split(" ");
		
		if (splitTestStep[0].equalsIgnoreCase("Select"))
		{
			testStep = testStep.replace("Select", "");
			testStep = testStep.replace("select", "");
			testStep = testStep.trim();
		}
		
		splitTestStep = testStep.split(" ");
		{
			selectData = splitTestStep[0];
			testStep = testStep.replace(selectData, "");
			testStep = testStep.trim();
		}
		
		splitTestStep = testStep.split(" ");
		if (splitTestStep[0].equalsIgnoreCase("From"))
		{
			testStep = testStep.replace("From", "");
			testStep = testStep.replace("from", "");
			testStep = testStep.trim();
		}
		
		selectField = testStep;
		
		selectField = "(//*[text()[contains(.,'" + selectField + ":')]]/./following::select)[1]";
		tsa.waitUntil(selectField, 20);
		tsa.selectBy(selectField, selectData);	
	}
}