package com.library;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UtilityScreen
{

	static Logger logger = LoggerInfo.getLogger(UtilityScreen.class.getClass().getName());
	
	public static void CaptureScreenshot(WebDriver driver)
	{
			File file = null;
		    
			int rand = ThreadLocalRandom.current().nextInt(1, 999);
		   
			try
		
			{
			  String filepath = "C:/tests/brillio/functional_regressiontests/CBD/ScreenShots/CBD_Reg";
				
		      file = new File(filepath + rand + ".png");
		      
		      File scrFile = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		     
		      FileUtils.copyFile(scrFile, file);
		      
			  logger.info("Screen Shot Taken");
			} 
		
			catch (Exception e)
			{
				logger.info("Exception while taking snap shot " + e.getMessage());
			}
	}
	
	
	public static String SnapShotCapture(WebDriver driver)
	{
		int rand = ThreadLocalRandom.current().nextInt(1, 999);
		
		try
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			
			File source = ts.getScreenshotAs(OutputType.FILE);
			
		  String filepath = "C:/tests/brillio/functional_regressiontests/CBD/ScreenShots/CBD_Reg";
		  
		  String dest = filepath + rand + ".png";
		  
		  File destination = new File(dest);
		  
		  FileUtils.copyFile(source, destination);
		  
		  logger.info("Screen Shot Taken when expected and actual is not matching");
		  
		  return dest;
			
		}catch (Exception e)
		{
			logger.info("Exception While Taking Failure Screen Shot");
			
			return e.getMessage();
		}
	}
}
