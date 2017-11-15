package excel;

import java.io.File;
import java.io.FileInputStream;

public class fileread {
	
	public static void main(String[] args) {
		//MIME타입에 따른 workbook생성
		String filePath = "";
		FileInputStream fis = new FileInputStream(new File(filePath));
		Workbook wb = null;
		if("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(fileType)) {
		    wb = new XSSFWorkbook(fis);  //엑셀2007 이상
		} else if("application/vnd.ms-excel".equals(fileType)){
		    wb = new HSSFWorkbook(fis);  //엑셀97~2003 까지
		}

		//WorkbookFactory를 이용한 workbook생성
		FileInputStream fis = new FileInputStream(new File(filePath));
		Workbook wb = WorkbookFactory.create(fis);
	}

}
