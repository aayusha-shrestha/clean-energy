package util;

import java.io.File;

public class StringUtils {
	public static final String TASK = "task";
	public static final String TASK_NOT_FOUND = "Task not found";
	public static final String USER_DEFAULT_ROLE = "USER";
	public static final String USER_ADMIN_ROLE = "ADMIN";
	public static final String GET_LOGIN_USER_INFO = "SELECT * FROM users WHERE user_name=?";

	// Start: Images save path
	public static final String IMAGE_ROOT_PATH = "Users\\DELL\\eclipse-workspace\\CleanEnergy\\src\\main\\webapp\\images\\";
	
	public static final String IMAGE_DIR_PRODUCT = "C:/" + IMAGE_ROOT_PATH + "product\\";
	public static final String IMAGE_DIR_USER = "C:/" + IMAGE_ROOT_PATH + "user\\";
	public static final String IMAGE_PATH_PATH = "/images/product";

	// End: Images save path
	
	// error messages
	public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occured.";
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String ERROR_MESSAGE = "errorMessage";
	// end error messages
	
	// register query
	public static final String INSERT_STUDENT = "INSERT INTO users"
			+ "(user_name, first_name, last_name, gender, email, phone_number, password, image)"
			+ "VALUES (?,?,?,?,?,?,?,?)";
	
	public static final String GET_LOGIN_STUDENT_INFO = "SELECT * FROM users "
			+ "WHERE user_name = ?";
	
	public static final String GET_ALL_STUDENT_INFO = "SELECT * FROM users";
	public static final String GET_PASSWORD = "SELECT password FROM users WHERE user_name = ?";
	// end register query
	
	// ********************** product query ******************************
	public static final String PRODUCT_SEARCH_ALL = "select p.product_id ,p.product_name ,p.product_desc ,p.product_price ,p.product_image,p.stock_qty,p.stock_level,p.category_id,c.category_name  "
			+ "from  products p "
			+ "left join category as c on c.category_id=p.category_id "
			+ "where (p.product_name LIKE ? "
			+ "or c.category_name LIKE ? ) "
			+ " AND (p.product_price <= ? OR ? = 0) "
			;
	public static final String PRODUCT_SEARCH_FEATURED = "SELECT * FROM products WHERE product_id BETWEEN 1 AND 4";
	public static final String INSERT_PRODUCT = "INSERT INTO products" 
			+ " (product_name ,product_desc, category_id, product_price , stock_qty, stock_level, product_image)"
			+ "VALUES (?,?,?,?,?,?,?)";
	public static final String GET_PRODUCT_INFO_BY_ID = "SELECT * FROM products WHERE product_id=?";
	public static final String UPDATE_PRODUCT = "UPDATE products SET "
			+ "product_name=?,product_desc=?,category_id=?, product_price=?,stock_qty=?, stock_level=?,"
			+ "product_image=? WHERE product_id=?";
	public static final String DELETE_PRODUCT = "DELETE FROM products " + "WHERE product_id=?";
	// ********************** end product query ******************************
	
	// ********************** admin: product page messages******************************
	public static final String PRODUCT_ERROR_MESSAGE_STOCK_QTY = "Stock Quantity must be a whole number.";
	public static final String PRODUCT_ERROR_MESSAGE_PRICE = "Price must be a whole number or decimal.";
	
	public static final String ADD_PRODUCT_SUCCESS_MESSAGE = "Product Added Succesfully!";
	public static final String ADD_PRODUCT_ERROR_MESSAGE = "Product could not be added!";
	public static final String UPDATE_PRODUCT_SUCCESS_MESSAGE = "Product Updated Succesfully!";
	public static final String UPDATE_PRODUCT_ERROR_MESSAGE = "Product could not be updated!";
	public static final String DELETE_PRODUCT_SUCCESS_MESSAGE = "Product Deleted Succesfully!";
	public static final String DELETE_PRODUCT_ERROR_MESSAGE = "Product could not be deleted!";
	// ********************** end admin: product page messages******************************
	
	
	//update 
	public static final String UPDATE_USER = "UPDATE users SET first_name=?, last_name=?, gender=?, email=?, phone_number=?, password=?, image=? WHERE user_name=?";
	// end product manipulation
	
