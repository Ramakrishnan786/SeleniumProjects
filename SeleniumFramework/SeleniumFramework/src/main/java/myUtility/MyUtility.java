package myUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;






public class MyUtility {
	Properties prop;
	public String  getValueFromPropertiesFile(String fileName, String key) throws Exception {
		String browserName = null;
		try {
		String projectpath = System.getProperty("user.dir");
		String propfilePath = projectpath +"/propertiesFile/"+fileName+".properties";
		BufferedReader reader = new BufferedReader(new FileReader(propfilePath));
		
		 prop = new Properties();
		prop.load(reader);
		 browserName = prop.getProperty(key);
		 
		System.out.println(browserName);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return browserName;
		
				
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
	
	public void reportGenerator() {
		try {
			
			ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter("testreport.html");
			
			ExtentReports ep = new ExtentReports();
			ep.attachReporter(htmlreporter);
			ExtentTest test = ep.createTest("First test");
			
			test.pass("First test case passed");
			test.info("This is test info");
			test.fail("This test is failed");
			ep.flush();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void excelFile() {
		try {
			XSSFWorkbook workbook  = new XSSFWorkbook("ExcelFiles/Book2.xlsx");
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int rowCount =sheet.getPhysicalNumberOfRows();
				System.out.println(rowCount);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
