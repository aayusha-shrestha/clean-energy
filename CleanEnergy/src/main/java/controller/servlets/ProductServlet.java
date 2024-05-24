package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.CartItemModel;
import model.ProductModel;
import util.CartAction;
import util.StringUtils;
import util.UserUtils;

/**
 * This servlet class handles productrequests. It handles 
 * add product to the cart and get product search data 
 * 
 * @author Tekmaya Chaudhari
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbControlller = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * Handles GET requests for displaying the product search.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("q");
		String price = request.getParameter("price");
		
		search = search!=null?search:"";
		price = price!=null?price:"";
		ArrayList<ProductModel> products = dbControlller.getAllProducts(search,price);
		request.setAttribute("products", products);
		RequestDispatcher dispatcher = request.getRequestDispatcher(StringUtils.PRODUCT_PAGE);
		dispatcher.forward(request, response);
	}

	/**
     * Handles HTTP POST requests for product page.
     *
     * @param request The HttpServletRequest object containing product data.
     * @param response The HttpServletResponse object for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter(StringUtils.TASK);
		CartAction action = CartAction.valueOf(task);

		switch (action) {
		case ADD_PRODUCT_TO_CART:
			addProductToCart(request,response);
			break;
		default:
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.TASK_NOT_FOUND);
		}
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *  handles add product to cart
	 */
	private void addProductToCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//Check if user is logged in 
		if (UserUtils.isUserLoggedIn(request)) {
			// Extract userId from the session
			int userId = UserUtils.getUserId(request);
			//Call DBController to get cart details by userId
			String cartId = dbControlller.getCart(userId);
			// Extract productId and item_qty from the request parameters
			int productId = Integer.valueOf(request.getParameter(StringUtils.PRODUCT_ID));
			int itemQty = Integer.valueOf(request.getParameter(StringUtils.ITEM_QTY));
			//Call DBController to add product to cart
			int result = dbControlller.addProductToCart(new CartItemModel(cartId, productId, itemQty));
			
			//Call DBController to update total of cart
			result = dbControlller.updateCartTotal(cartId);
			if (result > 0) {
				request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.CART_ADD_PRODUCT_SUCCESS_MESSAGE);
			} else {
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.CART_ADD_PRODUCT_ERROR_MESSAGE);
			}
		} else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.LOGIN_REQUIRED_CART_ERROR_MESSAGE);
		}
	}
}
