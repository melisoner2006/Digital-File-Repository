package document.masterCourseSyllabus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class McsViewer implements McsViewerI{
	private Map<Integer, MapFunctionI> mcsViewerMap;
	List<Map<String, String>> userInputs;
	Scanner scanner;
	Map<String, String> classId, documentName;
	
	public McsViewer(){
		mcsViewerMap = new HashMap<Integer, MapFunctionI>();
		scanner = new Scanner(System.in);
		setMap();
	}
	
	private Map<String, String> askClassId() {
		classId = new HashMap<String, String>();
		System.out.println("\nPlease enter class Id: (CPS100)\n");
		String c_id = scanner.nextLine().toLowerCase().trim();
		classId.put("c_id", c_id);
		return classId;
	}
	
	private Map<String,String> askDocumentName(){
		documentName = new HashMap<String,String>();
		System.out.println("\nPlease enter documentName: (document.pdf)\n");
		String dName = "c://DigitalRepositoryDocuments/"+scanner.next();
		documentName.put("documentName", dName);
		return documentName;
	}
	private void setMap(){
		/*ADD NEW*/
		this.mcsViewerMap.put(51, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askClassId());
				userInputs.add(askDocumentName());
			}
		});
		/*DELETE*/
		this.mcsViewerMap.put(52, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askClassId());
				
			}
		});
		/*UPDATE*/
		this.mcsViewerMap.put(53, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askClassId());
				userInputs.add(askDocumentName());
			}
		});
		/*SHOW ALL*//* all versions of a MCS in the document table and the backup table*/
		this.mcsViewerMap.put(54, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askClassId());
			}
		});
		/*RETRIEVE*//*Master Course Syllabus*/
		this.mcsViewerMap.put(55, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<Map<String, String>>();
				userInputs.add(askClassId());
				
			}
		});
	}

	@Override
	public List<Map<String, String>> runInput(int functionNo) {
		mcsViewerMap.get(functionNo).runCommand();
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
