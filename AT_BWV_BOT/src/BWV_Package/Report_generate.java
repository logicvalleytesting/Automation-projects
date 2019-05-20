package BWV_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ebay.xcelite.Xcelite;
import com.ebay.xcelite.sheet.XceliteSheet;
import com.ebay.xcelite.writer.SheetWriter;


public class Report_generate extends Get_token {
	public String json_output;
	public static XSSFSheet sheet1;
	 Xcelite xcelite = new Xcelite();
	
	
	@Test(priority = 3)
	public void Generate_Token() throws Exception {
		try {
			Get_token token = new Get_token();
			token.get_token_method();
			//System.out.println("Write file");
			System.out.println(Acc_token);
		} catch (Exception e) {
			fail=1;
			logger.error("Exceptions happen!", e);
			// exception = e.getMessage();
			System.out.println(e);
			e.printStackTrace();
			throw(e);
		}
	}
	
	@Test(priority = 4)
	public void Get_values() throws Exception {
		try {

			URL obj = new URL(prop.getProperty("Get_bwv_values"));
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			String puttoken = "bearer" + " " + Acc_token;
			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", puttoken);
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("bearer",GetAcctoken));

			int responseCode = con.getResponseCode();
			logger.info("\nSending 'GET' request to URL : " + prop.getProperty("Get_bwv_values"));
			logger.info("Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			//System.out.println(response.toString());
			String Resval = response.toString();
			json_output = response.toString();			
			
		//	System.out.println(json_output);
			logger.info("BWV API values are received");
			
			Test =extent.createTest("TC-2:Get BWV API values");
		    Test.log(Status.PASS,"BWV API values are received");
			
		} catch (Exception e) {
			fail=1;
			logger.error("Error occurred get API values");
			logger.error("Exceptions happen!", e);
			 Test.log(Status.FAIL, "Error occurred get API values"+e);
			// exception = e.getMessage();
			System.out.println(e);
			e.printStackTrace();
			throw(e);
		}
	}
	

	@Test(priority = 5)
	public void Write_Totrecords() throws JSONException, IOException {
		try {
	       List<All_records> Allrec = new ArrayList<>();
	      //getting whole json string
	        JSONObject jsonObj = new JSONObject(json_output);
	        //extracting data array from json string
	        JSONArray ja_data = jsonObj.getJSONArray("AllRecords");

			for (int i = 0; i < ja_data.length(); i++) {
				JSONObject jsonObject = ja_data.getJSONObject(i);
				All_records AR = new All_records();
				
				AR.setOfficerId(jsonObject.getString("OfficerId"));
				AR.setCasenumber(jsonObject.getString("Casenumber"));
				AR.setMessageType(jsonObject.getString("MessageType"));

				
				//System.out.println(jsonObject.getString("IsRecordSuccess"));
			
					AR.setIsRecordSuccess(jsonObject.getInt("IsRecordSuccess"));

				if (jsonObject.getString("RecordedFileName")!=null){
					AR.setRecordedFileName(jsonObject.getString("RecordedFileName"));
				}
				
				if (jsonObject.getString("RecordFailReason")!=null){
					AR.setRecordFailReason(jsonObject.getString("RecordFailReason"));
				}
				
				if (jsonObject.getString("DownloadedFileName")!=null){
					AR.setDownloadedFileName(jsonObject.getString("DownloadedFileName"));
				}

				AR.setDateActioned(jsonObject.getString("DateActioned"));
				Allrec.add(AR);
			}
		
			XceliteSheet sheet8=xcelite.createSheet("All records");	
			
			//XceliteSheet sheet=xcelite.getSheet(1);
			
          SheetWriter<All_records> writer = sheet8.getBeanWriter(All_records.class);
			System.out.println("All records results: "+Allrec);
			writer.write(Allrec); 
		
		//	xcelite.write(new File("Report/BWV_Results.xlsx"));
			
			logger.info("Total records API values are writed in excel sheet");
			Test =extent.createTest("TC-3:Write total records");
		    Test.log(Status.PASS,"BWV-total records writed in excel sheet");
		    
		} catch (Exception e) {
			fail=1;
			logger.error("Error occurred in write total records");
			logger.error("Exceptions happen!", e);
			
			 Test.log(Status.FAIL, "Error occurred in write total records"+e);
			// exception = e.getMessage();
			System.out.println(e);
			e.printStackTrace();
			throw(e);
		}

	}
	
	
	
