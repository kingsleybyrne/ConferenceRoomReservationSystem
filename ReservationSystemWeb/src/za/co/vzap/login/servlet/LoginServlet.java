package za.co.vzap.login.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import za.co.vzap.dto.BookingDTO;
import za.co.vzap.dto.ClientDTO;
import za.co.vzap.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private LoginService loginService = null;
	
	private static final long serialVersionUID = 1L;
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() throws SQLException {
        super();
        loginService = new LoginService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("userID");
		String password=request.getParameter("password");
		try {
			boolean result=loginService.authenticateUser(username, password);
			System.out.println("result = " + result + ".....>>>>>>>>");
			HttpSession session=request.getSession();
			if(result){
				System.out.println("in if(result).....>>>>>>>>");
				ClientDTO client=loginService.getUserDetails(username);
				BookingDTO bookingDetails=loginService.getBookingDetails(username);
				//UserDTO user = login
				session.setAttribute("booking", bookingDetails);
				System.out.println(bookingDetails);
				System.out.println(bookingDetails.toString());
				session.setAttribute("client", client);
				RequestDispatcher dispatcher=request.getRequestDispatcher("BookingSet1.jsp");
				
				//dispatcher.forward((ServletRequest) session, response);
				dispatcher.forward(request, response);
				//this.getServletConfig().getServletContext().setAttribute("booking", bookingDetails); // add to application context
			    //request.getRequestDispatcher("/Cancellations.jsp").forward(request, response);
				return;
			}
			else{
				response.sendRedirect("Login.jsp");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//doGet(request, response);
	}

}
