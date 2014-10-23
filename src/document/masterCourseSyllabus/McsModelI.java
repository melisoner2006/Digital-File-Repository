package document.masterCourseSyllabus;

import java.util.List;
import java.util.Map;

public interface McsModelI {
	public void runInput(int functionNo, List<Map<String, String>> userInputs);
	public String returnMessage();
}
