package za.co.vzap.sms;

import java.io.IOException;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;

import za.co.vzap.dto.BookingDTO;
import za.co.vzap.dto.ClientDTO;
//Reference Nexmo Free SMS API
public class SendSMS {
	public static final String API_KEY = "b5f96efc";
	public static final String API_SECRET = "fbmNh3MdWBDSIp0M";

	public static final String FROM_NUMBER = "NEXMO";
	//public static final String TO_NUMBER = "+27727031760";

	public SendSMS(BookingDTO booking,ClientDTO clientDTO) throws IOException, NexmoClientException{
		final String TO_NUMBER=clientDTO.getClientPhoneNumber();
		final String SMS_TEXT = "Dear, "+booking.getClientName()+". You have a booking pending: Confirm within 72 hours or your booking will be cancelled.";
		AuthMethod auth = new TokenAuthMethod(API_KEY, API_SECRET);
		NexmoClient client = new NexmoClient(auth);
		//System.out.println(FROM_NUMBER);

		SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(new TextMessage(
		        FROM_NUMBER,
		        TO_NUMBER,
		        SMS_TEXT));
		for (SmsSubmissionResult response : responses) {
		    System.out.println(response);
		}
		
//		NexmoSmsClient client = null;
//		try {
//			client = new NexmoSmsClient(API_KEY, API_SECRET);
//		} catch (Exception e) {
//			System.err.println("Failed to instanciate a Nexmo Client");
//			e.printStackTrace();
//			throw new RuntimeException("Failed to instanciate a Nexmo Client");
//		}
//
//		// Create a Text SMS Message request object ...
//
//		TextMessage message = new TextMessage(SMS_FROM, SMS_TO, SMS_TEXT);
//
//		// Use the Nexmo client to submit the Text Message ...
//
//		SmsSubmissionResult[] results = null;
//		try {
//			results = client.submitMessage(message);
//		} catch (Exception e) {
//			System.err.println("Failed to communicate with the Nexmo Client");
//			e.printStackTrace();
//			throw new RuntimeException("Failed to communicate with the Nexmo Client");
//		}
	}
//	public static void main(String args[]){
//		BookingDTO booking=new BookingDTO("Kingsley", "Byrne", "2018-07-11 08:00", "2018-07-11 12:00", "Pending", "Amazon", "IT General Meeting",12);
//		ClientDTO client=new ClientDTO("Kingsley", "Byrne", "Mr", "+27727031760", "kingsley.r.byrne@gmail.com", "IT");
//		try {
//			new SendSMS(booking,client);
//		} catch (IOException | NexmoClientException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
