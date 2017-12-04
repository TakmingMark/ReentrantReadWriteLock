package Server;

public class ServerController {
	private ServerView serverView;
	private ServerModel serverModel;
	
	private ServerController(ServerView serverView,ServerModel serverModel) {
		this.serverView=serverView;
		this.serverModel=serverModel;
	}
	
	public static ServerController getServerContorllerObject(ServerView serverView,ServerModel serverModel) {
		return new ServerController(serverView,serverModel);
	}
	
	private void initServer() {
		
	}
}
