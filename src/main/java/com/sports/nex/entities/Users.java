package com.sports.nex.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author nitin talwar
 *
 */
@XmlRootElement
@Entity
@Table(name="USERS")
public class Users implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3666021181012954216L;

	@Id
    @Column(name="UserPID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	private Integer userPID;
	
	@Column(name="userTypePID")
	private Integer userTypePID;
	
	@Column(name="userEmailAddress")
	private String userEmailAddress;
	
	@Column(name="userPassword")
	private String userPassword;
	
	@Column(name="creationDate")
	private Timestamp creationDate;
	
	@Column(name="lastModifiedDate")
	private Timestamp lastModifiedDate;
	
	@Column(name="lastModifiedBy")
	private String lastModifiedBy;
	
	public Users() {
		super();
	}
	
	public Users(Integer userPID, Integer userTypePID, String userEmailAddress,
			String userPassword, Timestamp creationDate,
			Timestamp lastModifiedDate, String lastModifiedBy) {
		super();
		this.userPID = userPID;
		this.userTypePID = userTypePID;
		this.userEmailAddress = userEmailAddress;
		this.userPassword = userPassword;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
	}

	public Integer getUserPID() {
		return userPID;
	}

	public void setUserPID(Integer userPID) {
		this.userPID = userPID;
	}

	public Integer getUserTypePID() {
		return userTypePID;
	}

	public void setUserTypePID(Integer userTypePID) {
		this.userTypePID = userTypePID;
	}

	public String getUserEmailAddress() {
		return userEmailAddress;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	
}
