package model;

import java.math.BigDecimal;

public class OrderProductModel {
	private int orderId;
	private int  productId;
	private int qty;
	private BigDecimal lineTotal;
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}
	/**
	 * @return the lineTotal
	 */
	public BigDecimal getLineTotal() {
		return lineTotal;
	}
	/**
	 * @param lineTotal the lineTotal to set
	 */
	public void setLineTotal(BigDecimal lineTotal) {
		this.lineTotal = lineTotal;
	}
	
}
