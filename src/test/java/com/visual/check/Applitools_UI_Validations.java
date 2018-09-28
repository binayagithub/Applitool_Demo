package com.visual.check;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.applitools.eyes.RectangleSize;
import com.relevantcodes.extentreports.LogStatus;
import com.testcases.ConfigClass;

@SuppressWarnings({ "serial", "unused" })
@Test(retryAnalyzer = com.BusinessProcess.RetryAnalyzer.class)

public class Applitools_UI_Validations extends ConfigClass
{
	  public void ApplitoolsHelloWorldprog()
	  {	
		try
		{
			  logger.info("Hello World Application launched");

	  
			  eyes.open(driver, "Hello World!", "My first Selenium Java test!");
	          
		      eyes.checkWindow("Hello!");
		      
		      logger.info("Hello World Home page Screen shot is validated");

	       
	          driver.findElement(By.tagName("button")).click();
	       
	          eyes.checkWindow("Click!");
       
	          eyes.close();
		}finally
		{
			driver.quit(); 
	        
			eyes.abortIfNotClosed();
		}
          
	  }
}