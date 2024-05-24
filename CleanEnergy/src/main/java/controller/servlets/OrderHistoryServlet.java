package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.OrderModel;
import util.StringUtils;
import util.UserUtils;

/**
 * Servlet implementation class OrderHistoryServlet
 */
@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbControlller = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = UserUtils.getUserId(request);
		List<OrderModel> orders = dbControlller.getOrderHistory(userId);
		
		request.setAttribute("orders", orders);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher(StringUtils.ORDER_HISTORY_PAGE);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
