import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThreadModel implements Runnable{

	private Socket clientSocket=null;
	private String IPAddress="";
	public int listenPort=0;
	private DataOutputStream socketOutput=null;
	private String outputMsg="";
	
	private ClientThreadModel(Socket clientSocket) {
		this.clientSocket=clientSocket;

	}
	
	public static ClientThreadModel getClientThreadModelObject(Socket clientSocket) {
		
		return new ClientThreadModel(clientSocket);
	}
	@Override
	public void run() {
		try {
		
			IPAddress=clientSocket.getLocalAddress().getHostAddress();
			listenPort=clientSocket.getPort();
			socketOutput=new DataOutputStream(clientSocket.getOutputStream());
			
			while(true){
				
	
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
