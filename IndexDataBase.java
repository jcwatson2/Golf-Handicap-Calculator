package application;

import java.util.HashMap;

public class IndexDataBase {
	HashMap<String, String> indexs;
	
	public IndexDataBase() {
		indexs = new HashMap<String, String>();
	}
	
	public boolean checkForIndex(String username, String pw) {
		if(!indexs.containsKey(username))return false;
		else return true;
	}

	public void addNewIndex(String username, String pw) {
		indexs.put(username, pw);
	}
	
	public boolean containsUsername(String user) {
		if(indexs.containsKey(user)) return true;
		else return false;
	}

}
