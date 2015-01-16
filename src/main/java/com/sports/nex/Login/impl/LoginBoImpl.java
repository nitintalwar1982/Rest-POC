package com.sports.nex.Login.impl;

import com.sports.nex.Login.LoginBo;

public class LoginBoImpl implements LoginBo {
	
	

	@Override
	public String login(String userName, String password) {

		if(userName == null || "".equals(userName)){
			return "Enter user name";
		} else if(userName == null || "".equals(userName)){
			return "Enter password";
		}
		return "success";
	}

}
