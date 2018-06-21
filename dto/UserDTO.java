package com.example.demo.dto;

import java.util.Date;

public class UserDTO {
	
	private String userId;
	private String userName;
	private String firstName;
	private String lastName;
	private Date creationDate;
	
	private String dptId;
	private String dptName;
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the dptId
	 */
	public String getDptId() {
		return dptId;
	}
	/**
	 * @param dptId the dptId to set
	 */
	public void setDptId(String dptId) {
		this.dptId = dptId;
	}
	/**
	 * @return the dptName
	 */
	public String getDptName() {
		return dptName;
	}
	/**
	 * @param dptName the dptName to set
	 */
	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	
	
}
