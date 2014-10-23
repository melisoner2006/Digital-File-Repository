package user;

public class UserModel {
	
	private String name;
	private String globalId;
	private String password;
	private String privilage;
	
	public UserModel(String name, String globalId, String password, String privilage){
		setName(name);
		setGlobalId(globalId);
		setPassword(password);
		setPrivilage(privilage);
	}
	public UserModel(String name, String globalId, String privilage){
		this(name, globalId, null, privilage);
	}
	public UserModel(String globalId, String privilage){
		this(null, globalId, null, privilage);
	}
	public UserModel(){
		//to eliminate conflict in the Factory class
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGlobalId() {
		return globalId;
	}
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrivilage() {
		return privilage;
	}
	public void setPrivilage(String privilage) {
		this.privilage = privilage;
	}	
}
