package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class UserDA {

	static Connection aConnection;
	static Statement aStatement;

	static String username;
	static String globalId;
	static String password;
	static String authority;

	static UserModel aUser;
	static Vector<UserModel> users;
	
	public UserDA(Connection c){
		aConnection = c;
		users = new Vector<UserModel>();
		try {
			if(aConnection != null){
				aStatement = aConnection.createStatement();
			}else{
				System.out.println("Access denied.\nPlease check your username and password or contact your system admin.");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static UserModel find(String globalId) {
		aUser = null;
		String sqlQuery = "SELECT * FROM usertable WHERE globalId = '"
				+ globalId + "';";
		// execute the query
		try {
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			boolean isEmpty = rs.next();
			if (isEmpty) {
				username = rs.getString(1);
				globalId = rs.getString(2);
				password = rs.getString(3);
				authority = rs.getString(4);

				// create a user instance
				aUser = new UserModel(username, globalId, authority);
			} else {
				// nothing was retrieved
				System.out.println("User is not in the database.\n");
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return aUser;
	}


}
