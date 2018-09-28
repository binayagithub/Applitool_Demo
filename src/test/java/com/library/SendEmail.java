package com.library;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
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
import org.apache.log4j.Logger;

public class SendEmail
{
	static Logger logger = LoggerInfo.getLogger(UtilityScreen.class.getClass().getName());
	
    public static void sendMailWithAttachment(String fileName,String automationTCs)
    {
        String host = "outlook.office365.com";
        
        String port = "587";
        
        final String user = "pumaadmin@brillio.com";

        final String password = "brillio@2021";
       
        String to =  "Binaya.padhi@brillio.com";
        
        Properties props = new Properties();
        
        props.put("mail.smtp.host", host);
        
        props.put("mail.smtp.port", port);
        
        props.put("mail.smtp.starttls.enable", "true");
        
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new Authenticator()
        {
            @Override
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user, password);
            }
        };
        
        Session session = Session.getInstance(props, auth);
        
        
        
        try
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            
            message.setSubject("CBD Regression Test Automation Execution Report");
            
            
         			BodyPart sendMailBody = new MimeBodyPart();

         			
         			

                    sendMailBody.setText("Hi All" +"\n"+ "\n"+ "Please find attached Test Automation Execution Report for PUMA PID Article..."+ "\n"+ "\n" + " FYI, attached is the list of automated test cases can be found in Sheet TestCases"+ "\n"+ "\n" + "Thanks" + "\n" + "Automation Team");
         			
         			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
         			
         			DataSource source = new FileDataSource(fileName);

         			
         			messageBodyPart2.setDataHandler(new DataHandler(source));
         			
         			int lastIndex = fileName.lastIndexOf("/");
         			
         			fileName = fileName.substring(lastIndex+1, fileName.length());

         			messageBodyPart2.setFileName(fileName);
         			
         			
         			DataSource sourceFile = new FileDataSource(automationTCs);
         			MimeBodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setDataHandler(new DataHandler(sourceFile));
                    
                    int lastIndexOf = automationTCs.lastIndexOf("/");
                    
                    automationTCs = automationTCs.substring(lastIndexOf+1, automationTCs.length());
                    
                    messageBodyPart.setFileName(automationTCs);

         			
         			Multipart multipart = new MimeMultipart();

         			
         			multipart.addBodyPart(messageBodyPart2);

         			
         			multipart.addBodyPart(sendMailBody);

         			
         			message.setContent(multipart);

         			Transport.send(message);
        }catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}