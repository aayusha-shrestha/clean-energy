package controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter(StringUtils.PRODUCT_ID_TO_DELETE));
		
		
		int result = dbController.deleteProduct(productId);
		
		switch(result) {
		case 1: 
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.DELETE_PRODUCT_SUCCESS_MESSAGE);
			break;
		case 0:
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.DELETE_PRODUCT_ERROR_MESSAGE);
			break;
		  
		default: 
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			break; 
		}
		
		response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_SERVLET);
	}
}
