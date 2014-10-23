package section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SectionViewer implements SectionViewerI {
	private Map<Integer, MapFunctionI> sectionViewerMap;
	List<Map<String, String>> userInputs;
	
	Map<String, String> classid, crn;
	Scanner scanner;
	
	public SectionViewer() {
		sectionViewerMap = new HashMap<Integer, MapFunctionI>();
		scanner = new Scanner(System.in);
		setMap();
	}

	private Map<String, String> askClassId() {
		classid = new HashMap<String, String>();
		System.out.println("\nPlease enter class Id: (CPS100)\n");
		String c_id = scanner.nextLine().toLowerCase().trim();
		classid.put("c_id", c_id);
		return classid;
	}
	
	private Map<String, String> askCrn() {
		crn = new HashMap<String, String>();
		System.out.println("\nPlease enter crn: (22210041)\n");
		String crn_ = scanner.nextLine().toLowerCase().trim();
		crn.put("crn", crn_);
		return crn;
	}
	
	private void setMap() {
		/*ADD NEW*/
		this.sectionViewerMap.put(31, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();
				userInputs.add(askClassId());
				userInputs.add(askCrn());
			}
		});
		/*DELETE*/
		this.sectionViewerMap.put(32, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();
				userInputs.add(askCrn());
				
			}
		});
		/*UPDATE*/
		this.sectionViewerMap.put(33, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();
				userInputs.add(askCrn());
				
				classid = new HashMap<String, String>();
				System.out.println("\nUpdate class Id: (CPS100)\n");
				String c_id = scanner.nextLine().toLowerCase().trim();
				classid.put("new_c_id", c_id);
				
				crn = new HashMap<String, String>();
				System.out
						.println("\nUpdate crn: (22210040)\n");
				String new_crn = scanner.nextLine().toLowerCase().trim();
				crn.put("new_crn", new_crn);
				
				userInputs.add(classid);
				userInputs.add(crn);
			}
		});
		/*SHOW SECTION INFO*/
		this.sectionViewerMap.put(34, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();
				userInputs.add(askCrn());
			}
		});
		/*SHOW ALL SECTIONS FOR CLASS ID*/
		this.sectionViewerMap.put(35, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();
				userInputs.add(askClassId());
			}
		});
		
	}

	@Override
	public List<Map<String, String>> runInput(int functionNo) {
		sectionViewerMap.get(functionNo).runCommand();
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
