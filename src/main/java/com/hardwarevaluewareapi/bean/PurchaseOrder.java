package com.hardwarevaluewareapi.bean;

import java.util.ArrayList;

public class PurchaseOrder {
	private String orderId;
	private String orderDate;
	private double totalAmount;
	private ArrayList<OrderItems> orderItemList;
	public PurchaseOrder(String orderId, String orderDate, double totalAmount, ArrayList<OrderItems> orderItemList) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.orderItemList = orderItemList;
	}
	public PurchaseOrder() {}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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
