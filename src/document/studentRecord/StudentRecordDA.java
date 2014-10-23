package document.studentRecord;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import document.document.BackupDocumentDA;
import document.document.BackupDocumentModel;
import document.document.DocumentDA;
import document.document.DocumentModel;

public class StudentRecordDA {
	static Connection aConnection;
	static Statement statement1 = null, statement2 = null;
	static PreparedStatement pstmt = null, pstmt2 = null;

	static String crn, documentName, assignmentType, assignmentNo, caseType, studentRecordId;
	static StudentRecordModel studentRecord;

	public StudentRecordDA(Connection c) {
		aConnection = c;

	}

	public static boolean find(String documentId) {
		String sqlQuery = "SELECT * FROM studentrecord WHERE sr_id = '"+documentId+"';";
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

	public static boolean add(StudentRecordModel aModel) throws SQLException {
		DocumentModel aDocument = new DocumentModel(aModel.getSr_id(),
				"Student Record", aModel.getDocumentName());

		boolean result = true;
		String sqlInsertCA = "INSERT INTO studentrecord (crn, a_type, a_no, caseType, sr_id) VALUES('"
				+ aModel.getCrn() + "','" 
				+ aModel.getAssignmentType() + "','" 
				+ aModel.getAssignmentNo() + "','"
				+ aModel.getCaseType() + "','"
				+ aModel.getSr_id() + "');";

		try {
			aConnection.setAutoCommit(false);
			try {
				statement1 = aConnection.createStatement();
				statement1.executeUpdate(sqlInsertCA);
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

	public static boolean delete(String documentId) throws SQLException {
		String deleteFromCs = "DELETE FROM studentrecord WHERE sr_id = '"
				+ documentId + "';";
		String deleteFromDocument = "DELETE FROM document WHERE d_id = '"
				+ documentId + "';";

		boolean result = true;
		try {
			aConnection.setAutoCommit(false);
			// *delete from the Assignment Table*//
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

	public static boolean update(String documentId, String documentName) throws SQLException {
		BackupDocumentModel bd = new BackupDocumentModel(documentId);
		DocumentModel doc = new DocumentModel(documentId,
				"Student Record", documentName);

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

	public static boolean retrieveFromDocument(String documentId) {

		int BUFFER_SIZE = 100000;
		byte[] buffer = new byte[BUFFER_SIZE];// ?
		boolean result = true;
		String sqlQuery = "SELECT document FROM document WHERE d_id = '"
				+ documentId + "';";
		try {
			statement1 = aConnection.createStatement();
			ResultSet rs = statement1.executeQuery(sqlQuery);
			String fileName = "C://Users/oner1m/student_record.pdf";
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
