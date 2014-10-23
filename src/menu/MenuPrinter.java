package menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import system.DatabaseConnectivity;

public class MenuPrinter {

	private Map<Integer, PrintModuleI> menuMap;

	public MenuPrinter() {
		menuMap = new HashMap<Integer, PrintModuleI>();
		setMenuMap();
	}
	public void runUserInput(int number){
		menuMap.get(number).runCommand();
	}
	
	void setMenuMap() {
		this.menuMap.put(10, new PrintModuleI() {

			List<Item> items = MenuModel.userOps.getModuleItems();
			@Override
			public void runCommand() {
				for (int i = 0; i < items.size(); i++) {
					System.out.println(items.get(i).name);
				}

			}

		});
		this.menuMap.put(20, new PrintModuleI() {

			List<Item> items = MenuModel.classOps.getModuleItems();
			@Override
			public void runCommand() {
				for (int i = 0; i < items.size(); i++) {
					System.out.println(items.get(i).name);
				}

			}

		});
		this.menuMap.put(30, new PrintModuleI() {

			List<Item> items = MenuModel.sectionOps.getModuleItems();
			@Override
			public void runCommand() {
				for (int i = 0; i < items.size(); i++) {
					System.out.println(items.get(i).name);
				}

			}

		});

		this.menuMap.put(50, new PrintModuleI() {

			List<Item> items = MenuModel.mcsOps.getModuleItems();
			@Override
			public void runCommand() {
				for (int i = 0; i < items.size(); i++) {
					System.out.println(items.get(i).name);
				}

			}

		});
		this.menuMap.put(60, new PrintModuleI() {

			List<Item> items = MenuModel.csOps.getModuleItems();
			@Override
			public void runCommand() {
				for (int i = 0; i < items.size(); i++) {
					System.out.println(items.get(i).name);
				}

			}

		});
		this.menuMap.put(70, new PrintModuleI() {

			List<Item> items = MenuModel.caOps.getModuleItems();
			@Override
			public void runCommand() {
				for (int i = 0; i < items.size(); i++) {
					System.out.println(items.get(i).name);
				}

			}

		});
		
		this.menuMap.put(80, new PrintModuleI() {

			List<Item> items = MenuModel.srOps.getModuleItems();
			@Override
			public void runCommand() {
				for (int i = 0; i < items.size(); i++) {
					System.out.println(items.get(i).name);
				}

			}

		});
		
		this.menuMap.put(-1, new PrintModuleI() {

			@Override
			public void runCommand() {
				DatabaseConnectivity.terminate();
			}

		});

	}
}
