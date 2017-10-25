import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.glass.ui.TouchInputSupport;

public class Server {

	private ServerSocket serverSocket=null;
	private ThreadPoolModel threadPool=null;
	private Socket clientSocket=null; 
	private DataOutputStream socketOutput = null;
	private int ListenPort;
	 
	private Server(int ListenPort,ThreadPoolModel threadPool) {
		this.ListenPort=ListenPort;
		initServerSocket();
	}
	
	public static Server getServerObject(int ListenPort,ThreadPoolModel threadPool) {
		
		return new Server(ListenPort,threadPool);
	}
	
	private void initServerSocket() {
		 try{
			 	serverSocket = new ServerSocket( ListenPort );
			 	printContentMsg("Server listening requests..."+"\r\n");
		          	
	            while ( true ){
	            	clientSocket = serverSocket.accept();
		        	socketOutput = new DataOutputStream( this.clientSocket.getOutputStream());
		      		threadPool.executeThreadPool(new ServerRunnable(clientSocket));
	            }
		 	}
	    catch ( IOException e ){
	        e.printStackTrace();
	        printContentMsg("Server failed to start,whether the "+ListenPort+" port has been occupied?");
	    }
	    finally{
	        if ( threadPool.isWorkThreadPoolExecutor()==false )
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
		
	}
}
	
	class ServerRunnable implements Runnable{
		private Socket clientSocket=null;
		private DataInputStream socketInput=null;
		private String inputMsg="",outputMsg="";
		private int heartBeatTime=30;
		
		public ServerRunnable(Socket clientSocket){
			try {
				this.clientSocket=clientSocket;
				this.clientSocket.setSoTimeout(60000);
				socketInput=new DataInputStream(this.clientSocket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		 
		@Override
		public void run(){
			try{
				while((inputMsg=socketInput.readUTF())!=null){
					printContentMsg(inputMsg+"\r\n");
					}
					
				}
			catch(Exception e){
				
			}
		}
		
		private void printContentMsg(String msg) {
			
		}
}
