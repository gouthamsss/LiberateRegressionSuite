package operations;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class TestAction 
{
	String actionClassVersion = "0.1.8 - Fixed : Infinite loop in wait until method";
	
	//Variable Declaration
	WebDriver driver;				//Declare WebDriver
	boolean passed = true;	
	int retry = 5;
	int i = 0;
	String xpath = "";
	
	public WebDriverWait longWait;
	public WebDriverWait shortWait;
	public WebDriverWait tinyWait;
	
	//Parameterized constructor. Receives declared driver instance.
	public TestAction(WebDriver driver)
	{
		this.driver = driver;
		
		longWait = new WebDriverWait(driver, 120);;
		shortWait  = new WebDriverWait(driver,5);
		tinyWait  = new WebDriverWait(driver,2);
	}
	
	//_____USER ACTION SECTION_____//
	//User Action : Click Action
	public boolean clickOn(String xpathLocator)
	{
		xpath = xpathLocator;
		for(int i=0;i<retry;i++)
		{
			waitFor(200);
			try
			{
				System.out.println("\tClicking on :" + xpathLocator);
				driver.findElement(By.xpath(xpathLocator)).click();
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
			}
		}
		
		return passed;
	}
	
	//Send data to Text field or pop up
	public boolean sendDatatoField(String xpathLocator, String value)
	{
		xpath = xpathLocator;

		for(int i=0;i<retry;i++)
		{
			try
			{
				System.out.println("\tSending data '" + value + "' to field : " + xpathLocator);
				clearInputField(xpathLocator);
				driver.findElement(By.xpath(xpathLocator)).sendKeys(value);
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
			}
		}
		if (i == 4)
			passed = false;
		
		return passed;	
	}
	
	//Retrieve and return data(string value) from web page
	public String getDatafromPage(String xpathLocator)
	{
		xpath = xpathLocator;

		String dataFromPage = "";
		
		System.out.println("\tGetting value from : " + xpathLocator);
		dataFromPage = driver.findElement(By.xpath(xpathLocator)).getText();
		
		return dataFromPage;
	}
	
	//Clear a Text box
	public boolean clearInputField(String xpathLocator)
	{
		xpath = xpathLocator;

		for(i=0;i<retry;i++)
		{
			try
			{
				driver.findElement(By.xpath(xpathLocator)).clear();
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
			}
		}
		if (i == 4)
			passed = false;
		
		return passed;	
	}
	
	//Select a value from combo box
	public boolean selectBy(String xpathLocator, String value)
	{
		xpath = xpathLocator;

		for(int i=0;i<retry;i++)
		{
			try
			{
				System.out.println("\tSelecting value '" + value +"' from : " + xpathLocator);
				Select appForm = new Select(driver.findElement(By.xpath(xpathLocator)));
				appForm.selectByValue(value);
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
			}
			
		}
		
		if (i == 4)
			passed = false;
		
		return passed;
	}
	
	//Select a value by index from combo box
	public boolean selectBy(String xpathLocator, int index)
	{
		xpath = xpathLocator;

		for(int i=0;i<retry;i++)
		{
			try
			{
				System.out.println("\tSelecting index'" + index +"' from : " + xpathLocator);
				Select appForm = new Select(driver.findElement(By.xpath(xpathLocator)));
				appForm.selectByIndex(index);
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
			}
		}
		if (i == 4)
			passed = false;
		
		return passed;
	}
	
	//Get selected value from the combo box
		public String getValueFromSelect(String xpathLocator)
		{
			xpath = xpathLocator;
			String selectedValue = "";
			
			for(int i=0;i<retry;i++)
			{
				try
				{
					System.out.println("\tGetting value from : " + xpathLocator);
					Select appForm = new Select(driver.findElement(By.xpath(xpathLocator)));
					
					WebElement option = appForm.getFirstSelectedOption();
					selectedValue = option.getText();

					break;
				}
				catch(Exception e)
				{
					handleExcpetion(e);
				}
			}
			if (i == 4)
				passed = false;
			
			return selectedValue;
		}
	
	//Wait until an element exist for long amount of time
	public boolean waitUntil(String xpathLocator, WebDriverWait waitperiod)
	{
		xpath = xpathLocator;

		for(int i=0;i<retry;i++)
		{
			try
			{
				System.out.println("\tWaiting for : " + xpathLocator);
				waitperiod.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
				passed = true;
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
			}
		}
		if (i == 4)
			passed = false;
		return passed;	
	}
	
	//_____COMBO USER ACTION SECTION_____//
	//Closes a pop up with 'OK' button (by clicking on 'OK' button)
	public void closeOKpopup()
	{
		String xpathforOK = "//input[contains (@value,'OK')]";
		
		try 
		{
			waitFor(500);
			if (driver.findElements(By.xpath(xpathforOK)).size() != 0)
			{
				clickOn(xpathforOK);
				waitUntilElementnotExist(xpathforOK, 500);
			}
		} 
		catch (Exception e) 
		{
			handleExcpetion(e);
		}
	}
	
	//Closes a pop up with 'Yes' and 'No' button (by clicking on 'Yes' button)
	public void clickYesOnYesNoPopup()
	{
		String xpathforYesNo = "//input[contains (@value,'Yes')]";
		
		try 
		{
			waitFor(500);
			if (driver.findElements(By.xpath(xpathforYesNo)).size() != 0)
			{
				clickOn(xpathforYesNo);
				waitUntilElementnotExist(xpathforYesNo, 500);
			}
			waitFor(500);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

	//_____SUPPORT FUNCTION SECTION_____//
	//Wait for a particular amount of time (milliseconds)
	public void waitFor(long time)
	{
		try 
		{
			Thread.sleep(time);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
		
	//Wait until a particular Element disappears.	
	public boolean waitUntilElementnotExist(String xpathLocator, int time)
	{
		xpath = xpathLocator;

		for(int i=0;i<retry;i++)
		{
			if(elementExist(xpathLocator))
			{
				waitFor(time);
			}
			else
			{
				passed = true;
				break;
			}
		}
		return passed;
	}
		
	//Check if an element Exists. True : Exists, False : Doesn't Exists
	public boolean elementExist(String xpathLocator)
	{
		xpath = xpathLocator;

		return(driver.findElements(By.xpath(xpathLocator)).size() != 0);
	}
	
	//move mouse to given location
	public void mouseMove(int x,int y)
	{
		Robot robot = null; 
		try { 
			robot = new Robot(); 
			robot.mouseMove(x,y);
		} catch (AWTException e)
		{ 
			e.printStackTrace();
		} 
	}
	
	//Scroll up
	public void scrollUp()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-2000)", "");
	}
	
	//Support Function : Exception Handling
	private void handleExcpetion(Exception e) 
	{
		if(e instanceof StaleElementReferenceException)
		{
			System.out.println("\tStaleElementReferenceException Occured - While looking for : '"+xpath+"' .");
		}
		if(e instanceof ElementNotVisibleException)
		{
			System.out.println("\tElementNotVisibleException Occured - While looking for : '"+xpath+"' .");				
		}
		if(e instanceof ElementNotFoundException)
		{
			System.out.println("\tElementNotFoundException Occured - While looking for : '"+xpath+"' . ");
		}
		if(e instanceof NoSuchElementException)
		{
			System.out.println("\nNoSuchElementException Occured- While looking for : '"+xpath+"' .");
			passed = false;
		}
		if(e instanceof WebDriverException)
		{
			e.printStackTrace();
			closeOKpopup();
			clickYesOnYesNoPopup();
			System.out.println("\tWebDriverException Occured- While looking for : '"+xpath+"' .");
		}
		if(e instanceof TimeoutException)
		{
			System.out.println("\tTimeoutException Occured while looking for : '"+xpath+"'.");
			passed = false;
		}
		
	}
}
