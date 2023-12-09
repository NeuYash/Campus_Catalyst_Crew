package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	   static final String DB_URL = "jdbc:mysql://localhost:3306/studentsdatabase1";
	   static final String USER = "root";
	   static final String PASS = "root@123";
	   
	   
	public static Connection conn(){
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		}catch (Exception e) {
			System.out.println("Cannot connect to database");
		}
		return null;
		
		
	}

}
