package operations;

public class ErrorHandler
{
	static TestAction ta = new TestAction(Operations.getdriver());
	
	static String xpath;
	static String ProductField;
	static String ErrorDescription;
	static boolean descriptionOnly = false;
	
	public static void handleOnScreenError(String OnScreenErrorMessage)
	{
		xpath = "(//*[@class='hdrMid'])[1]";
		String ScreenName = ta.getDatafromPage(xpath);
		
		if(ScreenName.equals("Product Fields"))
		{	
			handleProductFields(OnScreenErrorMessage);
		}
		else
		{
			ta.log("ERROR : Unhandles Page. Please contact support with onscreen log and screenshot");
		}
	}
	
	//HANDLE SCREEN ERROR
	private static void handleProductFields(String OnScreenErrorMessage)
	{
		String[] error;
		
		error = getProductFieldFromErrorMessage(OnScreenErrorMessage);
		try
		{
			error[1].getBytes();
			//If No Exception occurs
			ProductField = error[0].trim();
			ErrorDescription = error[1].trim();
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			descriptionOnly = true;
			ErrorDescription = error[0].trim();
		}
		
		if(!descriptionOnly)
		{
			xpath = "(//*[text()[contains(.,'"+ProductField+"')]]/./following::input)[1]";
			ta.sendDatatoField(xpath, TestData.randomString());
		}
	}
	
	//SUPPORT FUNCTIONS
	private static String[] getProductFieldFromErrorMessage(String OnScreenErrorMessage)
	{
		String error[] = OnScreenErrorMessage.split(":");
		return(error);
	}
}
