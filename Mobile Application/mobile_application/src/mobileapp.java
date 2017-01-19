import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStoreException;
	public class mobileapp extends MIDlet implements DiscoveryListener,CommandListener,Runnable {
	String strk;
	splscreen scr;
	String msg7="connectivity is not Establish\n Please try again";
	String Er;
	StreamConnection stream = null;
	String url;
	private Display mDisplay;
	int flag;
	private TextBox mSubmitBox;
	private Form mProgressForm;
	private StringItem mProgressString;
	private Command exit =new Command("Exit", Command.EXIT, 5);
	private Command help =new Command("Help", Command.HELP, 3);
	private Command item =new Command("Item", Command.ITEM, 4);
	private Command ok =new Command("OK", Command.OK, 5);
	private Command back =new Command("Back", Command.EXIT, 4);
	private Command stop =new Command("Stop", Command.STOP, 7);
	public String kUser = "user";
	public String kCustomer_ID = "Customer_ID";
	private Preferences mPreferences;
	private Form mForm;
	private Form form1;
	private Form form2;
	
	String sendMesg;
	public int len=0;
	private TextBox mSubmitBox1;
	private TextField mUserField, mCustomer_IDField;
	private TextField Brand_name,Product_type,Shop_name;
	private Command mExitCommand, mFindCommand, mCancelCommand,CancelCommand,searchCom;
	private Form mProgressForm1;
	private StringItem mProgressString1;
	private Command search =new Command("Search", Command.EXIT, 2);
	private Command about =new Command("About", Command.EXIT, 1);
	private Command credits =new Command("Modify", Command.EXIT, 3);
	private Command Rexit;

	

	static final boolean DEBUG = false;

	static final String DEBUG_address = "0013FDC157C8"; // N6630
	protected UUID uuid = new UUID(0x1101); // serial port profile

	protected int inquiryMode = DiscoveryAgent.GIAC; // no pairing is needed

	protected int connectionOptions = ServiceRecord.NOAUTHENTICATE_NOENCRYPT;

	protected Form infoArea = new Form("Bluetooth Client");

	protected Vector deviceList = new Vector();

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException 
	{}

	protected void pauseApp() 
	{}

	protected void startApp() throws MIDletStateChangeException 
	{
		record();
		int a;
		while(true)
		{
			if(strk.length()>1&&len>1)
			{System.out.println("sadiq");break;}
		}
		display1menu();
		//	makeInformationAreaGUI();
		//run();
		if (DEBUG) // skip inquiry in debug mode
			startServiceSearch(new RemoteDevice(DEBUG_address) {});
		else
			try
			{
				startDeviceInquiry();
			} catch (Throwable t) 
				{
					log(t);
				}
	}
	public void display1menu()
	{
		scr=new splscreen();
		scr.paint();
		Display.getDisplay(this).setCurrent(scr);
	}		
	/*-
	 *   -------  Device inquiry section -------
	 */

	private void startDeviceInquiry() 
	{
		try {
			log("Start inquiry method - this will take few seconds...");
			DiscoveryAgent agent = getAgent();
			agent.startInquiry(inquiryMode, this);
		    } catch (Exception e) 
			{
			 	log(e);
			}
	}

	public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) 
	{
		log("A device discovered (" + getDeviceStr(btDevice) + ")");
		deviceList.addElement(btDevice);
	}

	public void inquiryCompleted(int discType) 
	{
		makeDeviceSelectionGUI();
		log("Please select SERVER .");
		
	}

	/*-
	 *   -------  Service search section -------
	 */

	private void startServiceSearch(RemoteDevice device) 
	{
		try {
			log("Start search for Serial Port Profile service from "
					+ getDeviceStr(device));
			UUID uuids[] = new UUID[] { uuid };
			getAgent().searchServices(null, uuids, device, this);
  		    } catch (Exception e) 
			{log(e);}
	}

	/**
	 * This method is called when a service(s) are discovered.This method starts
	 * a thread that handles the data exchange with the server.
	 */
	public void servicesDiscovered(int transId, ServiceRecord[] records) 
	{
		log("Service discovered.");
		for (int i = 0; i < records.length; i++) 
		{
			ServiceRecord rec = records[i];
			url = rec.getConnectionURL(connectionOptions, false);
			sendMesg=mUserField.getString()+ "="+ mCustomer_IDField.getString()+"=";	
			handleConnection1(url);
		}
	}

	public void serviceSearchCompleted(int transID, int respCode) 
	{
		String msg = null;
		switch (respCode) 
		{
		case SERVICE_SEARCH_COMPLETED:
			msg = "the service search completed normally";
			break;
		case SERVICE_SEARCH_TERMINATED:
			msg = "the service search request was cancelled by a call to DiscoveryAgent.cancelServiceSearch()";
			break;
		case SERVICE_SEARCH_ERROR:
			msg = "an error occurred while processing the request";
			break;
		case SERVICE_SEARCH_NO_RECORDS:
			msg = "no records were found during the service search";
			break;
		case SERVICE_SEARCH_DEVICE_NOT_REACHABLE:
			msg = "the device specified in the search request could not be reached or the local device could not establish a connection to the remote device";
			break;
		}
		log("Service search completed - " + msg);
	}

	/*-
	 *   -------  The actual connection handling. -------
	 */

	public void run()
	{
		System.out.println("sadiquehussain");log("sadique");		
	}
					
		
	private void handleConnection1(final String url) {
		Thread echo = new Thread() {
			public void run() {
				
				StreamConnection stream = null;
				try {
					log("Connecting to server by url: " + url);
					stream = (StreamConnection) Connector.open(url);

					log("Bluetooth stream open.");
			InputStream in = stream.openInputStream();
			String sam =sendMesg;	
			log(sam);
			OutputStream out = stream.openOutputStream();
			log("Start echo loop.");
			char r2;
			 
			flag=sam.length();out.flush();
			if(flag!=-1){out.write(flag);out.flush();
                        for(int i=0;i<sam.length();i++)
                        {
                              r2= sam.charAt(i);         
                              flag=r2;
			      if(flag!=-1)out.write(flag);else break; out.flush();
                        }}
			out.close();
			log("write");
			int s= in.read();
			if(s!=-1)
			{char s1[]=new char[s];
			for(int i=0;i<s;i++)
			{	flag=in.read();
				if(flag!=-1){char r1 = (char)flag;
				s1[i]=r1;	}
				else break;
			}
			log("read");
			String s2=new String(s1);
			if(s2.length()<2)
			msg7="Conectivity Is Not Established\nPlease try Again";
			else msg7=s2;}
			in.close();
		       sam="hussain";
			log(sam);
						try {
							stream.close();
						} catch (IOException e) {
							log(e);
						}
	
					
				} catch (IOException e) {
					log(e);
				} finally {
					log("Bluetooth stream closed.");
					if (stream != null) {
						try {
							stream.close();
						} catch (IOException e) {
							log(e);
						}
				}
				}
			log("sadique");log("Bluetooth stream closed.");
					if (stream != null) {
						try {
							stream.close();
						} catch (IOException e) {
							log(e);
						}
				 }//if
				startApp2();
			}
				
		};
		echo.start();
	}

	/*-
	 *   -------  Graphic User Interface section -------
	 */

	private void makeInformationAreaGUI() 
	{
		infoArea.deleteAll();
		Display.getDisplay(this).setCurrent(infoArea);
	}

	private void makeDeviceSelectionGUI() 
	{
		final List devices = new List("Select a device", List.IMPLICIT);
		for (int i = 0; i < deviceList.size(); i++)
			devices.append(
					getDeviceStr((RemoteDevice) deviceList.elementAt(i)), null);
		devices.setCommandListener(new CommandListener() {
		public void commandAction(Command arg0, Displayable arg1) {
				makeInformationAreaGUI();
				startServiceSearch((RemoteDevice) deviceList.elementAt(devices
						.getSelectedIndex()));
			}
		});
		Display.getDisplay(this).setCurrent(devices);
	}

	synchronized private void log(String msg) 
	{
		infoArea.append(msg);
		infoArea.append("\n\n");
	}

	private void log(Throwable e) 
	{Er=Er+e.getMessage();
		log(e.getMessage());
	}

	/*-
	 *   -------  Utils section - contains utility functions -------
	 */

	private DiscoveryAgent getAgent() 
	{
		try {
			return LocalDevice.getLocalDevice().getDiscoveryAgent();
		     } catch (BluetoothStateException e) {
			throw new Error(e.getMessage());
			}
	}
	private String getDeviceStr(RemoteDevice btDevice) 
	{
		return getFriendlyName(btDevice) + " - 0x"
				+ btDevice.getBluetoothAddress();
	}

	private String getFriendlyName(RemoteDevice btDevice) 
	{
		try {
			return btDevice.getFriendlyName(false);
		} catch (IOException e) {
			return "no name available";
		}
	}
	public void record()
	{
		try {
			mPreferences = new Preferences("preferences");
		    }
			catch (RecordStoreException rse) 
			{
				mForm = new Form("Exception");
				mForm.append(new StringItem(null, rse.toString()));
				mForm.addCommand(new Command("Exit", Command.EXIT, 0));
				mForm.setCommandListener(this);
				return;
			}
		mForm = new Form("Login");
		mCustomer_IDField = new TextField("Customer_ID",
		mPreferences.get(kCustomer_ID), 32, 0);
		mUserField = new TextField("Name",
		mPreferences.get(kUser), 32, 0);
		mForm.append(mUserField);
		mForm.append(mCustomer_IDField);
		Rexit=new Command("Save", Command.EXIT, 0);
		mForm.addCommand(Rexit);
		strk=mCustomer_IDField.getString();
		len=strk.length();
		System.out.println(" "+strk.length());
		
		strk=mUserField.getString();
		System.out.println(" "+strk.length());
		//else{
		mForm.setCommandListener(this);
		Display.getDisplay(this).setCurrent(mForm);
		
	}
	public void destroyApp1(boolean unconditional) 
	{
		// Save the user name and Customer_ID.
		mPreferences.put(kCustomer_ID, mCustomer_IDField.getString());
		mPreferences.put(kUser, mUserField.getString());
		try { mPreferences.save(); }
		catch (RecordStoreException rse) {}
	}
	public void commandAction(Command c, Displayable s) 
	{
		if (c==Rexit) 
		{
			System.out.println("sa");
			destroyApp1(true);
			strk="length";len=5;
		}//if
		else if(c==mExitCommand||c==mFindCommand)
		{System.out.println("sam adsfasdjf");handleConnection1(url);}
		
		else if(c==search)
		{    Nsearch();
			System.out.println("sam");
		}
		else if(c==exit)
		{notifyDestroyed();}

		
	}
	public void Nsearch()
	{
		form1=null;
		form2 = new Form("Search");
		Brand_name = new TextField("Brand Name", "", 32, 0);
		Product_type = new TextField("Brand Type", "", 32, 0);
		Shop_name = new TextField("Shop Name", "", 32, 0);
		searchCom = new Command("Search", Command.SCREEN, 0);
		form2.append(Brand_name);
		form2.append(Product_type);
		form2.append(Shop_name);
		form2.addCommand(searchCom);
				
form2.setCommandListener(new CommandListener() 
		{	
			public void commandAction(Command c, Displayable s) 
			{
				if(c==searchCom)
				{
					sendMesg="Brand-"+Brand_name.getString()+ "="+ "Type-"+Product_type.getString()+"="+"Type-"+Shop_name.getString();	
					System.out.println("sdf"+url);
					handleConnection1(url);
				}
			}
		});
		Display.getDisplay(this).setCurrent(form2);            
	}

	public void startApp2()
	{
		form1 = new Form("Welcome");
		form1.append(msg7);
		form1.addCommand(search);
		//form1.addCommand(credits);
		//form1.addCommand(back);
		//form1.addCommand(about);
		form1.addCommand(exit);
		form1.setCommandListener(this);
		Display.getDisplay(this).setCurrent(form1);            
	
     }
}
