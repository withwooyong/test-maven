package jsonTest;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonTest {

	public static void main(String[] args) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String test = "Command(id=0, priority=0, dml=0, sourceFile=test, "
				+ "base_Adaptive_30fps_320x180_0300K=0300K, "
				+ "base_Adaptive_30fps_640x360_0600K=0600K, "
				+ "base_Adaptive_30fps_640x360_0900K=0900K, "
				+ "base_Adaptive_30fps_960x540_1500K=1500K, "
				+ "base_Adaptive_30fps_1280x720_3000K=3000K, "
				+ "base_Adaptive_30fps_1920x1080_5000K=5000K, "
				+ "fullcommand='')";
		
		String s = test.substring(0, test.length()-1).replace("Command(", "").replaceAll(" ", "");
		
		HashMap<String, Object> commandMap = new HashMap<String, Object>();
		
		String[] pairs = s.split(",");
		for (int i = 0; i < pairs.length; i++) {
			String pair = pairs[i];
			String[] keyValue = pair.split("=");
			commandMap.put(keyValue[0], NVL(keyValue[1]));
			//System.out.println(keyValue[0] + ":" + keyValue[1]);
		}
		// Command(id=null, priority=0, dml=0, sourceFile=test, Base_Adaptive_30fps_320x180_0300K=testtes, Base_Adaptive_30fps_640x360_0600K=test, Base_Adaptive_30fps_640x360_0900K=tesatesatsa, Base_Adaptive_30fps_960x540_1500K=tesstesa, Base_Adaptive_30fps_1280x720_3000K=tesatesat, Base_Adaptive_30fps_1920x1080_5000K=atestas, fullcommand=)

		// org.apache.commons.lang.StringUtils;
		//defaultString(str, str)은 첫 번째 인수가 공백문자(“”) 일 경우에 NULL이라고 인식하지 않는 반면에,
		//defaultIfEmpty(str, str)는 첫 번째 인수가 공백문자(“”)일 경우에도 NULL이라고 인식한다.
		System.out.println(commandMap.toString());
		Command command = mapper.convertValue(commandMap, Command.class);
		System.out.println(command.toString());
	}
	
	public static String NVL(Object obj) {

		if(obj == null) {
			return new String("");
		} else {
			return obj.toString().trim();
		}
	}

}
