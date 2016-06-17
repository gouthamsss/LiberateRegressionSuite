package main;

public class TestExecutor 
{
//	public static String Environment 	= "S10";
//	public static String AUTURL			= "http://172.21.73.80:8081/liberate-LONI01-S10/";
	public static String Environment 	= "S06";
	public static String AUTURL			= "http://172.21.73.80:8083/liberate-LONI02-S06/";
	
	public static void main(String[] args)
	{
		MainClass.AUTURL = AUTURL;
		
		TestCase.MaintainQueryAccountNumber();
	}
}
