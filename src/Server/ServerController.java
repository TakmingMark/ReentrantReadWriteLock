package Server;

import Observer.Observer;

public class ServerController implements Observer {
	private ServerView serverView;
	private ServerModel serverModel;
	
	private ServerController() {
	}
	
	public static ServerController getServerContorllerObject() {
		return new ServerController();
	}
	
	public void initServerController() {
		
	}
	
	@Override
	public void update(String msg) {
		serverView.update(msg);
	}
	
	@Override
	public void transport(String msg) {
		serverModel.transmitMsgBySocket(msg);
	}
	
	public void setServerView(ServerView serverView) {
		this.serverView = serverView;
	}

	public void setServerModel(ServerModel serverModel) {
		this.serverModel = serverModel;
	}
}
