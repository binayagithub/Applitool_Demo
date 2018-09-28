package com.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.Eyes;
import com.library.ExcelLibrary;
import com.library.LoggerInfo;
import com.library.SendEmail;
import com.library.UtilityScreen;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

@SuppressWarnings({ "serial" })
public class ConfigClass
{
	/*Global Variables*/
	public static WebDriver driver;
	/*WebDriver Config*/
	public static ExcelLibrary lib = new ExcelLibrary();
	/*Excel Library to load*/
	
	public static Properties prop = new Properties();
	/*Load Properties File*/
	
	static String fileName = "C:/tests/brillio/functional_regressiontests/CBD/Reports/CBD_Regression_Suite_Report.html";
	
	static String testcases = "C:/tests/brillio/functional_regressiontests/CBD/Test Cases/CBD_Automation_TestCases.xlsx";
	/*Extent Report Path which includes FileName.html*/
	
	public static ExtentReports extent = new ExtentReports(fileName, true);
	/*Extent Reports Configuration*/
	
	public static ExtentTest test;
	/*Extent Report method*/
	
	public static Logger logger = LoggerInfo.getLogger(UtilityScreen.class.getClass().getName());
	/*Logger File Configuration*/
	
	public static String extFile = "C:/tests/brillio/functional_regressiontests/CBD/extent-config.xml";
	/*Extent Report configuration file*/
	
	public static String SourceFile = "C:/tests/brillio/functional_regressiontests/CBD/LocatorProperties/CBDObject.properties";
	/*Object Properties file Configuration*/
	
	public static String chromeDriverLaunch = "C:/tests/brillio/functional_regressiontests/CBD/ExternalLib/chromedriver.exe";
	/*Chrome Driver*/
	
	public static Eyes eyes; /*Configuration of Visutal Validtion i.e Applitools object called 'eyes'*/
							@BeforeSuite						
							public void ExtentReports()
							
							{
								/*Configuration of Extent Reports*/
								extent.loadConfig(new File(extFile));
							}
							
							
														@AfterSuite
														public void FlushExtentReports()
														{
															/*Flush is the method used to generate reports*/
															extent.flush();
															logger.info("Extent Report Flushed and Report is available now");
															
															/*Sending Email*/
															SendEmail.sendMailWithAttachment(fileName,testcases);
													    	logger.info("Email Sent with attached execution report");
													    	
													    	BatchInfo mybatch = new BatchInfo(System.getenv("APPLITOOLS_BATCH_NAME")); 
															mybatch.setId(System.getenv("APPLITOOLS_BATCH_ID"));
															eyes.setBatch(mybatch);
														}
							
							@BeforeMethod
							public void LaunchApplication()
							{
								String browser, appurl;
								
								eyes = new Eyes();
								
								eyes.setApiKey("kpNPv4d85D7tePADMLAY5Qw3nJFafGzeNDduPd4AgTo110");
								
								browser=lib.getExcelData("Config", 1, 0);
								
								appurl=lib.getExcelData("Config", 3, 0);
								
								logger.info("Test Execution started for " + this.getClass().getSimpleName());
								
								File src = new File(SourceFile);
								
								FileInputStream fis = null;
								
								try {fis = new FileInputStream(src);} catch (FileNotFoundException e) {e.printStackTrace();}
								
								try {prop.load(fis);} catch (IOException e) {e.printStackTrace();}
								
												if(browser.equalsIgnoreCase("chrome"))
												{
														System.setProperty("webdriver.chrome.driver", chromeDriverLaunch);
														ChromeOptions o = new ChromeOptions();
														o.addArguments("disable-extensions");
														o.addArguments("--start-maximized");
														 driver = new ChromeDriver(o);
												}
									driver.manage().window().maximize();
									driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
									driver.get(appurl);
							}
							
														@AfterMethod
														public void LogoutApplication()
														{
															extent.endTest(test);
															logger.info("Test Case Execution Completed for " + this.getClass().getSimpleName());
														}
					
}
