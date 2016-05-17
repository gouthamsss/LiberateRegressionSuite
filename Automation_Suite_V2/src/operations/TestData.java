package operations;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class TestData
{
	public static String testDataClassVersion = "TestData 0.1.5 : Added: Build Number";
	
	public static String buildNumber;
	
	public static SecureRandom random	= new SecureRandom();
	public static TestAction ta			= new TestAction(Operations.getdriver());
	
	
	
	//Save and Retrieve Data
	public static boolean validateDBFile()
	{
		File dbFile				= new File("data.db");
	    Statement statement		= null;
	    Connection connection	= null;
	    
		if(!dbFile.exists())
		{
			try
			{
				dbFile.createNewFile();
				ta.log("Created Data file");
		    	connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			    ta.log("Opened database successfully");
			    
				statement = connection.createStatement();
				
				String fieldDetailsTable = "CREATE TABLE Field_Detail ("+
										   "id             INTEGER PRIMARY KEY AUTOINCREMENT"+
										   "                        UNIQUE"+
										   "                        NOT NULL,"+
										   "environment    STRING  NOT NULL,"+
										   "servicepackage STRING,"+
										   "fieldname      STRING  NOT NULL,"+
										   "fieldtype      STRING  NOT NULL,"+
										   "fieldmaxlength INTEGER,"+
										   "fieldminlength INTEGER);";
				
				statement.executeUpdate(fieldDetailsTable);
				ta.log("Configured data file");
				//Create Tables
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	//Data Generation
	public static String randomString()
	{
		String customerName = new BigInteger(130, random).toString(32);
		customerName = customerName.substring(0,12);
		
		return customerName;
	}
	public static String  randomNumeric()
	{
		long max = 9999999999L;
		max = (long) (Math.random() * ((max - 1) + 1));
		
		return Long.toString(max);
	}
}
