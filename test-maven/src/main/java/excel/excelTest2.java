package excel;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelTest2 {

	public static void main(String[] args) {

		String filePath = "D:\\ESS\\단독IPTV\\BM_UKEY_PROD-20171115.xlsx";

		try {
			// xls 파일 읽기***************************************************
			/*
			POIFSFileSystem excel = new POIFSFileSystem(new FileInputStream(filePath));
			HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			*/
			// ************************************************************************
			 
			//*********************************************** xlsx 파일 읽기
			System.out.println(filePath);
			XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
			XSSFSheet sheet = null;
			XSSFRow row = null;
			XSSFCell cell = null;
			//****************************************************************			

			int sheetNum = workBook.getNumberOfSheets();

			for (int k = 0; k < sheetNum; k++) {
				sheet = workBook.getSheetAt(k);
				int rows = sheet.getPhysicalNumberOfRows();

				for (int r = 0; r < rows; r++) {
					row = sheet.getRow(r);
					int cells = row.getPhysicalNumberOfCells();

					for (short c = 0; c < cells; c++) {
						cell = row.getCell(c);
						System.out.println("row:" + r + "==cells:" + c);

						if (cell == null) {
							continue;
						}

						switch (cell.getCellType()) {
						case 0:
							System.out.println(cell.getNumericCellValue());
							break;
						case 1:
							System.out.println(cell.getStringCellValue());
							break;

						case Cell.CELL_TYPE_FORMULA:
							System.out.println(cell.getCellFormula());
							break;

						default:
							System.out.println("");
						}
					} // cell
				} // row
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
