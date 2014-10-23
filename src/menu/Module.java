package menu;

import java.util.List;

public class Module {
	
	 int moduleNo;
	 String moduleName;
	 List<Item> moduleItems;
	
	public Module(int no, String name, List<Item> userItems){
		this.moduleNo = no;
		this.moduleName = name;
		this.moduleItems = userItems;
	}

	public int getModuleNo() {
		return moduleNo;
	}

	public void setModuleNo(int moduleNo) {
		this.moduleNo = moduleNo;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<Item> getModuleItems() {
		return moduleItems;
	}

	public void setModuleItems(List<Item> moduleItems) {
		this.moduleItems = moduleItems;
	}

}
