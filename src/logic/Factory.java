package logic;

import classOperations.*;
import document.assignment.*;
import document.courseSyllabus.*;
import document.masterCourseSyllabus.*;
import document.studentRecord.*;
import menu.*;
import section.*;
import system.*;

public class Factory extends AbstractMVCFactory {

	@Override
	public SystemModelI makeSysModel() {
		SystemModelI aModel = new SystemModel();
		return aModel;
	}

	@Override
	public SystemViewerI makeSysViewer(SystemModelI model) {
		SystemViewerI aViewer = new SystemViewer();
		return aViewer;
	}

	@Override
	public MenuViewerI makeMenuViewer() {
		MenuViewerI aViewer = new MenuViewer();
		return aViewer;
	}

	@Override
	public MenuModelI makeMenuModel() {
		MenuModelI aModel = new MenuModel();
		return aModel;
	}

	@Override
	public ClassModelI makeClassModel() {
		ClassModelI aModel = new ClassModel();
		return aModel;
	}

	@Override
	public ClassViewerI makeClassViewer() {
		ClassViewerI aViewer = new ClassViewer();
		return aViewer;
	}

	@Override
	public SectionModelI makeSectionModel() {
		SectionModelI aModel = new SectionModel();
		return aModel;
	}

	@Override
	public SectionViewerI makeSectionViewer() {
		SectionViewerI aViewer = new SectionViewer();
		return aViewer;
	}

	@Override
	public AssignmentModelI makeAssignmentModel() {
		AssignmentModelI aModel = new AssignmentModel();
		return aModel;
	}

	@Override
	public AssignmentViewerI makeAssignmentViewer() {
		AssignmentViewerI aViewer = new AssignmentViewer();
		return aViewer;
	}

	@Override
	public McsModelI makeMCSmodel() {
		McsModelI aModel = new McsModel();
		return aModel;
	}

	@Override
	public McsViewerI makeMCSviewer() {
		McsViewerI aViewer = new McsViewer();
		return aViewer;
	}

	@Override
	public CourseSyllabusModelI makeCSmodel() {
		CourseSyllabusModelI aModel = new CourseSyllabusModel();
		return aModel;
	}

	@Override
	public CourseSyllabusViewerI makeCSviewer() {
		CourseSyllabusViewerI aViewer = new CourseSyllabusViewer();
		return aViewer;
	}

	@Override
	public StudentRecordModelI makeSRmodel() {
		StudentRecordModelI aModel = new StudentRecordModel();
		return aModel;
	}

	@Override
	public StudentRecordViewerI makeSRviewer() {
		StudentRecordViewerI aViewer = new StudentRecordViewer();
		return aViewer;
	}

}
