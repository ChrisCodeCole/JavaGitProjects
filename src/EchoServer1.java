import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoServer1 implements Runnable
{
	private Socket[] s;
	private int idx;
	private BufferedReader sbr;
	
	EchoServer1(Socket[] sk, int index)
	{
		this.s = sk;
		this.idx = index;
		try {
			sbr = new BufferedReader(new InputStreamReader(s[idx].getInputStream()));
		} catch (IOException e) {};
	}
	
	public void run() {
		boolean end = false;
		while (!end) {
			try {
				// Read from the socket
				String str = sbr.readLine();
				// Echo to the other sockets
				for (int i = 0; i < server.MAX_CLIENT; i++) {
					if ((s[i] != null) && (i != idx)) {
						DataOutputStream s_writer = new DataOutputStream(s[i].getOutputStream());
						s_writer.writeBytes(str+"\n");
						System.out.println("[" + idx + "]" + str + " to " + i);
					}
				}
				if (server.prog_end_flag == true) {
					end = true;
				}
			} catch (IOException e) {};
		}
	}
}