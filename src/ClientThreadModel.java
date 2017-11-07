
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThreadModel implements Runnable{

	private Socket clientSocket=null;
	private ClientView clientView=null;
	private String IPAddress="";
	public int listenPort=0;
	private DataInputStream socketInput=null;
	private String inputMsg="";
	
	private ClientThreadModel(Socket clientSocket,ClientView clientView) {
		this.clientSocket=clientSocket;
		this.clientView=clientView;
		initClientThreadModel();
	}
	
	public static ClientThreadModel getClientThreadModelObject(Socket clientSocket,ClientView clientView) {
		return new ClientThreadModel(clientSocket,clientView);
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
		clientView.updatejTextArea(msg+"\r\n");
	}
}
