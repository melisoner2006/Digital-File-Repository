package document.studentRecord;

import java.util.List;
import java.util.Map;

public interface StudentRecordViewerI {
	List<Map<String, String>> runInput(int functionNo);
	int passFunctionCode(int functionNo);
	void printConsoleMessage(String message);
}