	@Test(priority = 6)
	public void Write_Tot_Cont_off_wise() throws JSONException, IOException {
		try {
	       List<TotalCountOfficerWise> Totcnt_off_wise = new ArrayList<>();
	      //getting whole json string
	        JSONObject jsonObj = new JSONObject(json_output);
	        //extracting data array from json string
	        JSONArray ja_data = jsonObj.getJSONArray("TotalCountOfficerWise");

			for (int i = 0; i < ja_data.length(); i++) {
				JSONObject jsonObject = ja_data.getJSONObject(i);
				TotalCountOfficerWise AR = new TotalCountOfficerWise();
				
				AR.setOfficerId(jsonObject.getString("OfficerId"));
				AR.setMessageType(jsonObject.getString("MessageType"));
				AR.setCount_MessageType(jsonObject.getString("Count_MessageType"));
				
				Totcnt_off_wise.add(AR);
			}
		
			XceliteSheet sheet2=xcelite.createSheet("Total Count officer wise");
          SheetWriter<TotalCountOfficerWise> writer = sheet2.getBeanWriter(TotalCountOfficerWise.class);
			System.out.println("Excel"+Totcnt_off_wise);
			writer.write(Totcnt_off_wise); 
		//	xcelite.write(new File("Report/BWV_Results.xlsx"));
			
			logger.info("BWV-total count officer wise records writed in excel sheet");
			Test =extent.createTest("TC-4:Write total count officer wise");
		    Test.log(Status.PASS,"BWV-total count officer wise records writed in excel sheet");
		    
		} catch (Exception e) {
			fail=1;
			logger.error("Error occurred Write total count officer wise records");
			logger.error("Exceptions happen!", e);
			// exception = e.getMessage();
			 Test.log(Status.FAIL, "Error occurred Write total count officer wise records"+e);
			System.out.println(e);
			e.printStackTrace();
			throw(e);
		}
	}
	
	
	@Test(priority = 7)
	public void Write_Totalcount() throws JSONException, IOException {
		try {
	       List<TotalCount> Totcount = new ArrayList<>();
	      //getting whole json string
	        JSONObject jsonObj = new JSONObject(json_output);
	        //extracting data array from json string
	        JSONArray ja_data = jsonObj.getJSONArray("TotalCount");

			for (int i = 0; i < ja_data.length(); i++) {
				JSONObject jsonObject = ja_data.getJSONObject(i);
				TotalCount AR = new TotalCount();
				AR.setMessageType(jsonObject.getString("MessageType"));
				AR.setCount_MessageType(jsonObject.getString("Count_MessageType"));
				Totcount.add(AR);
			}
			
			XceliteSheet sheet2=xcelite.createSheet("Total Count");	
			
			//XceliteSheet sheet=xcelite.getSheet(1);
			
          SheetWriter<TotalCount> writer = sheet2.getBeanWriter(TotalCount.class);
			System.out.println("Excel"+Totcount);
			writer.write(Totcount); 
		//	xcelite.write(new File("Report/BWV_Results.xlsx"));
			
			logger.info("BWV-total count records writed in excel sheet");
			
			Test =extent.createTest("TC-5:Write total count");
		    Test.log(Status.PASS,"BWV-total count records writed in excel sheet");
		} catch (Exception e) {
			fail=1;
			logger.error("Error occurred Write total count");
			logger.error("Exceptions happen!", e);
			// exception = e.getMessage();
			 Test.log(Status.FAIL, "Error occurred in Write total count"+e);
			
			System.out.println(e);
			e.printStackTrace();
			throw(e);
		}

	}
	

	
@Test(priority = 8)
	public void Write_Totalaction() throws JSONException, IOException {
		try {
			
	       List<TotalAction> Totaction = new ArrayList<>();
	      //getting whole json string
	        JSONObject jsonObj = new JSONObject(json_output);
	        //extracting data array from json string
	        JSONArray ja_data = jsonObj.getJSONArray("TotalAction");

			for (int i = 0; i < ja_data.length(); i++) {
				  JSONObject jsonObject = ja_data.getJSONObject(i);
				TotalAction AR = new TotalAction();
				AR.setOfficer(jsonObject.getString("Officer"));
				AR.setTotalActionCount(jsonObject.getString("TotalActionCount"));
				AR.setTotalVisitCount(jsonObject.getString("TotalVisitCount"));
				Totaction.add(AR);
			}

	          XceliteSheet sheet1=xcelite.createSheet("Total Action");
			SheetWriter<TotalAction> writer = sheet1.getBeanWriter(TotalAction.class);
			System.out.println("Excel"+Totaction);
			writer.write(Totaction); 
			
		//	xcelite.write(new File("Report/BWV_Results.xlsx"));
			logger.info("BWV-total action records writed in excel sheet");
			
			Test =extent.createTest("TC-6:Write total action");
		    Test.log(Status.PASS,"BWV-total action records writed in excel sheet");

		} catch (Exception e) {
			fail=1;
			logger.error("Error occurred Write total action ");
			logger.error("Exceptions happen!", e);
			// exception = e.getMessage();
			Test.log(Status.FAIL, "Error occurred in Write total action"+e);
			System.out.println(e);
			e.printStackTrace();
			throw(e);
		}
	}
	

	@Test(priority = 9)
	public void Write_allfiles() {
		try {
	         xcelite.write(new File("Report/BWV_Results.xlsx"));
	        /* Test =extent.createTest("TC-6:Write_Allfiles");
			    Test.log(Status.PASS,"BWV-All files writed in excel sheet.");*/
			    logger.info("BWV-All files writed in excel sheet.");
		} catch (Exception e) {
			fail=1;
			logger.error("Error occurred Write all files ");
			logger.error("Exceptions happen!", e);
			
			//Test.log(Status.FAIL, "Error occurred in Write all files."+e);
			System.out.println(e);
			e.printStackTrace();
			throw(e);
		}
	}
	
	 
	  
	

}
