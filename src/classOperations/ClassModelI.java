package classOperations;

import java.util.List;
import java.util.Map;

public interface ClassModelI {
	
	public void runInput(int functionNo, List<Map<String, String>> userInputs);
	public String returnMessage();
	
}
