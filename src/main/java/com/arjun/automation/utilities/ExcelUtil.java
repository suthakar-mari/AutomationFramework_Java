package com.arjun.automation.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil 
{
	public static FileInputStream fi;
	public static FileOutputStream fo;
	
	public static XSSFWorkbook workBook;
	public static XSSFSheet workSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static int getRowCount(String xlFile, String xlSheet) throws IOException
	{
		fi = new FileInputStream(xlFile);
		workBook = new XSSFWorkbook(fi);
		workSheet = workBook.getSheet(xlSheet);
		int rowCount = workSheet.getLastRowNum();
		workBook.close();
		fi.close();
		return rowCount;
	}
	
	public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException
	{
		fi = new FileInputStream(xlFile);
		workBook = new XSSFWorkbook(fi);
		workSheet = workBook.getSheet(xlSheet);
		row = workSheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fi.close();
		return cellCount;
	}
	
	public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException
	{
		fi = new FileInputStream(xlFile);
		workBook = new XSSFWorkbook(fi);
		workSheet = workBook.getSheet(xlSheet);
		row = workSheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		String data;
		
		try
		{
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
		}
		catch (Exception e)
		{
			data = "";
		}
		
		workBook.close();
		fi.close();
		return data;
	}
	
	public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data) throws IOException
	{
		fi = new FileInputStream(xlFile);
		workBook = new XSSFWorkbook(fi);
		workSheet = workBook.getSheet(xlSheet);
		row = workSheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlFile);
		workBook.write(fo);
		workBook.close();
		fi.close();
		fo.close();
	}
}
