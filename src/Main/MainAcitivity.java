package Main;
import Client.ClientController;
import Client.ClientModel;
import Client.ClientThreadModel;
import Client.ClientView;
import Pattern.PatternController;
import Pattern.PatternModel;
import Pattern.PatternView;
import Server.ServerController;
import Server.ServerModel;
import Server.ServerView;
import Tools.ThreadPoolModel;

public class MainAcitivity {
	public static void main(String args[]) {
		MainAcitivity mainAcitivity=new MainAcitivity();
		mainAcitivity.initPattern();
	}
	
	public void initPattern() {
		PatternController patternController=PatternController.getPatternControllerObject();
		PatternView patternView=PatternView.getPatternViewObject();
		PatternModel patternModel=PatternModel.getPatternModelObject(patternController);
		
		patternController.setMainAcitivity(this);
		patternController.setPatternModel(patternModel);
		patternController.setPatternView(patternView);
		patternController.initPatternController();
	}
	
	public void initServer() {
		ServerController serverContorller=ServerController.getServerContorllerObject();
		ServerView serverView=ServerView.getServerViewObject();
		ThreadPoolModel threadPoolModel=ThreadPoolModel.getThreadPoolModelObject(20, 60000);
		ServerModel serverModel=ServerModel.getServerObject(serverContorller,threadPoolModel,5050); //發生位置的地方
		
		serverContorller.setServerView(serverView);
		serverContorller.setServerModel(serverModel);
		serverContorller.initServerController();
		
		Thread serverModelProcess =new Thread(serverModel);
		serverModelProcess.start();
		
	}
	
	public void initClient() {
		ClientController clientController=ClientController.getClientControllerObject();
		ClientView clientView=ClientView.getClientViewObject();
		ClientThreadModel clientThreadModel=null;
		ClientModel clientModel=ClientModel.getClientModelObject(clientController,"172.0.0.1",5050);
	
		clientController.setClientView(clientView);
		clientController.setClientModel(clientModel);
		clientController.initClientController();
	}
}
