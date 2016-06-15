package operations;

public class TestConfiguration
{
	static TestAction ta = new TestAction(Operations.getdriver());
	
	public static boolean saveFieldDetails(String environment,String servicepackage, String fieldID, String FieldDataType)
	{		
		return true;
	}
	
	//TODO REMOVE STUBS
	public static boolean stubFillProductFields(TestAction ta2)
	{
		ta = ta2;
		boolean passed = false;
		String xpath;
		
		xpath = "(//*[text()[contains(.,'Bill Description')]]/./following::input)[1]";
		if(ta.elementExist(xpath))
			passed = ta.sendDatatoField(xpath, TestData.randomString());
		
		xpath = "(//*[text()[contains(.,'AAA_User ID')]]/./following::input)[1]";
		if(ta.elementExist(xpath))
			passed = ta.sendDatatoField(xpath, TestData.randomString());
		
		xpath = "(//*[text()[contains(.,'Keyword')]]/./following::input)[1]";
		if(ta.elementExist(xpath))
			passed = ta.sendDatatoField(xpath, TestData.randomNumeric());
		
		xpath = "(//*[text()[contains(.,'Product Cease Date')]]/./following::input)[1]";
		if(ta.elementExist(xpath))
			passed = ta.sendDatatoField(xpath, "01-May-2024");
		
		return passed;
	}
}
