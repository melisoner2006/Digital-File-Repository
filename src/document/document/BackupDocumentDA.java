package document.document;

import java.sql.*;

public class BackupDocumentDA {

	static Connection aConnection;
	static Statement statement1;
	static PreparedStatement pstmt;

	public BackupDocumentDA(Connection c) {
		BackupDocumentDA.aConnection = c;
		try {
			statement1 = aConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int find(String documentId) {
		String sqlQuery = "SELECT MAX(versionno) FROM backup_documents WHERE d_id = '"
				+ documentId + "';";
		ResultSet rs;
		int verNo = 0;
		// returns version number.
		try {
			rs = statement1.executeQuery(sqlQuery);
			if (rs.next()) {
				verNo = rs.getInt(1);
				return verNo;
			} else
				return verNo;
		} catch (SQLException e) {
			e.printStackTrace();
			return verNo;
		}

	}

	public static boolean insert(BackupDocumentModel bd) throws SQLException {
		String insertIntoBackup = "INSERT INTO backup_documents (d_id, versionno, document, timestamp) VALUES (?,?,?,?)";

		boolean result = true;
		try {
			aConnection.setAutoCommit(false);

			try {
				pstmt = aConnection.prepareStatement(insertIntoBackup);
				pstmt.setString(1, bd.getDocumentId());
				pstmt.setInt(2, bd.getVersionNo());
				pstmt.setBlob(3, bd.getDocument());
				pstmt.setTimestamp(4, bd.getTimestamp());

				pstmt.executeUpdate();
			} finally {
				if (pstmt != null) {
					pstmt.close();
				}
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

	public static String getVersionInfo(String documentId) throws SQLException {
		String selectFromBackups = "SELECT versionno, timestamp FROM backup_documents"
				+ " WHERE d_id = '" + documentId + "';";
		String result = "";
		ResultSet rs = null;
		try {
			aConnection.setAutoCommit(false);
			try {
				statement1 = aConnection.createStatement();
				rs = statement1.executeQuery(selectFromBackups);
				if (rs.next()) {
					result += "v." + rs.getInt(1) + " "
							+ rs.getTimestamp(2) + "\n";
					while (rs.next()) {
						result += "v." + rs.getInt(1) + " "
								+ rs.getTimestamp(2) + "\n";
					}
				}

			} finally {
				statement1.close();
			}
			aConnection.commit();
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

}
