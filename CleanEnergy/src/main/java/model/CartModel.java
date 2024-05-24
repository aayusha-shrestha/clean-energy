package model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cartId;
	private int userId;
	private BigDecimal cartTotal;
	
	
	/**
	 * @return the cartId
	 */
	public String getCartId() {
		return cartId;
	}
	public CartModel() {
	}
	public CartModel(int userId, BigDecimal cartTotal) {
		this.userId = userId;
		this.cartTotal = cartTotal;
	}
	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the cartTotal
	 */
	public BigDecimal getCartTotal() {
		return cartTotal;
	}
	/**
	 * @param cartTotal the cartTotal to set
	 */
	public void setCartTotal(BigDecimal cartTotal) {
		this.cartTotal = cartTotal;
	}
		
}
