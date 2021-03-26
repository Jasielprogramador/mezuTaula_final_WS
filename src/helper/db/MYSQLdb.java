package helper.db;

import java.net.SocketAddress;

import java.sql.*;
import java.util.ArrayList;

import helper.info.*;
import java.util.*;

public class MYSQLdb {
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String user = "admin";
	private String passwd = "euiti2019";
	private String driver = "com.mysql.jdbc.Driver";
	
	private Connection conn;
	
	   public MYSQLdb() {
		   try {
		       Class.forName(this.driver).newInstance();
		       this.conn = DriverManager.getConnection(this.url,this.user,this.passwd);
		       System.out.println("Connected to DB!!!");
		   } catch(Exception e) {
		        System.out.println("Exception: " + e.getMessage());
		   }
	   }
	   
	   public void setUserInfo(String email, String password, String username) {
		   String query = "INSERT INTO mezutaula.users VALUES ('" + email + "', '" + password + "', '" + username + "');";
		   System.out.println("\tDB query: " + query);
		   try {
		         Statement st = this.conn.createStatement();
		         st.executeUpdate(query); 
		         System.out.println("Query successful!!!");
		       } catch(Exception e) {
		          System.out.println("Exception: " + e.getMessage());
		      }

	   }
	   public String getUsername(String email, String password) {
		   String query = "SELECT username FROM mezutaula.users WHERE email='" + email + "' AND password='" + password + "';";
		   System.out.println("\tDB query: " + query);
		   String username = null;
		   try {
		         Statement st = this.conn.createStatement();
		         ResultSet res = st.executeQuery(query); 
		         while(res.next()) {
		            username = res.getString("username");
		          }
		         System.out.println("\tQuery successful!!!");
		       } catch(Exception e) {
		          System.out.println("Exception: " + e.getMessage());
		      }
		   return username;
	   }
	   
	   public void setMessageInfo(String message, String username) {
		   String query = "INSERT INTO mezutaula.messages VALUES ('0', '" + message + "', '" + username + "');";
		   System.out.println("\tDB query: " + query);
		   try {
		         Statement st = this.conn.createStatement();
		         st.executeUpdate(query); 
		         System.out.println("\tQuery successful!!!");
		       } catch(Exception e) {
		          System.out.println("Exception: " + e.getMessage());
		      }
	   }
	   
	   public ArrayList<MessageInfo> getAllMessages() {
		      String query = "SELECT * FROM mezutaula.messages;";
		      System.out.println("\tDB query: " + query);
		      ArrayList<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();
		      
		      try {
		         Statement st = this.conn.createStatement();
		          ResultSet res = st.executeQuery(query); 
		          while(res.next()) {
		             messageInfoList.add(new MessageInfo(res.getString("message"), res.getString("username")));
		          }   
		          System.out.println("\tQuery successful!!!");
		       } catch(Exception e) {
		          System.out.println("Exception: " + e.getMessage());
		      }
		      return messageInfoList;
		   }

}
