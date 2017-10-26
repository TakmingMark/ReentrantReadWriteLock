import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

class ServerRunnable implements Runnable{
	private Socket clientSocket=null;
	private DataInputStream socketInput=null;
	private String inputMsg="";
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
			e.printStackTrace();
		}
	}
	
	private void printContentMsg(String msg) {
		
	}
}