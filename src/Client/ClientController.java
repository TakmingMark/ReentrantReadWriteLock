package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController {
	private ClientView clientView=null;
	private ClientModel clientModel=null;
	
	private ClientController(ClientView clientView, ClientModel clientModel) {
		this.clientView=clientView;
		this.clientModel=clientModel;
		initClientController();
	}
	
	public static ClientController getClientControllerObject(ClientView clientView, ClientModel clientModel) {
		return new ClientController(clientView,clientModel);
	}
	
	public void initClientController(){
		ClientAction clientAction=new ClientAction(clientModel);
		clientView.setReadButtonListener(clientAction);
		clientView.setCancelReadButtonListener(clientAction);
		clientView.setWriteButtonListener(clientAction);
		clientView.setCancelWriteButtonListener(clientAction);
	}
}

class ClientAction implements ActionListener{

	ClientModel clientModel;
	public ClientAction(ClientModel clientModel) {
		this.clientModel=clientModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		clientModel.buttonClick(e.getActionCommand());
	}
	
}
