package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import configuration.Base;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestUtil extends Base {
	
	
	public static void Entertext(WebElement ele, String value) 
	{
		if (!((value.equalsIgnoreCase("NA")) || (value.equalsIgnoreCase("N/A"))))
		{
			ele.clear();
			ele.sendKeys(value);
		}
	}
	
	public static void clear(WebElement ele) 
	{	
		ele.clear();
	}
	
	public static void click(WebElement ele, String value) 
	{
		if (!((value.equalsIgnoreCase("NA")) || (value.equalsIgnoreCase("N/A"))))
		{
			ele.click();
		}
	}
	
	public static void click(WebElement ele) 
	{
		ele.click();
	}
	
	public static void SelectByText(WebElement ele, String text) {
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (!((text.equalsIgnoreCase("NA")) || (text.equalsIgnoreCase("N/A"))))
		{
			Select select = new Select(ele);
			select.selectByVisibleText(text);
		}
	}
	
	public static void SelectByValue(WebElement ele, String value) 
	{
		if (!((value.equalsIgnoreCase("NA")) || (value.equalsIgnoreCase("N/A"))))
		{
			Select select = new Select(ele);
			select.selectByValue(value);
		}
	}

	public static void SelectByIndex(WebElement ele, int index) 
	{
		Select select = new Select(ele);
		select.selectByIndex(index);
	}
	
	public static String Gettext(WebElement ele) 
	{
		String text_raw = ele.getText();
		String text=text_raw.trim();
		return text;
	}

	public static String GetAttributeValue(WebElement ele, String attributename) 
	{
		String attributevalue = ele.getAttribute(attributename);
		return attributevalue;
	}
	
	public static ArrayList<HashMap<String, String>> storeExcelDataToHashMap(String filepath, String sheetname) throws Exception 
	{
		Workbook w;
		Sheet s;

		FileInputStream f = new FileInputStream(filepath);
		w = new XSSFWorkbook(f);
		s = w.getSheet(sheetname);
		int totalrow = s.getLastRowNum();
		int totalcol = s.getRow(0).getLastCellNum();

		ArrayList<HashMap<String, String>> records = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> data;

		for (int i = 1; i <= totalrow; i++) {
			data = new HashMap<String, String>();
			for (int j = 0; j < totalcol; j++) {

				String value = s.getRow(i).getCell(j).getStringCellValue();
				String key = s.getRow(0).getCell(j).getStringCellValue();
				data.put(key, value);
			}
			records.add(data);
		}
		w.close();
		return records;
	}
	
	
	
	public static String take_screenshot(WebDriver driver) throws Exception 
	{
		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filename = System.getProperty("user.dir") + prop.getProperty("ScreenshotDirectory") + "/"
				+ System.currentTimeMillis() + ".png";
		File destinationPath = new File(filename);
		FileUtils.copyFile(sourcePath, destinationPath);
		return filename;
	}
	
	public static String take_fullpage_screenshot(WebDriver driver) throws Exception 
	{
		String filename = System.getProperty("user.dir") + prop.getProperty("ScreenshotDirectory") + "/"
				+ System.currentTimeMillis() + ".png";

		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
		ImageIO.write(fpScreenshot.getImage(), "PNG", new File(filename));
		return filename;
	}
	
	public static void redirect(String url) throws Exception 
	{
		driver.navigate().to(url);
	}
	
	public static void refreshPage(WebDriver driver) 
	{
		driver.navigate().refresh();
	}
	
	
	
}
