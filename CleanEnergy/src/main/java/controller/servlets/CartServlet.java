package controller.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DatabaseController;
import model.CartItemModel;
import util.CartAction;
import util.MessageUtil;
import util.StringUtils;
import util.UserUtils;

/**
 * This servlet class handles cart basic requests. It handles 
 * remove product from the cart, update quantity in cart and delete product from cart 
 * 
 * @author Tekmaya Chaudhari
 */
@WebServlet(urlPatterns = "/cart", asyncSupported = true)
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbControlller = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
	}

	/**
     * Handles HTTP GET requests for cart.
     *
     * @param request The HttpServletRequest object containing login form data.
     * @param response The HttpServletResponse object for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Check if user is logged in 
		if(UserUtils.isUserLoggedIn(request)) {
			// Extract userId from the session
			int userId = UserUtils.getUserId(request);
			//Call DBController to get cart details by userId
			String cartId = dbControlller.getCart(userId);
			//Call DBController to get items from cart by cartId
			List<CartItemModel> cartItems = dbControlller.getCartItems(cartId);
			//Call DBController to total from cart by cartId
			BigDecimal cartTotal = dbControlller.getCartTotal(cartId);
			//Set attributes cartItems and cartTotal in request
			request.setAttribute("cartItems", cartItems);
			request.setAttribute("cartTotal", cartTotal);
	
			RequestDispatcher dispatcher = request.getRequestDispatcher(StringUtils.CART_PAGE);
			dispatcher.forward(request, response);
		} else {
			// Extract referer page from request
	        String previousPage = request.getHeader("referer")!=null? request.getHeader("referer").toString():"index.jsp";
	        // Handle messages using session for sendRedirect
			MessageUtil.setErrorMessage(request, StringUtils.LOGIN_REQUIRED_CART_VIEW_ERROR_MESSAGE);
			response.sendRedirect(previousPage);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartAction action = CartAction.valueOf(request.getParameter(StringUtils.TASK));
		switch (action) {
			case REMOVE_PRODUCT:
				doDelete(request, response);
				break;
			case CREATE_ORDER:
				createOrder(request);
				break;
			case UPDATE_QTY_PRODUCT:
				doPut(request, response);
				break;
			default:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.TASK_NOT_FOUND);
		}
		doGet(request, response);
	}
	
	/**
     * Handles HTTP DELETE and  requests for cart and Removes a product from the user's cart.
     *
     * @param request The HttpServletRequest object containing login form data.
     * @param response The HttpServletResponse object for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

    	// Extract userId from the session
		int userId = UserUtils.getUserId(request);
		//Call DBController to get cart details by userId
		String cartId = dbControlller.getCart(userId);
		
		// Extract cart_item_id from the request parameters
		int cartItemId = Integer.valueOf(request.getParameter(StringUtils.CART_ITEM_ID));
		
		//Call DBController to remove product from cart
		int result = dbControlller.removeProductFromCart(cartItemId);
		
		//Call DBController to update total of cart
		result = dbControlller.updateCartTotal(cartId);
		
		if(result>0) {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.CART_ITEM_REMOVE_SUCCESS_MESSAGE);			
		}else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.CART_ITEM_REMOVE_ERROR_MESSAGE);
		}
	}

    /**
     * Handles HTTP POST requests for login.
     *
     * @param request The HttpServletRequest object containing login form data.
     * @param response The HttpServletResponse object for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// Extract userId from the session
		int userId = UserUtils.getUserId(request);
		//Call DBController to get cart details by userId
		String cartId = dbControlller.getCart(userId);
		
		// Extract item_qty and cart_item_id from the request parameters
		int cartItemQty = Integer.valueOf(request.getParameter(StringUtils.ITEM_QTY));
		int cartItemId = Integer.valueOf(request.getParameter(StringUtils.CART_ITEM_ID));

		//Call DBController to update quantity in cart items
		int result = dbControlller.updateProductQtyCart(cartItemQty, cartItemId);
		
		//Call DBController to update total of all items in cart
		result = dbControlller.updateCartTotal(cartId);
		// Handle update results with appropriate messages and redirects
		if (result > 0) {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.CART_ITEM_UPDATE_QTY_SUCCESS_MESSAGE);

		} else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.CART_ITEM_UPDATE_QTY_ERROR_MESSAGE);
		}
	}

	/**
     * Creates an order from the user's cart.
     */
	private void createOrder(HttpServletRequest request) {
		// Extract userId from the session
		int userId = UserUtils.getUserId(request);
		//Call DBController to get cart details by userId
		String cartId = dbControlller.getCart(userId);
		
		//Call DBController to create order from in cart
		int result = dbControlller.createOrder(cartId);
		// Handle order create results with appropriate messages and redirects
		if (result > 0) {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.ORDER_CREATED_SUCCESS_MESSAGE);
		} else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ORDER_CREATED_FAILED_MESSAGE);
		}
	}

}
