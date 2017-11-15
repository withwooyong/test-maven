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

import lombok.val;

public class XLSX2 {

	public static void main(String[] args) {
		
		String filePath = "D:\\ESS\\단독IPTV\\BM_UKEY_PROD-20171115.xlsx";
		
		try {
			String[] keyss = { "PP_CD","UKEY_PROD_ID","UKEY_PROD_NM","SVC_CD","SVC_PP_NM","NETWORK_CD","NETWORK_COMPANY","SERVICE_PROD_TYPE","PROD_TARGET_TYPE","SPEED_CD","SMS_PROD_ID","PROD_STATUS","VOD_SVC_LEVEL","IPTV_SVC_LEVEL","PPM_ABLE","VAS_ABLE","ALC_ABLE","CD_PROD_FG","OLD_UKEY_PROD_ID","FIR_SPEED_CD","SND_SPEED_CD" };
			String[] keys ={ "<td><input type=\\\"text\\\" name=\\\"PP_CD\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"UKEY_PROD_ID\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"UKEY_PROD_NM\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"SVC_CD\\\" value=\"\"",  
					"<td><input type=\\\"text\\\" name=\\\"SVC_PP_NM\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"NETWORK_CD\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"NETWORK_COMPANY\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"SERVICE_PROD_TYPE\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"PROD_TARGET_TYPE\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"SPEED_CD\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"SMS_PROD_ID\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"PROD_STATUS\\\" value=\"",  
					"<td><input type=\\\"text\\\" name=\\\"VOD_SVC_LEVEL\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"IPTV_SVC_LEVEL\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"PPM_ABLE\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"VAS_ABLE\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"ALC_ABLE\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"CD_PROD_FG\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"OLD_UKEY_PROD_ID\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"FIR_SPEED_CD\\\" value=\"", 
					"<td><input type=\\\"text\\\" name=\\\"SND_SPEED_CD\\\" value=\""
					 };			
			
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
					buf.append("<form name=\"ins" + rowindex + "\" action=\"#\"><tr>");
					buf.append("<td><input type=\"submit\" value=\"Submit\"></td>");
					
					for (columnindex = 0; columnindex <= cells; columnindex++) { // 셀값을 읽는다
						XSSFCell cell = row.getCell(columnindex);
						buf.append(keys[columnindex] + cell.getStringCellValue() + "\r\n");
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
