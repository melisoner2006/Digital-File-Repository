package document.studentRecord;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import section.SectionDA;
import document.document.BackupDocumentDA;

public class StudentRecordModel implements StudentRecordModelI {

	private String crn, assignmentType, assignmentNo, documentName, caseType,
			sr_id;
	private String documentId;
	private Map<Integer, MapFunctionI> srOperationsMap;
	private List<Map<String, String>> userInputs;

	private String message;

	public StudentRecordModel(String crn, String a_type, String a_no,
			String caseType, String documentName) {

		setCrn(crn);
		setAssignmentType(a_type);
		setAssignmentNo(a_no);
		setDocumentName(documentName);
		setCaseType(caseType);
		setSr_id(generateAssignmentId(crn, a_type, a_no, caseType));
	}

	public StudentRecordModel(String crn, String a_type, String a_no,
			String caseType) {

		setCrn(crn);
		setAssignmentType(a_type);
		setAssignmentNo(a_no);
		setSr_id(generateAssignmentId(crn, a_type, a_no, caseType));
	}

	public StudentRecordModel() {
		srOperationsMap = new HashMap<Integer, MapFunctionI>();
		setModel();

	}

	public String getCrn() {
		return crn;
	}

	public String getAssignmentType() {
		return assignmentType;
	}

	public String getAssignmentNo() {
		return assignmentNo;
	}

	public String getDocumentName() {
		return documentName;
	}

	public String getCaseType() {
		return caseType;
	}

	public String getSr_id() {
		return sr_id;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	public void setAssignmentNo(String assignmentNo) {
		this.assignmentNo = assignmentNo;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public void setSr_id(String sr_id) {
		this.sr_id = sr_id;
	}

	private String generateAssignmentId(String crn, String a_type, String a_no,
			String caseType) {
		String unique = crn + a_type + a_no + caseType;
		int sr_id = unique.hashCode();
		return String.valueOf(sr_id);
	}

	private void setModel() {
		/* ADD NEW */
		this.srOperationsMap.put(81, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();

				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				caseType = userInputs.get(3).get("case_type");
				documentName = userInputs.get(4).get("documentName");

				StudentRecordModel newRecord = new StudentRecordModel(crn,
						assignmentType, assignmentNo, caseType, documentName);
				if (SectionDA.find(crn)) {
					if (!StudentRecordDA.find(newRecord.getSr_id())) {
						try {
							if (StudentRecordDA.add(newRecord)) {
								message = "Student Record is added to the database.\n";
							} else {
								message = "Student Record can not be added to the database.\n";
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else {
						message = "This document is already in the database./n";
					}
				} else {
					message = "This section is not in the database./n";
				}

			}

		});

		/* DELETE */
		this.srOperationsMap.put(82, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				caseType = userInputs.get(3).get("case_type");

				StudentRecordModel newRecord = new StudentRecordModel(crn,
						assignmentType, assignmentNo, caseType);
				documentId = newRecord.getSr_id();

				if (StudentRecordDA.find(documentId)) {
					try {
						if (StudentRecordDA.delete(documentId)) {
							message = "Student Record is deleted from the database.\n";
						} else {
							message = "Student Record can not be deleted.\n";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					message = "Student Record is not in the database.\n";
				}

			}

		});

		/* UPDATE */
		this.srOperationsMap.put(83, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				caseType = userInputs.get(3).get("case_type");
				documentName = userInputs.get(4).get("documentName");

				StudentRecordModel newRecord = new StudentRecordModel(crn,
						assignmentType, assignmentNo, caseType, documentName);
				documentId = newRecord.getSr_id();

				if (StudentRecordDA.find(documentId)) {
					try {
						if (StudentRecordDA.update(documentId, documentName)) {
							message = "Student Record is updated.\n";
						} else
							message = "Student Record can not be updated.\n";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		/* SHOW ASSIGNMENT INFO */
		this.srOperationsMap.put(84, new MapFunctionI() {

			@Override
			public void runCommand() {

				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				caseType = userInputs.get(3).get("case_type");

				StudentRecordModel newRecord = new StudentRecordModel(crn,
						assignmentType, assignmentNo, caseType);
				documentId = newRecord.getSr_id();

				if (StudentRecordDA.find(documentId)) {
					try {
						message = StudentRecordDA.showAll(documentId);
						message += BackupDocumentDA.getVersionInfo(documentId);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					message = "There is no Student Record for this section.\n";
				}
			}

		});

		/* RETRIEVE FROM DOCUMENTS */
		this.srOperationsMap.put(85, new MapFunctionI() {

			@Override
			public void runCommand() {

				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");

				caseType = userInputs.get(3).get("case_type");

				StudentRecordModel newRecord = new StudentRecordModel(crn,
						assignmentType, assignmentNo, caseType);
				documentId = newRecord.getSr_id();

				if (StudentRecordDA.find(documentId)) {
					if (StudentRecordDA.retrieveFromDocument(documentId)) {
						message = "Student Record is downloaded. Please check your C:// directory.\n";
					} else {
						message = "Student Record can not be downloaded.\n";
					}
				} else {
					message = "There is no Student Record for this section.\n";
				}
			}

		});

	}

	@Override
	public void runInput(int functionNo, List<Map<String, String>> userInputs) {
		this.userInputs = userInputs;
		srOperationsMap.get(functionNo).runCommand();
		
	}

	@Override
	public String returnMessage() {
		return message;
	}
}
