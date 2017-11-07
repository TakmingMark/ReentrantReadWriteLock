import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

public class ClientModel {

	private ClientView clientView;
	private String IPAddress;
	private int ListenPort;
	
	private Thread clientThreadModel;
	private Socket clientSocket=null;
	private DataOutputStream socketOutput;
	
	private final ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	private final Lock readLock=readWriteLock.readLock();
	private final Lock writeLock=readWriteLock.writeLock();
	
	private ClientModel(ClientView clientView,String IPAddress,int ListenPort) {
		this.clientView=clientView;
		this.IPAddress=IPAddress;
		this.ListenPort=ListenPort;
		initClientSocket();
	}
	
	public static ClientModel getClientModelObject(ClientView clientView,String IPAddress,int ListenPort) {
		return new ClientModel(clientView,IPAddress,ListenPort);
	}
	
	private void initClientSocket() {
		try {
			clientSocket=new Socket(IPAddress, ListenPort);
			socketOutput=new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clientThreadModel=new Thread(ClientThreadModel.getClientThreadModelObject(clientSocket));
	
		printContentMsg("Client connect..."+"\r\n");
	}
	
	public void buttonClick(String clickEvent) {
		System.out.println(clickEvent);
		if(clickEvent.equals("Read"))
			pressReadButton();
		else if(clickEvent.equals("CancelRead"))
			pressCancelReadButton();
		else if(clickEvent.equals("Write")) 
			pressWriteButton();
		else if(clickEvent.equals("CancelWrite"))
			pressCancelWriteButton();
	}
	
	private void pressReadButton() {
		clientView.setReadButtonStatus();
		try {
			socketOutput.writeUTF("test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void pressCancelReadButton() {
		clientView.setCancelReadButtonStatus();
	}
	
	private void pressWriteButton() {
		clientView.setWriteButtonStatus();
	}
	
	private void pressCancelWriteButton() {
		clientView.setCancelWriteButtonStatus();
	}
	
	public void printContentMsg(String msg) {
		System.out.println(msg);
	}

}
