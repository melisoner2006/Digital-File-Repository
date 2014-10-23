package logic;

import java.sql.Connection;

import menu.*;
import system.*;
import user.*;

public class MainController {

	private static Connection aConnection;

	private static SystemModelI sysModel;
	private static SystemViewerI sysViewer;
	private static MenuModelI menuModel;
	private static UserModel user;
	private static Menu menu;
	static boolean exit = false;

	public MainController() {
		AbstractMVCFactory objs = new Factory();
		menuModel = objs.makeMenuModel();	
		sysModel = objs.makeSysModel();
		sysViewer = objs.makeSysViewer(sysModel);
		new SystemController(objs, aConnection);
		menuModel = new MenuModel();

	}
	
	public static void main(String[] args) {
		new MainController();
		user = SystemController.login(sysModel, sysViewer);
		menu = menuModel.setMenu(user);
		SystemController.invokeMenu(menu);
	}
}
