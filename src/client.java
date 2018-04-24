//
// file name: client.java
// programmer: Sean Yang
// date: April 17, 2018
// desc: A socket program for multi-client server.
//

import java.io.*;
import java.net.*;
import java.util.Scanner;

class client
{
	public static boolean prog_end_flag = false;
	
	public static void main(String args[])
	{
		try {
			Socket s = new Socket("localhost", 2010);

			SocketReader s_reader = new SocketReader(s);
			DataOutputStream s_writer = new DataOutputStream(s.getOutputStream());
			Scanner kbd = new Scanner(System.in);
			boolean end = false;
			
			Thread t = new Thread(s_reader);
			t.start();

			while (!end) {
				try {
					String str = kbd.nextLine();
					s_writer.writeBytes(str+"\n");
					if (str.equals(".")) {
						end = true;
						prog_end_flag = true;
						Thread.sleep(1000);
					}
				} catch (InterruptedException ie) {};
			}
		} catch (IOException e) {
			System.out.println("Error in connecting to the server");
			System.out.println(e);
			System.exit(1);
		};
	}
}

class SocketReader implements Runnable
{
	private Socket s;
	private BufferedReader sbr;
	
	SocketReader(Socket sk)
	{
		this.s = sk;
		try {
			sbr = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {};
		
	}
	
	public void run() {
		boolean end = false;
		while (!end) {
			try {
				String str = sbr.readLine();
				System.out.println(str);
				if (str.equals(".")) {
					end = true;
					client.prog_end_flag = true;
				}
			} catch (IOException e) {};
		}
	}
}
