package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnectivity {
	static String url = "jdbc:mysql://localhost:3306/reposys";
	static Connection aConnection;
	
	public static Connection initialize(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			aConnection = DriverManager.getConnection(url, "root","root");
			System.out.println("WELCOME TO THE DIGITAL REPOSITORY SYSTEM\n");
//			System.out.println("Database connectivity is successful!\n");
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException e){
			System.out.println(e);
		}
		return aConnection;
	}
	

	public static void terminate(){
		try{
			System.out.println("Database connectivity is closed.\n");
			aConnection.close();
		}catch(SQLException e){
			System.out.println(e);
		}
	}
}
