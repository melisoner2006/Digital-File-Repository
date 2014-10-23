package system;

import java.util.Scanner;

public class SystemViewer implements SystemViewerI {

	private Scanner scanner;

	public SystemViewer() {
		scanner = new Scanner(System.in);
	}

	@Override
	public String askGlobalId() {
		String globalId;
		System.out.println("Please enter your globalId:");
		globalId = scanner.next();
		return globalId;
	}

}
