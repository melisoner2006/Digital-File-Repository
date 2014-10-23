package menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import user.UserModel;

public class MenuModel implements MenuModelI{

	private static Item addUser, deleteUser, updateUser, showUser, showAllUsers;
	private static Item addClass, deleteClass, updateClass, showClass;
	private static Item addSection, deleteSection, updateSection, showSection,
			showAllSections;

	private static Item addMCSdoc, deleteMCSdoc, updateMCSdoc, showMCSdoc, retrieveMCSdoc;
	private static Item addCSdoc, deleteCSdoc, updateCSdoc, showCSdoc, retrieveCSdoc;
	private static Item addCAdoc, deleteCAdoc, updateCAdoc, showCAdoc, retrieveCAdoc;
	private static Item addSRdoc, deleteSRdoc, updateSRdoc, showSRdoc, retrieveSRdoc;
	
	static List<Item> userItems,classItems, sectionItems, assignItems, mcsItems, csItems, caItems, srItems;
	
	static int no; 
	static String name;

	public static Module userOps, classOps, sectionOps, mcsOps, csOps,
			caOps, srOps;

	public static Menu adminMenu, userMenu;
	
	Set<Integer> adminMenuSet = new HashSet<Integer>();
	Set<Integer> userMenuSet = new HashSet<Integer>();

	public MenuModel() {

		setAllMenuItems();
		setAllModules();
		setAdminMenu();
		setUserMenu();
		fillAdminMenuSet();
		fillUserMenuSet();

	}

	void setAllMenuItems() {
		addUser = new Item(11, "|____Add new user:11");
		deleteUser = new Item(12, "|____Delete user:12");
		updateUser = new Item(13, "|____Update use:13");
		showUser = new Item(14, "|____Show user info:14");
		showAllUsers = new Item(15, "|____Show all users:15");

		addClass = new Item(21, "|____Add new class:21");
		deleteClass = new Item(22, "|____Delete class:22");
		updateClass = new Item(23, "|____Update class:23");
		showClass = new Item(24, "|____Show class info:24");

		addSection = new Item(31, "|____Add new section:31");
		deleteSection = new Item(32, "|____Delete section:32");
		updateSection = new Item(33, "|____Update section:33");
		showSection = new Item(34, "|____Show section info:34");
		showAllSections = new Item(35, "|____Show all sections for a class:35");


		addMCSdoc = new Item(51, "|____Add new Master Course Syllabus:51");
		deleteMCSdoc = new Item(52, "|____Delete Master Course Syllabus:52");
		updateMCSdoc = new Item(53, "|____Update Master Course Syllabus:53");
		showMCSdoc = new Item(54, "|____Show Master Course Syllabus info:54");
		retrieveMCSdoc = new Item(55, "|____Retrieve Master Course Syllabus:55");

		addCSdoc = new Item(61, "|____Add new Course Syllabus:61");
		deleteCSdoc = new Item(62, "|____Delete Course Syllabus:62");
		updateCSdoc = new Item(63, "|____Update Course Syllabus:63");
		showCSdoc = new Item(64, "|____Show Course Syllabus info:64");
		retrieveCSdoc = new Item(65, "|____Retrieve Course Syllabus:65");

		addCAdoc = new Item(71, "|____Add new Course Assignment:71");
		deleteCAdoc = new Item(72, "|____Delete Course Assignment:72");
		updateCAdoc = new Item(73, "|____Update Course Assignment:73");
		showCAdoc = new Item(74, "|____Show Course Assignment info:74");
		retrieveCAdoc = new Item(75, "|____Retrieve Course Assignment:75");

		addSRdoc = new Item(81, "|____Add new Student Record:81");
		deleteSRdoc = new Item(82, "|____Delete Student Record:82");
		updateSRdoc = new Item(83, "|____Update Student Record:83");
		showSRdoc = new Item(84, "|____Show Student Record info:84");
		retrieveSRdoc = new Item(85, "|____Retrieve Student Record:85");

	}

	void setAllModules() {
		setUserOpsModule();
		setClassOpsModule();
		setSectionOpsModule();
		
		setMCSOpsModule();
		setCSOpsModule();
		setCAOpsModule();
		setSROpsModule();

	}
	
	static void setUserOpsModule(){
		no = 10;
		name = "|_User operations:10";
		userItems = new ArrayList<Item>();
		userItems.add(addUser);
		userItems.add(deleteUser);
		userItems.add(updateUser);
		userItems.add(showUser);
		userItems.add(showAllUsers);
		
		userOps = new Module(no, name, userItems);
	}
	
