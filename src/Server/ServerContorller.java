import com.sun.corba.se.spi.protocol.InitialServerRequestDispatcher;

public class ServerContorller {
	private ServerView serverView;
	private ServerModel serverModel;
	
	private ServerContorller(ServerView serverView,ServerModel serverModel) {
		this.serverView=serverView;
		this.serverModel=serverModel;
	}
	
	public static ServerContorller getServerContorllerObject(ServerView serverView,ServerModel serverModel) {
		return new ServerContorller(serverView,serverModel);
	}
	
	private void initServer() {
		
	}
}
