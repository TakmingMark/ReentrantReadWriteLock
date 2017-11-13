package Tools;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserList<UserIP,UserOutputStream> {
	private HashMap<UserIP, UserOutputStream> userList=new HashMap<UserIP, UserOutputStream>();
	
	public boolean addUser(UserIP userIP,UserOutputStream userOutputStream) {
		if(checkRepeat(userIP)) {
			return true;
		}
		else {
			userList.put(userIP, userOutputStream);
			return false;
		}
	}
	
	public List<UserIP> getIPList() {
		List<UserIP> iPList=new ArrayList<UserIP>(userList.keySet());
		return iPList;
	}
	
	public List<UserOutputStream> getOutPutStreamList() {
		List<UserOutputStream> outPutStreamList=new ArrayList<UserOutputStream>(userList.values());
		return outPutStreamList;
	}
	
	public void moveUserByIP(UserIP userIP) {
		userList.remove(userIP);
	}
	
	public boolean checkRepeat(UserIP userIP) {
		for(UserIP element:userList.keySet()) 
			if(element.equals(userIP))
				return true;
			return false;
	}
}
