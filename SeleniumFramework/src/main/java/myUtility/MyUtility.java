package myUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;





public class MyUtility {
	Properties prop;
	public void getValueFromJsonFile(String fileName, String key) throws Exception {
		try {
		String projectpath = System.getProperty("user.dir");
		String propfilePath = projectpath +"/propertiesFile/"+fileName+".properties";
		BufferedReader reader = new BufferedReader(new FileReader(propfilePath));
		
		 prop = new Properties();
		prop.load(reader);
		 String browserName = prop.getProperty(key);
		System.out.println(browserName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void updationOfJsonFile(String fileName, String key, String updatedValue) {
		try {
			String projectpath = System.getProperty("user.dir");
			String propfilePath = projectpath +"/propertiesFile/"+fileName+".properties";
			BufferedReader reader = new BufferedReader(new FileReader(propfilePath));
			FileOutputStream fos = new FileOutputStream(new File(propfilePath));
			
			 prop = new Properties();
			prop.load(reader);
			prop.setProperty(key,updatedValue);
			prop.store(fos,updatedValue);
			
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
	}
//	public String getDriverFromPropertiesFile() {
//		
//		 String browserName = prop.getProperty("browser");
//		 
//		 if(browserName != null) 
//			return browserName;
//		 else
//			 throw new RuntimeException("Browser name not present in the properties file");
//	}
	
	public Map<String,String> getDataFromJsonFile(String jsonFileName, String jsonHeaderName) {
		Map<String, String> dataFromJson = null;
		try {
			
			JSONParser parser = new JSONParser();
			String projectPath = System.getProperty("user.dir");
			Object jsonObj = parser.parse(new FileReader(projectPath+"/JsonFiles/"+jsonFileName+".json"));
			JSONObject jso= (JSONObject) jsonObj;
			 dataFromJson = (Map<String, String>) jso.get(jsonHeaderName);
			 System.out.println(dataFromJson.toString());
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return dataFromJson;
	}
}
