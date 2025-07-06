package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][]getData() throws IOException
	{
		String path=".\\testData\\Amazon_loginData.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);
		
		
		int totalRows=xlutil.getRowCount("sheet1");
		int totalCol=xlutil.getCellCount("sheet1", 1);
		
		String loginData[][]=new String[totalRows][totalCol];
		
		for (int i=1;i<=totalRows;i++)
		{
			for(int j=0;j<totalCol;j++)
			{
				loginData[i-1][j]=xlutil.getCellData("sheet1", i, j);
			}
		}
		return loginData;
	}
}
