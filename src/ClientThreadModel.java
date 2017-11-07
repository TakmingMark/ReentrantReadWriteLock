
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThreadModel implements Runnable{

	private Socket clientSocket=null;
	private String IPAddress="";
	public int listenPort=0;
	private DataInputStream socketInput=null;
	private String inputMsg="";
	
	private ClientThreadModel(Socket clientSocket) {
		this.clientSocket=clientSocket;
		initClientThreadModel();
	}
	
	public static ClientThreadModel getClientThreadModelObject(Socket clientSocket) {
		
		return new ClientThreadModel(clientSocket);
	}
	
	private  void initClientThreadModel() {
		IPAddress=clientSocket.getLocalAddress().getHostAddress();
		listenPort=clientSocket.getPort();
		try {
			socketInput=new DataInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while((inputMsg=socketInput.readUTF())!=null){
				printContentMsg(inputMsg+"\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void printContentMsg(String msg) {
		System.out.println(msg);
	}
}
