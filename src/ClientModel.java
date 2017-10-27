import java.io.IOException;
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

	private String IPAddress;
	private int ListenPort;
	private Socket clientSocket=null;
	
	private final ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	private final Lock readLock=readWriteLock.readLock();
	private final Lock writeLock=readWriteLock.writeLock();
	
	private ClientModel(String IPAddress,int ListenPort) {
		this.IPAddress=IPAddress;
		this.ListenPort=ListenPort;
		initClientSocket();
	}
	
	public static ClientModel getClientModelObject(String IPAddress,int ListenPort) {
		return new ClientModel(IPAddress,ListenPort);
	}
	
	
	public void initClientSocket() {
		
		try {
			clientSocket=new Socket(IPAddress,ListenPort);
			clientSocket.setSoTimeout(60000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printContentMsg("Client connect..."+"\r\n");
//	  	new Thread(new ClientThread(clientSocket)).start();
	}
	
	public void printContentMsg(String msg) {
		System.out.println(msg);
	}
}
