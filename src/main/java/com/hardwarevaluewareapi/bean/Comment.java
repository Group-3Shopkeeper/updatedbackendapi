package com.hardwarevaluewareapi.bean;

public class Comment {
	private String shopKeeperId;
	private String userId;
	private String commentId;
	private String userName;
	private long timestamp;
	private String productId;
	private String comment;
	private String userImageUrl;
	private Integer rating;
	public String getShopKeeperId() {
		return shopKeeperId;
	}
	public void setShopKeeperId(String shopKeeperId) {
		this.shopKeeperId = shopKeeperId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserImageUrl() {
		return userImageUrl;
	}
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Comment(String shopKeeperId, String userId, String commentId, String userName, long timestamp,
			String productId, String comment, String userImageUrl, Integer rating) {
		super();
		this.shopKeeperId = shopKeeperId;
		this.userId = userId;
		this.commentId = commentId;
		this.userName = userName;
		this.timestamp = timestamp;
		this.productId = productId;
		this.comment = comment;
		this.userImageUrl = userImageUrl;
		this.rating = rating;
	}
	public Comment() {
		super();
	}
	
	
}
