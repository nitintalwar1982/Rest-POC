package com.sports.nex.entities;

import java.io.Serializable;

public class League implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6586598991801163039L;

	private String profileName;
	
	private String description;
	
	private String pocFName;
	
	private String pocLName;
	
	private String pocPhone;
	
	private String location;
	
	
	public League() {
		super();
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPocFName() {
		return pocFName;
	}

	public void setPocFName(String pocFName) {
		this.pocFName = pocFName;
	}

	public String getPocLName() {
		return pocLName;
	}

	public void setPocLName(String pocLName) {
		this.pocLName = pocLName;
	}

	public String getPocPhone() {
		return pocPhone;
	}

	public void setPocPhone(String pocPhone) {
		this.pocPhone = pocPhone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	

}
