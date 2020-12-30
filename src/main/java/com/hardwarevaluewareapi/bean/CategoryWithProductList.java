package com.hardwarevaluewareapi.bean;

import java.util.ArrayList;
import java.util.List;

public class CategoryWithProductList {
	private String categoryId;
	private String categoryName;
	private String imageUrl;
	private List<Product> list;
	
	public CategoryWithProductList() {
		
	}
	
	public CategoryWithProductList(String categoryId, String categoryName, String imageUrl, List<Product> list) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.imageUrl = imageUrl;
		this.list = list;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	
	
}

