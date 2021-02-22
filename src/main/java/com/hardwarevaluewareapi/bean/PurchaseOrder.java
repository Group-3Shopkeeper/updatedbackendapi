package com.hardwarevaluewareapi.bean;

import java.util.ArrayList;

public class PurchaseOrder {
	private String orderId;
	private String date;
	private double totalAmount;
	private String shippingStatus;
	private ArrayList<OrderItems> orderItemList;
	
	public PurchaseOrder(String orderId, String date, double totalAmount, ArrayList<OrderItems> orderItemList) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.totalAmount = totalAmount;
		this.orderItemList = orderItemList;
	}
	public PurchaseOrder() {}
	
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public ArrayList<OrderItems> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(ArrayList<OrderItems> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
}
