package com.tech.blog.dao;

import java.sql.*;

import com.tech.blog.entities.User;

public class UserDao {
    
	private Connection con;
	

	public UserDao(Connection con) {
		this.con = con;
	}
	//method to insert user to data base:
	
	public boolean saveUser(User user)
	{
		boolean f=false;
		try {
			
			//user --> database
			String query="insert into user(name,email,password,gender,about) values(?,?,?,?,?)";
			
			PreparedStatement psmt=this.con.prepareStatement(query);
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getEmail());
			psmt.setString(3, user.getPassword());
			psmt.setString(4, user.getGender());
			psmt.setString(5, user.getAbout());
			
			psmt.executeUpdate();
			f=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	//get user by useremail amd userpassword
	public User getUSerByEmailAndPassword(String email,String password) {
		User user=null;
		
		try {
			String query="select * from user where email=? and password=?";
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1, email);
			pstm.setString(2, password);
			
			ResultSet set=pstm.executeQuery();
			
			if(set.next()) {
				user=new User();
				// data from db
				String name=set.getString("name");
				user.setName(name);
				
				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setGender(set.getString("gender"));
				user.setAbout(set.getString("about"));
				user.setDateTime(set.getTimestamp("rdate"));
				user.setProfile(set.getString("profile"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return user;
	}
	
	public boolean updateUser(User user) {
		
		boolean f=false;
		
		try{
			
			String query="update user set name=? , email=? , password=? , gender=? , about=? , profile=? where id=?";
			PreparedStatement p=con.prepareStatement(query);
			
			p.setString(1, user.getName());
			p.setString(2, user.getEmail());
			p.setString(3, user.getPassword());
			p.setString(4, user.getGender());
			p.setString(5, user.getAbout());
			p.setString(6, user.getProfile());
			p.setInt(7, user.getId());
			p.executeUpdate();
			f=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public User getUserByUserId(int userId) {
		User user=null;
		String q="select * from user where id=?";
		try {
			PreparedStatement ps=this.con.prepareStatement(q);
			ps.setInt(1, userId);
			ResultSet set=ps.executeQuery();
			if(set.next()) {
				user=new User();
				// data from db
				String name=set.getString("name");
				user.setName(name);
				
				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setGender(set.getString("gender"));
				user.setAbout(set.getString("about"));
				user.setDateTime(set.getTimestamp("rdate"));
				user.setProfile(set.getString("profile"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
		
	}
	
}
