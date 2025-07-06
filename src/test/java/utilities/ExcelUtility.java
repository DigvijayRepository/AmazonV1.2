package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;			
	}
	
	public int getCellCount(String sheetname,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		int colCount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return colCount;			
	}
	
	public String getCellData(String sheetname, int rownum,int colNum ) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(colNum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try
		{
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	
	public void setCellData(String sheetname,int rownum,int colNum ,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())
		{
			workbook =new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi=new FileInputStream(path);
		workbook =new XSSFWorkbook();
		
		if(workbook.getSheetIndex(sheetname)==-1)
			workbook.createSheet(sheetname);
		sheet=workbook.getSheet(sheetname);
		
		if(sheet.getRow(rownum)==null)
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colNum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
}
