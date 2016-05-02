package operations;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TestData
{
	String testDataClassVersion = "0.1.1 : Amended : Method nam e to generate random string";
	
	public static SecureRandom random = new SecureRandom();
	
	public static String randomString()
	{
		String customerName = new BigInteger(130, random).toString(32);
		customerName = customerName.substring(0,12);
		
		return customerName;
	}
}
