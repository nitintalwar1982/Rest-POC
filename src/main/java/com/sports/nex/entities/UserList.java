package com.sports.nex.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserList")
public class UserList implements Serializable{
	
	List<Users> list;

	public List<Users> getList() {
		return list;
	}

	public void setList(List<Users> list) {
		this.list = list;
	}
	
	

}
