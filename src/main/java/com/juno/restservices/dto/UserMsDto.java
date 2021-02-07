package com.juno.restservices.dto;

public class UserMsDto {
	private Long id;
	private String userName;
	private String emailAddress;
	private String roleName;

	public UserMsDto(Long id, String userName, String emailAddress,String roleName) {
		super();
		this.id = id;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.roleName=roleName;
	}

	public UserMsDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
