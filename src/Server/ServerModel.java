package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.sun.glass.ui.TouchInputSupport;

import Observer.Observer;
import Protocol.Architecture_Protocol;
import Protocol.Communication_Protocol;
import Protocol.ReadWriteState_Protocol;
import Tools.ThreadPoolModel;
import Tools.UserList;

public class ServerModel implements Runnable,Observer{

	private ServerSocket serverSocket=null;
	private ThreadPoolModel threadPool=null;
	private ServerView serverView=null;
	private Socket clientSocket=null; 
	private ServerThreadModel serverThread=null;
	private String clientIP="";
	private DataOutputStream socketOutput = null;
	private UserList<String, DataOutputStream> userList=null;
	private int ListenPort=0;
	 

	
	@Override
	public void run() {
		initServerSocket();
	}
	
	private ServerModel(int ListenPort,ThreadPoolModel threadPool,ServerView serverView) {
		this.ListenPort=ListenPort;
		this.threadPool=threadPool;
		this.serverView=serverView;
		initServerModel();
	}
	
	public static ServerModel getServerObject(int ListenPort,ThreadPoolModel threadPool,ServerView serverView) {
		
		return new ServerModel(ListenPort,threadPool,serverView);
	}
	
	private void initServerModel() {
		userList=new UserList<String, DataOutputStream>();
	}
	
	private void initServerSocket() {
		 try{
			 	serverSocket = new ServerSocket( ListenPort );
			 	printContentMsg("Server listening requests..."+"\r\n");
		         
	            while ( true ){
	            	clientSocket = serverSocket.accept();
	            	clientIP=clientSocket.getInetAddress().toString()+":"+clientSocket.getPort();
		        	socketOutput = new DataOutputStream( this.clientSocket.getOutputStream());
		        	serverThread=ServerThreadModel.getServerThreadModelObject(clientSocket,serverView);
		      		serverThread.attach(serverView, Architecture_Protocol.View);
		      		serverThread.attach(this, Architecture_Protocol.Model);
		        	threadPool.executeThreadPool(serverThread);
		      		userList.addUser(clientIP, socketOutput);
		      		printUserList();
	            }
		 	}
	    catch ( IOException e ){
	        e.printStackTrace();
	        printContentMsg("Server failed to start,whether the "+ListenPort+" port has been occupied?");
	    }
	    finally{
	        if ( threadPool.isWorkThreadPoolExecutor()!=false )
	        	threadPool.shutdown();
	        if ( serverSocket != null )
	            try{
	                serverSocket.close();
	            }
	            catch ( IOException e ){
	                e.printStackTrace();
	            }
	        }
	}
	
	public void printContentMsg(String msg) {
		System.out.println(msg);
	}
	
	public void printUserList() {
		for(String element:userList.getIPList()) {
			printContentMsg(element);
		}
	}

	@Override
	public void update(String msg) {
		String[] tokens = msg.split("\\"+Communication_Protocol.SPLIT_SIGN);
		String clientIP=tokens[0];
		String content=tokens[1];
		DataOutputStream socketOutput=userList.getOutPutStreamByIP(clientIP);
		msg=Communication_Protocol.M_V+Communication_Protocol.SPLIT_SIGN+content+Communication_Protocol.SPLIT_SIGN+Communication_Protocol.M_V;
		transmitMsgBySocket(socketOutput, msg);
	}
	
	public void transmitMsgBySocket(DataOutputStream socketOutput,String msg) {
		try {
			socketOutput.writeUTF(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	

