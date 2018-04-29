import java.io.BufferedReader;	
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javafx.application.Platform;

public class SocketReader1 implements Runnable
{
	private Socket s;
	private BufferedReader sbr;
	private client currentClient;
	
	SocketReader1(Socket sk, client currentClient)
	{
		this.s = sk;
		this.currentClient = currentClient;
		try {
			sbr = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {};
		
	}
	
	public void run() {
		boolean end = false;
		while (!end) {
			try {
				String str = sbr.readLine();
				
				//Add new received message to JavaFX application
	            Platform.runLater(() -> {

					MessageHBox addReceivedMessage = new MessageHBox(str, true);
					currentClient.vboxTextArea.getChildren().add(addReceivedMessage);
	            });
	            
				if (str.equals(".")) {
					end = true;
					client.prog_end_flag = true;
				}
			} catch (IOException e) {};
		}
	}

}