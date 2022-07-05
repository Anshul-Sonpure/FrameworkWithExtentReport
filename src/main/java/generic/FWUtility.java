package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FWUtility implements Sys_Constant {
	public static String getdata(String path,String sheet, int row,int cell) 
	{ String v = null;
	   Workbook wk;
	try {
		wk = WorkbookFactory.create(new FileInputStream(path));
		v =  wk.getSheet(sheet).getRow(row).getCell(cell).toString();
	} catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	   
	return v;
		
	}
	
	public  static void writedata(String path,String sheet,int row, int cell,String value)
	{
		Workbook wk;
		try {
			wk = WorkbookFactory.create(new FileInputStream(path));
			Sheet sh = wk.getSheet(sheet);
			Row r= null;
			if(sh!=null)
				sh.createRow(row);
			r = sh.getRow(row);
			r = sh.createRow(cell);
			Cell c = r.createCell(cell);
			if(c==null)
				c=r.createCell(cell);
			c.setCellValue(value);
			FileOutputStream fos = new FileOutputStream(new File(path));
			wk.write(fos);
			fos.close();
			wk.close();
		   	System.out.println("written in Book1");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
   }
   
	public static String capturescreen(WebDriver driver, String name)
	{
		TakesScreenshot tks = (TakesScreenshot) driver;
		File src = tks.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\Reports\\" + name +".png";
        File destination = new File(dest);
        try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
                     
        return dest;
	
	}
	public static int getLastrownum(String path,String sheet) throws InvalidFormatException
	{  int count = 0;
		try {
			Workbook wk = WorkbookFactory.create(new File(path));
			count=wk.getSheet(sheet).getLastRowNum();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		
		
		
		
		
	}
			
			
		
		
		
	

}
	
	
	
	
	

