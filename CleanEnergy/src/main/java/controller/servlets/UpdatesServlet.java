package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.UserModel;
import util.StringUtils;
import util.ValidationUtil;


@WebServlet(urlPatterns = {"/UpdatesServlet"})
@MultipartConfig
public class UpdatesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    DatabaseController dbController = new DatabaseController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username from session
    	// Fetch user profile data from the database
    	
    	
    	String username = (String) request.getSession().getAttribute("username");
    	if (username == null) {
    	    // Redirect to login page if user is not logged in
    	    response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE);
    	    return;
    	}

    	try {
    	    // Fetch user data from the database based on the username
    	    UserModel user = dbController.getUserByUsername(username);
    	    if (user == null) {
    	        // Handle user not found
    	        response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE);
    	        return;
    	    }
    	    
    	    // Set user data as attribute to be displayed in the view
    	    request.setAttribute("user", user);

    	    // Forward the request to the profile page
    	    request.getRequestDispatcher(StringUtils.USER_PROFILE_PAGE).forward(request, response);
    	} catch (IOException e) {
    	    // Handle IO exception
    	    e.printStackTrace(); // Log the exception for debugging
    	    request.setAttribute("errorMessage", "Failed to fetch user profile.");
    	    request.getRequestDispatcher(StringUtils.USER_PROFILE_PAGE).forward(request, response);
    	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle form submission for updating user profile
        // Retrieve form data
    	// form and this name same
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phonenumber"); 
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        Part part = request.getPart("image");
        String image = part.getSubmittedFileName(); 

        
        //get user from session
        HttpSession s = request.getSession();
        s.getAttribute("username");        
        // Create a UserModel object with updated data 
        // Update user profile
        UserModel updatedUser = new UserModel(username, firstName, lastName, gender, email, phoneNumber, password, image);
        
        
     // first name validation
		if (!ValidationUtil.validateName(firstName)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE_FIRST_NAME);
			request.getRequestDispatcher(StringUtils.USER_PROFILE_PAGE).forward(request, response);

		}
		
	    
	     // first name validation
		if (!ValidationUtil.validatelastName(lastName)) {
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE_LAST_NAME);
				request.getRequestDispatcher(StringUtils.USER_PROFILE_PAGE).forward(request, response);

			}
			
		// validating phone number
		if (!ValidationUtil.validatePhoneNumber(phoneNumber)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE_PHONE_NUMBER);
			request.getRequestDispatcher(StringUtils.USER_PROFILE_PAGE).forward(request, response);

		}
		/*
		 * // validating password else if (!ValidationUtil.validatePassword(password)) {
		 * request.setAttribute(StringUtils.ERROR_MESSAGE,
		 * StringUtils.REGISTER_ERROR_MESSAGE_PASSWORD);
		 * request.getRequestDispatcher(StringUtils.USER_PROFILE_PAGE).forward(request,
		 * response); return; }
		 */


        
        //update database
        PrintWriter out = response.getWriter();
        boolean reply = dbController.updateUser(updatedUser);
       
        
        if(reply) {
        	
        	out.println("Update details at database");
        	String contextPath = request.getContextPath();
    	    String path = contextPath + "/images/user" + updatedUser.getImage();
    	    out.println("Image path: " + path);
    	    
//    	    dbController.deleteFile(path);
//    	    if (dbController.saveFile(part.getInputStream(), path)) {
//    	    	out.println("Profile Picture  updated");
//    	    }
//    	    else {
//    	    	out.println("Profile Picture not updated");
//    	    }    
    	    
        } else {
        	out.println("Not Update details at database");
        }
    }
        
        
        
        /*try {
            
            // If update successful, redirect to the user profile page
            request.setAttribute("successMessage", "Success to update user profile.");
            response.sendRedirect(request.getContextPath() + StringUtils.WELCOME_PAGE);
        } catch (SQLException | ClassNotFoundException e) {
            // If update fails, display an error message
        	e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to update user profile.");
            request.getRequestDispatcher(StringUtils.USER_PROFILE_PAGE).forward(request, response);
        }
    }*/
}
