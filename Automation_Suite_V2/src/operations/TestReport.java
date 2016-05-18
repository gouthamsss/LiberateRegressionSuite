package operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.TakesScreenshot;
import org.apache.poi.util.Units;

import com.sun.media.sound.InvalidFormatException;

public class TestReport
{
	static String ClassVersion = "TestReport 1.0.0 : Removed : Access to main create document method";

	String scenarioName;
	String reportLocation;
	
	String documentName;
	
	static int screenshotCount = 0;
	
	public TestReport(String scenarioName, String reportLocation)
	{
		this.scenarioName = scenarioName;
		this.reportLocation = reportLocation;
	}
	
	public void takeScreenshot()
	{
		String filename = scenarioName + "_" + String.valueOf(screenshotCount);
		String location = reportLocation + filename + ".png";
		
		File screenshot = ((TakesScreenshot)Operations.getdriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		try 
		{
			org.apache.commons.io.FileUtils.copyFile(screenshot, new File(location));
			screenshotCount = screenshotCount + 1;
		}
		catch (IOException e) 
		{
			System.out.println("Not able to save screenshot " + filename + " to " + location);
			e.printStackTrace();
		}

		System.out.println(" - Took Screenshot : " + filename);
	}
	
	public void createScreenshotDocument(String Document_Name)
	{
		documentName = Document_Name;
		createDocument();
	}
	public void createScreenshotDocument()
	{
		documentName = scenarioName;
		createDocument();
	}
	
	private void createDocument()
	{
		createDirectory(reportLocation);
		
		XWPFDocument doc = new XWPFDocument();
		String wordDoc = reportLocation + documentName;
		int scrnCount = screenshotCount;
		
	    XWPFParagraph para = doc.createParagraph();    
	    XWPFRun run = para.createRun();
	    
	    for (int i = 0; i < scrnCount; i++)
	    {
			String imgFile = reportLocation + scenarioName + "_" + (i) + ".png";
					
			File file = new File(imgFile);
		    
			try 
			{
				FileInputStream is = new FileInputStream(imgFile);
				run.addBreak();
			    run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(540), Units.toEMU(303)); //720 x 405 - 16:9
			    is.close();
			    file.delete();
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (InvalidFormatException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	    }
	    
	    FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(wordDoc + ".docx");
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try 
	    {
			doc.write(fos);
		    fos.close();   
		}
	    catch (IOException e) 
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    System.out.println("Created Document " + documentName + ".docx");
		//TODO add create screenshot document code
	    
	    screenshotCount = 0;
	}
	
	private void createDirectory(String path)
	{
		File dir = new File(path);
		
		if (!dir.exists())
		{
			try
			{
				dir.mkdirs();
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		}

	}
}
