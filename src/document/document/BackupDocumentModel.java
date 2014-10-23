package document.document;

import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BackupDocumentModel {

	private int versionNo;
	private Timestamp timestamp;
	private String documentId;
	private Blob document;
	private long length;

	public BackupDocumentModel(String documentId) {
		setDocumentId(documentId);
		setVersionNo(documentId);
		setBlob(documentId);
		setTimestamp();
		setLength();
	}

	public int getVersionNo() {
		return versionNo;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getDocumentId() {
		return documentId;
	}

	public long getLength() {
		return length;
	}

	public Blob getDocument() {
		return document;
	}

	public void setLength() {
		try {
			this.length = document.length();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setVersionNo(String documentId) {
		this.versionNo = (BackupDocumentDA.find(documentId)) + 1;
	}

	public void setTimestamp() {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		this.timestamp = new Timestamp(date.getTime());
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public void setBlob(String documentId) {
		try {
			this.document = DocumentDA.get(documentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
