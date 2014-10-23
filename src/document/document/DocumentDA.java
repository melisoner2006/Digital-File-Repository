package document.document;

import java.sql.*;

public class DocumentDA {

	static Connection aConnection;
	static Statement aStatement;
	static PreparedStatement pstmt;

	static DocumentModel aDocument;

	public DocumentDA(Connection c) {
		aConnection = c;
		try {
			aStatement = aConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean find(String documentId) {

		boolean isFull = false;
		String sqlQuery = "SELECT * FROM document WHERE d_id = '" + documentId
				+ "';";
		ResultSet rs;
		try {
			rs = aStatement.executeQuery(sqlQuery);
			isFull = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isFull;
	}

	public static boolean add(DocumentModel doc) throws SQLException {
		aDocument = doc;
		String sqlInsert = "INSERT INTO document (d_id, documentType, document, timestamp) VALUES (?,?,?,?)";
		boolean result = true;
		try {
			aConnection.setAutoCommit(false);

			try {

				pstmt = aConnection.prepareStatement(sqlInsert);
				pstmt.setString(1, aDocument.getDocumentId());
				pstmt.setString(2, aDocument.getDocumentType());
				pstmt.setBlob(3, aDocument.getFis());
				pstmt.setTimestamp(4, aDocument.getTimestamp());
				pstmt.executeUpdate();

				aConnection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			aConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			aConnection.rollback();
			result = false;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
//			aConnection.setAutoCommit(true);
		}
		return result;
	}

	public static boolean update(DocumentModel aDocument) {

		return true;
	}

	public static boolean delete(String documentId) {
		String sqlQuery = "DELETE FROM document WHERE d_id = '" + documentId
				+ "';";
		try {
			aStatement.execute(sqlQuery);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Blob get(String documentId) throws SQLException {

		Blob documentObject = null;
		String getFromDocument = "SELECT document FROM document WHERE d_id = '"
				+ documentId + "';";

		try {
			aConnection.setAutoCommit(false);
			aStatement = aConnection.createStatement();
			ResultSet rs = null;
			rs = aStatement.executeQuery(getFromDocument);

			if (rs.next()) {
				documentObject = rs.getBlob("document");
			}
			aConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			aConnection.rollback();
		} finally {
			if (aStatement != null) {
				aStatement.close();
			}
			aConnection.setAutoCommit(true);
		}

		return documentObject;
	}
}
