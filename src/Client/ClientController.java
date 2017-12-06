package Client;
import Observer.Observer;
import Protocol.ReadWriteState_Protocol;

public class ClientController implements Observer {
	private ClientView clientView=null;
	private ClientModel clientModel=null;
	
	private ClientController() {
	}
	
	public static ClientController getClientControllerObject() {
		return new ClientController();
	}
	
	public void initClientController(){
		clientView.getReadButton().addActionListener(e -> pressReadButton());
		clientView.getCancelReadButton().addActionListener(e -> pressCancelReadButton());
		clientView.getWriteButton().addActionListener(e -> pressWriteButton());
		clientView.getCancelWriteButton().addActionListener(e -> pressCancelWriteButton());
	}
	
	@Override
	public void update(String msg) {
		clientView.update(msg);
	}

	@Override
	public void transport(String msg) {
	}
	
	private void pressReadButton() {
		clientView.setReadButtonStatus();
		clientModel.transmitMsgBySocket(ReadWriteState_Protocol.READ);
	}
	
	private void pressCancelReadButton() {
		clientView.setCancelReadButtonStatus();
		clientModel.transmitMsgBySocket(ReadWriteState_Protocol.CANCEL_READ);
	}
	
	private void pressWriteButton() {
		clientView.setWriteButtonStatus();
		clientModel.transmitMsgBySocket(ReadWriteState_Protocol.WRITE);
	}
	
	private void pressCancelWriteButton() {
		clientView.setCancelWriteButtonStatus();
		clientModel.transmitMsgBySocket(ReadWriteState_Protocol.CANCEL_WRITE);
	}
	
	public void setClientView(ClientView clientView) {
		this.clientView = clientView;
	}

	public void setClientModel(ClientModel clientModel) {
		this.clientModel = clientModel;
	}
}

