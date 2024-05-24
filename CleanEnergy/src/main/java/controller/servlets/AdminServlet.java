package controller.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.OrderModel;
import model.ProductModel;
import util.StringUtils;
import util.ValidationUtil;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = "/admin")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,//2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50)
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProductModel> products = dbController.getAllProducts("","");
		request.setAttribute("products", products);
		ArrayList<OrderModel> orders = dbController.getOrders();
		request.setAttribute("orders", orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher(StringUtils.ADMIN_PAGE);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		  
		int productId = 0; //temporary placeholder since productId is auto-increment in db but ProductModel has productId in constructor
		String productName = request.getParameter(StringUtils.PRODUCT_NAME);
		String productDesc = request.getParameter(StringUtils.PRODUCT_DESC);
	    int categoryId = Integer.parseInt(request.getParameter(StringUtils.CATEGORY_ID));
	    
	    // Validation for product price
	    BigDecimal productPrice = null;
	    try {
	        productPrice = new BigDecimal(request.getParameter(StringUtils.PRODUCT_PRICE));
	    } catch (NumberFormatException e) {
	    	request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PRODUCT_ERROR_MESSAGE_PRICE);
	        request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
	    }
		
		// Validation for stock qty
	    int stockQty = 0;
	    try {
	        stockQty = Integer.parseInt(request.getParameter(StringUtils.STOCK_QTY));
	    } catch (NumberFormatException e) {
	        request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PRODUCT_ERROR_MESSAGE_STOCK_QTY );
	        request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
	    }

		String stockLevel = request.getParameter(StringUtils.STOCK_LEVEL);
		Part imagePart = request.getPart(StringUtils.PRODUCT_IMAGE);
		  
		ProductModel productModel = new ProductModel(productId,productName, productDesc,
				categoryId, productPrice, stockQty, stockLevel, imagePart);
		
		String savePath = StringUtils.IMAGE_DIR_PRODUCT;
		String fileName = productModel.getProductImgUrlFromPart();
		if(!fileName.isEmpty() && fileName != null)
			imagePart.write(savePath + fileName);
		
		int result = dbController.addProduct(productModel);
		  
		switch (result) { 
		case 1: 
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.ADD_PRODUCT_SUCCESS_MESSAGE);
			response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_SERVLET);
			break;
		case 0:
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ADD_PRODUCT_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request,response);
			break;
		  
		default: 
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request,response); 
			break; 
		}
		 
	}
}

