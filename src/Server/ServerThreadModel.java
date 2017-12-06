package Server;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import Observer.Observer;
import Observer.Subject;
import Protocol.Architecture_Protocol;
import Protocol.Communication_Protocol;
import Protocol.ReadWriteState_Protocol;

class ServerThreadModel implements Runnable,Subject{
	private Socket clientSocket=null;
	private HashMap<String,Observer> observers=null;
	private DataInputStream socketInput=null;
	private String inputMsg="";
//	private int heartBeatTime=30;
	
	private final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();  
	private final Lock readLock = readWriteLock.readLock();  
	private final Lock writeLock = readWriteLock.writeLock();  
	
	private ServerThreadModel(Socket clientSocket){
		this.clientSocket=clientSocket;
		initServerThreadModel();
    }
	
	public static ServerThreadModel getServerThreadModelObject(Socket clientSocket) {
		return new ServerThreadModel(clientSocket);
	}
	
	private void initServerThreadModel() {
		observers=new HashMap<>();
		try {
			this.clientSocket.setSoTimeout(60000);
			socketInput=new DataInputStream(this.clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
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
		 observer.transport(msg);
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
		 String protocol=tokens[0];
		 String msg=tokens[1];
		 String architecture="";
		 
		switch (protocol) {
		case Communication_Protocol.SERVER_CONTROLLER:
			architecture=Architecture_Protocol.Controller;
			 this.updateServerState(architecture,msg);
			break;
		default:
			break;
		}
		 
	}

	public void updateServerState(String architecture,String msg) {
		String clientIP=clientSocket.getInetAddress().toString()+":"+clientSocket.getPort();
		String content=null;

		switch (msg) {
		case ReadWriteState_Protocol.READ:
			if(readLock.tryLock()) 
				content=ReadWriteState_Protocol.READING;
			else
				content=ReadWriteState_Protocol.HAVE_READED;
			break;
		case ReadWriteState_Protocol.CANCEL_READ:
			readLock.unlock();
			content=ReadWriteState_Protocol.CANCEL_READING;
			break;
		case ReadWriteState_Protocol.WRITE:
			if(writeLock.tryLock())
				content=ReadWriteState_Protocol.WRITING;
			else 
				content=ReadWriteState_Protocol.HAVE_WROTE;
			break;
		case ReadWriteState_Protocol.CANCEL_WRITE:
			writeLock.unlock();
			content=ReadWriteState_Protocol.CANCEL_WRITING;
			break;
		default:
			content="not have any protocol has used";
			break;
		}
		msg=clientIP+Communication_Protocol.SPLIT_SIGN+content;
		notifyObserver(architecture,msg);
	}
}