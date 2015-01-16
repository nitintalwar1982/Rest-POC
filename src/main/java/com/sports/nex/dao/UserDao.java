package com.sports.nex.dao;

import java.util.List;

import com.sports.nex.entities.Users;

public interface UserDao {
	
	public String save(Users p);
    
    public List<Users> list();

}
