package jsonTest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class gsonTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		String jsonInString = "{\"priority\":0,"
				+ "\"dml\":0,"
				+ "\"sourceFile\":\"test\","
				+ "\"base_Adaptive_30fps_320x180_0300K\":\"test\","
				+ "\"base_Adaptive_30fps_640x360_0600K\":\"test\","
				+ "\"base_Adaptive_30fps_640x360_0900K\":\"tests\","
				+ "\"base_Adaptive_30fps_960x540_1500K\":\"testsat\","
				+ "\"base_Adaptive_30fps_1280x720_3000K\":\"tesatesa\","
				+ "\"base_Adaptive_30fps_1920x1080_5000K\":\"tesatsea\","
				+ "\"fullcommand\":\"\"}";

//		Command comm = new Command();
//		comm.setBase_Adaptive_30fps_320x180_0300K("test");
//	    Gson gson = new Gson();
//	    String jsonString = gson.toJson(comm);
//	    JSONObject object = new JSONObject(jsonString);
//		System.out.println(object.get("base_Adaptive_30fps_320x180_0300K"));
	    
	    ObjectMapper mapper = new ObjectMapper();
		Command command = mapper.readValue(jsonInString, Command.class);
		System.out.println(command.getBase_Adaptive_30fps_1280x720_3000K());
	    
	    
	    
	}
	
}
