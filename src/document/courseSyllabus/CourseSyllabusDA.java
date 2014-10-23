package document.courseSyllabus;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import document.document.BackupDocumentDA;
import document.document.BackupDocumentModel;
import document.document.DocumentDA;
import document.document.DocumentModel;

public class CourseSyllabusDA {
	static Connection aConnection;
	static Statement statement1 = null, statement2 = null;
	static PreparedStatement pstmt = null, pstmt2 = null;

	static String crn, documentName, cs_id;
	static CourseSyllabusModel aCs;

	public CourseSyllabusDA(Connection c) {
		aConnection = c;

	}

	public static boolean find(String crn) {
		String sqlQuery = "SELECT * FROM coursesyllabus WHERE crn = '"
				+ crn + "';";
		boolean inDB = false;
		ResultSet rs;

		try {
			statement1 = aConnection.createStatement();
			rs = statement1.executeQuery(sqlQuery);
			if (rs.next())
				inDB = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inDB;
	}
	
	public static boolean add(CourseSyllabusModel aModel) throws SQLException {
		DocumentModel aDocument = new DocumentModel(aModel.getCs_id(),
				"Course Syllabus", aModel.getDocumentName());

		boolean result = true;
		String sqlInsertMcs = "INSERT INTO coursesyllabus (crn, cs_id) VALUES('"
				+ aModel.getCrn() + "','" + aModel.getCs_id() + "');";

		try {
			aConnection.setAutoCommit(false);
			try {
				statement1 = aConnection.createStatement();
				statement1.executeUpdate(sqlInsertMcs);
			} finally {
				if (statement1 != null) {
					statement1.close();
				}
			}
			DocumentDA.add(aDocument);
			aConnection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			aConnection.rollback();
			result = false;
		} finally {
			if (statement1 != null) {
				statement1.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			aConnection.setAutoCommit(true);
		}

		return result;
	}

	public static boolean delete(String crn, String documentId) throws SQLException {
		String deleteFromCs = "DELETE FROM coursesyllabus WHERE crn = '"
				+ crn + "';";
		String deleteFromDocument = "DELETE FROM document WHERE d_id = '"
				+ documentId + "';";

		boolean result = true;
		try {
			aConnection.setAutoCommit(false);
			// *delete from the Master Course Syllabus Table*//
			try {
				statement1 = aConnection.createStatement();
				statement1.executeUpdate(deleteFromCs);
			} finally {
				if (statement1 != null) {
					statement1.close();
				}
			}
			BackupDocumentModel bd = new BackupDocumentModel(documentId);
			BackupDocumentDA.insert(bd);

			try {
				statement2 = aConnection.createStatement();
				statement2.executeUpdate(deleteFromDocument);
			} finally {
				if (statement2 != null) {
					statement2.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			aConnection.rollback();
			result = false;
		} finally {
			if (statement1 != null) {
				statement1.close();
			}
			if (statement2 != null) {
				statement2.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			aConnection.setAutoCommit(true);
		}

		return result;
	}

	public static boolean update(String crn, String documentName, String documentId) throws SQLException {
		BackupDocumentModel bd = new BackupDocumentModel(documentId);
		DocumentModel doc = new DocumentModel(documentId,
				"Course Syllabus", documentName);

		boolean result = true;
		try {
			aConnection.setAutoCommit(false);

			BackupDocumentDA.insert(bd);
			String updateDocumentTable = "UPDATE document SET document = ?, timestamp = ? WHERE d_id = ?";
			try {
				pstmt2 = aConnection.prepareStatement(updateDocumentTable);

				pstmt2.setBlob(1, doc.getFis(), doc.getDocumentLength());
				pstmt2.setTimestamp(2, doc.getTimestamp());
				pstmt2.setString(3, doc.getDocumentId());

				pstmt2.executeUpdate();
			} finally {
				if (pstmt2 != null) {
					pstmt2.close();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			aConnection.rollback();
			result = false;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (pstmt2 != null) {
				pstmt2.close();
			}
			aConnection.setAutoCommit(true);
		}

		return result;
	}

	public static String showAll(String documentId) throws SQLException {

		String selectFromDocuments = "SELECT documentType, timestamp FROM document "
				+ "WHERE d_id = '" + documentId + "';";

		String result = "";
		ResultSet rs = null;
		try {
			aConnection.setAutoCommit(false);
			try {
				statement1 = aConnection.createStatement();
				rs = statement1.executeQuery(selectFromDocuments);
				if (rs.next()) {
					result += rs.getString(1) + " " + rs.getTimestamp(2) + "\n";
				}
			} finally {
				statement1.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			aConnection.rollback();
		} finally {
			if (statement1 != null) {
				statement1.close();
			}
			aConnection.setAutoCommit(true);
		}

		return result;

	}

	public static boolean retrieveFromDocument(String crn, String documentId) {

		int BUFFER_SIZE = 100000;
		byte[] buffer = new byte[BUFFER_SIZE];// ?
		boolean result = true;
		String sqlQuery = "SELECT document FROM document WHERE d_id = '"
				+ documentId + "';";
		try {
			statement1 = aConnection.createStatement();
			ResultSet rs = statement1.executeQuery(sqlQuery);
			String fileName = "C://Users/oner1m/cs_" + crn + ".pdf";
			if (rs.next()) {
				Blob blob = rs.getBlob(1);
				InputStream is = blob.getBinaryStream();
				OutputStream os;
				try {
					os = new FileOutputStream(fileName);
					int bytesRead = 0;
					while ((bytesRead = is.read(buffer, 0, buffer.length)) > 0) {
						os.write(buffer, 0, bytesRead);
						os.flush();
					}
					os.close();
					is.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}

		return result;
	}

}
