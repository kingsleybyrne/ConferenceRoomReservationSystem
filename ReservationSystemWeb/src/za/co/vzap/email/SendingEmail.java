package za.co.vzap.email;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.nexmo.client.NexmoClientException;

import za.co.vzap.dto.BookingDTO;
import za.co.vzap.dto.ClientDTO;
import za.co.vzap.sms.SendSMS;
//Reference Open Source Code
/**
*
* @author All Open source developers
* @version 1.0.0.0
* @since 2014/12/22
*/
public class SendingEmail {
	private Properties properties;
	
	public SendingEmail(ClientDTO client,BookingDTO booking){
		properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String authenticateUsername="vzapreservationsystem@gmail.com";
		String authenticatePassword="vzap123@";
		String fromEmail="vzapreservationsystem@gmail.com";
		String subject="Conference Room Booking Confirmation";
		String emailMessage="Dear "+client.getClientName()+",<br />You have booked,<br /><br />Room:<t /> "+booking.getRoomName()+" room<br />From:<t />"+booking.getStartDate()+"<br />To:<t /> "+booking.getEndDate()+"<br />Description:<t /> "+booking.getMeetingDescription()+""
				+ "<br /><br />Please confirm your booking within 72 hours or it will be automatically cancelled"
				+ "<br /><br />Thank you,"
				+ "<br />Conference Room Reservation System</ br>";
		Session session=Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(authenticateUsername,authenticatePassword);
			}
		});
		try {
	         Message message = new MimeMessage(session);
	         //long sentTime=message.getSentDate().getTime();
	         message.setFrom(new InternetAddress(fromEmail));
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(client.getClientEmail()));
	         message.setSubject(subject);
	         MimeMultipart multipart = new MimeMultipart("related");
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<H1>"+emailMessage+"</H1>"+"<a href=\"mailto:vzapreservationsystem@gmail.com?subject=Confirm Booking "+client.getClientName()+" "+client.getClientSurname()+" "+booking.getRoomName()+" Room"+"&body="+" </ br>"+"I confirm my booking.</ br></ br> Please press send.\"><button>Confirm Booking</ button></ a>"+"<a href=\"mailto:vzapreservationsystem@gmail.com?subject=Decline Booking "+client.getClientName()+" "+client.getClientSurname()+" "+booking.getRoomName()+" Room"+"&body=I decline my booking.</ br></ br> Please press send.\"><button>Decline Booking</button ></a >"+"</ br></ br>";
	         messageBodyPart.setContent(htmlText, "text/html");
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
//	         DataSource fds = new FileDataSource(
//	            "resources/new logo.png");

//	         messageBodyPart.setDataHandler(new DataHandler(fds));
//	         messageBodyPart.setHeader("Content-ID", "<image>");
//	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(multipart);
	         Transport.send(message);
			try {
				SendSMS sms=new SendSMS(booking,client);
			} catch (IOException | NexmoClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	         System.out.println("Sent message successfully....");
	         ReadEmail read=new ReadEmail();
			read.start();
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	}
//	public static void main(String [] agrs){
//		ClientDTO client =new ClientDTO("Kingsley", "Byrne", "Mr", "0727031760", "kingsley.r.byrne@gmail.com", "IT");
//		BookingDTO booking = new BookingDTO("Kingsley", "Byrne", "2018-07-11 08:00", "2018-07-11 12:00", "Pending", "Amazon", "IT General Meeting",12);
//		new SendingEmail(client, booking);
//	}
}
