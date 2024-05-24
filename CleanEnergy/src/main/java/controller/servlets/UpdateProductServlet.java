package controller.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/UpdateProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,//2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter(StringUtils.PRODUCT_ID_TO_UPDATE));
		ProductModel product = dbController.getProductInfoById(id);
		
		request.setAttribute("product", product);
		request.getRequestDispatcher(StringUtils.UPDATE_PRODUCT_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("uprodId"));
		
		ProductModel previousProdInfo = dbController.getProductInfoById(productId);
		
		String productName = request.getParameter("uprodName"); 
		String productDesc = request.getParameter("uprodDesc");
		
		// Validation for category id String 
		int categoryId;
		String categoryIdParam = request.getParameter("ucategId"); 
		if (categoryIdParam == null || categoryIdParam.isEmpty()) { 
			categoryId = previousProdInfo.getCategoryId(); 
		} else { 
			categoryId = Integer.parseInt(categoryIdParam); 
		}
				
		// Validation for product price
	    BigDecimal productPrice = null;
	    try {
	        productPrice = new BigDecimal(request.getParameter("uprodPrice"));
	    } catch (NumberFormatException e) {
	    	request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PRODUCT_ERROR_MESSAGE_PRICE);
	        request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
	    }
	    
	    // Validation for stock qty
	    int stockQty = 0;
	    try {
	        stockQty = Integer.parseInt(request.getParameter("ustockQty"));
	    } catch (NumberFormatException e) {
	        request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PRODUCT_ERROR_MESSAGE_STOCK_QTY );
	        request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
	    }
	    
		String stockLevel = request.getParameter("ustockLvl");
		Part imagePart = request.getPart("uprodImage");
	  
		ProductModel productModel = new ProductModel(productId, productName, productDesc,
				categoryId, productPrice, stockQty, stockLevel, imagePart);
		
		// Validation for product image
		String previousImageFilename  = previousProdInfo.getProductImgUrlFromPart();
		String savePath = StringUtils.IMAGE_DIR_PRODUCT;
		String fileName = productModel.getProductImgUrlFromPart();
		if (imagePart == null || imagePart.getSize() == 0) {
	         fileName = previousImageFilename; // Update the previous image filename
	    } else {
	        if (!fileName.isEmpty()) {
	            imagePart.write(savePath + fileName);
	        }
	    }
		productModel.setImageUrlFromDB(fileName);
		
		int result = dbController.updateProduct(productModel);
		switch (result) { 
		case 1: 
			request.setAttribute("successMessage","Added");
			response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_SERVLET);
			break;
		case 0:
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ADD_PRODUCT_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.UPDATE_PRODUCT_PAGE).forward(request,response);
			break;
		  
		default: 
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.UPDATE_PRODUCT_PAGE).forward(request,response); 
			break; 
		}
	}
}
