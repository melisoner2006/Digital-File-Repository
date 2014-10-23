package classOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClassViewer implements ClassViewerI {
	private Map<Integer, MapFunctionI> classViewerMap;
	List<Map<String, String>> userInputs;

	Map<String, String> classid, classname;
	Scanner scanner;

	public ClassViewer() {
		classViewerMap = new HashMap<Integer, MapFunctionI>();
		scanner = new Scanner(System.in);
		setMap();
	}

	@Override
	public List<Map<String, String>> runInput(int functionNo) {
		classViewerMap.get(functionNo).runCommand();
		return userInputs;

	}

	@Override
	public int passFunctionCode(int functionNo) {
		return functionNo;
	}

	private Map<String, String> askClassId() {
		classid = new HashMap<String, String>();
		System.out.println("\nPlease enter class Id: (CPS100)\n");
		String c_id = scanner.nextLine().toLowerCase().trim();
		classid.put("c_id", c_id);
		return classid;
	}

	private Map<String, String> askClassName() {
		classname = new HashMap<String, String>();
		System.out
				.println("\nPlease enter class name: (Computers and Society)\n");
		String c_name = scanner.nextLine().toLowerCase();
		classname.put("c_name", c_name);
		return classname;
	}

	private void setMap() {
		/*ADD NEW*/
		this.classViewerMap.put(21, new MapFunctionI() {

			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();

				userInputs.add(askClassId());
				userInputs.add(askClassName());
			}

		});
		/*DELETE*/
		this.classViewerMap.put(22, new MapFunctionI() {

			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();

				userInputs.add(askClassId());

			}

		});
		/*UPDATE*/
		this.classViewerMap.put(23, new MapFunctionI() {

			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();

				userInputs.add(askClassId());
			
				
				classid = new HashMap<String, String>();
				System.out.println("\nUpdate class Id: (CPS100)\n");
				String c_id = scanner.nextLine().toLowerCase().trim();
				classid.put("new_c_id", c_id);
				
				classname = new HashMap<String, String>();
				System.out
						.println("\nUpdate class name: (Computers and Society)\n");
				String c_name = scanner.nextLine().toLowerCase();
				classname.put("new_c_name", c_name);
				
				userInputs.add(classid);
				userInputs.add(classname);
				

			}

		});
		/*SHOW CLASS INFO*/
		this.classViewerMap.put(24, new MapFunctionI() {

			@Override
			public void runCommand() {
				userInputs = new ArrayList<>();
				userInputs.add(askClassId());
			}

		});
	}

	@Override
	public void printConsoleMessage(String message){
		System.out.println(message);
	}

}
