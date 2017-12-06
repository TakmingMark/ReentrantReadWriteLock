package Server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Protocol.Architecture_Protocol;
import Protocol.Communication_Protocol;
import Tools.TextContent;
import Tools.ThreadPoolModel;
import Tools.UserList;

public class ServerModel implements Runnable{
	private ServerController serverController=null;
	private ServerThreadModel serverThread=null;
	private ServerSocket serverSocket=null;
	private ThreadPoolModel threadPool=null;
	private Socket clientSocket=null; 

	private String clientIP="";
	private DataOutputStream socketOutput = null;
	private UserList<String, DataOutputStream> userList=null;
	private int ListenPort=0;
	 
	private ServerModel(ServerController serverController,ThreadPoolModel threadPool,int ListenPort) {
		this.serverController=serverController;
		this.threadPool=threadPool;
		this.ListenPort=ListenPort;
		
		initServerModel();
	}
	
	public static ServerModel getServerObject(ServerController serverController,ThreadPoolModel threadPool,int ListenPort) {
		return new ServerModel(serverController,threadPool,ListenPort);
	}
	
	private void initServerModel() {
		userList=new UserList<String, DataOutputStream>();
	}
	
	@Override
	public void run() {
		initServerSocket();
	}
	
	private void initServerSocket() {
		 try{
			 	serverSocket = new ServerSocket( ListenPort );
			 	updateTextView(TextContent.ServerCreateSuccess);
		         
	            while ( true ){
	            	clientSocket = serverSocket.accept();
	            	clientIP=clientSocket.getInetAddress().toString()+":"+clientSocket.getPort();
	            	socketOutput = new DataOutputStream( this.clientSocket.getOutputStream());
		        	serverThread=ServerThreadModel.getServerThreadModelObject(clientSocket);
		      		serverThread.attach(Architecture_Protocol.Controller,serverController);
		        	threadPool.executeThreadPool(serverThread);
		      		userList.addUser(clientIP, socketOutput);
		      		printUserList();
	            }
		 	}
	    catch ( IOException e ){
	        e.printStackTrace();
	        updateTextView(TextContent.ServerCreateFail);
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
	
	private void updateTextView(String msg) {
		serverController.update(msg);
	}
	
	private void printUserList() {
		updateTextView("---------------------------------------------");
		for(String element:userList.getIPList()) {
			updateTextView(element+":"+userList.getOutPutStreamByIP(element));
		}
	}
	
	public void transmitMsgBySocket(String inputMsg) {
		try {
				String[] tokens = inputMsg.split("\\"+Communication_Protocol.SPLIT_SIGN);
				String clientIP=tokens[0];
				String msg=tokens[1];
				
				socketOutput=userList.getOutPutStreamByIP(clientIP);
				socketOutput.writeUTF(Communication_Protocol.CLIENT_CONTROLLER+Communication_Protocol.SPLIT_SIGN+msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	

