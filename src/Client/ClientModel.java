package Client;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Protocol.Architecture_Protocol;
import Protocol.Communication_Protocol;

public class ClientModel{

	private ClientController clientController=null;
	private String IPAddress="";
	private int ListenPort=0;
	
	private ClientThreadModel clientThreadModel=null;
	private Thread clientThread=null;
	private Socket clientSocket=null;
	private DataOutputStream socketOutput=null;
	
	private ClientModel(ClientController clientController,String IPAddress,int ListenPort) {
		this.clientController=clientController;
		this.IPAddress=IPAddress;
		this.ListenPort=ListenPort;
		initClientSocket();
	}
	
	public static ClientModel getClientModelObject(ClientController clientController,String IPAddress,int ListenPort) {
		return new ClientModel(clientController,IPAddress,ListenPort);
	}
	
	private void initClientSocket() {
		try {
			clientSocket=new Socket(IPAddress, ListenPort);
			socketOutput=new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clientThreadModel=ClientThreadModel.getClientThreadModelObject(clientSocket);
		clientThreadModel.attach(Architecture_Protocol.Controller,clientController);
		clientThread=new Thread(clientThreadModel);
		clientThread.start();
	}

	public void transmitMsgBySocket(String msg) {
		try {
			socketOutput.writeUTF(Communication_Protocol.SERVER_CONTROLLER+Communication_Protocol.SPLIT_SIGN+msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
