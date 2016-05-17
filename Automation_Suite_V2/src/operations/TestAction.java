package operations;

import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Predicate;

public class TestAction 
{
	static String ClassVersion = "TestAction Class 1.1.2 - Improvements";
	
	//Variable Declaration
	WebDriver driver;				//Declare WebDriver
	boolean passed = true;	
	int retry = 5;
	int i = 0;
	String xpath = "";
	
	public WebDriverWait waitPeriod;
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
	
	//Parameterized constructor. Receives declared driver instance.
	public TestAction(WebDriver driver)
	{
		this.driver = driver;
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
				log("Action - Clicking on : " + xpathLocator);
				driver.findElement(By.xpath(xpathLocator)).click();
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
				if (i == (retry-1))
					passed = false;
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
				log("Action - Sending data '" + value + "' to field : " + xpathLocator);
				clearInputField(xpathLocator);
				driver.findElement(By.xpath(xpathLocator)).sendKeys(value);
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
				if (i == (retry-1))
					passed = false;
			}
		}
		
		return passed;	
	}
	
	//Retrieve and return data(string value) from web page
	public String getDatafromPage(String xpathLocator)
	{
		xpath = xpathLocator;

		String dataFromPage = "";
		
		for(i = 0; i < retry; i++)
		{
			try
			{
				log("Action - Getting value from : " + xpathLocator);
				dataFromPage = driver.findElement(By.xpath(xpathLocator)).getText();
				log("Value	- '"+dataFromPage+"'");
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
				if (i == (retry-1))
					passed = false;
			}			
		}
		
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
				if (i == (retry-1))
					passed = false;
			}
		}
		
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
				log("Action - Selecting value '" + value +"' from : " + xpathLocator);
				Select select = new Select(driver.findElement(By.xpath(xpathLocator)));
				select.selectByValue(value);
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
				if (i == (retry-1))
					passed = false;
			}
		}
		
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
				log("Action - Selecting index'" + index +"' from : " + xpathLocator);
				Select select = new Select(driver.findElement(By.xpath(xpathLocator)));
				select.selectByIndex(index);
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
				if (i == (retry-1))
					passed = false;
			}
		}
		
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
				log("Action - Getting value from : " + xpathLocator);
				Select select = new Select(driver.findElement(By.xpath(xpathLocator)));
				
				WebElement option = select.getFirstSelectedOption();
				selectedValue = option.getText();
				
				log(selectedValue);
				
				break;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
				if (i == (retry-1))
					passed = false;
			}
		}
		
		return selectedValue;
	}
	
	//Check if a particular value is available in Select
	public boolean checkElementinSelect(String xpathLocator, String ElementToFind)
	{
		
		List <WebElement> selectValues = getAllfromSelect(xpathLocator);
		
		for(WebElement element: selectValues)
		{
			System.out.println(element.getText());
			if(element.getText().contains(ElementToFind))
			{
				passed = true;
				break;
			}
			else 
			{
				passed = false;
			}
		}
				
		return passed;
	}
	
	public int numberofElementsinSelect(String xpathLocator)
	{
		List <WebElement> selectValues = getAllfromSelect(xpathLocator);
		
		return selectValues.size();
	}
	
	//Wait until an element exist for long amount of time
	public boolean waitUntil(String xpathLocator, int timetowait)
	{
		xpath = xpathLocator;
		waitPeriod = new WebDriverWait(driver, timetowait);

		try
		{
			log("Action - Waiting for : " + xpathLocator);
			waitPeriod.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
			passed = true;
		}
		catch(TimeoutException e)
		{
			log("ERROR - TimeoutException Occured while looking for : '"+xpath+"'.");
			passed = false;
		}
		return passed;	
	}
	
	//_____COMBO USER ACTION SECTION_____//
	//Closes a pop up with 'OK' button (by clicking on 'OK' button)
	public boolean closeOKpopup()
	{
		String xpathforOK = "(//input[@value='OK'])[last()]";
		
		try 
		{
			waitFor(500);
			boolean OKButtonExists = driver.findElements(By.xpath(xpathforOK)).size() != 0; 
			if (OKButtonExists)
			{
				driver.findElement(By.xpath(xpathforOK)).click();
				waitUntilElementnotExist(xpathforOK, 2);
				passed = true;
			}
		} 
		catch (Exception e) 
		{
			handleExcpetion(e);
			passed = false;
		}
		
		return passed;
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
				driver.findElement(By.xpath(xpathforYesNo)).click();
				waitUntilElementnotExist(xpathforYesNo, 5);
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
	public boolean waitUntilElementnotExist(String xpathLocator, int timetowait)
	{
		passed = true;
		
		xpath = xpathLocator;
		waitPeriod = new WebDriverWait(driver, timetowait);
		
		if(elementExist(xpathLocator))
		{
			try
			{
				log("Action - Waiting for : " + xpathLocator + " - To disappear");
				waitPeriod.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath(xpathLocator))));
			}
			catch(TimeoutException e)
			{
				log("ERROR - TimeoutException Occured while looking for : '"+xpath+"'.");
			}
			finally
			{
				if(elementExist(xpathLocator))
					passed = false;
			}
		}	
		else
		{
			passed = true;
		}
		return passed;
	}
	
	//Wait until provided combo box loads all values.
	@Deprecated
	public boolean waitUntilSelectOptionsPopulated(String xpathLocator)
	{
		waitFor(500);
		
		WebDriverWait wait = new  WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(xpathLocator))));
		
		xpath = xpathLocator;
		Select locSelect = null;
		
		locSelect = new Select(driver.findElement(By.xpath(xpath)));
		
		final Select select = locSelect;
		
		log("Action - Waiting for : " + xpathLocator + " - To load values");
		
		passed = false;
		
        new FluentWait<WebDriver>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(new Predicate<WebDriver>()
                {
                    public boolean apply(WebDriver d)
                    {
                		for(int i=0;i<retry;i++)
                		{
                			try
                			{
                				passed = (!select.getOptions().isEmpty());
                				if(passed)
                					break;
                			}
                			catch(Exception e)
                			{
                				handleExcpetion(e);
                			}
                		}
                    	return passed;
                    }
                });
		return passed;
    }
	public boolean waitUntilSelectOptionLoads(String xpathLocator, int timetowait)
	{
		boolean selectLoaded = false;
		xpath = xpathLocator;
		int limit = timetowait * 1000;
		int selectSize = 0;
		
		Select select;
		
		log("Action - Waiting for : " + xpath + " to load values");
		
		for (int i = 0; i < limit; i = i + 500)
		{
			waitFor(500);
			
			try
			{
				select = new Select(driver.findElement(By.xpath(xpath)));
				selectSize = select.getOptions().size();
				selectLoaded = selectSize > 2;
			}
			catch(Exception e)
			{
				handleExcpetion(e);
			}
			
			if(selectLoaded)
			{
				passed = true;
				break;
			}
			else
			{
				if(i == limit)
				{
					log("ERROR - TimeoutException Occured while waiting for : '"+xpath+"' - to load values");
					passed = false;
				}
			}
		}
		return passed;
	}
	
	//Check if an element Exists. True : Exists, False : Doesn't Exists
	public boolean elementExist(String xpathLocator)
	{
		boolean exists;

		log("Action : Checking availability of element '"+xpathLocator+"'");

		xpath = xpathLocator;
		exists = driver.findElements(By.xpath(xpathLocator)).size() != 0;
		
		if(exists)
			log("Element Available");
		else
			log("Element is NOT Available");
		
		return exists;
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
			log("ERROR - StaleElementReferenceException Occured - While looking for : '"+xpath+"' .");
			waitFor(500);
		}
		if(e instanceof ElementNotVisibleException)
		{
			log("ERROR - ElementNotVisibleException Occured - While looking for : '"+xpath+"' .");				
		}
		if(e instanceof ElementNotFoundException)
		{
			log("ERROR - ElementNotFoundException Occured - While looking for : '"+xpath+"' . ");
		}
		if(e instanceof NoSuchElementException)
		{
			log("ERROR - NoSuchElementException Occured- While looking for : '"+xpath+"' .");
			passed = false;
		}
		if(e instanceof WebDriverException)
		{
			e.printStackTrace();
			closeOKpopup();
			clickYesOnYesNoPopup();
			log("ERROR - WebDriverException Occured- While looking for : '"+xpath+"' .");
		}
		if(e instanceof TimeoutException)
		{
			log("ERROR - TimeoutException Occured while looking for : '"+xpath+"'.");
			passed = false;
		}
		
	}
	
	//Quits and close driver
	public void closeSession()
	{
		driver.close();
		driver.quit();
	}
	
	//Returns all element in a select by list
	public List<WebElement> getAllfromSelect(String xpathLocator)
	{
        Select select = new Select(driver.findElement(By.xpath(xpathLocator)));
        List <WebElement> elementcount = (List<WebElement>) select.getOptions();
		
		return elementcount;
	}
	
	//Method to redirect console messages
	public void log(String message)
	{
		String msg = sdf.format(new Date()) + " : " + message + "\n";
		System.out.print(msg);
	}
}
