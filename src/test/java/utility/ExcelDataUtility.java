package utility;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import org.apache.logging.log4j.core.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataUtility {

	static Workbook w = null;
	static Sheet s = null;
	static Row r = null;
	static Cell c = null;
	static FileInputStream f = null;

	public static Workbook initializeDataSource(String filename) throws Exception {

		f = new FileInputStream(new File(filename));
		w = new XSSFWorkbook(f);
		return w;
	}

	public static int excelTotalRowCount(String filename, String sheetname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int rowcount = s.getLastRowNum();
		return rowcount;
	}

	public static int excelTotalColumnCount(String filename, String sheetname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int colcount = s.getRow(0).getLastCellNum();
		return colcount;
	}

	public static String getCellData(String filename, String sheetname, int rownum, int colcum) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		r = s.getRow(rownum);
		c = r.getCell(colcum);
		DataFormatter formatter = new DataFormatter();
		String value;
		try {
			value = formatter.formatCellValue(c);
		} catch (Exception e) {

			value = "NA";
			System.out.println(e.getMessage());
		}
		if (value.isEmpty()) {
			value = "NA";
		}
		return value;
	}

	public static String getCellData(String filename, String sheetname, int rownum, String colname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int desiredcolnum = columnNumByColName(filename, sheetname, colname);
		r = s.getRow(rownum);
		c = r.getCell(desiredcolnum);
		DataFormatter formatter = new DataFormatter();
		String value;
		try {

			value = formatter.formatCellValue(c);
		} catch (Exception e) {
			value = "NA";
			System.out.println(e.getMessage());
		}

		if (value.isEmpty()) {
			value = "NA";
		}
		return value;
	}

	public static int columnNumByColName(String filename, String sheetname, String colname) throws Exception {

		int desiredcolnum = 0;
		int colcount = excelTotalColumnCount(filename, sheetname);

		for (int i = 0; i < colcount; i++) {

			String colval = s.getRow(0).getCell(i).getStringCellValue();
			if (colval.equalsIgnoreCase(colname)) {
				desiredcolnum = i;
				break;
			}
		}

		return desiredcolnum;
	}

	public static int rowNumByColValue(String filename, String sheetname, String colname, String colvalue)
			throws Exception {

		int desiredcolnum = columnNumByColName(filename, sheetname, colname);
		int totalrow = excelTotalRowCount(filename, sheetname);
		int desiredrownum = 0;

		for (int i = 1; i <= totalrow; i++) {
			String cellval = s.getRow(i).getCell(desiredcolnum).getStringCellValue();
			if (cellval.equalsIgnoreCase(colvalue)) {
				desiredrownum = i;
				break;
			}
		}

		return desiredrownum;
	}

	public static void writeToExcelByRef(String filename, String sheetname, String writablecolname, String writevalue,
			String refcol, String refcolvalue) throws Exception {
		int desiredwritablecolnum = columnNumByColName(filename, sheetname, writablecolname);
		int desiredrownum = rowNumByColValue(filename, sheetname, refcol, refcolvalue);
		s.getRow(desiredrownum).createCell(desiredwritablecolnum, CellType.STRING).setCellValue(writevalue);
		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static void writeToExcel(String filename, String sheetname, String writevalue, int rownum, int colnum)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		if (colnum == 0) {
			s.createRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		} else {
			s.getRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		}
		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static void writeToExcel(String filename, String sheetname, String writevalue, int rownum, String colname)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int colnum = columnNumByColName(filename, sheetname, colname);
		if (colnum == 0) {
			s.createRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		} else {
			s.getRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		}
		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static void writeToExcel(String filename, String sheetname, String writevalue, String row, String colname)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int colnum = columnNumByColName(filename, sheetname, colname);
		int rownum = Integer.parseInt(row);
		s.getRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();
	}
	
	
	

	public static ArrayList<HashMap<String, String>> storeExcelDataToHashMap(String filename, String sheetname)
			throws Exception {

		w = initializeDataSource(filename);
		System.out.println("File name is"+filename);
		s = w.getSheet(sheetname);
		System.out.println("sheet name is"+sheetname);
		int totalrow = excelTotalRowCount(filename, sheetname);
		System.out.println("The total row is "+totalrow+" in "+sheetname+" sheet.");
		int totalcol = excelTotalColumnCount(filename, sheetname);		
		System.out.println("The total column is "+totalcol+" in "+sheetname+" sheet.");
		ArrayList<HashMap<String, String>> records = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> data;

		for (int i = 1; i <= totalrow; i++) {
			data = new HashMap<String, String>();
			for (int j = 0; j < totalcol; j++) {

				String value = getCellData(filename, sheetname, i, j);
				String key = getCellData(filename, sheetname, 0, j);
				data.put(key, value);
			}
			records.add(data);
		}
		w.close();
		return records;
	}

	public static void deleteColumnData(String filename, String sheetname, String colname) throws Exception 
	{
		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int totalrow = excelTotalRowCount(filename, sheetname);
		int colnum = columnNumByColName(filename, sheetname, colname);
		for (int i = 1; i <= totalrow; i++) {

			s.getRow(i).createCell(colnum, CellType.STRING).setCellValue(" ");
		}

		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();
	}

	public static void deleteFullDataExceptHeading(String filename, String sheetname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int totalrow = excelTotalRowCount(filename, sheetname);
		for (int i = 1; i <= totalrow; i++) {
			
			s.removeRow(s.getRow(i));
			System.out.println("Removed "+i+" row in "+sheetname+" sheet");
		}

		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static void copyDataFromOtherSheet(String filename, String target_sheetname, String origin_sheetname)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(origin_sheetname);
		int totalrow = excelTotalRowCount(filename, origin_sheetname);
		int totalcol = excelTotalColumnCount(filename, origin_sheetname);
		for (int i = 1; i <= totalrow; i++) {

			for (int j = 0; j < totalcol; j++) {

				String data = getCellData(filename, origin_sheetname, i, j);
				String colname = getCellData(filename, origin_sheetname, 0, j);
				writeToExcel(filename, target_sheetname, data, i, colname);
			}
		}
	}	

	public static void updateAllColumnValue(String filename, String sheetname, String columnname, String writevalue)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int totalrow = excelTotalRowCount(filename, sheetname);

		for (int i = 1; i <= totalrow; i++) 
		{
			ExcelDataUtility.writeToExcel(filename, sheetname, writevalue, String.valueOf(i), columnname);
		}
	}

}
