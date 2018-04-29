import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener implements Runnable
{
	private Socket[] s;
	private int port;
	private int n_client;
	
	SocketListener(Socket[] sk, int p)
	{
		this.s = sk;
		this.port = p;
		for (int i = 0; i < server.MAX_CLIENT; i++) {
			s[i] = null;
		}
	}
	public void run() {
		while (!server.prog_end_flag) {
			if (n_client < server.MAX_CLIENT) {
				// Add clients to the server socket, and create echo servers for each client with unique index
				try {
					s[n_client] = (new ServerSocket(port)).accept();
					System.out.println("[" + n_client + "]" + " Connected");
					EchoServer1 es = new EchoServer1(s, n_client);
					Thread t_es = new Thread(es);
					t_es.start();
					n_client++;
				} catch (IOException e) {};
			} else {
				System.out.println("Maximum client reached!");
			}
		}
	}
}

