package com.hardwarevaluewareapi.bean;

public class User {
	private String userId;
	private String name;
	private String address;
	private String mobile;
	private String email;
	private String imageUrl;
	private String token;

	public User(String userId, String name, String address, String mobile, String email, String imageUrl,
			String token) {
		super();
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
		this.imageUrl = imageUrl;
		this.token = token;
	}

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
