package com.hardwarevaluewareapi.bean;

public class ProductName {
	
	private String categoryId;
	private String productName;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductName(String categoryId, String productName) {
		super();
		this.categoryId = categoryId;
		this.productName = productName;
	}
	public ProductName() {
		super();
	}
	
	

}
