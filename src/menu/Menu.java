package menu;

import java.util.List;

public class Menu {
	
	 int menuNo;
	 String menuName;
	 List<Module> menuModules;

	public Menu(int no, String name, List<Module> menuModules) {
		setMenuNo(no);
		setMenuName(name);
		setMenuModules(menuModules);
	}

	private void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	private void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	private  void setMenuModules(List<Module> menuModules2) {
		menuModules = menuModules2;
	}

	public  int getMenuNo() {
		return menuNo;
	}

	public  String getMenuName() {
		return menuName;
	}

	public List<Module> getMenuModules() {
		return menuModules;
	}
	
	
	
}
