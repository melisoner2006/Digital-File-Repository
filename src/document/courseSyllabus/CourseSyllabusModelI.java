package document.courseSyllabus;

import java.util.List;
import java.util.Map;

public interface CourseSyllabusModelI {
	public void runInput(int functionNo, List<Map<String, String>> userInputs);
	public String returnMessage();
}
