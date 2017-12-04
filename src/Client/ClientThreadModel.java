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
	private Map<Observer, String> observers = null;
	private String IPAddress = "";
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
		IPAddress = clientSocket.getLocalAddress().getHostAddress();
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
		notifyObserver(Architecture_Protocol.View, "Client connect..." + "\r\n");
		receiveMsgBySocket();
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
//		System.out.println(inputMsg);
		String[] tokens = inputMsg.split("\\" + Communication_Protocol.SPLIT_SIGN);
		String headProtocol = tokens[0];
		String msg = tokens[1];
		String rearProtocol = tokens[2];
		String architecture = null;

		if (headProtocol.startsWith(Communication_Protocol.M) && rearProtocol.endsWith(Communication_Protocol.M)) {
			architecture = Architecture_Protocol.Model;
			notifyObserver(architecture, msg);
		} else if (headProtocol.startsWith(Communication_Protocol.V)
				&& rearProtocol.endsWith(Communication_Protocol.V)) {
			architecture = Architecture_Protocol.View;
			notifyObserver(architecture, msg);
		} else if (headProtocol.startsWith(Communication_Protocol.C)
				&& rearProtocol.endsWith(Communication_Protocol.C)) {
			architecture = Architecture_Protocol.Controller;
			notifyObserver(architecture, msg);
		} else if (headProtocol.startsWith(Communication_Protocol.M_V)
				&& rearProtocol.endsWith(Communication_Protocol.M_V)) {
			architecture = Architecture_Protocol.Model;
			notifyObserver(architecture, msg);
			architecture = Architecture_Protocol.View;
			notifyObserver(architecture, msg);
		} else if (headProtocol.startsWith(Communication_Protocol.M_C)
				&& rearProtocol.endsWith(Communication_Protocol.M_C)) {
			architecture = Architecture_Protocol.Model;
			notifyObserver(architecture, msg);
			architecture = Architecture_Protocol.Controller;
			notifyObserver(architecture, msg);
		} else if (headProtocol.startsWith(Communication_Protocol.V_C)
				&& rearProtocol.endsWith(Communication_Protocol.V_C)) {
			architecture = Architecture_Protocol.View;
			notifyObserver(architecture, msg);
			architecture = Architecture_Protocol.Controller;
			notifyObserver(architecture, msg);
		} else if (headProtocol.startsWith(Communication_Protocol.M_V_C)
				&& rearProtocol.endsWith(Communication_Protocol.M_V_C)) {
			architecture = Architecture_Protocol.Model;
			notifyObserver(architecture, msg);
			architecture = Architecture_Protocol.View;
			notifyObserver(architecture, msg);
			architecture = Architecture_Protocol.Controller;
			notifyObserver(architecture, msg);
		}
	}

	@Override
	public void attach(Observer observer, String architecture) {
		observers.put(observer, architecture);
	}

	@Override
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObserver(String action, String msg) {

		for (Map.Entry<Observer, String> element : observers.entrySet()) {
			Observer observer = element.getKey();
			String architecture = element.getValue();

			if (architecture == action) {
				observer.update(msg);
			} else if (architecture == action) {
				observer.update(msg);
			} else if (architecture == action) {
				observer.update(msg);
			}
		}
	}
}
