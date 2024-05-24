package model;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderModel {
	private String orderId; 
	private Date orderDate;
	private int userId;
	private String orderStatus;
	private BigDecimal orderAmount;

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the orderAmount
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	
}
