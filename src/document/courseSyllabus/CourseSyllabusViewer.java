package document.courseSyllabus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import document.masterCourseSyllabus.MapFunctionI;

public class CourseSyllabusViewer implements CourseSyllabusViewerI {

	private Map<Integer, MapFunctionI> csViewerMap;
	List<Map<String, String>> userInputs;
	Scanner scanner;
	Map<String, String> crn, documentName;
	
	public CourseSyllabusViewer(){
		csViewerMap = new HashMap<Integer, MapFunctionI>();
		scanner = new Scanner(System.in);
		setMap();
	}
	
	private Map<String, String> askCrn() {
		crn = new HashMap<String, String>();
		System.out.println("\nPlease enter crn: (22210078)\n");
		String crn_ = scanner.nextLine().toLowerCase().trim();
		crn.put("crn", crn_);
		return crn;
	}
	
	private Map<String,String> askDocumentName(){
		documentName = new HashMap<String,String>();
		System.out.println("\nPlease enter documentName: (document.pdf)\n");
		String dName = "c://DigitalRepositoryDocuments/"+scanner.next();
		documentName.put("documentName", dName);
		return documentName;
	}
	private void setMap() {
		/*ADD NEW*/
		this.csViewerMap.put(61, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askCrn());
				userInputs.add(askDocumentName());
			}
		});
		/*DELETE*/
		this.csViewerMap.put(62, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askCrn());
				
			}
		});
		/*UPDATE*/
		this.csViewerMap.put(63, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askCrn());
				userInputs.add(askDocumentName());
			}
		});
		/*SHOW ALL*//* all versions of a CS in the document table and the backup table*/
		this.csViewerMap.put(64, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askCrn());
			}
		});
		/*RETRIEVE*//*Master Course Syllabus*/
		this.csViewerMap.put(65, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askCrn());
				
			}
		});
	}

	@Override
	public List<Map<String, String>> runInput(int functionNo) {
		csViewerMap.get(functionNo).runCommand();
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
