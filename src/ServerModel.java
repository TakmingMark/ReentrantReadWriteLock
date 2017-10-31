import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.glass.ui.TouchInputSupport;

public class ServerModel implements Runnable{

	private ServerSocket serverSocket=null;
	private ThreadPoolModel threadPool=null;
	private Socket clientSocket=null; 
	private DataOutputStream socketOutput = null;
	private int ListenPort;
	 
	@Override
	public void run() {
		initServerSocket();
	}
	
	private ServerModel(int ListenPort,ThreadPoolModel threadPool) {
		this.ListenPort=ListenPort;
		this.threadPool=threadPool;
	}
	
	public static ServerModel getServerObject(int ListenPort,ThreadPoolModel threadPool) {
		
		return new ServerModel(ListenPort,threadPool);
	}
	
	private void initServerSocket() {
		 try{
			 	serverSocket = new ServerSocket( ListenPort );
			 	printContentMsg("Server listening requests..."+"\r\n");
		         
			 	//發生lock地方
	            while ( true ){
	            	clientSocket = serverSocket.accept();
		        	socketOutput = new DataOutputStream( this.clientSocket.getOutputStream());
		      		threadPool.executeThreadPool(new ServerThreadModel(clientSocket));
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
}
	

