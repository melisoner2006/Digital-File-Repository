package section;

import java.util.List;
import java.util.Map;

public interface SectionModelI {
	public void runInput(int functionNo, List<Map<String, String>> userInputs);
	public String returnMessage();
}
