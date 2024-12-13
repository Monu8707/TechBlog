package com.tech.blog.entities;


import java.sql.*;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private Timestamp timestamp;
    private String about;
    private String profile;
	public User(int id, String name, String email, String password, String gender, Timestamp timestamp, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.timestamp = timestamp;
		this.about = about;
	}
	public User() {
		
	}
	public User(String name, String email, String password, String gender,String about) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		
		this.about = about;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public void setDateTime(Timestamp timestamp) {
		// TODO Auto-generated method stub
		this.timestamp = timestamp;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
		    
    
}
