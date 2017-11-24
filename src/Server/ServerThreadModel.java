package Server;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.crypto.interfaces.PBEKey;

import Observer.Observer;
import Observer.Subject;
import Protocol.Architecture_Protocol;
import Protocol.Communication_Protocol;
import Protocol.ReadWriteState_Protocol;

class ServerThreadModel implements Runnable,Subject{
	private Socket clientSocket=null;
	private HashMap<Observer,String> observers=null;
	private ServerView serverView=null;
	private DataInputStream socketInput=null;
	private String inputMsg="";
	private int heartBeatTime=30;
	
	private final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();  
	private final Lock readLock = readWriteLock.readLock();  
	private final Lock writeLock = readWriteLock.writeLock();  
	
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
//			this.clientSocket.setSoTimeout(60000);
			socketInput=new DataInputStream(this.clientSocket.getInputStream());
		} catch (IOException e) {
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
		 String[] tokens = inputMsg.split("\\"+Communication_Protocol.SPLIT_SIGN);
		 String headProtocol=tokens[0];
		 String msg=tokens[1];
		 String rearProtocol=tokens[2];
		 String architecture=null;
		 
		 if(headProtocol.startsWith(Communication_Protocol.M) && rearProtocol.endsWith(Communication_Protocol.M)) {
			 architecture=Architecture_Protocol.Model;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communication_Protocol.V) && rearProtocol.endsWith(Communication_Protocol.V)) {
			 architecture=Architecture_Protocol.View;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communication_Protocol.C) && rearProtocol.endsWith(Communication_Protocol.C)) {
			 architecture=Architecture_Protocol.Controller;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communication_Protocol.M_V) && rearProtocol.endsWith(Communication_Protocol.M_V)) {
			 architecture=Architecture_Protocol.Model;
			 notifyObserver(architecture,msg);
			 architecture=Architecture_Protocol.View;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communication_Protocol.M_C) && rearProtocol.endsWith(Communication_Protocol.M_C)) {
			 architecture=Architecture_Protocol.Model;
			 notifyObserver(architecture,msg);
			 architecture=Architecture_Protocol.Controller;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communication_Protocol.V_C) && rearProtocol.endsWith(Communication_Protocol.V_C)) {
			 architecture=Architecture_Protocol.View;
			 notifyObserver(architecture,msg);
			 architecture=Architecture_Protocol.Controller;
			 notifyObserver(architecture,msg);
		 }
		 else if(headProtocol.startsWith(Communication_Protocol.M_V_C) && rearProtocol.endsWith(Communication_Protocol.M_V_C)) {
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
		    	if(architecture==Architecture_Protocol.Model) {
		    		update(observer,msg);
		    	}
		    	else
		    		observer.update(msg);
		    }
		}
	}
	
	public void update(Observer observer,String msg) {
		String clientIP=clientSocket.getInetAddress().toString()+":"+clientSocket.getPort();
		String content=null;
		if(msg.equals(Communication_Protocol.READ)) {
			if(readLock.tryLock()) {
				content=Communication_Protocol.READING;
			}
			else {
				content=Communication_Protocol.HAVE_READED;
			}
		}
		else if(msg.equals(Communication_Protocol.CANCEL_READ)) {
			readLock.unlock();
			content=Communication_Protocol.CANCEL_READING;
		}
		else if(msg.equals(Communication_Protocol.WRITE)) {
			if(writeLock.tryLock()) {
				content=Communication_Protocol.WRITING;
			}
			else {
				
				content=Communication_Protocol.HAVE_WROTE;
			}

		}
		else if(msg.equals(Communication_Protocol.CANCEL_WRITE)) {
			writeLock.unlock();
			content=Communication_Protocol.CANCEL_WRITING;
		}
		msg=clientIP+Communication_Protocol.SPLIT_SIGN+content;
		observer.update(msg);
	}
}