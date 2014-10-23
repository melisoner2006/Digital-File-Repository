package section;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import classOperations.ClassDA;

public class SectionModel implements SectionModelI {
	private String classId;
	private String crn;
	
	private Map<Integer, MapFunctionI> sectionOperationsMap;
	private List<Map<String,String>> userInputs;
	
	private String message;
	
	public SectionModel(String classId, String crn){
		setClassId(classId);
		setCrn(crn);
	}
	public SectionModel(){
		sectionOperationsMap = new HashMap<Integer, MapFunctionI>();
		setMap();
	}
	public String getClassId() {
		return classId;
	}
	public String getCrn() {
		return crn;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}
	

	@Override
	public void runInput(int functionNo, List<Map<String, String>> userInputs) {
		this.userInputs = userInputs;
		sectionOperationsMap.get(functionNo).runCommand();
		
	}
	@Override
	public String returnMessage() {
		return message;
	}
	
	private void setMap(){
		/*ADD NEW*/
		this.sectionOperationsMap.put(31, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				boolean classInDB = ClassDA.find(userInputs.get(0).get("c_id"));
				boolean sectionInDB = SectionDA.find(userInputs.get(1).get("crn"));
				
				if(sectionInDB){
					message = "\nThis section is in the database.\n";
				}
				if(!classInDB){
					message = "\nThis class is not in the database.\nPlease add this class first";
				}
				if(classInDB && !sectionInDB){
					boolean added = SectionDA.add(userInputs.get(0).get("c_id"), userInputs.get(1).get("crn"));
					if (added) message = "Courses is added to the database.\n";
					else message = "Courses can not be added to the database.\n";
				}
				
			}
		});
		/*DELETE*/
		this.sectionOperationsMap.put(32, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				boolean sectionInDB = SectionDA.find(userInputs.get(0).get("crn"));

				if(!sectionInDB){
					message = "\nThis section is not in the database.\n";
				}else{
					boolean deleted = SectionDA.delete(userInputs.get(0).get("crn"));
					if (deleted) message = "Section is deleted from the database.\n";
					else message = "Section can not be deleted from the database.\n";
				}
				
			}
		});
		/*UPDATE*/
		this.sectionOperationsMap.put(33, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				
				String crn = userInputs.get(0).get("crn");
				String new_c_id = userInputs.get(1).get("new_c_id");
				String new_crn = userInputs.get(2).get("new_crn");
				
				SectionModel sectionToBeUpdated = new SectionModel();
				sectionToBeUpdated.setClassId(new_c_id);
				sectionToBeUpdated.setCrn(new_crn);
				
				boolean sectionInDB = SectionDA.find(crn);
				boolean classInDB = ClassDA.find(new_c_id);
				
				if(!sectionInDB){
					message = "\nThis section is not in the database.\nPlease add the section first.";
				}
				if(!classInDB){
					message = "\nThis class is not in the database.\nPlease add this class first.";
				}
				if(classInDB && sectionInDB){
					boolean added = SectionDA.update(crn, sectionToBeUpdated);
					if (added) message = "Section is updated.\n";
					else message = "Section can not be updated.\n";
				}

				
			}
		});
		/*SHOW SECTION INFO*/
		this.sectionOperationsMap.put(34, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				String crn = userInputs.get(0).get("crn");
				boolean sectionInDB = SectionDA.find(crn);
				if(!sectionInDB){
					message = "Section is not in the database";
				}else{
					SectionDA.get(crn);
				}
				
			}
		});
		/*SHOW ALL SECTIONS IN CLASS ID*/
		this.sectionOperationsMap.put(35, new MapFunctionI() {
			
			@Override
			public void runCommand() {
				message = new String();
				String c_id = userInputs.get(0).get("c_id");
				boolean classInDB = ClassDA.find(c_id);
				if(!classInDB){
					message = "\nClass is not in the database";
				}
				else{
					Vector<SectionModel> sections = SectionDA.getAll(c_id);
					if(sections != null && sections.size() > 0){
						for(int i = 0; i<sections.size(); i++){
							message = message + sections.get(i).getClassId()+ " "+
									  sections.get(i).getCrn()+"\n";
						}
					}else{
						message = "\nThere is no section for this class in the database.\n";
					}
				}
				
			}
		});
	}
	
	
}
