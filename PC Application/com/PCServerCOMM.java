//package example.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.*;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/**
 * A class that demonstrates Bluetooth communication between server mode PC and
 * client mode device through serial port profile.
 * 
 * @see <a href="http://sourceforge.net/projects/bluecove/">BlueCove - JSR-82
 *      implementation</a>
 */
public class PCServerCOMM {

	/*-
	 * ================
	 * Bluetooth Server
	 * ================
	 * 
	 * This example application is a straighforward implementation of 
	 * a bluetooth server.
	 * 
	 * 
	 * Usage
	 * =====
	 * 
	 * Start the program. Events are logged by printing them out with standard 
	 * output stream. Once the server is up and ready to accept connections, 
	 * connect to server with client.
	 * 
	 * 
	 * How it Works
	 * ============
	 * 
	 * The application starts a loop to wait for a new connection. After a new 
	 * connection is reseived the connection is handled. In the handling 
	 * operation few tokens and end token is written to connection stream. 
	 * Each read token is logged to standard output. After handling session 
	 * the loop continues by waiting for a new connection.
	 * 
	 */

	/*-
	 * 
	 * ---- Bluetooth attributes ----
	 */

	// serial port profile
	protected String UUID = new UUID("1101", true).toString();

	protected int discoveryMode = DiscoveryAgent.GIAC; // no paring needed

	/*-
	 * 
	 * ---- Connection handling attributes ----
	 */
	protected int endToken = 255;

	public PCServerCOMM() {
		try {
			LocalDevice device = LocalDevice.getLocalDevice();
			device.setDiscoverable(DiscoveryAgent.GIAC);

			String url = "btspp://localhost:" + UUID + ";name=PCServerCOMM";

			log("Create server by uri: " + url);
			StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector
					.open(url);

			serverLoop(notifier);

		} catch (Throwable e) {
			log(e);
		}
	}

	private void serverLoop(StreamConnectionNotifier notifier) {
		try {
			while (true) { // infinite loop to accept connections.
				log("Waiting for connection...");
				handleConnection(notifier.acceptAndOpen());
			}
		} catch (Exception e) {
			log(e);
		}
	}

	private void handleConnection(StreamConnection conn) throws IOException {
		OutputStream out = conn.openOutputStream();
		InputStream in = conn.openInputStream();
		startReadThread(in);

		try { // to write some tokens through the bluetooth link
			String sam="sadique hussain mobile number 9990768936";
			//int[] tokens = new int[] { 1, 3, 5, 7, endToken };
			out.write(sam.length());
			for (int i = 0; i < sam.length(); i++) {
			out.write(sam.charAt(i));}
			Thread.sleep(1000);
			
		} catch (Exception e) {
			log(e);
		} finally {
			log("Close connection.");
			if (conn != null) {
				try {
					conn.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private void startReadThread(final InputStream in) {
		Thread reader = new Thread() {
			public void run() {
				try {
               					//while (true) {
                                                 int s= in.read();
						char s1[]=new char[s];

						for(int i=0;i<s;i++)
						{char r1 = (char)in.read();
						s1[i]=r1;}
						String s2=new String(s1);
						log("read:" + s2);
						//int r = in.read();
						//log("read:" + r);

						//if (r == endToken)
						//	break;
					//}
				} catch (Throwable e) {
					log(e);
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
						}
					}
				}
			}
		};
		reader.start();
	}

	/*-
	 *   -------  Utility section -------
	 */

	private void log(String msg) {
		System.out.println(msg);
	}

	private void log(Throwable e) {
		log(e.getMessage());
		e.printStackTrace();
	}

	/*-
	 *   -------  Bootstrap section, i.e., main method -------
	 */

	public static void main(String[] args) {
		new PCServerCOMM();
	}

}
