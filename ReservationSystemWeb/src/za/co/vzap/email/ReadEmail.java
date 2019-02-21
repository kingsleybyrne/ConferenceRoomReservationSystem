package za.co.vzap.email;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import za.co.vzap.dao.MySqlGeneralDAO;
import za.co.vzap.dao.MySqlReportDAO;
import za.co.vzap.dto.BookingDTO;
//Referencing OpenSource code
/**
*
* @author All Open source developers
* @version 1.0.0.0
* @since 2014/12/22
*/

public class ReadEmail extends Thread {

	public void run(){
		while(true){
			String host = "pop.gmail.com";
			String user = "vzapreservationsystem@gmail.com";
			String password = "vzap123@";

			Properties properties = System.getProperties();
			Session session = Session.getDefaultInstance(properties, null);
			Store store = null;
			try {
				store = session.getStore("pop3s");
			} catch (NoSuchProviderException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}

			try {
				store.connect(host, user, password);
			} catch (MessagingException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}

			Folder folder = null;
			try {
				folder = store.getFolder("inbox");
			} catch (MessagingException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			try {
				folder.open(Folder.READ_ONLY);
			} catch (MessagingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			Message[] message = null;
			try {
				message = folder.getMessages();
			} catch (MessagingException e2) {
				e2.printStackTrace();
			}
			MySqlGeneralDAO genDao=new MySqlGeneralDAO();
			MySqlReportDAO list=new MySqlReportDAO();
			ArrayList<BookingDTO> clientList=null;
			try {
				clientList=(ArrayList<BookingDTO>)list.bookingAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < message.length; i++) {
				Date sentDate = null;
				try {
					sentDate = message[message.length-1].getSentDate();
					 long myTime = sentDate.getTime();
					 SimpleDateFormat df = new SimpleDateFormat("HH:mm");
					 Date d = df.parse(Long.toString(myTime));
					 Calendar cal = Calendar.getInstance();
					 cal.setTime(d);
					 cal.add(Calendar.HOUR, 72);
					 String newTime = df.format(cal.getTime());
					if(newTime.equals(System.currentTimeMillis())){
						genDao.updateBookingStatusCancel(clientList.get(i).getClientName(), clientList.get(i).getClientSurname(), clientList.get(i).getRoomName());
						genDao.updateRoomCancels(clientList.get(i).getRoomName());
					}
				} catch (MessagingException | ParseException e2) {
					e2.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(message[i].getSentDate().after(sentDate)){

						Scanner sc = null;
						try {
							sc = new Scanner(message[i].getSubject());
						} catch (MessagingException e1) {
							e1.printStackTrace();
						}
						sc.useDelimiter(" ");
						String confirmOrDecline=sc.next();
						sc.next();
						String name=sc.next();
						String surname=sc.next();
						String roomName=sc.next();
						System.out.println(name+" "+surname+" "+roomName);

//						MySqlReportDAO list=new MySqlReportDAO();
//						try {
//							ArrayList<ClientDTO> clientList=(ArrayList<ClientDTO>)list.clientAll();
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
						if(confirmOrDecline.equalsIgnoreCase("Confirm")){
							try {
								genDao.updateBookingStatusBooked(name, surname, roomName);
								genDao.updateRoomCount(roomName);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							System.out.println("Booking confirmed send to DAO");
						}
						if(confirmOrDecline.equalsIgnoreCase("Decline")){
							try {
								genDao.updateRoomCancels(roomName);
								genDao.updateBookingStatusCancel(name, surname, roomName);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							System.out.println("Booking declined send to DAO");
						}
					}
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			try {
				folder.close(true);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			try {
				store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
