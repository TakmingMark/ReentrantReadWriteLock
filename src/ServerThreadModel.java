import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

class ServerThreadModel implements Runnable{
	private Socket clientSocket=null;
	private DataInputStream socketInput=null;
	private String inputMsg="";
	private int heartBeatTime=30;
	
	public ServerThreadModel(Socket clientSocket){
		try {
			printContentMsg("have");
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
		catch (SocketException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void printContentMsg(String msg) {
		System.out.println(msg);
	}
}