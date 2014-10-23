package system;

import user.UserModel;

public interface SystemModelI {
	
	public UserModel isUserInDB(String globalId);
//	void runInput(int functionNo);
}
