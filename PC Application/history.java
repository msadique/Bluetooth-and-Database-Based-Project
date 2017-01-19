import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.WindowEvent;

public class history extends JFrame implements ActionListener{
	

	Container cont;
	JLabel a1,add,search,del,confirm;
	JTextField id_add,id_search,id_del;
	JButton exitB,addB,resetB,searchB,delB,backB;
	ResultSet rs;
	PreparedStatement st;
	Connection con;
	
	protected int closeOp;
	public static final int MAYBE_EXIT_ON_CLOSE = 101;
	history()
	{
		super("Shopping History");
		cont=getContentPane();
		cont.setLayout(null);
		
		a1=new JLabel("Customers' Shopping History");
		add=new JLabel("Enter new Records ");
		search=new JLabel("Search History");
		del=new JLabel("Delete Records");
		confirm=new JLabel("");
		id_add=new JTextField(50);
		id_search=new JTextField(50);
		id_del=new JTextField(50);

		addB=new JButton("Click Here");
		resetB=new JButton("Reset");
		searchB=new JButton("Click Here");
		delB=new JButton("Click Here");
		backB=new JButton("Back");
		Font font = new Font("Dialog", Font.PLAIN, 40);
		a1.setFont(font);
		exitB=new JButton("Exit");
				

	try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}

		cont.add(a1);
		cont.add(add);
		cont.add(search);
		cont.add(del);
		cont.add(exitB);
		cont.add(addB);
		cont.add(resetB);
		cont.add(searchB);
		cont.add(delB);
		cont.add(backB);
		cont.add(id_add);
		cont.add(id_search);
		cont.add(id_del);
		cont.add(confirm);
		
		
		
		a1.setBounds(400,50,600,70);
		add.setBounds(200,150,200,30);
		id_add.setBounds(450,150,100,30);
		addB.setBounds(600,150,200,30);
		search.setBounds(200,200,200,30);
		id_search.setBounds(450,200,100,30);
		searchB.setBounds(600,200,200,30);
		del.setBounds(200,250,200,30);
		id_del.setBounds(450,250,100,30);
		delB.setBounds(600,250,200,30);
		backB.setBounds(250,350,150,30);
		resetB.setBounds(450,350,150,30);
		exitB.setBounds(650,350,150,30);
		confirm.setBounds(200,450,400,100);
		
		exitB.addActionListener(this);
		backB.addActionListener(this);
		resetB.addActionListener(this);
		addB.addActionListener(this);
		searchB.addActionListener(this);
		delB.addActionListener(this);
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultLookAndFeelDecorated(true);
		setIconImage(new ImageIcon("Sunset.jpg").getImage());
		
		
		setVisible(true);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}



public void actionPerformed(ActionEvent ae)
{
	String str= ae.getActionCommand();
	if(ae.getSource()==exitB)
	{
		dispose();
	}
	
	if(ae.getSource()==resetB)
	{
		id_add.setText("");
		id_search.setText("");
		id_del.setText("");
		
	}
	
	if(ae.getSource()==backB)
	{
		admin ad=new admin();
		dispose();
		
	}
	
	if(ae.getSource()==searchB)
	{
		DataTable dt=new DataTable();
		dt.cust_id.setText(id_search.getText());
		dispose();
	}

	if(ae.getSource()==addB)
	{
		addhistory ah=new addhistory();
		ah.cust_id1.setText(id_add.getText());
	}

	if(ae.getSource()==delB)
	{
		
		String z=id_del.getText().toString();

		try
		{
			st=con.prepareStatement("delete from history where CUST_ID=?");
			st.setString(1,z);
			rs=st.executeQuery();
		}
		catch(Exception e)
		{	
	
		}
		Font font = new Font("Dialog", Font.PLAIN, 20);
		confirm.setFont(font);
		confirm.setText("Records deleted successfully");
	}
}
//public static final int MAYBE_EXIT_ON_CLOSE = 101;
protected void processWindowEvent(WindowEvent e)
{
	if (e.getID() == WindowEvent.WINDOW_CLOSING) 
	{
		if (e.getID() == WindowEvent.WINDOW_CLOSING) 
		{
			if (closeOp == MAYBE_EXIT_ON_CLOSE) 
			{
				int exit = JOptionPane.showConfirmDialog(this, "Are you sure?");
				if (exit == JOptionPane.YES_OPTION) 
				{
					System.exit(0);
				}
			}
		}
	}
	super.processWindowEvent(e);
}


public static void main(String args[])
{
	history hist=new history();
}
}
