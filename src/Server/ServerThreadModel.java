package Server;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import Observer.Observer;
import Observer.Subject;
import Protocol.Architecture_Protocol;
import Protocol.Communcation_Protocol;

class ServerThreadModel implements Runnable,Subject{
	private Socket clientSocket=null;
	private HashMap<Observer,String> observers=null;
	private ServerView serverView=null;
	private DataInputStream socketInput=null;
	private String inputMsg="";
	private int heartBeatTime=30;
	
	private ServerThreadModel(Socket clientSocket,ServerView serverView){
		this.clientSocket=clientSocket;
		this.serverView=serverView;
		initServerThreadModel();
    }
	
	public static ServerThreadModel getServerThreadModelObject(Socket clientSocket,ServerView serverView) {
		return new ServerThreadModel(clientSocket,serverView);
	}
	
	private void initServerThreadModel() {
		observers=new HashMap<>();
		try {
			this.clientSocket.setSoTimeout(60000);
			socketInput=new DataInputStream(this.clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		receiveMsgBySocket();
	}

	private void receiveMsgBySocket() {
		try{
			while((inputMsg=socketInput.readUTF())!=null){
				processInputMsg(inputMsg);
				}
			}
		catch (SocketException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void processInputMsg(String inputMsg) {
		 String[] tokens = inputMsg.split(Communcation_Protocol.SPLIT_SIGN);
		 String headProtocol=tokens[0];
		 String msg=tokens[1];
		 String rearProtocol=tokens[2];
		 String architecture=null;
		 
		 if(headProtocol.startsWith(Communcation_Protocol.M) && rearProtocol.endsWith(Communcation_Protocol.M)) {
			 architecture=Architecture_Protocol.Model;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communcation_Protocol.V) && rearProtocol.endsWith(Communcation_Protocol.V)) {
			 architecture=Architecture_Protocol.View;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communcation_Protocol.C) && rearProtocol.endsWith(Communcation_Protocol.C)) {
			 architecture=Architecture_Protocol.Controller;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communcation_Protocol.M_V) && rearProtocol.endsWith(Communcation_Protocol.M_V)) {
			 architecture=Architecture_Protocol.Model;
			 notifyObserver(architecture,msg);
			 architecture=Architecture_Protocol.View;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communcation_Protocol.M_C) && rearProtocol.endsWith(Communcation_Protocol.M_C)) {
			 architecture=Architecture_Protocol.Model;
			 notifyObserver(architecture,msg);
			 architecture=Architecture_Protocol.Controller;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communcation_Protocol.V_C) && rearProtocol.endsWith(Communcation_Protocol.V_C)) {
			 architecture=Architecture_Protocol.View;
			 notifyObserver(architecture,msg);
			 architecture=Architecture_Protocol.Controller;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communcation_Protocol.M_V_C) && rearProtocol.endsWith(Communcation_Protocol.M_V_C)) {
			 architecture=Architecture_Protocol.Model;
			 notifyObserver(architecture,msg);
			 architecture=Architecture_Protocol.View;
			 notifyObserver(architecture,msg);
			 architecture=Architecture_Protocol.Controller;
			 notifyObserver(architecture,msg);
		 }
	}

	@Override
	public void attach(Observer observer,String architecture) {
		observers.put(observer, architecture);
	}

	@Override
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObserver(String action,String msg) {
		
		for(Map.Entry<Observer, String> element : observers.entrySet()) {
		    Observer observer = element.getKey();
		    String architecture = element.getValue();
		    
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