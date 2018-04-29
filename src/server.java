//
// file name: server.java
// programmer: Sean Yang
// date: April 17, 2018
// desc: A socket program that receives messages from multiple clients and echo back to the other clients.
//       The SocketListener class is keep running and accept client's connection requests.
//       Upon accepting a connection, it starts a new thread that echo back to the other clients.
//

//import java.io.*;
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
		
		//Simple check for ending server
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



