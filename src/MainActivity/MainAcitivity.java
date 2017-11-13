import java.util.List;

public class MainAcitivity {
	public static void main(String args[]) {
		MainAcitivity mainAcitivity=new MainAcitivity();
		mainAcitivity.initPattern();
	}
	
	public void initPattern() {
		PatternView patternView=PatternView.getPatternViewObject();
		PatternModel patternModel=PatternModel.getPatternModelObject(this,patternView);
		PatternController patternController=PatternController.getPatternControllerObject(patternView, patternModel);
	}
	
	public void initServer() {
		ServerView serverView=ServerView.getServerViewObject();
		ThreadPoolModel threadPoolModel=ThreadPoolModel.getThreadPoolModelObject(4, 60000);
		ServerModel serverModel=ServerModel.getServerObject(5050,threadPoolModel,serverView); //發生位置的地方
		Thread serverModelProcess =new Thread(serverModel);
		serverModelProcess.start();
		ServerContorller serverContorller=ServerContorller.getServerContorllerObject(serverView, serverModel);
	}
	
	public void initClient() {
		ClientView clientView=ClientView.getClientViewObject();
		ClientModel clientModel=ClientModel.getClientModelObject(clientView,"163.19.227.78",5050);
		ClientController clientController=ClientController.getClientControllerObject(clientView, clientModel);
	}
}