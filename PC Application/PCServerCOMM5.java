import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
public class PCServerCOMM5 {


	// serial port profile
	protected String UUID = new UUID("1101", true).toString();

	protected int discoveryMode = DiscoveryAgent.GIAC; // no paring needed

	/*-
	 * 
	 * ---- Connection handling attributes ----
	 */
	protected int endToken = 255;

	public PCServerCOMM5() {
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
		System.out.println("write");
		try { // to write some tokens through the bluetooth link
			int[] tokens = new int[] { 1, 3, 5, 7, endToken };
			out.flush();
			for (int i = 0; i < tokens.length; i++) {out.flush();
				out.write(tokens[i]);
				out.flush();
				log("write:" + tokens[i]);
				// wait a sec before next write.
				Thread.sleep(1 * 1000);
			}
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
				try {System.out.println("read");
					int flag1;System.out.println("read");
               					//while (true) {
                                                 int s= in.read();
						if(s!=-1){System.out.println("123");
						char s1[]=new char[s];

						for(int i=0;i<s;i++)
						{
							flag1=in.read();
							if(flag1!=-1)
								{char r1 = (char)flag1;s1[i]=r1;}
							else
							break;							
						}
						String s2=new String(s1);
						log("read:" + s2);}

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

	

	public static void main(String[] args) {
		new PCServerCOMM5();
	}

}
