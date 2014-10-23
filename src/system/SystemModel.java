package system;

import user.UserDA;
import user.UserModel;

public class SystemModel implements SystemModelI {
	
	public SystemModel(){
	}

	@Override
	public UserModel isUserInDB(String globalId) {
		UserModel currentUser = UserDA.find(globalId);
		return currentUser;
		
	}

}
