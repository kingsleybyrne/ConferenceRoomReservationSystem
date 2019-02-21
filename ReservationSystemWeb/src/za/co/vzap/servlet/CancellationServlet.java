package za.co.vzap.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.vzap.dao.MySqlGeneralDAO;

/**
 * Servlet implementation class CancellationServlet
 */
@WebServlet("/cancellation")
public class CancellationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private MySqlGeneralDAO genDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellationServlet() {
        super();
        genDao=new MySqlGeneralDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("cancelName");
		String surname=request.getParameter("cancelSurname");
		String startDate=request.getParameter("cancelStartDate");
		String endDate=request.getParameter("cancelEndDate");
		String roomName=request.getParameter("cancelRoomName");
		String act = request.getParameter("act");
		if (act == null) {
		    //no button has been selected
		} else if (act.equals("Confirm Booking")) {
		    //delete button was pressed
			try {
				genDao.updateBookingStatusBooked(name, surname, roomName);
				System.out.println("Confirmed");
				RequestDispatcher dispatcher=request.getRequestDispatcher("Statistics.jsp");

				//dispatcher.forward((ServletRequest) session, response);
				dispatcher.forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (act.equals("Cancel Booking")) {
			try {
				genDao.updateBookingStatusCancel(name, surname, roomName);
				System.out.println("CANCELLED");
				RequestDispatcher dispatcher=request.getRequestDispatcher("Statistics.jsp");

				//dispatcher.forward((ServletRequest) session, response);
				dispatcher.forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    //update button was pressed
		} else {
		    //someone has altered the HTML and sent a different value!
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("Statistics.jsp");
		
		//dispatcher.forward((ServletRequest) session, response);
		dispatcher.forward(request, response);
		//response.sendRedirect("Statistics.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
