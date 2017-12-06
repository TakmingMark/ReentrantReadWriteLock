package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import Observer.Observer;
import Observer.Subject;
import Protocol.Architecture_Protocol;
import Protocol.Communication_Protocol;

public class ClientThreadModel implements Runnable, Subject {

	private Socket clientSocket = null;
	private Map<String,Observer> observers = null;
	public int listenPort = 0;
	private DataInputStream socketInput = null;
	private String inputMsg = "";

	private ClientThreadModel(Socket clientSocket) {
		this.clientSocket = clientSocket;
		initClientThreadModel();
	}

	public static ClientThreadModel getClientThreadModelObject(Socket clientSocket) {
		return new ClientThreadModel(clientSocket);
	}

	private void initClientThreadModel() {
		observers = new HashMap<>();
		listenPort = clientSocket.getPort();
		try {
			socketInput = new DataInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String architecture=Architecture_Protocol.Controller;
		notifyObserver(architecture, "Client connect..." + "\r\n");
		
		receiveMsgBySocket();
	}

	@Override
	public void attach(String architecture,Observer observer) {
		observers.put(architecture,observer);
	}

	@Override
	public void detach(String architecture) {
		observers.remove(architecture);
	}
	
	@Override
	public void notifyObserver(String architecture,String msg) {
		 Observer observer=observers.get(architecture);
		 observer.update(msg);
	}
	
	private void receiveMsgBySocket() {
		try {
			while ((inputMsg = socketInput.readUTF()) != null) {
				processInputMsg(inputMsg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processInputMsg(String inputMsg) {
		String[] tokens = inputMsg.split("\\" + Communication_Protocol.SPLIT_SIGN);
		String protocol = tokens[0];
		String msg = tokens[1];
		String architecture = null;

		switch (protocol) {
		case Communication_Protocol.CLIENT_CONTROLLER:
			architecture=Architecture_Protocol.Controller;
			 this.notifyObserver(architecture,msg);
			break;
		default:
			break;
		}
	}
}
