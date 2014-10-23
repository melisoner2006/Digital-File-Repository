package document.assignment;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import document.document.BackupDocumentDA;
import section.SectionDA;


public class AssignmentModel implements AssignmentModelI {
	private String crn, assignmentType, assignmentNo, documentName, assignmentId;

	private Map<Integer, MapFunctionI> assignmentOperationsMap;
	private List<Map<String, String>> userInputs;


	private String message;

	public AssignmentModel(String crn, String a_type, String a_no, String documentName) {
		
		setCrn(crn);
		setAssignmentType(a_type);
		setAssignmentNo(a_no);
		setDocumentName(documentName);
		setAssignmentId(generateAssignmentId(crn, a_type, a_no));
	}

	public AssignmentModel(String crn, String a_type, String a_no) {
		
		setCrn(crn);
		setAssignmentType(a_type);
		setAssignmentNo(a_no);
		setAssignmentId(generateAssignmentId(crn, a_type, a_no));
	}
	public AssignmentModel() {
		assignmentOperationsMap = new HashMap<Integer, MapFunctionI>();
		setModel();

	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	public String getDocumentName() {
		return documentName;
	}

	public String getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	public String getAssignmentNo() {
		return assignmentNo;
	}

	public void setAssignmentNo(String assignmentNo) {
		this.assignmentNo = assignmentNo;
	}

	public String getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}

	private String generateAssignmentId(String crn, String a_type,
			String a_no) {
		String unique = crn + a_type + a_no;
		int a_id =unique.hashCode();
		return String.valueOf(a_id);
	}
	
	private void setModel() {
		/* ADD NEW */
		this.assignmentOperationsMap.put(71, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				documentName = userInputs.get(3).get("documentName");
				
				AssignmentModel newAssignment = new AssignmentModel(crn, assignmentType,assignmentNo, documentName);
				if (SectionDA.find(crn)) {
					if (! AssignmentDA.find(newAssignment.getAssignmentId())){
						try {
							if (AssignmentDA.add(newAssignment)) {
								message = "Course Assignment is added to the database.\n";
							} else {
								message = "Course Assignment can not be added to the database.\n";
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
		this.assignmentOperationsMap.put(72, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				
				AssignmentModel newAssignment = new AssignmentModel(crn, assignmentType,assignmentNo);
				assignmentId = newAssignment.getAssignmentId();
				
				if (AssignmentDA.find(assignmentId)) {
					try {
						if (AssignmentDA.delete(assignmentId)) {
							message = "Course Assignment is deleted from the database.\n";
						} else {
							message = "Course Assignment can not be deleted.\n";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					message = "Course Assignment is not in the database.\n";
				}

			}

		});
		/* UPDATE */
		this.assignmentOperationsMap.put(73, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				documentName = userInputs.get(3).get("documentName");
				
				AssignmentModel newAssignment = new AssignmentModel(crn, assignmentType,assignmentNo,documentName);
				assignmentId = newAssignment.getAssignmentId();
				
				if (AssignmentDA.find(assignmentId)) {
					try {
						if (AssignmentDA.update(assignmentId, documentName)) {
							message = "Course Assignment is updated.\n";
						} else
							message = "Course Assignment can not be updated.\n";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		/* SHOW ASSIGNMENT INFO */
		this.assignmentOperationsMap.put(74, new MapFunctionI() {

			@Override
			public void runCommand() {
				
				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				
				AssignmentModel newAssignment = new AssignmentModel(crn, assignmentType,assignmentNo);
				assignmentId = newAssignment.getAssignmentId();
				
				if (AssignmentDA.find(assignmentId)) {
					try {
						message = AssignmentDA.showAll(assignmentId);
						message += BackupDocumentDA
								.getVersionInfo(assignmentId);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					message = "There is no Course Assignment for this section.\n";
				}
			}

		});
		
		/*RETRIEVE FROM DOCUMENTS*/
		this.assignmentOperationsMap.put(75, new MapFunctionI() {

			@Override
			public void runCommand() {
				
				message = new String();
				crn = userInputs.get(0).get("crn");
				assignmentType = userInputs.get(1).get("a_type");
				assignmentNo = userInputs.get(2).get("a_no");
				
				AssignmentModel newAssignment = new AssignmentModel(crn, assignmentType,assignmentNo);
				assignmentId = newAssignment.getAssignmentId();
				
				
				if (AssignmentDA.find(assignmentId)) {
					if (AssignmentDA.retrieveFromDocument(assignmentId)) {
						message = "Course Assignment is downloaded. Please check your C:// directory.\n";
					} else {
						message = "Course Assignment can not be downloaded.\n";
					}
				} else {
					message = "There is no Course Assignment for this section.\n";
				}
			}

		});
	}

	@Override
	public void runInput(int functionNo, List<Map<String, String>> userInputs) {
		this.userInputs = userInputs;
		assignmentOperationsMap.get(functionNo).runCommand();

	}

	@Override
	public String returnMessage() {
		return message;
	}

}
