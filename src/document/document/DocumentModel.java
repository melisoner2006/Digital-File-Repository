package document.document;

import java.io.*;
import java.sql.*;

public class DocumentModel {

	private String documentId, documentType;
	private Timestamp timestamp;
	private int documentLength;

	private static File file;
	private InputStream inputStream;

	public DocumentModel(String documentId, String documentType,
			String documentName) {

		setDocumentId(documentId);
		this.documentType = documentType;
		setFis(documentName);
		setDocumentLength();
		setTimestamp();
	}

	

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}



	public int getDocumentLength() {
		return documentLength;
	}

	public void setDocumentLength() {
		this.documentLength = (int) file.length();
	}

	public String getDocumentId() {
		return documentId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp() {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		this.timestamp = new Timestamp(date.getTime());
	}

	public FileInputStream getFis() {
		return (FileInputStream) inputStream; //?
	}

	public void setFis(String documentName) {
		
		file = new File(documentName);
		try {
			this.inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


}
