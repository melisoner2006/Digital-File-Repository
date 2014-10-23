package section;

import java.sql.*;
import java.util.Vector;


public class SectionDA {
	static Connection aConnection;
	static Statement aStatement;

	static String classId, crn;
	static SectionModel aSection;
	static Vector<SectionModel> sections;
	
	public SectionDA(Connection c){
		 aConnection = c;
		 sections = new Vector<SectionModel>();
		 try{
			 aStatement = aConnection.createStatement();
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
	}
	
	public static boolean find(String crn){
		aSection = null;
		boolean isFull = false;
		String sqlQuery = "SELECT * FROM section where crn = '" + crn+ "';";
		ResultSet rs;
		try{
		rs = aStatement.executeQuery(sqlQuery);
		isFull = rs.next();
		}catch(SQLException e){
			e.printStackTrace();
		}
		 return isFull;
	}
	
	public static SectionModel get(String crn){
		aSection = null;
		String sqlQuery = "SELECT * FROM section WHERE crn = '"+crn+"';";
		try{
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			boolean isFull = rs.next();
			if(isFull){
				classId = rs.getString(1);
				crn = rs.getString(2);
				//create a section instance
				aSection = new SectionModel(classId, crn);
			}else{
				System.out.println("This section is not in the database.\n");
			}
			rs.close();
		}catch(SQLException E){
			E.printStackTrace();
		}
		System.out.println(aSection.getClassId() + " " + aSection.getCrn());
		return aSection;
	}
	
	public static boolean add(String c_id, String crn){
		String sqlInsert = "INSERT INTO section (c_id, crn) VALUES ('"
				+ c_id +"', '" + crn + "');";
		
		
			try {
				aStatement.execute(sqlInsert);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		
	}
	
	public static boolean delete(String crn){
		String sqlQuery = "DELETE FROM section  WHERE crn = '" + crn +  "';";
		
		
			try {
				aStatement.execute(sqlQuery);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		
	}
	
	public static boolean update(String crn, SectionModel newSection) {
		String sqlUpdate = "UPDATE section " + "SET c_id = '" + newSection.getClassId()
				+ "', " + "crn = '" + newSection.getCrn()
				+ "' WHERE crn = '" + crn + "' ;";
		try {
			aStatement.execute(sqlUpdate);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Vector<SectionModel> getAll(String c_id){
		String sqlQuery = "SELECT c_id, crn FROM section WHERE c_id = '"+c_id+"';";
		try{
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			boolean hasMore = rs.next();
			if(hasMore){
				while(hasMore){
					c_id = rs.getString(1);
					crn = rs.getString(2);
					
					aSection = new SectionModel(c_id, crn);
					sections.add(aSection);
					hasMore = rs.next();
				}
			}
		}catch(SQLException E){
			E.printStackTrace();
		}
		return sections;
	}
}
