package excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSX {

	public static void main(String[] args) {
		
		try {
			String[] keys = { "PP_CD","UKEY_PROD_ID","UKEY_PROD_NM","SVC_CD","SVC_PP_NM","NETWORK_CD","NETWORK_COMPANY","SERVICE_PROD_TYPE","PROD_TARGET_TYPE","SPEED_CD","SMS_PROD_ID","PROD_STATUS","VOD_SVC_LEVEL","IPTV_SVC_LEVEL","PPM_ABLE","VAS_ABLE","ALC_ABLE","CD_PROD_FG","OLD_UKEY_PROD_ID","FIR_SPEED_CD","SND_SPEED_CD" };
			String[] keyss ={ "<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"PP_CD\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"UKEY_PROD_ID\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"UKEY_PROD_NM\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"SVC_CD\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"SVC_PP_NM\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"NETWORK_CD\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"NETWORK_COMPANY\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"SERVICE_PROD_TYPE\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"PROD_TARGET_TYPE\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"SPEED_CD\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"SMS_PROD_ID\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"PROD_STATUS\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"VOD_SVC_LEVEL\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"IPTV_SVC_LEVEL\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"PPM_ABLE\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"VAS_ABLE\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"ALC_ABLE\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"CD_PROD_FG\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"OLD_UKEY_PROD_ID\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"FIR_SPEED_CD\",\r\n" + 
					"<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"SND_SPEED_CD\"" };
			String filePath = "D:\\ESS\\단독IPTV\\BM_UKEY_PROD-20171115.xlsx";
			
			// 읽어들인 데이터를 저장
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

			// 한 행을 저장
			Map<String, Object> map = null;
			
			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int rowindex = 0;                                                                                                                                                                                                                                                                                                                                   
			int columnindex = 0;
			
			XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0을 준다) 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
			
			int rows = sheet.getPhysicalNumberOfRows(); // 행의 수
			System.out.println("rows=" + rows);
			System.out.println("sheet.getLastRowNum()" + sheet.getLastRowNum());
			for (rowindex = 1; rowindex < rows; rowindex++) { // 행을읽는다				
				map = new HashMap<String, Object>();
				XSSFRow row = sheet.getRow(rowindex);
				
				if (row != null) {					
					int cells = row.getPhysicalNumberOfCells(); // 셀의 수
					//System.out.println("cells=" + cells);
					StringBuffer buf = new StringBuffer();
					buf.append("<form name=\"ins001\" action=\"#\"><tr>");
					buf.append("<td><input type=\"submit\" value=\"Submit\"></td>");
					
					for (columnindex = 0; columnindex <= cells; columnindex++) { // 셀값을 읽는다
						XSSFCell cell = row.getCell(columnindex);
						
						String value = cell.getStringCellValue() + "";
						
						map.put(keys[columnindex], value);
												
						if (cell == null) { // 셀이 빈값일경우를 위한 널체크
							continue;
						} else {
							// 타입별로 내용 읽기
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								value = cell.getCellFormula();
								System.out.println("Formula");
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								value = cell.getNumericCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue() + "";
								//System.out.println(value);
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								value = cell.getBooleanCellValue() + "";								
								break;
							case XSSFCell.CELL_TYPE_ERROR:
								value = cell.getErrorCellValue() + "";
								//System.out.println(value);
								break;
							}
						}
						// 한개의 셀 데이터
						map.put(keys[columnindex], value);
						//System.out.println("각 셀 내용 :" + value);
					}
					buf.append("</tr></form>");
					data.add(map);
				}
			}
			// 읽어 들인 데이터 출력
//			for (int i = 0; i < data.size(); i++) {
//				System.out.println(i + " " + data.get(i));
//			}
//			for (Map<String, Object> temp : data) {
//				System.out.println(temp);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