	static void setClassOpsModule(){
		no = 20;
		name = "|_Class operations:20";
		
		classItems = new ArrayList<Item>();
		classItems.add(addClass);
		classItems.add(deleteClass);
		classItems.add(updateClass);
		classItems.add(showClass);
		
		classOps = new Module(no, name, classItems);
	}
	

	static void setSectionOpsModule() {
		no = 30;
		name = "|_Section operations:30";
		ArrayList<Item> menuItems = new ArrayList<Item>();

		menuItems.add(addSection);
		menuItems.add(deleteSection);
		menuItems.add(updateSection);
		menuItems.add(showSection);
		menuItems.add(showAllSections);

		sectionOps = new Module(no, name, menuItems);
	}
	

	static void setMCSOpsModule() {
		no = 50;
		name = "|_Master Course Syllabus document-operations:50";
		ArrayList<Item> menuItems = new ArrayList<Item>();

		menuItems.add(addMCSdoc);
		menuItems.add(deleteMCSdoc);
		menuItems.add(updateMCSdoc);
		menuItems.add(showMCSdoc);
		menuItems.add(retrieveMCSdoc);

		mcsOps = new Module(no, name, menuItems);

	}

	static void setCSOpsModule() {
		no = 60;
		name = "|_Course Syllabus document-operations:60";
		ArrayList<Item> menuItems = new ArrayList<Item>();

		menuItems.add(addCSdoc);
		menuItems.add(deleteCSdoc);
		menuItems.add(updateCSdoc);
		menuItems.add(showCSdoc);
		menuItems.add(retrieveCSdoc);

		csOps = new Module(no, name, menuItems);
	}
	
	static void setCAOpsModule() {
		no = 70;
		name = "|_Course Assingment document-operations:70";
		ArrayList<Item> menuItems = new ArrayList<Item>();

		menuItems.add(addCAdoc);
		menuItems.add(deleteCAdoc);
		menuItems.add(updateCAdoc);
		menuItems.add(showCAdoc);
		menuItems.add(retrieveCAdoc);

		caOps = new Module(no, name, menuItems);

	}

	static void setSROpsModule() {
		no = 80;
		name = "|_Student Records document-operations:80";
		ArrayList<Item> menuItems = new ArrayList<Item>();

		menuItems.add(addSRdoc);
		menuItems.add(deleteSRdoc);
		menuItems.add(updateSRdoc);
		menuItems.add(showSRdoc);
		menuItems.add(retrieveSRdoc);

		srOps = new Module(no, name, menuItems);

	}

	static void setAdminMenu() {
		List<Module> adminMenuModules = new ArrayList<Module>();
		adminMenuModules.add(userOps);
		adminMenuModules.add(classOps);
		adminMenuModules.add(sectionOps);
		
		adminMenuModules.add(mcsOps);
		adminMenuModules.add(csOps);
		adminMenuModules.add(caOps);
		adminMenuModules.add(srOps);


		adminMenu = new Menu(1, "Admin menu", adminMenuModules);
	}
	
	static void setUserMenu() {
		List<Module> userMenuModules = new ArrayList<Module>();
		
		userMenuModules.add(mcsOps);
		userMenuModules.add(csOps);
		userMenuModules.add(caOps);
		userMenuModules.add(srOps);


		userMenu = new Menu(2, "User menu", userMenuModules);
	}
	
	void fillAdminMenuSet(){
		adminMenuSet.add(userOps.moduleNo);
		adminMenuSet.add(classOps.moduleNo);
		adminMenuSet.add(sectionOps.moduleNo);
		
		adminMenuSet.add(mcsOps.moduleNo);
		adminMenuSet.add(csOps.moduleNo);
		adminMenuSet.add(caOps.moduleNo);
		adminMenuSet.add(srOps.moduleNo);
	}
	
	void fillUserMenuSet(){

		userMenuSet.add(mcsOps.moduleNo);
		userMenuSet.add(csOps.moduleNo);
		userMenuSet.add(caOps.moduleNo);
		userMenuSet.add(srOps.moduleNo);
	}

	
	@Override
	public Menu setMenu(UserModel currentUser){
		if(currentUser.getPrivilage().equals("A")){
			return adminMenu;
		}else{
			return userMenu;
		}
	}
	

}
