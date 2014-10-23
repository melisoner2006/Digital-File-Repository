package logic;

import menu.*;
import system.*;
import classOperations.*;
import document.assignment.*;
import document.courseSyllabus.*;
import document.masterCourseSyllabus.*;
import document.studentRecord.*;
import section.*;


public abstract class AbstractMVCFactory {
	
	abstract public SystemModelI makeSysModel();
	abstract public SystemViewerI makeSysViewer(SystemModelI sysModel);
	
	abstract public MenuModelI makeMenuModel();
	abstract public MenuViewerI makeMenuViewer();
	
	abstract public ClassModelI makeClassModel();
	abstract public ClassViewerI makeClassViewer();
	
	abstract public SectionModelI makeSectionModel();
	abstract public SectionViewerI makeSectionViewer();
	
	abstract public AssignmentModelI makeAssignmentModel();
	abstract public AssignmentViewerI makeAssignmentViewer();

	abstract public McsModelI makeMCSmodel();
	abstract public McsViewerI makeMCSviewer();
	
	abstract public CourseSyllabusModelI makeCSmodel();
	abstract public CourseSyllabusViewerI makeCSviewer();
	
	abstract public StudentRecordModelI makeSRmodel();
	abstract public StudentRecordViewerI makeSRviewer();
}
