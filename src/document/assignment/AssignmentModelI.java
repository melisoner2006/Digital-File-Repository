package document.assignment;

import java.util.List;
import java.util.Map;

public interface AssignmentModelI {
	public void runInput(int functionNo, List<Map<String, String>> userInputs);
	public String returnMessage();
}
