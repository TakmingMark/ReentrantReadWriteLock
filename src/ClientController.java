import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientController {
	private ClientView clientView=null;
	private ClientModel clientModel=null;
	
	private ClientController(ClientView clientView, ClientModel clientModel) {
		initClientController();
	}
	
	public static ClientController getClientControllerObject(ClientView clientView, ClientModel clientModel) {
		return new ClientController(clientView,clientModel);
	}
	
	public void initClientController(){

	}
}
