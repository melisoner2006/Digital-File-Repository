package document.courseSyllabus;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import section.SectionDA;
import document.assignment.MapFunctionI;
import document.document.BackupDocumentDA;

public class CourseSyllabusModel implements CourseSyllabusModelI {
	private String crn, documentName, cs_id;

	private Map<Integer, MapFunctionI> csOperationsMap;
	private List<Map<String, String>> userInputs;

	private static String message;
	
	/*Constructors*/
	public CourseSyllabusModel(String crn, String documentName){
		setCrn(crn);
		setCs_id(crn);
		setDocumentName(documentName);
	}
	
	public CourseSyllabusModel(String crn){
		setCrn(crn);
		setCs_id(crn);
	}
	
	public CourseSyllabusModel(){
		csOperationsMap = new HashMap<Integer, MapFunctionI>();
		setModel();
	}
	/*Accessors*/
	public String getDocumentName() {
		return documentName;
	}

	public String getCs_id() {
		return cs_id;
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

	public void setCs_id(String crn) {
		this.cs_id = generateCsId(crn);
	}
	
	private String generateCsId(String crn){
		return String.valueOf(crn.hashCode());
	}
	
	/*Set Model*/
	private void setModel() {
		/*ADD NEW*/
		this.csOperationsMap.put(61, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				documentName = userInputs.get(1).get("documentName");
				CourseSyllabusModel newCs = new CourseSyllabusModel(crn, documentName);
				if (SectionDA.find(crn)) {
					if (!CourseSyllabusDA.find(crn)) {
						try {
							if (CourseSyllabusDA.add(newCs)) {
								message = "Course Syllabus is added to the database.\n";
							} else {
								message = "Course Syllabus can not be added to the database.\n";
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
		/*DELETE*/
		this.csOperationsMap.put(62, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				if (CourseSyllabusDA.find(crn)) {
					try {
						if (CourseSyllabusDA.delete(crn, setDocumentId(crn))) {
							message = "Course Syllabus is deleted from the database.\n";
						} else {
							message = "Course Syllabus can not be deleted.\n";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					message = "Course Syllabus is not in the database.\n";
				}

			}
		});
		/*UPDATE*/
		this.csOperationsMap.put(63, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				documentName = userInputs.get(1).get("documentName");
				if (CourseSyllabusDA.find(crn)) {
					try {
						if (CourseSyllabusDA.update(crn, documentName,
								setDocumentId(crn))) {
							message = "Course Syllabus is updated.\n";
						} else
							message = "Course Syllabus can not be updated.\n";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		/*SHOW ALL*/
		this.csOperationsMap.put(64, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				if (CourseSyllabusDA.find(crn)) {
					try {
						message = CourseSyllabusDA.showAll(setDocumentId(crn));
						message += BackupDocumentDA
								.getVersionInfo(setDocumentId(crn));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					message = "There is no Course Syllabus for this section.\n";
				}
			}
		});
		/*RETRIEVE FROM DOCUMENTS*/
		this.csOperationsMap.put(65, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				crn = userInputs.get(0).get("crn");
				if (CourseSyllabusDA.find(crn)) {
					if (CourseSyllabusDA.retrieveFromDocument(crn, setDocumentId(crn))) {
						message = "Course Syllabus is downloaded. Please check your C:// directory.\n";
					} else {
						message = "Course Syllabus can not be downloaded.\n";
					}
				} else {
					message = "There is no Course Syllabus for this section.\n";
				}
			}
		});

		
	}
	
	public static String setDocumentId(String crn) {
		return String.valueOf(crn.hashCode());
	}

	@Override
	public void runInput(int functionNo, List<Map<String, String>> userInputs) {
		this.userInputs = userInputs;
		csOperationsMap.get(functionNo).runCommand();
	}

	@Override
	public String returnMessage() {
		return message;
	}

}
