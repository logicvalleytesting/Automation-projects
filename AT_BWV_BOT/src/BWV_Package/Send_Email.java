package BWV_Package;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class Send_Email extends Get_token{
	ITestResult result;
	public String DT_Today;
	public String DT_Yesterday;
	
	@Test(priority=10)
	public void get_results() {
		
		 Calendar cal = Calendar.getInstance();
		   DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 //  System.out.println("Today's date is "+dateFormat.format(cal.getTime()));
		    DT_Today=dateFormat.format(cal.getTime());

		   cal.add(Calendar.DATE, -1);
		 //  System.out.println("Yesterday's date was "+dateFormat.format(cal.getTime())); 
		    DT_Yesterday=dateFormat.format(cal.getTime()); 

		}
	
	@Test(priority=11)
	public void mail_configuration() {
		// Create object of Property file
try {

				// this will set host of server- you can change based on your requirement 
		        prop.put("mail.smtp.host", prop.getProperty("SMTP_Hostname"));
		 
				// set the port of socket factory 
				prop.put("mail.smtp.socketFactory.port", prop.getProperty("SMTP_Port_num"));
		 
				// set socket factory
				prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

				 prop.put("mail.smtp.socketFactory.fallback", "true"); 

				// set the authentication to true
				prop.put("mail.smtp.auth", "true");
		 
				// set the port of SMTP server
				prop.put("mail.smtp.port", prop.getProperty("SMTP_Port_num"));
			
				// This will handle the complete authentication
				Session session = Session.getDefaultInstance(prop,new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(prop.getProperty("AT_Username"), prop.getProperty("AT_Password"));
							}
						});
				
		 
				try {
					
					// Create object of MimeMessage class
					Message message = new MimeMessage(session);

					// Set the from address
					message.setFrom(new InternetAddress(prop.getProperty("From_Email_add")));

					// Set the recipient to address
					message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(prop.getProperty("REC_TO")));
					
					// Set the recipient cc address
					message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(prop.getProperty("REC_CC")));
		            
		            // Add the subject link
					message.setSubject("BWV Reports on"+ DT_Yesterday);
					
					/* System.out.println("Test32434");*/
					if(fail==5){
						// Create object to add multimedia type content
						BodyPart messageBodyPart1 = new MimeBodyPart();
						// Set the body of email
						messageBodyPart1.setText("Hi,\r\n" + 
								"   \r\n" + 
								" Please find the attached document for Bwv Reports on "+ DT_Yesterday+".");
			 
						// Create another object to add another content
						MimeBodyPart messageBodyPart2 = new MimeBodyPart();
						MimeBodyPart messageBodyPart3 = new MimeBodyPart();
			 
						// Mention the file which you want to send
						String filename = "Report/BWV_Results.xlsx";
						
						// Mention the file which you want to send
						String filename1 = "Report/ExtentReportResults.html";
			 
						// Create data source and pass the filename
						DataSource source = new FileDataSource(filename);
						DataSource source1 = new FileDataSource(filename1);
			 
						// set the handler
						messageBodyPart2.setDataHandler(new DataHandler(source));
						messageBodyPart3.setDataHandler(new DataHandler(source1));
			 
						// set the file
						messageBodyPart2.setFileName(filename);
						messageBodyPart3.setFileName(filename1);
			 
						// Create object of MimeMultipart class
						Multipart multipart = new MimeMultipart();
			 
						// add body part 1
						multipart.addBodyPart(messageBodyPart2);
						multipart.addBodyPart(messageBodyPart3);
			 
						// add body part 2
						multipart.addBodyPart(messageBodyPart1);
			 
						// set the content
						message.setContent(multipart);
						
					}else {

						// Create object to add multimedia type content
						BodyPart messageBodyPart1 = new MimeBodyPart();
			 
						// Set the body of email
						messageBodyPart1.setText("Hi,\r\n" + 
								"   \r\n" + 
								" BWV build has failed, please check log file.");
			 
						// Create another object to add another content
						MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			 
						// Mention the file which you want to send
						String filename = "log/testlog.log";
			 
						// Create data source and pass the filename
						DataSource source = new FileDataSource(filename);
			 
						// set the handler
						messageBodyPart2.setDataHandler(new DataHandler(source));
			 
						// set the file
						messageBodyPart2.setFileName(filename);
			 
						// Create object of MimeMultipart class
						Multipart multipart = new MimeMultipart();
			 
						// add body part 1
						multipart.addBodyPart(messageBodyPart2);
			 
						// add body part 2
						multipart.addBodyPart(messageBodyPart1);
			 
						// set the content
						message.setContent(multipart);
					}
					
					// finally send the email
					Transport.send(message);
		 
					System.out.println("=====Email Sent=====");
					logger.info("=====>>Email Sent<<=====");
					Test =extent.createTest("TC-7:Mail configuration");
					Test.log(Status.INFO, "Email Sent");
		 
				} catch (MessagingException e) {
		 
					throw new RuntimeException(e);
		 
				}
				
   }catch (Exception e) {
	logger.error("Exceptions happen!", e);
	System.out.println(e);
	e.printStackTrace();
	throw(e);
 }
		 
}
	
	 @AfterMethod
	    public static void endTest(){
	   extent.flush();
	    }
	  
		
		
	}