	//cart query
	public static final String INSERT_CART = "INSERT INTO cart(cart_id, user_id, cart_total) VALUE(?, ?, ?)";
	public static final String SELECT_CART = "SELECT COUNT(*) as total ,cart_id  FROM cart WHERE user_id=?";
	
	// add product page parameter names
	public static final String PRODUCT_NAME = "prodName";
	public static final String PRODUCT_DESC = "prodDesc";
	public static final String CATEGORY_ID= "categId";
	public static final String PRODUCT_PRICE= "prodPrice";
	public static final String STOCK_QTY = "stockQty";
	public static final String STOCK_LEVEL = "stockLvl";
	public static final String PRODUCT_IMAGE = "prodImage";
	// end add product page parameter names
	
	// modify product page parameter names
	public static final String PRODUCT_ID_TO_UPDATE = "updateProdId";
	public static final String PRODUCT_ID_TO_DELETE = "deleteProdId";
	// end modify product page parameter names
		
	// register page parameter names
	public static final String USER_NAME = "username";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE = "phoneNumber";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	//end parameter names
	
	// Register Messages
	public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered!";
	public static final String REGISTER_ERROR_MESSAGE = "Please correct the form data";
	
	public static final String REGISTER_ERROR_MESSAGE_FIRST_NAME = "Please fill correct First Name only String.";
	public static final String REGISTER_ERROR_MESSAGE_LAST_NAME = "Please fill correct Last Name only String.";
	public static final String REGISTER_ERROR_MESSAGE_PHONE_NUMBER = "Invalid Phone Number Format, must have '+' sign at first and 10 digit.";
	public static final String REGISTER_ERROR_MESSAGE_PASSWORD = "Invalid password, must have combination of First Uppercase, Numbers and Characters.";
	
	public static final String REGISTER_ERROR_MESSAGE_RETYPE_PASSWORD = "Password does not match!";
	public static final String REGISTER_ERROR_USERNAME = "Username already exits!";
	public static final String REGISTER_ERROR_PHONE_NUMBER= "Phone number already exists!";
	public static final String REGISTER_ERROR_EMAIL = "Email already exits!";
	public static final String REGISTER_ERROR_PASSWORD = "Password already exits!. Enter unique password.";
	
	public static final String LOGIN_SUCCESS_MESSAGE = "Logged in successfully!";
	public static final String LOGIN_ERROR_MESSAGE = "An error occurred while logging in!";
	public static final String LOGIN_ERROR_WRONG_USER_PASSWORD = "Wrong username or password!";
	public static final String LOGIN_ERROR_ACCOUNT_DOES_NOT_EXIST = "Account does not exist! Please click on Register Your Account link below to create new account.";
	public static final String LOGIN_ERROR_SERVER = "Server encountered an error";
	public static final String LOGIN_REQUIRED_CART_ERROR_MESSAGE = "Please login first to add product to cart!";
	public static final String LOGIN_REQUIRED_CART_VIEW_ERROR_MESSAGE = "Please login first to view cart!";

	// End Register Messages

	//start JSP Route
	public static final String LOGIN_PAGE = "/pages/login.jsp";
	public static final String REGISTER_PAGE = "/pages/register.jsp";
	public static final String HOME_PAGE = "/index.jsp";
	public static final String ADMIN_PAGE = "/pages/admin.jsp";
	public static final String PRODUCT_PAGE = "/WEB-INF/pages/product.jsp";
	public static final String CART_PAGE = "/WEB-INF/pages/cart.jsp";
	public static final String MESSAGE_PAGE = "/pages/message.jsp";
	public static final String ADMIN_MESSAGE_PAGE = "/pages/adminMessage.jsp";

	public static final String ADD_PRODUCT_PAGE = "/pages/addProduct.jsp";
	public static final String UPDATE_PRODUCT_PAGE = "/pages/updateProduct.jsp";
	public static final String PAGE_URL_HEADER = "pages/header.jsp";
	public static final String PAGE_URL_FOOTER = "pages/footer.jsp";
	public static final String USER_PROFILE_PAGE ="/pages/UserProfile.jsp";
	//end JSP Route
	
	// SERVLET
	public static final String ADMIN_SERVLET = "/admin";
	public static final String REGISTER_SERVLET = "/RegisterServlet";
	public static final String SERVLET_URL_LOGOUT = "/LogoutServlet";
	//end SERVLET route
	
