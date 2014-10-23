package document.studentRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import document.assignment.MapFunctionI;

public class StudentRecordViewer implements StudentRecordViewerI{
	private Map<Integer, MapFunctionI> srViewerMap;
	List<Map<String, String>> userInputs;
	
	Map<String, String> crn, assignmentType, assignmentNo, documentName, caseType;
	Scanner scanner;
	
	public StudentRecordViewer(){
		srViewerMap= new HashMap<Integer, MapFunctionI>();
		scanner = new Scanner(System.in);
		setMap();
	}


	private Map<String,String> askDocumentName(){
		documentName = new HashMap<String,String>();
		System.out.println("\nPlease enter documentName: (document.pdf)\n");
		String dName = "c://DigitalRepositoryDocuments/"+scanner.next();
		documentName.put("documentName", dName);
		return documentName;
	}
	
	private Map<String,String> askCrn(){
		
		crn = new HashMap<String, String>();
		System.out.println("\nPlease enter crn: (22210041)\n");
		String crn_ = scanner.nextLine().toLowerCase().trim();
		crn.put("crn", crn_);
		return crn;
	}
	
	private Map<String, String> askCaseType(){
		caseType = new HashMap<String, String>();
		
		System.out.println("\n1: Best case"
				+ "\n2: Average case"
				+ "\n3: Worst case"
				+ "Please enter the case type:");
		String case_type = scanner.next();
		int case_type_no = Integer.parseInt(case_type);

		switch(case_type_no){
		case 1:
			case_type = "Best case";
			break;
		case 2:
			case_type = "Average case";
			break;
		case 3:
			case_type = "Worst case";
			break;
		}
		
		caseType.put("case_type", case_type);
		return caseType;
	}
	
	private Map<String,String> askAssignmentType(){
		
		assignmentType = new HashMap<String, String>();
		System.out.println("\n1: Homework"
				+ "\n2: Project Assignment"
				+ "\n3: Lab Assignment"
				+ "\n4: Midterm Exam"
				+ "\n5: Final Exam"
				+ "\n6: Quiz\n"
				+ "Please enter the assignment type:");
		String a_type = scanner.next();
		
		if(!a_type.equals("*")){
			int a_type_no = Integer.parseInt(a_type);
			 
			switch(a_type_no){
			case 1:
				a_type = "Homework";
				break;
			case 2:
				a_type = "Project Assignment";
				break;
			case 3:
				a_type = "Lab Assignment";
				break;
			case 4:
				a_type = "Midterm Exam";
				break;
			case 5:
				a_type = "Final Exam";
				break;
			case 6:
				a_type = "Quiz";
				break;
			}
		}
		assignmentType.put("a_type", a_type);
		return assignmentType;
	}
	
	private Map<String,String> askAssignmentNo(){
		
		assignmentNo = new HashMap<String, String>();
		System.out.println("\nPlease enter assignment number:");
		String a_no = scanner.next();
		assignmentNo.put("a_no", a_no);
		return assignmentNo;
		
	}
	
	
	
	
	private void setMap() {
		/*ADD NEW*/
		this.srViewerMap.put(81, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				
				userInputs.add(askCrn());
				userInputs.add(askAssignmentType());
				userInputs.add(askAssignmentNo());
				userInputs.add(askCaseType());
				userInputs.add(askDocumentName());
				
			}
		});
		
		/*DELETE*/
		this.srViewerMap.put(82, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				
				userInputs.add(askCrn());
				userInputs.add(askAssignmentType());
				userInputs.add(askAssignmentNo());
				userInputs.add(askCaseType());
				
			}
		});
		/*UPDATE*/
		this.srViewerMap.put(83, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				
				userInputs.add(askCrn());
				userInputs.add(askAssignmentType());
				userInputs.add(askAssignmentNo());
				userInputs.add(askCaseType());
				userInputs.add(askDocumentName());
				
			}
		});
		
		/*SHOW ALL*/
		this.srViewerMap.put(84, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				
				userInputs.add(askCrn());
				userInputs.add(askAssignmentType());
				userInputs.add(askAssignmentNo());
				userInputs.add(askCaseType());
				
			}
		});
		
		/*RETRIEVE*/
		this.srViewerMap.put(85, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				
				userInputs.add(askCrn());
				userInputs.add(askAssignmentType());
				userInputs.add(askAssignmentNo());
				userInputs.add(askCaseType());
				
			}
		});
		
		
		
	}
	
	
	
	@Override
	public List<Map<String, String>> runInput(int functionNo) {
		srViewerMap.get(functionNo).runCommand();
		return userInputs;
	}

	@Override
	public int passFunctionCode(int functionNo) {
		return functionNo;
	}

	@Override
	public void printConsoleMessage(String message) {
		System.out.println(message);
	}

}
