package com.example.demo.entity;

public class Department {


	private String dptId;
	private String dptName;
	
	/*
	 * Constructor
	 */
	public Department(String id, String name) {
		dptId = id;
		dptName = name;
	}
	
	public Department() {
		
	}
	
	/*
	 *  Getters & Setters
	 */
	public String getDptId() {
		return dptId;
	}
	public void setDptId(String dptId) {
		this.dptId = dptId;
	}
	public String getDptName() {
		return dptName;
	}
	public void setDptName(String dptName) {
		this.dptName = dptName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dptId == null) ? 0 : dptId.hashCode());
		result = prime * result + ((dptName == null) ? 0 : dptName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (dptId == null) {
			if (other.dptId != null)
				return false;
		} else if (!dptId.equals(other.dptId))
			return false;
		if (dptName == null) {
			if (other.dptName != null)
				return false;
		} else if (!dptName.equals(other.dptName))
			return false;
		return true;
	}
	
}
