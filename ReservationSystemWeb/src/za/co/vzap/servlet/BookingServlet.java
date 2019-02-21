package za.co.vzap.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.util.converter.DateTimeStringConverter;
import za.co.vzap.dao.MySqlGeneralDAO;
import za.co.vzap.dto.BookingDTO;
import za.co.vzap.dto.ClientDTO;
import za.co.vzap.email.SendingEmail;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MySqlGeneralDAO genDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingServlet() {
		super();
		genDao=new MySqlGeneralDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("fname");
		String surname=request.getParameter("lname");
		String email=request.getParameter("email");
		String phoneNumber=request.getParameter("phone");
		String dateStart=request.getParameter("dateStart");
		String dateEnd=request.getParameter("dateEnd");
		String attendees=request.getParameter("noOfAttendees");
		int noOfAttendees=Integer.parseInt(attendees);
		String meetingDescription=request.getParameter("meetingDescription");
		String roomName=request.getParameter("roomName");
		String booked="pending";
		//String dateStart1=dateStart.replace("/", "-");
		//String dateEnd1=dateStart.replace("/", "-");
		System.out.println("This is the startDate before"+dateStart);
		System.out.println("This is the endDate after"+dateEnd);
		dateStart=dateStart.replaceAll("/", "-");
		dateEnd=dateEnd.replaceAll("/", "-");
		String dateStart1=dateStart.substring(0, 16);
		String dateEnd1=dateEnd.substring(0, 16);
		//String equipment=request.getParameter("equipmentSelect");
		//System.out.println(equipment);
		System.out.println("This is the startDate"+dateStart1);
		System.out.println("This is the endDate"+dateEnd1);

		Scanner scStart=new Scanner(dateStart1);
		scStart.useDelimiter("-");
		String dayStart=scStart.next();
		String monthStart=scStart.next();
		String yearStart=scStart.next().substring(0,4);
		Scanner scStartTime=new Scanner(dateStart1);
		scStartTime.useDelimiter(" ");
		System.out.println("Second set"+scStartTime.next());
		String timeStart=scStartTime.next();
		String properStartDate=yearStart+"-"+dayStart+"-"+monthStart+" "+timeStart+":00.0";

		Scanner scEnd=new Scanner(dateEnd1);
		scEnd.useDelimiter("-");
		String dayEnd=scEnd.next();
		String monthEnd=scEnd.next();
		String yearEnd=scEnd.next().substring(0, 4);
		Scanner scEndTime=new Scanner(dateEnd1);
		scEndTime.useDelimiter(" ");
		System.out.println("Second set"+scEndTime.next());
		String timeEnd=scEndTime.next();
		String properEndDate=yearEnd+"-"+dayEnd+"-"+monthEnd+" "+timeEnd;
		//Do equipment
		if(!(name.equals(null)&&surname.equals(null)&&email.equals(null)&&phoneNumber.equals(null)&&dateStart.equals(null)&&dateEnd.equals(null)&&attendees.equals(null)&&meetingDescription.equals(null)&&roomName.equals(null))){
			try {
				System.out.println(dateStart +" "+dateEnd);
				ClientDTO client=(ClientDTO)request.getSession().getAttribute("client");
				BookingDTO booking=new BookingDTO(name, surname, properStartDate, properEndDate, booked, roomName, meetingDescription, noOfAttendees);
				SendingEmail sendEmail=new SendingEmail(client, booking);
				genDao.insertBooking(booking);
				RequestDispatcher dispatcher=request.getRequestDispatcher("Cancellation.jsp");

				//dispatcher.forward((ServletRequest) session, response);
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}
		else{
			response.sendRedirect("Cancellation.jsp");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
