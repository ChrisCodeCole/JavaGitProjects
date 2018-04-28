//
// file name: server.java
// programmer: Sean Yang
// date: April 17, 2018
// desc: A socket program that receives messages from multiple clients and echo back to the other clients.
//       The SocketListener class is keep running and accept client's connection requests.
//       Upon accepting a connection, it starts a new thread that echo back to the other clients.
//

import java.io.*;
import java.net.*;
import java.lang.InterruptedException;
import java.util.Scanner;


public class server
{
	public static boolean prog_end_flag = false;
	public static final int MAX_CLIENT = 10;
	private static Socket []m_sk;

	public static void main(String args[])
	{
		m_sk = new Socket[MAX_CLIENT];
		
		SocketListener sl = new SocketListener(m_sk, 2010);
		Thread t_sl = new Thread(sl);
		t_sl.start();

		Scanner kbd = new Scanner(System.in);
		boolean end = false;
		
		while (!end) {
			try {
				String str = kbd.nextLine();
				
				if (str.equals(".")) {
					System.out.println("Ending request detected!");
					prog_end_flag = true;
					Thread.sleep(1000);
					end = true;
				}
			} catch (InterruptedException ie) {};
		}
	}
}

class SocketListener implements Runnable
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
				try {
					s[n_client] = (new ServerSocket(port)).accept();
					System.out.println("[" + n_client + "]" + " Connected");
					EchoServer es = new EchoServer(s, n_client);
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

class EchoServer implements Runnable
{
	private Socket[] s;
	private int idx;
	private BufferedReader sbr;
	
	EchoServer(Socket[] sk, int index)
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
				// read from the socket
				String str = sbr.readLine();
				// echo to the other sockets
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

