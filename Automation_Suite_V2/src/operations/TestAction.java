package operations;

import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	String actionClassVersion = "1.0.1 - Amended : Depricated waitUntilSelectOptionsPopulated. Pending Fix";
	
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
		
		log("Action - Getting value from : " + xpathLocator);
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
	public void closeOKpopup()
	{
		String xpathforOK = "//input[contains (@value,'OK')]";
		
		try 
		{
			waitFor(500);
			if (driver.findElements(By.xpath(xpathforOK)).size() != 0)
			{
				clickOn(xpathforOK);
				waitUntilElementnotExist(xpathforOK, 5);
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
	
	//Method to redirect console messages
	public void log(String message)
	{
		String msg = "\n" + sdf.format(new Date()) + " : " + message;
		System.out.print(msg);
	}
}
