import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

public class GetValuesFromClient {
	Socket listener;
	String frequency;
	String highestValue;
	String lowestValue;
	
	public GetValuesFromClient(Socket socket) throws IOException {
		listener=socket;
	}
	public String getFrequency(ServerSocket socket) throws UnsupportedEncodingException, IOException {
		int read = -1;
		byte[] buffer = new byte[5*1024]; // a read buffer of 5KiB
		byte[] readFreq;
		StringBuilder clientData = new StringBuilder();
		while ((read =  listener.getInputStream().read(buffer)) > -1) {
		    readFreq = new byte[read];
		    System.arraycopy(buffer, 0, readFreq, 0, read);
		    frequency = new String(readFreq,"UTF-8"); // assumption that client sends data UTF-8 encoded
		    clientData.append(frequency);
		}
		return clientData.toString();
		
		 
	}
	public String getHighestValue(ServerSocket socket) throws UnsupportedEncodingException, IOException {
		int read = -1;
		byte[] buffer = new byte[5*1024]; // a read buffer of 5KiB
		byte[] readHighValue;
		StringBuilder clientData = new StringBuilder();
		while ((read =  listener.getInputStream().read(buffer)) > -1) {
		    readHighValue = new byte[read];
		    System.arraycopy(buffer, 0, readHighValue, 0, read);
		    highestValue = new String(readHighValue,"UTF-8"); // assumption that client sends data UTF-8 encoded
		    clientData.append(highestValue);
		}
		return clientData.toString();
		
		 
	}
	
	public String getLowestValue(ServerSocket socket) throws UnsupportedEncodingException, IOException {
		int read = -1;
		byte[] buffer = new byte[5*1024]; // a read buffer of 5KiB
		byte[] readLowValue;
		StringBuilder clientData = new StringBuilder();
		while ((read =  listener.getInputStream().read(buffer)) > -1) {
		    readLowValue = new byte[read];
		    System.arraycopy(buffer, 0, readLowValue, 0, read);
		    lowestValue = new String(readLowValue,"UTF-8"); // assumption that client sends data UTF-8 encoded
		    clientData.append(lowestValue);
		}
		return clientData.toString();
		
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	new GetValuesFromClient(socket);

	}

}
