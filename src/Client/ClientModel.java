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

import com.sun.tracing.dtrace.ProviderAttributes;
import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

public class ClientModel implements Observer{

	private ClientView clientView;
	private String IPAddress;
	private int ListenPort;
	
	private ClientThreadModel clientThreadModel;
	private Thread clientThread;
	private Socket clientSocket=null;
	private DataOutputStream socketOutput;
	
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
		
		clientThreadModel=ClientThreadModel.getClientThreadModelObject(clientSocket);
		clientThreadModel.attach(this,Architecture_Protocol.Model);
		clientThreadModel.attach(clientView,Architecture_Protocol.View);
		clientThread=new Thread(clientThreadModel);
		clientThread.start();
	}
	
	public void buttonClick(String clickEvent) {
		System.out.println(clickEvent);
		if(clickEvent.equals(ReadWirteState_Protocol.READ))
			pressReadButton();
		else if(clickEvent.equals(ReadWirteState_Protocol.CANCEL_READ))
			pressCancelReadButton();
		else if(clickEvent.equals(ReadWirteState_Protocol.WRITE)) 
			pressWriteButton();
		else if(clickEvent.equals(ReadWirteState_Protocol.CANCEL_WRITE))
			pressCancelWriteButton();
	}
	
	private void pressReadButton() {
		clientView.setReadButtonStatus();
		transmitMsgBySocket(Communcation_Protocol.M_V+Communcation_Protocol.SPLIT_SIGN+Communcation_Protocol.READ+Communcation_Protocol.SPLIT_SIGN+Communcation_Protocol.M_V);
	}
	
	private void pressCancelReadButton() {
		clientView.setCancelReadButtonStatus();
		transmitMsgBySocket(Communcation_Protocol.M_V+Communcation_Protocol.SPLIT_SIGN+Communcation_Protocol.CANCEL_READ+Communcation_Protocol.SPLIT_SIGN+Communcation_Protocol.M_V);
	}
	
	private void pressWriteButton() {
		clientView.setWriteButtonStatus();
		transmitMsgBySocket(Communcation_Protocol.M_V+Communcation_Protocol.SPLIT_SIGN+Communcation_Protocol.WRITE+Communcation_Protocol.SPLIT_SIGN+Communcation_Protocol.M_V);
	}
	
	private void pressCancelWriteButton() {
		clientView.setCancelWriteButtonStatus();
		transmitMsgBySocket(Communcation_Protocol.M_V+Communcation_Protocol.SPLIT_SIGN+Communcation_Protocol.CANCEL_WRITE+Communcation_Protocol.SPLIT_SIGN+Communcation_Protocol.M_V);
	}

	public void transmitMsgBySocket(String msg) {
		try {
			socketOutput.writeUTF(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		
	}

}
