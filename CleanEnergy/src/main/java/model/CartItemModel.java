package model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItemModel implements Serializable{
	private int cartItemId;
	private String cartId;
	private int productId;
	private String productName;
	private String productImage;
	private BigDecimal productPrice;
	private int itemQty;
	
	
	public CartItemModel() {
	}
	public CartItemModel(String cartId, int productId, int itemQty) {
		this.cartId = cartId;
		this.productId = productId;
		this.itemQty = itemQty;
	}
	/**
	 * @return the cartId
	 */
	public String getCartId() {
		return cartId;
	}
	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return the itemQty
	 */
	public int getItemQty() {
		return itemQty;
	}
	/**
	 * @param itemQty the itemQty to set
	 */
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the cartItemId
	 */
	public int getCartItemId() {
		return cartItemId;
	}
	/**
	 * @param cartItemId the cartItemId to set
	 */
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	/**
	 * @return the productPrice
	 */
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	/**
	 * @return the productImage
	 */
	public String getProductImage() {
		return productImage;
	}
	/**
	 * @param productImage the productImage to set
	 */
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
}
