
public class MainAcitivity {
	public static void main(String args[]) {
		MainAcitivity mainAcitivity=new MainAcitivity();
		mainAcitivity.initPattern();
		
		
//		mainAcitivity.initServer();  在這直接呼叫是沒問題的
	}
	
	public void initPattern() {
		PatternView patternView=PatternView.getPatternViewObject();
		PatternModel patternModel=PatternModel.getPatternModelObject();
		PatternController patternController=PatternController.getPatternControllerObject(this,patternView, patternModel);
	}
	
	public void initServer() {
		ServerView serverView=ServerView.getServerViewObject();
		ThreadPoolModel threadPoolModel=ThreadPoolModel.getThreadPoolModelObject(4, 60000);
		ServerModel serverModel=ServerModel.getServerObject(5050,threadPoolModel); //發生位置的地方
		Thread serverModelThread =new Thread(serverModel);
		serverModelThread.start();
		ServerContorller serverContorller=ServerContorller.getServerContorllerObject(serverView, serverModel);
	}
	
	public void initClient() {
		ClientView clientView=ClientView.getClientViewObject();
		ClientModel clientModel=ClientModel.getClientModelObject("163.19.227.78",5050);
	}

}
