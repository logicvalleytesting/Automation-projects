package BWV_Package;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Get_token {
	public static Logger logger;
	public static Properties prop;
	public static Properties path;
	JSONObject myResponse ;
	public static   ExtentHtmlReporter htmlReports;
    public static   ExtentReports extent;
    public static ExtentTest Test;
    public static   String filename= "./Report/ExtentReportResults.html";
    public static String concatenate=".";
	public static String Acc_token;
	
	public static int pass;
	public static int fail=5;
	
	@Test(priority=1)
	public void Properties_config() throws Exception {
		try {
			
		
		logger=Logger.getLogger(Get_token.class);
	       // configure log4j properties file
	       PropertyConfigurator.configure("log4j.properties");
	    
	     File file = new File("./Datafile.properties");
	       FileInputStream fileInput = null;
			fileInput = new FileInputStream(file);
			prop = new Properties();
			prop.load(fileInput);	

			 htmlReports = new ExtentHtmlReporter(filename);
		    	extent =new ExtentReports();
		    	extent.attachReporter(htmlReports);
		    	htmlReports.config().setReportName("Automation Testing");
		    	htmlReports.config().setTheme(Theme.STANDARD);
		    	htmlReports.config().setTestViewChartLocation(ChartLocation.TOP);
		    	extent.setSystemInfo("Project Name", "BWV BOT");
		    	extent.setSystemInfo("Environment", "Automation-BOT");
                    pass=0;
                   
		} catch(Exception  e) {
			logger.error("Get token method configuration failure"); 
			logger.error("Exceptions happen!", e); 
			fail=1;
			System.out.println(e);
			 e.printStackTrace();
				 throw(e);
		}
	}
	
	@Test(priority=2)
	public String get_token_method() throws Exception {
		try {
			/* System.out.println("Second");*/
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			   logger.info("=============================================Execution started at"+timeStamp +"====================================================");
			URL obj = new URL(prop.getProperty("TokenURL"));
			System.out.println(prop.getProperty("TokenURL"));
				//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				
				//add reuqest header
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("grant_type", prop.getProperty("grant_type")));
				params.add(new BasicNameValuePair("username", prop.getProperty("username")));
				params.add(new BasicNameValuePair("password", prop.getProperty("password")));
				
				/*System.out.println(prop.getProperty("grant_type"));
			   System.out.println(prop.getProperty("username"));
				System.out.println(prop.getProperty("password"));*/

				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				
				
				
				//logger.info("Token generated login credentials-grant_type:"+prop.getProperty("grant_type")+",User name:"+ prop.getProperty("username")+",Password:"+prop.getProperty("password"));
				
				wr.writeBytes(getQuery(params));

				wr.flush();
				wr.close();

				int responseCode = con.getResponseCode();
				//System.out.println("\nSending 'POST' request to URL : " + prop.getProperty("TokenURL"));
				logger.info("\nSending 'POST' request to URL : " + prop.getProperty("TokenURL"));
				//System.out.println("Post parameters : " + urlParameters);
				//System.out.println("Response Code : " + responseCode);
				logger.info("Response Code : " + responseCode);
				
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			//	 String ResuserName =
				in.close();
				 myResponse = new JSONObject(response.toString());
				// System.out.println("Test string"+myResponse);
				//  System.out.println(myResponse);
				  Acc_token=myResponse.getString("access_token");
				//  System.out.println(Acc_token);

//			String token=response.toString("");	 
			
			if(Acc_token != null) {
				logger.info("Token generated");
				Test =extent.createTest("TC-1:Get token");
			    Test.log(Status.PASS,"Token generated login credentials-grant_type:"+prop.getProperty("grant_type")+",User name:"+ prop.getProperty("username")+",Password:"+prop.getProperty("password"));
			}else {
				logger.info("Token not generated");
				Test.log(Status.PASS,"Token not generated");
				fail=1;
			}

		 return Acc_token;
			
				} catch(Exception  e) {
					fail=1;
					logger.error("Token Not gennerated"); 
					logger.error("Exceptions happen!", e); 
					// exception = e.getMessage();
					 Test.log(Status.FAIL, "Token Not gennerated"+e);
					 System.out.println(e);
					 e.printStackTrace();
					 throw(e);
				
		}
	}
	
	private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException{
		try {
	    StringBuilder result = new StringBuilder();
	    boolean first = true;

	    for (NameValuePair pair : params)
	    {
	        if (first)
	            first = false;
	        else
	            result.append("&");
	        result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	    }

	    return result.toString();
		} catch(Exception  e) {
			fail=1;
			logger.error("Get query list failure"); 
			logger.error("Exceptions happen!", e); 
			// exception = e.getMessage();
			 System.out.println(e);
			 e.printStackTrace();
		}
		return null;
	}
}
