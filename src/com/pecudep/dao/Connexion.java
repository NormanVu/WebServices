package com.pecudep.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
private String url="jdbc:mysql://localhost:3306/pecudep";
	
	private static String user="root";
	
	private static String pwd="";
	
	private static Connection con;

	public Connexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		try{
			con=DriverManager.getConnection(url,user,pwd);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		if(con==null){
			new Connexion();
			return con;
		}else{
			return con;
		}
	}
	
	/*public static void  main(String [] arg) throws SQLException {
		Connexion con=new Connexion();
		System.out.println("chargée!");
		con.getConnection();
		System.out.println("fin!");
	}*/
}
