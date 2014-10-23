package classOperations;

import java.sql.*;
import java.util.Vector;


public class ClassDA {

	static Connection aConnection;
	static Statement aStatement;

	static String classId, className;

	static ClassModel aClass;
	static Vector<ClassModel> classes;

	public ClassDA(Connection c) {
		aConnection = c;
		classes = new Vector<ClassModel>();
		try {
			aStatement = aConnection.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean find(String c_id) {
		aClass = null;
		boolean isFull = false;
		String sqlQuery = "SELECT * FROM course WHERE c_id = '"
				+ c_id + "';";
		ResultSet rs;
		try {
			rs = aStatement.executeQuery(sqlQuery);
			isFull = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isFull;

	}

	public static ClassModel get(String c_id) {
		
		aClass = null;
		String sqlQuery = "SELECT * FROM course WHERE c_id = '"
				+ c_id + "';";
		try {
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			boolean isFull = rs.next();
			if (isFull) {
				classId = rs.getString(1);
				className = rs.getString(2);
			
				// create a class instance
				aClass = new ClassModel(classId, className);
			} else {
				// nothing was retrieved
				System.out.println("Class is not in the database.\n");
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		System.out.println(aClass.getClassId() + " "+aClass.getClassName());
		return aClass;

	}

	public static boolean add(String c_id, String c_name) {
		String sqlInsert = "INSERT INTO course (c_id, c_name"
				+ ") VALUES ('"
				+ c_id
				+ "', '"
				+ c_name + "');";
		
		try {
			aStatement.execute(sqlInsert);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean delete(String c_id) {
		String sqlQuery = "DELETE FROM course WHERE c_id = '"
				+ c_id + "';";
		try {
			aStatement.execute(sqlQuery);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean update(String c_id, ClassModel newClass) {
		String sqlUpdate = "UPDATE course " + "SET c_id = '" + newClass.getClassId()
				+ "', " + "c_name = '" + newClass.getClassName()
				+ "' WHERE c_id = '" + c_id + "' ;";
		try {
			aStatement.execute(sqlUpdate);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}