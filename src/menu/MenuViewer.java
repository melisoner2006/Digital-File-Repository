package menu;

import java.util.Scanner;

public class MenuViewer implements MenuViewerI{
	Scanner scanner;
	
	public MenuViewer() {
		scanner = new Scanner(System.in);
	}

	@Override
	public void printMenu(Menu menu) {
		System.out.println("\n");
		for (int i = 0; i < menu.getMenuModules().size(); i++) {
			System.out.println(menu.getMenuModules().get(i).moduleName);
		}	
	}

	@Override
	public int askModule() {
		System.out.println("\nPlease select your operation:\n");
		return scanner.nextInt();
		
	}

	@Override
	public int askItem() {
		System.out.println("\nPlease select a function:\n");
		return scanner.nextInt();
	}

}
