package com.sports.nex;

	import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sports.nex.dao.UserDao;
import com.sports.nex.entities.Users;
	 
	 
	public class SpringHibernateMain {
	 
	    public static void main(String[] args) {
	 
	    	
	    	/*Context initContext = new InitialContext();
	    	Context envContext  = (Context)initContext.lookup("java:/comp/env");
	    	DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
	    	Connection conn = ds.getConnection();
	    	*/
	        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	         
	        UserDao UserDao = context.getBean(UserDao.class);
	         
	        Users user = new Users();
	        user.setUserEmailAddress("nitintalwar1982@gmail.com");
	        user.setUserPassword("tetst");
	        user.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
	        user.setLastModifiedBy("nitin talwar");
	        user.setLastModifiedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
	        user.setUserTypePID(0);
	        
	        UserDao.save(user);
	         
	        System.out.println("User::"+user);
	         
	        List<Users> list = UserDao.list();
	         
	        for(Users p : list){
	            System.out.println("User List::"+p);
	        }
	        //close resources
	        context.close();    
	    }
	}