package document.masterCourseSyllabus;

import java.sql.SQLException;
import java.util.*;

import classOperations.*;
import document.assignment.MapFunctionI;
import document.document.BackupDocumentDA;

public class McsModel implements McsModelI {
	private String classId, documentName, mcs_id;

	private Map<Integer, MapFunctionI> mcsOperationsMap;
	private List<Map<String, String>> userInputs;

	private String message;

	/* Constructors */
	public McsModel(String classId, String documentName) {
		setClassId(classId);
		setMcs_id(classId);
		setDocumentName(documentName);
	}

	public McsModel(String classId) {
		setClassId(classId);
		setMcs_id(classId);
	}

	public McsModel() {
		mcsOperationsMap = new HashMap<Integer, MapFunctionI>();
		setModel();
	}

	/* Accessor methods */
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public String getMcs_id() {
		return mcs_id;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public void setMcs_id(String classId) {
		this.mcs_id = generateMcsId(classId);
	}

	/*
	 * Generate MCS table primary key, and foreign key to the document and
	 * backup_documents tables.
	 */
	private String generateMcsId(String classId) {
		return Integer.toString(classId.hashCode());
	}

	private void setModel() {
		/* ADD NEW */
		this.mcsOperationsMap.put(51, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				classId = userInputs.get(0).get("c_id");
				documentName = userInputs.get(1).get("documentName");
				McsModel newMcs = new McsModel(classId, documentName);
				if (ClassDA.find(classId)) {
					if (!McsDA.find(classId)) {
						try {
							if (McsDA.add(newMcs)) {
								message = "Master Course Syllabus is added to the database.\n";
							} else {
								message = "Master Course Syllabus can not be added to the database.\n";
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else {
						message = "This document is already in the database./n";
					}
				} else {
					message = "This class is not in the database./n";
				}
			}

		});
		/* DELETE */
		this.mcsOperationsMap.put(52, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				classId = userInputs.get(0).get("c_id");
				if (McsDA.find(classId)) {
					try {
						if (McsDA.delete(classId, setDocumentId(classId))) {
							message = "Master Course Syllabus is deleted from the database.\n";
						} else {
							message = "Master Course Syllabus can not be deleted.\n";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					message = "Master Course Syllabus is not in the database.\n";
				}

			}

		});
		/* UPDATE */
		this.mcsOperationsMap.put(53, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				classId = userInputs.get(0).get("c_id");
				documentName = userInputs.get(1).get("documentName");
				if (McsDA.find(classId)) {
					try {
						if (McsDA.update(classId, documentName,
								setDocumentId(classId))) {
							message = "Master Course Syllabus is updated.\n";
						} else
							message = "Master Course Syllabus can not be updated.\n";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		});
		/* SHOW ALL */
		this.mcsOperationsMap.put(54, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				classId = userInputs.get(0).get("c_id");
				if (McsDA.find(classId)) {
					try {
						message = McsDA.showAll(setDocumentId(classId));
						message += BackupDocumentDA
								.getVersionInfo(setDocumentId(classId));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					message = "There is no Master Course Syllabus for this class id.\n";
				}
			}

		});
		/* RETRIEVE */
		this.mcsOperationsMap.put(55, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				classId = userInputs.get(0).get("c_id");
				if (McsDA.find(classId)) {
					if (McsDA.retrieveFromDocument(classId)) {
						message = "Master Course Syllabus is downloaded. Please check your C://Users/__globalId__/ directory.\n";
					} else {
						message = "Master Course Syllabus can not be downloaded.\n";
					}
				} else {
					message = "There is no Master Course Syllabus for this class id.\n";
				}
			}

		});

	}

	public static String setDocumentId(String classId) {
		return String.valueOf(classId.hashCode());
	}

	@Override
	public void runInput(int functionNo, List<Map<String, String>> userInputs) {
		this.userInputs = userInputs;
		mcsOperationsMap.get(functionNo).runCommand();
	}

	@Override
	public String returnMessage() {
		return message;
	}
}