	// start SQL Queries student_info
	public static final String GET_USERNAME = "SELECT COUNT(*) FROM users WHERE user_name = ?";
	public static final String GET_PHONE = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM users WHERE email = ?";
	// end SQL Queries
	
	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";
	
	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";
	// End: Normal Text
	
	/*****************************************
	 * cart string starts
	 ***************************************************************/
	public static final String CART_GET_DETAILS = "SELECT cart_total,user_id from cart WHERE cart_id=?";
	public static final String CART_SELECT_PRODUCT_DETAILS = "select ci.cart_item_id,ci.cart_id, p.product_id, p.product_name,p.product_price,p.product_image,ci.item_qty "
			+ "from cart_items as ci " + "left join cart as c on c.cart_id=ci.cart_id "
			+ "left join products as p on p.product_id=ci.product_id " + "WHERE ci.cart_id=?";

	public static final String CART_UPDATE_TOTAL = "UPDATE cart as ct set cart_total=( "
			+ "		select COALESCE(sum(ci.item_qty*p.product_price),0) as cart_total " + "		from cart_items as ci "
			+ "		left join products as p on p.product_id=ci.product_id " + " 	where ci.cart_id=ct.cart_id "
			+ ") where cart_id=?";
	public static final String CART_GET_TOTAL = "SELECT SUM(p.product_price) as cart_totaL " + "FROM cart_items as ci "
			+ "LEFT JOIN products as p on p.product_id=ci.product_id " + "WHERE ci.cart_iD=?";
	public static final String CART_ITEM_INSERT_SQL = "INSERT INTO cart_items(cart_id, product_id, item_qty) VALUES(?, ?, ?)";
	public static final String CART_ITEM_REMOVE_SQL = "DELETE FROM cart_items WHERE cart_item_id=?";
	public static final String CART_ITEM_UPDATE_QTY_SQL = "UPDATE cart_items SET item_qty=? WHERE cart_item_id=?";
	public static final String CART_ITEM_DELETE_ALL_SQL = "DELETE FROM cart_items WHERE cart_id=?";
	public static final String CART_DELETE_SQL = "DELETE FROM cart WHERE cart_id=?";

	public static final String PRODUCT_ID = "product_id";
	public static final String CART_ITEM_ID = "cart_item_id";
	public static final String ITEM_QTY = "item_qty";
	public static final String CART_CURRENCY_SYMBOL = "$";
	public static final String CART_ADD_PRODUCT_SUCCESS_MESSAGE = "Product added to cart successfully.";
	public static final String CART_ADD_PRODUCT_ERROR_MESSAGE = "Product could be added to cart.";
	public static final String CART_ITEM_REMOVE_SUCCESS_MESSAGE = "Product removed from cart successfully.";
	public static final String CART_ITEM_REMOVE_ERROR_MESSAGE = "Product could not be removed from cart.";
	public static final String CART_ITEM_UPDATE_QTY_SUCCESS_MESSAGE = "Product quantity updated successfully.";
	public static final String CART_ITEM_UPDATE_QTY_ERROR_MESSAGE = "Product quantity could be updated.";
	/******************************************
	 * cart string ends
	 ************************************************************/

	/******************************************
	 * order string start
	 ************************************************************/
	public static final String ORDER_INSERT_SQL = "INSERT INTO orders(order_id,order_date,user_id, order_status, order_amount) VALUES(? ,?, ?, ?, ?)";
	public static final String ORDER_PRODUCT_INSERT_SQL = "INSERT INTO orders_products(order_id,product_id, qty, line_total) VALUES(?,?, ?, ?)";
	public static final String ORDER_GET_HISTORY_SQL = "SELECT * FROM orders WHERE user_id=?";
	public static final String ORDER_CREATED_SUCCESS_MESSAGE = "Order created successfully.";
	public static final String ORDER_CREATED_FAILED_MESSAGE = "Unable to create order.";
	public static final String ORDER_HISTORY_PAGE = "/WEB-INF/pages/orderHistory.jsp";
	public static final String ORDER_GET_ALL_HISTORY = "SELECT * FROM orders";
	public static final String UPDATE_ORDER = "UPDATE orders SET order_status='DELIVERED' WHERE order_id=?";

	/******************************************
	 * order string ends
	 ************************************************************/
}
