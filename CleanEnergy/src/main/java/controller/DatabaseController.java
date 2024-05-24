package controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import model.CartItemModel;
import model.CartModel;
import model.OrderModel;
import model.PasswordEncryptionWithAes;
import model.StudentModel;
import model.UserModel;
import model.ProductModel;
import util.OrderStatus;
import util.StringUtils;

public class DatabaseController {
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/clean_energy_db";
		String user = "root"; 
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
	}
	
	public int getUserLoginInfo(String username, String password) {

		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String userDb = rs.getString("user_name");
				String passwordDb = rs.getString("password");
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);

				if (decryptedPwd != null && userDb.equals(username) && decryptedPwd.equals(password)) {
					return rs.getInt("id");
				} else {
					return 0;
				}
			} else {
				return 0;
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public UserModel getUserInfo(String userName) {
		UserModel userModel = new UserModel();
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, userName);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				userModel.setUsername(rs.getString("user_name"));
				userModel.setUserRole(rs.getString("user_role"));				
			}
			
			return userModel;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return userModel;
		}
	}

	
	//fetch data from db
		public UserModel getUserByUsername(String username) {
	        UserModel user = null;
	        try (Connection con = getConnection()) {
	            PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_STUDENT_INFO);
	            st.setString(1, username);
	            ResultSet rs = st.executeQuery();
	            if (rs.next()) {
	                user = new UserModel();
	                user.setUsername(rs.getString("user_name"));    //database and yesko name same
	                user.setFirstName(rs.getString("first_name"));
	                user.setLastName(rs.getString("last_name"));
	                user.setGender(rs.getString("gender"));
	                user.setEmail(rs.getString("email"));
	                user.setPhoneNumber(rs.getString("phone_number"));
	                user.setPassword(rs.getString("password"));
	                user.setImage(rs.getString("image"));
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }
		 
		
		//userModel
		public boolean updateUser(UserModel updatedUser) {
			boolean value = false;
			try (Connection con = getConnection()){
				PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_USER);
				st.setString(1, updatedUser.getFirstName());
				st.setString(2, updatedUser.getLastName());
				st.setString(3, updatedUser.getGender());
		        st.setString(4, updatedUser.getEmail());
		        st.setString(5, updatedUser.getPhoneNumber());
		        st.setString(6, updatedUser.getPassword());
		        st.setString(7, updatedUser.getImage());
		        st.setString(8, updatedUser.getUsername()); // WHERE clause
		        
		        st.executeUpdate();
		        value = true;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return value;
			
		}
		
		
	public int addStudent(UserModel userModel) {
		try(Connection con = getConnection()){
		
			//check for existing username
			PreparedStatement checkUsernameSt = con.prepareStatement(StringUtils.GET_USERNAME);
			checkUsernameSt.setString(1, userModel.getUsername());
			ResultSet checkUsernameRs = checkUsernameSt.executeQuery();
			checkUsernameRs.next();
			if(checkUsernameRs.getInt(1) >0){
				return -2;   //username already exists
			}
		
			
			// checking existing phone number
			PreparedStatement checkPhoneSt = con.prepareStatement(StringUtils.GET_PHONE);
			checkPhoneSt.setString(1, userModel.getPhoneNumber());
			ResultSet checkPhoneRs = checkPhoneSt.executeQuery();
			checkPhoneRs.next();
			if (checkPhoneRs.getInt(1) >0){
				return -4;   //phone number already exists
			}
			
			//checking existing email
			PreparedStatement checkEmailSt = con.prepareStatement(StringUtils.GET_EMAIL);
			checkEmailSt.setString(1, userModel.getEmail());
			ResultSet checkEmailRs = checkEmailSt.executeQuery();
			checkEmailRs.next();
			if(checkEmailRs.getInt(1) >0){
				return -3;   //email already exists
			}
			
			//checking existing password
			PreparedStatement checkPasswordSt = con.prepareStatement(StringUtils.GET_PASSWORD);
			checkPasswordSt.setString(1, userModel.getUsername());
			ResultSet checkPasswordRs = checkPasswordSt.executeQuery();
			if (checkPasswordRs.next()) {
			    String hashedPasswordFromDb = checkPasswordRs.getString("password");

			    // Encrypt the provided password for comparison
			    String hashedPasswordProvided = PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword());

			    // Compare the hashed passwords
			    if (hashedPasswordFromDb.equals(hashedPasswordProvided)) {
			        return -5;   //password already exists
			    }
			}
			
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_STUDENT);
			st.setString(1, userModel.getUsername());
			st.setString(2, userModel.getFirstName());
			st.setString(3, userModel.getLastName());
			st.setString(4, userModel.getGender());
			st.setString(5, userModel.getEmail());
			st.setString(6, userModel.getPhoneNumber());
			st.setString(7, PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword()));
			st.setString(8, userModel.getImage());
			
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException| ClassNotFoundException e) {
			e.printStackTrace(); //print the caught exception to the console.
			return -1;
		}
	}
	
	public int getStudentLoginInfo(String username, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_STUDENT_INFO);
			st.setString(1, username);
			ResultSet result = st.executeQuery();
			
			// Check if there's a record returned from the query
	        if (result.next()) {
	            // Get the username from the database
	            String userDb = result.getString("user_name");

	            // Get the password from the database
	            String encryptedPwd = result.getString("password");

	            String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, username);
	            // Check if the username and password match the credentials from the database
	            if (decryptedPwd!=null && userDb.equals(username)
	            		&& decryptedPwd.equals(password)) {
	                return 1;
	            } else {
	                return 0;
	            }
	        } else {
	            return -1;
	        }
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -2;
		}
	}
	
	public ArrayList<StudentModel> getAllStudentsInfo(){
		try(Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_STUDENT_INFO);
			ResultSet rs = st.executeQuery();
			
			ArrayList<StudentModel> students = new ArrayList<>();
			
			while(rs.next()) {
				StudentModel student = new StudentModel();
				
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setDob(rs.getDate("dob").toLocalDate());
				student.setGender(rs.getString("gender"));
				student.setEmail(rs.getString("email"));
				student.setPhoneNumber(rs.getString("phone_number"));
				student.setSubject(rs.getString("subject"));
				student.setUsername(rs.getString("user_name"));
				
				students.add(student);
			}
			
			return students;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	public ArrayList<ProductModel> getFeaturedProducts() {
		ArrayList<ProductModel> products = new ArrayList<>();
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.PRODUCT_SEARCH_FEATURED);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				ProductModel product = new ProductModel();
				product.setProductId(resultSet.getInt("product_id"));
				product.setProductName(resultSet.getString("product_name"));
				product.setProductDesc(resultSet.getString("product_desc"));
				product.setCategoryId(resultSet.getInt("category_id"));
				product.setProductPrice(resultSet.getBigDecimal("product_price"));
				product.setStockQty(resultSet.getInt("stock_qty"));
				product.setStockLevel(resultSet.getString("stock_level"));
				product.setImageUrlFromDB(resultSet.getString("product_image"));
				products.add(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public int addProduct(ProductModel productModel) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_PRODUCT);
			st.setString(1, productModel.getProductName());
			st.setString(2, productModel.getProductDesc());
			st.setInt(3, productModel.getCategoryId());
			st.setBigDecimal(4, productModel.getProductPrice());
			st.setInt(5, productModel.getStockQty());
			st.setString(6, productModel.getStockLevel());
			st.setString(7, productModel.getProductImgUrlFromPart());
			
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException| ClassNotFoundException e) {
			e.printStackTrace(); //print the caught exception to the console.
			return -1;
		}
	}
	
	public ProductModel getProductInfoById(int id){
		ProductModel product = new ProductModel();
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_PRODUCT_INFO_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductDesc(rs.getString("product_desc"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductPrice(rs.getBigDecimal("product_price"));
				product.setStockQty(rs.getInt("stock_qty"));
				product.setStockLevel(rs.getString("stock_level"));
				product.setImageUrlFromDB(rs.getString("product_image"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public int updateProduct(ProductModel productModel) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_PRODUCT);
			st.setString(1, productModel.getProductName());
			st.setString(2, productModel.getProductDesc());
			st.setInt(3, productModel.getCategoryId());
			st.setBigDecimal(4, productModel.getProductPrice());
			st.setInt(5, productModel.getStockQty());
			st.setString(6, productModel.getStockLevel());
			st.setString(7, productModel.getProductImgUrlFromPart());
			st.setInt(8, productModel.getProductId());
			
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException| ClassNotFoundException e) {
			e.printStackTrace(); //print the caught exception to the console.
			return -1;
		}
	}
	
	public int deleteProduct(int productId) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_PRODUCT);
			st.setInt(1, productId);
			
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException| ClassNotFoundException e) {
			e.printStackTrace(); //print the caught exception to the console.
			return -1;
		}
	}
	
	public ArrayList<OrderModel> getOrders() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.ORDER_GET_ALL_HISTORY);
			ResultSet result = stmt.executeQuery();

			ArrayList<OrderModel> orders = new ArrayList<OrderModel>();

			while (result.next()) {
				OrderModel order = new OrderModel();
				order.setOrderId(result.getString("order_id"));
				order.setOrderDate(result.getDate("order_date"));
				order.setUserId(result.getInt("user_id"));
				order.setOrderStatus(result.getString("order_status"));
				order.setOrderAmount(result.getBigDecimal("order_amount"));
				 

				orders.add(order);
			}
			return orders;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	* This method returs ArrayList of a ProductModel by search and price
	* against a database.
	*
	* @param search The search provided by the user attempting to search.
	* @param price The search provided by the user attempting to search.
	* @return An ArrayList of ProductModel
	* @throws SQLException           if a database access error occurs.
	* @throws ClassNotFoundException if the JDBC driver class is not found.
	*/
	public ArrayList<ProductModel> getAllProducts(String search, String price) {
		ArrayList<ProductModel> products = new ArrayList<>();
		// Try-catch block to handle Exceptions
		try (Connection con = getConnection()) {
			// Prepare a statement using the predefined query for product search
			PreparedStatement st = con.prepareStatement(StringUtils.PRODUCT_SEARCH_ALL);
			// Set the search in the first,second parameter of the prepared statement
			st.setString(1, "%" + search + "%");
		    st.setString(2, "%" + search + "%");
		    // Set the price in the third,fourth parameter of the prepared statement			
		    st.setString(3, price);
		    st.setString(4, price);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				ProductModel product = new ProductModel();
				product.setProductId(resultSet.getInt("product_id"));
				product.setProductName(resultSet.getString("product_name"));
				product.setProductDesc(resultSet.getString("product_desc"));
				product.setCategoryId(resultSet.getInt("category_id"));
				product.setProductPrice(resultSet.getBigDecimal("product_price"));
				product.setStockQty(resultSet.getInt("stock_qty"));
				product.setStockLevel(resultSet.getString("stock_level"));
				product.setImageUrlFromDB(resultSet.getString("product_image"));
				products.add(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	

	/**
	* This method returns cartId of logged in user
	* against a database.
	*
	* @param userId The userId that is obtained by the logged in user.
	* @return An cartId of String if cart exists , if not creates new cart and then return the cartId
	* @throws Exception
	*/
	public String getCart(int userId) {
		try (Connection con = getConnection()) {
			// check for cart
			String cartId = UUID.randomUUID().toString();
			PreparedStatement checkCartSt = con.prepareStatement(StringUtils.SELECT_CART);
			checkCartSt.setInt(1, userId);
			ResultSet checkCartRs = checkCartSt.executeQuery();
			checkCartRs.next();
			if (checkCartRs.getInt(1) > 0) {
				return checkCartRs.getString("cart_id"); // cart already exists
			} else {
				PreparedStatement st = con.prepareStatement(StringUtils.INSERT_CART);
				st.setString(1, cartId);
				st.setInt(2, userId);
				st.setBigDecimal(3, BigDecimal.ZERO);

				int result = st.executeUpdate();
				return result > 0 ? cartId : "";
			}

		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	* This method returns Total of Cart by cartId
	* against a database.
	*
	* @param cartId The cartId
	* @return An Object of CartModel
	* @throws Exception
	*/
	public BigDecimal getCartTotal(String cartId) {

		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_GET_DETAILS);
			st.setString(1, cartId);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getBigDecimal("cart_total");

			} else {
				return BigDecimal.ZERO;
			}

		} catch (SQLException | ClassNotFoundException ex) {
			return BigDecimal.ZERO;
		}

	}
	
	/**
	* This method returns cart details of logged in user
	* against a database.
	*
	* @param cartId The cartId 
	* @return An Object of CartModel
	* @throws Exception
	*/
	public CartModel getCartDetails(String cartId) {

		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_GET_DETAILS);
			st.setString(1, cartId);
			ResultSet rs = st.executeQuery();
			CartModel cart = new CartModel();
			while (rs.next()) {
				cart.setCartTotal(rs.getBigDecimal("cart_total"));
				cart.setUserId(rs.getInt("user_id"));
			}
			return cart;

		} catch (SQLException | ClassNotFoundException ex) {
			return null;
		}

	}
	
	/**
	* This method returns ArrayList of Cart CartItemModel for logged in user by userId
	* against a database.
	*
	* @param cartId The cartId
	* @return An Object of CartModel
	* @throws Exception
	*/
	public ArrayList<CartItemModel> getCartItems(String cartId) {
		ArrayList<CartItemModel> cartItems = new ArrayList<>();
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_SELECT_PRODUCT_DETAILS);
			st.setString(1, cartId);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				CartItemModel cartItem = new CartItemModel();
				cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
				cartItem.setCartId(resultSet.getString("cart_id"));
				cartItem.setProductId(resultSet.getInt("product_id"));
				cartItem.setProductName(resultSet.getString("product_name"));
				cartItem.setProductPrice(resultSet.getBigDecimal("product_price"));
				cartItem.setItemQty(resultSet.getInt("item_qty"));
				cartItem.setProductImage(resultSet.getString("product_image"));
				cartItems.add(cartItem);
			}
		} catch (Exception e) {
			return new ArrayList<>();
		}
		return cartItems;

	}

	/**
	* This method adds product i.ie CartItemModel object 
	* against a database.
	*
	* @param cartItem The CartItemModel object
	* @return An Integer that denotes 1 for success ,0 for failure and -1 for errors due to exception
	* @throws Exception
	*/
	public int addProductToCart(CartItemModel cartItem) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_ITEM_INSERT_SQL);
			st.setString(1, cartItem.getCartId());
			st.setInt(2, cartItem.getProductId());
			st.setInt(3, cartItem.getItemQty());
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (Exception e) {
			return -1;
		}
	}
	
	/**
	* This method removes product from cart by cartItemId
	* against a database.
	*
	* @param cartItemId of cartItemsModel
	* @return An Integer that denotes 1 for success ,0 for failure and -1 for errors due to exception
	* @throws Exception
	*/
	public int removeProductFromCart(int cartItemId) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_ITEM_REMOVE_SQL);
			st.setInt(1, cartItemId);

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	* This method updates quantity for product for cartItem
	* against a database.
	*
	* @param cartItemQty An Interfer that denotes quantity of product
	* @param cartItemId of Integer
	* @return An Integer that denotes 1 for success ,0 for failure and -1 for errors due to exception
	* @throws Exception
	*/
	public int updateProductQtyCart(Integer cartItemQty, int cartItemId) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_ITEM_UPDATE_QTY_SQL);
			st.setInt(1, cartItemQty);
			st.setInt(2, cartItemId);

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	* This method updates cart total for cart
	* against a database.
	*
	* @param cartId of String 
	* @return An Integer that denotes 1 for success ,0 for failure and -1 for errors due to exception
	* @throws Exception
	*/
	public int updateCartTotal(String cartId) {

		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_UPDATE_TOTAL);
			st.setString(1, cartId);

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (Exception e) {
			return -1;
		}
	}
	
	/**
	* This method creates order from cart that is saved in database to order and orderProduct
	* against a database.
	*
	* @param cartId of String 
	* @return An Integer that denotes 1 for success ,0 for failure and -1 for errors due to exception
	* @throws Exception
	*/
	public int createOrder(String cartId) {
		String orderId = UUID.randomUUID().toString();

		ArrayList<CartItemModel> cartItems = getCartItems(cartId);
		CartModel cart = getCartDetails(cartId);

		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.ORDER_INSERT_SQL);

			st.setString(1, orderId);
			st.setDate(2, new Date(System.currentTimeMillis()));
			st.setInt(3, cart.getUserId());
			st.setString(4, OrderStatus.PENDING.toString());
			st.setBigDecimal(5, cart.getCartTotal());

			int result = st.executeUpdate();
			if (result > 0) {
				for (CartItemModel cartItem : cartItems) {
					BigDecimal lineTotal = cartItem.getProductPrice()
							.multiply(BigDecimal.valueOf(cartItem.getItemQty()));
					st = con.prepareStatement(StringUtils.ORDER_PRODUCT_INSERT_SQL);
					st.setString(1, orderId);
					st.setInt(2, cartItem.getProductId());
					st.setInt(3, cartItem.getItemQty());
					st.setBigDecimal(4, lineTotal);

					result = st.executeUpdate();
				}
				// delete all items from cart
				st = con.prepareStatement(StringUtils.CART_ITEM_DELETE_ALL_SQL);
				st.setString(1, cartId);
				st.executeUpdate();

				// delete cart
				st = con.prepareStatement(StringUtils.CART_DELETE_SQL);
				st.setString(1, cartId);
				result = st.executeUpdate();

				return result;
			} else {
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	* This method returns List of OrderModel as order history
	* against a database.
	*
	* @param userId of String 
	* @return ArrayList of OrderModel
	* @throws Exception
	*/
	public ArrayList<OrderModel> getOrderHistory(int userId) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.ORDER_GET_HISTORY_SQL);
			stmt.setInt(1, userId);
			ResultSet result = stmt.executeQuery();

			ArrayList<OrderModel> orders = new ArrayList<OrderModel>();

			while (result.next()) {
				OrderModel order = new OrderModel();
				order.setOrderId(result.getString("order_id"));
				order.setOrderDate(result.getDate("order_date"));
				order.setOrderStatus(result.getString("order_status"));
				order.setOrderAmount(result.getBigDecimal("order_amount"));
				
				orders.add(order);
			}
			return orders;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
