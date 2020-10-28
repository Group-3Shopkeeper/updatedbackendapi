package com.hardwarevaluewareapi.bean;

public class OrderItems {
	private String orderItemId;
	private String productId;
	private String shopKeeperId;
	private String productName;
	private int qty;
	private String imageUrl;
	private double price;
	private double amount;

	public OrderItems() {
	}

	public OrderItems(String orderItemId, String productId, String shopKeeperId, String productName, int qty,
			String imageUrl, double price, double amount) {
		super();
		this.orderItemId = orderItemId;
		this.productId = productId;
		this.shopKeeperId = shopKeeperId;
		this.productName = productName;
		this.qty = qty;
		this.imageUrl = imageUrl;
		this.price = price;
		this.amount = amount;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getShopKeeperId() {
		return shopKeeperId;
	}

	public void setShopKeeperId(String shopKeeperId) {
		this.shopKeeperId = shopKeeperId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
