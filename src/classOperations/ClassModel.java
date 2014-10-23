package classOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassModel implements ClassModelI {
	private String classId;
	private String className;

	private Map<Integer, MapFunctionI> classOperationsMap;
	private List<Map<String, String>> userInputs;

	private String message;

	public ClassModel(String id, String name) {
		setClassId(id);
		setClassName(name);
	}

	public ClassModel() {
		// to eliminate conflict in the Factory class
		classOperationsMap = new HashMap<Integer, MapFunctionI>();
		setMap();
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassId() {
		return classId;
	}

	public String getClassName() {
		return className;
	}

	@Override
	public void runInput(int functionNo, List<Map<String, String>> userInputs) {
		this.userInputs = userInputs;
		classOperationsMap.get(functionNo).runCommand();
	}

	@Override
	public String returnMessage() {
		return message;
	}

	private void setMap() {
		/* ADD NEW */
		this.classOperationsMap.put(21, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				boolean inDB = ClassDA.find(userInputs.get(0).get("c_id"));

				if (inDB) {
					message = "\nThis courseId is in the database.\n";
				} else {
					boolean added = ClassDA.add(userInputs.get(0).get("c_id"),
							userInputs.get(1).get("c_name"));
					if (added)
						message = "Courses is added to the database.\n";
					else
						message = "Courses can not be added to the database.\n";
				}
			}

		});
		/* DELETE */
		this.classOperationsMap.put(22, new MapFunctionI() {

			@Override
			public void runCommand() {
				message = new String();
				boolean inDB = ClassDA.find(userInputs.get(0).get("c_id"));
				if (inDB) {
					boolean deleted = ClassDA.delete(userInputs.get(0).get(
							"c_id"));
					if (deleted)
						message = "Courses is deleted from the database.\n";
					else
						message = "Course can not be deleted from the database.\n";
				} else {
					message = "\nThis courseId is not in the database.\n";
				}

			}

		});
		/* UPDATE */
		this.classOperationsMap.put(23, new MapFunctionI() {

			@Override
			public void runCommand() {
				ClassModel classToBeUpdated = new ClassModel();
				classToBeUpdated.setClassId(userInputs.get(1).get("new_c_id"));
				classToBeUpdated.setClassName(userInputs.get(2).get(
						"new_c_name"));

				message = new String();
				boolean inDB = ClassDA.find(userInputs.get(0).get("c_id"));
				if (inDB) {
					boolean updated = ClassDA.update(
							userInputs.get(0).get("c_id"), classToBeUpdated);
					if (updated)
						message = "Courses is updated.\n";
					else
						message = "Courses can not be updated.\n";
				} else {
					message = "\nThis courseId is not in the database.\n";
				}

			}

		});
		/* SHOW CLASS INFO */
		this.classOperationsMap.put(24, new MapFunctionI() {

			@Override
			public void runCommand() {

				message = new String();
				boolean inDB = ClassDA.find(userInputs.get(0).get("c_id"));
				if (inDB) {
					ClassDA.get(userInputs.get(0).get("c_id"));
				} else {
					message = "\nClass is not in the database.\n";
				}

			}

		});
	}

}
