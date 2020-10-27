package com.hardwarevaluewareapi.bean;

public class Shopkeeper {
	private String shopKeeperId;
	private String shopName;
	private String contactNumber;
	private String address;
	private String imageUrl;
	private String email;
	private String token;

	public Shopkeeper(String shopKeeperId, String shopName, String contactNumber, String address, String imageUrl,
		
		String email, String token) {
		super();
		this.shopKeeperId = shopKeeperId;
		this.shopName = shopName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.imageUrl = imageUrl;
		this.email = email;
		this.token = token;
	}
	public Shopkeeper() {
	}

	public String getShopKeeperId() {
		return shopKeeperId;
	}
	public void setShopKeeperId(String shopKeeperId) {
		this.shopKeeperId = shopKeeperId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
