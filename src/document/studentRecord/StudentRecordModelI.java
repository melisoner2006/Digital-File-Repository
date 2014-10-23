package document.studentRecord;

import java.util.List;
import java.util.Map;

public interface StudentRecordModelI {
	public void runInput(int functionNo, List<Map<String, String>> userInputs);
	public String returnMessage();
}
