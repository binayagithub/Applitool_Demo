package com.library;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.testcases.*;


public class ExcelLibrary extends ConfigClass
{
	static XSSFWorkbook wb;
	
	static XSSFSheet sheet;
	public static File src = new File("C:/tests/brillio/functional_regressiontests/CBD/DataProviders/CBD_DataSheet.xlsx");
	
	public String getExcelData(String sheetName, int row, int col)
	{
		try {
			File src = new File("C:/tests/brillio/functional_regressiontests/CBD/DataProviders/CBD_DataSheet.xlsx");
			
			FileInputStream fis = new FileInputStream(src);
			
			wb = new XSSFWorkbook(fis);
	
			} catch (Exception e)
			{	
			System.out.println(e.getMessage());
			}
		
		sheet = wb.getSheet(sheetName);
		
		String data = sheet.getRow(row).getCell(col).getStringCellValue();
		
		return data;
	}
	
	public static int rwoCount(String sheetName)
	{
	sheet = wb.getSheet(sheetName);
	return  sheet.getLastRowNum()-sheet.getFirstRowNum();
	}
   public static int colCount(String sheetName)
 {
	sheet = wb.getSheet(sheetName);
	return sheet.getRow(rwoCount(sheetName)).getLastCellNum();
	}

																						public int WriteToExcel(String sheetName, int row, int col, int val) throws IOException
																						{
																							 FileInputStream fis = new FileInputStream(src);
																																					           
																							FileOutputStream fos = null;
																																					           
																							XSSFWorkbook wb = new XSSFWorkbook(fis);
																																					           
																							XSSFSheet st = wb.getSheet(sheetName);
																																					           
																							XSSFRow rowNum = st.getRow(row);
																																					           
																							if(rowNum==null)
																						    {
																								rowNum = st.createRow(row);
																						    }
																																					           
																							 XSSFCell colNum = rowNum.getCell(col);
																																					           
																							if(colNum==null)
																							 {
																								colNum=rowNum.createCell(col);
																							 }
																																					           
																							String setval=Integer.toString(val);
																																					           
																							 colNum.setCellValue(setval);
																																					          
																							fos = new FileOutputStream(src);
																																					           
																							 wb.write(fos);
																																					           
																							 wb.close();
																							 
																							return val;
																						}

public String WriteToExcelStr(String sheetName, int row, int col, String msg)
{
	FileInputStream fis = null;
																				
	try {fis = new FileInputStream(src);} catch (FileNotFoundException e) {e.printStackTrace();}
																				
	FileOutputStream fos = null;
																				
    XSSFWorkbook wb = null;
																				
	try {wb = new XSSFWorkbook(fis);} catch (IOException e) {e.printStackTrace();}
																				
	XSSFSheet st = wb.getSheet(sheetName);
																				
	XSSFRow rowNum = st.getRow(row);
																				
	if(rowNum==null)
	{
		rowNum = st.createRow(row);
		}
																				
	XSSFCell colNum = rowNum.getCell(col);
																				
	if(colNum==null)
	{
	colNum=rowNum.createCell(col);
	}
																				
	colNum.setCellValue(msg);
																				
	try {fos = new FileOutputStream(src);} catch (FileNotFoundException e) {e.printStackTrace();}
																				
	try {wb.write(fos);} catch (IOException e) {e.printStackTrace();}
																				
	try {wb.close();} catch (IOException e) {e.printStackTrace();}
																				
	return msg;
	}
}