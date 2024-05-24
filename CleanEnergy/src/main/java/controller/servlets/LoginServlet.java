package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import model.UserModel;
import util.StringUtils;

/**
 * This Servlet class handles login requests for a cleanEnergy system.
 * It retrieves username and password from the login form submission,
 * validates them against a database using a `DBController`, and redirects the user
 * accordingly based on the login result. 
 * 
 * @author Tekmaya Chaudhari
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbControlller = new DatabaseController();

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	

	}

	/**
     * Handles HTTP POST requests for login.
     *
     * @param request The HttpServletRequest object containing login form data.
     * @param response The HttpServletResponse object for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract userName and password from the request
		String userName = request.getParameter(StringUtils.USER_NAME);
		String password = request.getParameter(StringUtils.PASSWORD);

		// Call DBController to validate user login credentials
		int loginResult = dbControlller.getUserLoginInfo(userName, password);
		
		/**
		 * Handle login results with appropriate messages and redirects
		 * Here if loginResult is userId if its greater than 0
		 * db error if loginResult -1 which is else statment in this case
		 * wrong credentials if loginResult is  0
		 * user does not exist if loginResult is -2 
		 * */
		if (loginResult >= 1) 
		{
			UserModel user = dbControlller.getUserInfo(userName);
			// creating new session
			HttpSession userSession = request.getSession();
			userSession.setAttribute("username", userName);
			userSession.setAttribute("userId", loginResult);
			userSession.setAttribute("userRole", user.getUserRole());
			userSession.setMaxInactiveInterval(30*60);
			
			
			// creating new cookie
			Cookie userCookie = new Cookie("user", userName);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE);
			if(user.getUserRole().equalsIgnoreCase(StringUtils.USER_DEFAULT_ROLE)) {
				response.sendRedirect(request.getContextPath() );
			}else {
				response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_SERVLET);
			}
		}else if(loginResult==-2){
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.LOGIN_ERROR_ACCOUNT_DOES_NOT_EXIST);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
			doGet(request, response);
		}
		else if (loginResult == 0) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.LOGIN_ERROR_WRONG_USER_PASSWORD);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
			doGet(request, response);
		} else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.LOGIN_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
			doGet(request, response);
		}
	}

}
