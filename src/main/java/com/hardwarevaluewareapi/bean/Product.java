package com.hardwarevaluewareapi.bean;

import java.util.ArrayList;

public class Product {
	private String productId;
	private String categoryId;
	private String shopKeeperId;
	private String name;
	private double price;
	private double discount;
	private String brand;
	private int qtyInStock;
	private String imageUrl;
	private String description;
	private long timestamp;
	private String secondImageUrl;
	private String thirdImageurl;	
	public Product() {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.shopKeeperId = shopKeeperId;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.brand = brand;
		this.qtyInStock = qtyInStock;
		this.imageUrl = imageUrl;
		this.secondImageUrl = secondImageUrl;
		this.thirdImageurl = thirdImageurl;
		this.description = description;
		this.timestamp = timestamp;
	
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getShopKeeperId() {
		return shopKeeperId;
	}
	public void setShopKeeperId(String shopKeeperId) {
		this.shopKeeperId = shopKeeperId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getQtyInStock() {
		return qtyInStock;
	}
	public void setQtyInStock(int qtyInStock) {
		this.qtyInStock = qtyInStock;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSecondImageUrl() {
		return secondImageUrl;
	}
	public void setSecondImageUrl(String secondImageUrl) {
		this.secondImageUrl = secondImageUrl;
	}
	public String getThirdImageurl() {
		return thirdImageurl;
	}
	public void setThirdImageurl(String thirdImageurl) {
		this.thirdImageurl = thirdImageurl;
	}


	
}