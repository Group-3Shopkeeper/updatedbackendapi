package com.hardwarevaluewareapi.bean;

public class OrderItems {
	private String orderItemId;
	private String productId;
	private String shopKeeperId;
	private String name;
	private int qty;
	private String imageUrl;
	private double price;
	private double total;

	public OrderItems() {
	}

	public OrderItems(String orderItemId, String productId, String shopKeeperId, String name, int qty,
			String imageUrl, double price, double total) {
		super();
		this.orderItemId = orderItemId;
		this.productId = productId;
		this.shopKeeperId = shopKeeperId;
		this.name = name;
		this.qty = qty;
		this.imageUrl = imageUrl;
		this.price = price;
		this.total = total;
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
		return name;
	}

	public void setProductName(String name) {
		this.name = name;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
