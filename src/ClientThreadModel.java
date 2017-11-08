
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientThreadModel implements Runnable,Subject{

	private Socket clientSocket=null;
	private Map<Observer, Architecture> observers=null;
	private String IPAddress="";
	public int listenPort=0;
	private DataInputStream socketInput=null;
	private String inputMsg="";
	
	private ClientThreadModel(Socket clientSocket) {
		this.clientSocket=clientSocket;
		initClientThreadModel();
	}
	
	public static ClientThreadModel getClientThreadModelObject(Socket clientSocket) {
		return new ClientThreadModel(clientSocket);
	}
	
	private  void initClientThreadModel() {
		observers=new HashMap<>();
		IPAddress=clientSocket.getLocalAddress().getHostAddress();
		listenPort=clientSocket.getPort();
		try {
			socketInput=new DataInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		notifyObserver(Architecture.View,"Client connect..."+"\r\n");
		receiveMsgBySocket();
	}
	
	
	private void receiveMsgBySocket() {
		try {
			while((inputMsg=socketInput.readUTF())!=null){
				processInputMsg(inputMsg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processInputMsg(String inputMsg) {
		 String[] tokens = inputMsg.split(":");
		 String headProtocol=tokens[0];
		 String msg=tokens[1];
		 String rearProtocol=tokens[2];
		 Architecture architecture=null;
		 
		 if(headProtocol.startsWith("1") && rearProtocol.startsWith("1")) {
			 architecture=Architecture.Controller;
		 }
		 else if(headProtocol.startsWith("2") && rearProtocol.startsWith("2")){ 
			 architecture=Architecture.View;
		 }
		 else if(headProtocol.startsWith("3") && rearProtocol.startsWith("3")){
			 architecture=Architecture.Model;
		 }
		 
		 notifyObserver(architecture,inputMsg);
//		clientView.updatejTextArea(msg+"\r\n");
	}

	@Override
	public void attach(Observer observer,Architecture architecture) {
		observers.put(observer, architecture);
	}

	@Override
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObserver(Architecture action,String msg) {
		
		for(Map.Entry<Observer, Architecture> element : observers.entrySet()) {
		    Observer observer = element.getKey();
		    Architecture architecture = element.getValue();
		    
		    if(architecture==action) {
		    	observer.update(msg);
		    }
		    else if(architecture==action) {
		    	observer.update(msg);
		    }
		    else if(architecture==action) {
		    	observer.update(msg);
		    }
		}
	}
}
