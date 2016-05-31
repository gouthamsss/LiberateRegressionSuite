package operations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.TakesScreenshot;

import com.sun.media.sound.InvalidFormatException;

public class TestReport
{
	static String ClassVersion = "TestReport 1.0.1 : Changed : Use log insted of printing to console";

	String scenarioName;
	String reportLocation;
	
	String documentName;
	
	static int screenshotCount = 0;
	
	TestAction ta = new TestAction(Operations.getdriver());
	
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
			ta.log("Not able to save screenshot " + filename + " to " + location);
			ta.log(e.getMessage());
		}

		ta.log(" - Took Screenshot : " + filename);
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
		
	    ta.log("Created Document " + documentName + ".docx");
		//TODO add create screenshot document code
	    
	    screenshotCount = 0;
	}
	
	private static void createDirectory(String path)
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
	
	public static void writelog(String message)
	{
		String dir = "Log\\";
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		createDirectory(dir);
		
		File logfile = new File(dir+date.toString());
		if(!logfile.exists())
		{
			try
			{
				logfile.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try(FileWriter fw = new FileWriter(dir, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(message);
			    //more code
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}
}
