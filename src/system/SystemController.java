package system;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import logic.AbstractMVCFactory;
import menu.Menu;
import menu.MenuPrinter;
import menu.MenuViewerI;
import classOperations.*;
import document.assignment.*;
import document.courseSyllabus.*;
import document.document.*;
import document.masterCourseSyllabus.*;
import document.studentRecord.*;
import section.*;
import user.*;

public class SystemController {
	static int attemptCounter = 0;
	
	private static ClassModelI classModel;
	private static ClassViewerI classViewer;

	private static SectionModelI sectionModel;
	private static SectionViewerI sectionViewer;

	private static AssignmentModelI assignmentModel;
	private static AssignmentViewerI assignmentViewer;

	private static McsModelI mcsModel;
	private static McsViewerI mcsViewer;

	private static CourseSyllabusModelI csModel;
	private static CourseSyllabusViewerI csViewer;

	private static StudentRecordModelI srModel;
	private static StudentRecordViewerI srViewer;
	
	private static MenuViewerI menuViewer;
	private static MenuPrinter menuPrinter;

	static int moduleNo, functionNo;
	
	public SystemController(AbstractMVCFactory objs, Connection aConnection) {
		
		classModel = objs.makeClassModel();
		classViewer = objs.makeClassViewer();

		sectionModel = objs.makeSectionModel();
		sectionViewer = objs.makeSectionViewer();

		assignmentModel = objs.makeAssignmentModel();
		assignmentViewer = objs.makeAssignmentViewer();

		mcsModel = objs.makeMCSmodel();
		mcsViewer = objs.makeMCSviewer();

		csModel = objs.makeCSmodel();
		csViewer = objs.makeCSviewer();

		srModel = objs.makeSRmodel();
		srViewer = objs.makeSRviewer();

		menuViewer = objs.makeMenuViewer();
		menuPrinter = new MenuPrinter();
		
		aConnection = DatabaseConnectivity.initialize();
		
		new UserDA(aConnection);
		new ClassDA(aConnection);
		new SectionDA(aConnection);
		new AssignmentDA(aConnection);
		new McsDA(aConnection);
		new CourseSyllabusDA(aConnection);
		new DocumentDA(aConnection);
		new BackupDocumentDA(aConnection);
		new StudentRecordDA(aConnection);


	
	}
	

	public static UserModel login(SystemModelI sysModel, SystemViewerI sysViewer) {

		UserModel currentUser = sysModel.isUserInDB(sysViewer.askGlobalId());

		if (currentUser == null) {
			attemptCounter++;
			if (attemptCounter == 1 || attemptCounter == 2) {

				System.out.println("Attempt is not successful."
						+ "Your username or password "
						+ "is wrong. Please try again.\n");
			SystemController.login(sysModel, sysViewer);	
			}
			else{
				System.out.println("Attempt 3 was not successful."
						+ "Please contact your system admin!");
				DatabaseConnectivity.terminate();
			}
		}

		return currentUser;
	}
	

	public static void runMenu(int moduleNo, int functionNo) {

		if (moduleNo == 20) {
			List<Map<String, String>> inputs = classViewer.runInput(functionNo);
			classModel.runInput(functionNo, inputs);
			classViewer.printConsoleMessage(classModel.returnMessage());
		}
		if (moduleNo == 30) {
			List<Map<String, String>> inputs = sectionViewer
					.runInput(functionNo);
			sectionModel.runInput(functionNo, inputs);
			sectionViewer.printConsoleMessage(sectionModel.returnMessage());
		}

		if (moduleNo == 50) {
			List<Map<String, String>> inputs = mcsViewer.runInput(functionNo);
			mcsModel.runInput(functionNo, inputs);
			mcsViewer.printConsoleMessage(mcsModel.returnMessage());
		}
		if (moduleNo == 60) {
			List<Map<String, String>> inputs = csViewer.runInput(functionNo);
			csModel.runInput(functionNo, inputs);
			csViewer.printConsoleMessage(csModel.returnMessage());
		}
		if (moduleNo == 70) {
			List<Map<String, String>> inputs = assignmentViewer
					.runInput(functionNo);
			assignmentModel.runInput(functionNo, inputs);
			assignmentViewer.printConsoleMessage(assignmentModel
					.returnMessage());
		}

		if (moduleNo == 80) {
			List<Map<String, String>> inputs = srViewer.runInput(functionNo);
			srModel.runInput(functionNo, inputs);
			srViewer.printConsoleMessage(srModel.returnMessage());
		}

	}
	public static void exit(){
		DatabaseConnectivity.terminate();
	}
	
	public static void invokeMenu(Menu menu) {

		menuViewer.printMenu(menu);
		moduleNo = menuViewer.askModule();
		if(moduleNo != -1){
			menuPrinter.runUserInput(moduleNo);
			functionNo = menuViewer.askItem();	
			if(functionNo != -1){
				SystemController.runMenu(moduleNo, functionNo);
				invokeMenu(menu);
			}
			else{
				SystemController.exit();
			}
		}
		else{
			SystemController.exit();
		}
		
	}
	
}
