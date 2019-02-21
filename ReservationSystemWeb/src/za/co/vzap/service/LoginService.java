package za.co.vzap.service;

import java.sql.SQLException;
import java.util.ArrayList;
import za.co.vzap.dao.MySqlReportDAO;
import za.co.vzap.dto.BookingDTO;
import za.co.vzap.dto.ClientDTO;
import za.co.vzap.dto.UserDTO;

public class LoginService {
	private MySqlReportDAO listDao=null;
	private ArrayList<UserDTO> listUsers=null;
	private ArrayList<ClientDTO> listClients=null;


	public LoginService() throws SQLException
	{
		listDao =new MySqlReportDAO();
		listClients=(ArrayList<ClientDTO>)listDao.clientAll();
		listUsers=(ArrayList<UserDTO>)listDao.userListAll();
	}

	public boolean authenticateUser(String username,String password) throws SQLException, InterruptedException{
		for(int i=0;i<listUsers.size();i++){
			if(listUsers.get(i).getClientUsername().equals(username)){
				if(listUsers.get(i).getClientPassword().equals(password)){
					return true;
				}
			}
		}
		return false;
	}
	public ClientDTO getUserDetails(String username) throws SQLException{
		String clientName=null;
		String clientSurname=null;
		String clientTitle=null;
		String clientPhoneNumber=null;
		String clientEmail=null;
		String department=null;
		for(int i=0;i<listClients.size();i++){
			System.out.println("In for loop");
			if(listClients.get(i).getClientEmail().equals(username)){
				clientName=listClients.get(i).getClientName();
				clientSurname=listClients.get(i).getClientSurname();
				clientTitle=listClients.get(i).getClientTitle();
				clientPhoneNumber=listClients.get(i).getClientPhoneNumber();
				clientEmail=listClients.get(i).getClientEmail();
				department=listClients.get(i).getDepartment();
				System.out.println("Client returned");
				ClientDTO client=new ClientDTO(clientName, clientSurname, clientTitle, clientPhoneNumber, clientEmail, department);
				return client;
			}
		}
		System.out.println("Client not found return null");
		return null;
	}
	@SuppressWarnings("unused")
	public BookingDTO getBookingDetails(String username) throws SQLException{
		String clientName=null;
		String clientSurname=null;
		//ArrayList<BookingDTO> bookingList=new ArrayList<BookingDTO>();
		for(int i=0;i<listClients.size();i++){
			System.out.println("In for loop");
			if(listClients.get(i).getClientEmail().equals(username)){
				clientName=listClients.get(i).getClientName();
				clientSurname=listClients.get(i).getClientSurname();
				System.out.println("client="+clientName+clientSurname);
				String startDate=null;
				String endDate = null;
				String booked=null;
				String roomName=null;
				String meetingDescription=null;
				int noOfAttendees=0;
				ArrayList<BookingDTO> bookingList=(ArrayList<BookingDTO>)listDao.bookingAll();
				ArrayList<BookingDTO> bookingDetails=new ArrayList<BookingDTO>();
				for(int a=0;a<bookingList.size();a++){
					System.out.println("in for ");
					if(bookingList.get(a).getClientName().equals(clientName)&&bookingList.get(a).getClientSurname().equals(clientSurname)){
						System.out.println("in if");
						startDate=bookingList.get(a).getStartDate();
						endDate=bookingList.get(a).getEndDate();
						booked=bookingList.get(a).getBooked();
						roomName=bookingList.get(a).getRoomName();
						meetingDescription=bookingList.get(a).getMeetingDescription();
						noOfAttendees=bookingList.get(a).getNumberOfAttendees();
						BookingDTO bookingClient=new BookingDTO(clientName, clientSurname, startDate, endDate, booked, roomName, meetingDescription, noOfAttendees);
						bookingDetails.add(bookingClient);
						return bookingClient;
					}
				}
				
			}
		}
		return null;
	}}
