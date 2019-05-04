package com.kishan.SpringBootRestApi.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Post {

	private Integer postId;
	@Size(min=2,message = "post details cannot be less than 2 chars")
	private String postDetails;
	@NotNull(message = "post cannot exist without user")
	private String userName;
	public Post() {
		super();
	}
	public Post(int postId,String postDetails,
			String userName) {
		super();
		this.postId = postId;
		this.postDetails = postDetails;
		this.userName = userName;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostDetails() {
		return postDetails;
	}
	public void setPostDetails(String postDetails) {
		this.postDetails = postDetails;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
