package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.StudentModel;
import model.UserModel;
import util.StringUtils;
import util.ValidationUtil;

/**
 * This servlet class handles student registration requests. It extracts student
 * information from the registration form submission, performs basic data
 * validation(to be implemented ), and attempts to register the student in the
 * database using a 'DBController'. The user is redirected to the login page
 * upon successful registration.
 * 
 * 
 * @author Clean Energy (cleanenergy@gmail.com)
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.REGISTER_SERVLET })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");

		String userName = request.getParameter(StringUtils.USER_NAME);
		String firstName = request.getParameter(StringUtils.FIRST_NAME);
		String lastName = request.getParameter(StringUtils.LAST_NAME);
		String gender = request.getParameter(StringUtils.GENDER);
		String email = request.getParameter(StringUtils.EMAIL);
		String phone = request.getParameter(StringUtils.PHONE);
		String password = request.getParameter(StringUtils.PASSWORD);
		String retypePassword = request.getParameter(StringUtils.RETYPE_PASSWORD);
		String image = request.getParameter("image");
		

		UserModel studentModel = new UserModel(userName, firstName, lastName, gender, email, phone, password, image);
		
		// first name validation
		if (!ValidationUtil.validateName(firstName)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE_FIRST_NAME);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);

		}
		// validating phone number
		else if (!ValidationUtil.validatePhoneNumber(phone)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE_PHONE_NUMBER);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);

		}
		// validating password
		else if (!ValidationUtil.validatePassword(password)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE_PASSWORD);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		
		// matching retype password with original password
		else if (password.equals(retypePassword) == false) 
		{
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE_RETYPE_PASSWORD);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);

		} 
		
		else 
		{
			int result = dbController.addStudent(studentModel);

			switch (result) {
			case 1:
				request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE);
				response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE);
				break;

			case 0:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request,
						response);

				break;

			case -3:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_EMAIL);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request,
						response);

				break;
				
			case -2:
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_USERNAME);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request,
					response);
			
			case -4:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_PHONE_NUMBER);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request,
						response);

				break;
			default:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request,
						response);
				break;
		}
		}

	}

}