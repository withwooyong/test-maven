package excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReadSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ExcelReadSample sample = new ExcelReadSample();
		
		String file = "D:\\ESS\\단독IPTV\\BM_UKEY_PROD-20171115.xlsx";

		try {
			sample.excelRead(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void excelRead(File file) throws IOException {

		// map의 key정의D:\ESS\단독IPTV
		//PP_CD	UKEY_PROD_ID	UKEY_PROD_NM	SVC_CD	SVC_PP_NM	NETWORK_CD	NETWORK_COMPANY	SERVICE_PROD_TYPE	PROD_TARGET_TYPE	SPEED_CD	SMS_PROD_ID	PROD_STATUS	VOD_SVC_LEVEL	IPTV_SVC_LEVEL	PPM_ABLE	VAS_ABLE	ALC_ABLE	CD_PROD_FG	OLD_UKEY_PROD_ID	FIR_SPEED_CD	SND_SPEED_CD

		String[] keys = { "PP_CD","UKEY_PROD_ID","UKEY_PROD_NM","SVC_CD","SVC_PP_NM","NETWORK_CD","NETWORK_COMPANY","SERVICE_PROD_TYPE","PROD_TARGET_TYPE","SPEED_CD","SMS_PROD_ID","PROD_STATUS","VOD_SVC_LEVEL","IPTV_SVC_LEVEL","PPM_ABLE","VAS_ABLE","ALC_ABLE","CD_PROD_FG","OLD_UKEY_PROD_ID","FIR_SPEED_CD","SND_SPEED_CD" };

		Workbook w = null;
		try {
			w = Workbook.getWorkbook(file);
			// 첫번째 엑셀 시트를 가져옴.
			Sheet sheet = w.getSheet(0);

			// 읽어들인 데이터를 저장
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

			// 한 행을 저장
			Map<String, Object> map = null;

			// 시트의 행 사이즈 만큼 읽어옴.
			for (int j = 0; j < sheet.getRows(); j++) {

				map = new HashMap<String, Object>();

				// 열 사이즈 만큼 읽어옴.
				for (int i = 0; i < sheet.getColumns(); i++) {
					// 한 셀을 읽어옴.
					Cell cell = sheet.getCell(i, j);

					// 셀타입
					CellType type = cell.getType();

					// 셀타입이 문자열
					if (type == CellType.LABEL) {
						System.out.println("문자열 입니다. " + cell.getContents());
					}

					// 셀타입이 숫자
					if (type == CellType.NUMBER) {
						System.out.println("숫자 입니다. " + cell.getContents());
					}

					// 한개의 셀 데이터
					map.put(keys[i], cell.getContents());
				}

				data.add(map);
			}

			// 읽어 들인 데이터 출력
			for (Map<String, Object> temp : data) {
				System.out.println(temp);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

}
