package classOperations;

import java.util.List;
import java.util.Map;


public interface ClassViewerI {
	List<Map<String, String>> runInput(int functionNo);
	int passFunctionCode(int functionNo);
	void printConsoleMessage(String message);
}
