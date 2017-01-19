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
import java.awt.*;import java.io.*;
import javax.swing.*;import javax.imageio.*;

public class customers extends JFrame implements ActionListener{

	Container cont;
	JLabel name,cust_id,address,phone,pre_visit,confirm;
	JTextField name1,cust_id1,phone1,pre_visit1,id_search;
	JButton delete,exit,send_msg,search,address1,reset;
	ResultSet rs;
	PreparedStatement st;
	Connection con;

	customers()
	{
		super("Customers' Details");
		cont=getContentPane();
		cont.setLayout(null);//new GridLayout(8,2));
		name=new JLabel("Name");
		cust_id=new JLabel("Customer id");
		address=new JLabel("Address");
		phone=new JLabel("Phone/Mobile No");
		pre_visit=new JLabel("Last Visit");
		confirm=new JLabel("");
		GCanvas canvas=new GCanvas();

		Font font = new Font("Dialog", Font.PLAIN, 30);
		confirm.setFont(font);
	

		name1=new JTextField(10);
		cust_id1=new JTextField(10);
		address1=new JButton("Click to view address");
		phone1=new JTextField(10);
		pre_visit1=new JTextField(10);
		id_search=new JTextField(10);
		
		delete=new JButton("Delete");
		exit=new JButton("EXIT");
		send_msg=new JButton("Send Msg");	
		reset=new JButton("Reset");
		
		search=new JButton("Search");
		
		search.setBounds(390,200,120,30);
		id_search.setBounds(370,150,150,30);
		send_msg.setBounds(150,150,150,30);
		name.setBounds(50,250,250,30);
		name1.setBounds(250,250,200,30);
		cust_id.setBounds(50,290,150,30);
		cust_id1.setBounds(250,290,200,30);
		address.setBounds(50,330,150,30);
		address1.setBounds(250,330,200,30);
		phone.setBounds(50,370,150,30);
		phone1.setBounds(250,370,200,30);
		pre_visit.setBounds(50,410,150,30);
		pre_visit1.setBounds(250,410,200,30);
		confirm.setBounds(100,550,500,40);
		delete.setBounds(200,450,100,30);
		reset.setBounds(330,450,100,30);
		exit.setBounds(460,450,100,30);
		canvas.setBounds(750,150,500,400);
		
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}

		cont.add(name);
		cont.add(name1);
		cont.add(cust_id);
		cont.add(cust_id1);
		cont.add(address);
		cont.add(address1);
		cont.add(phone);
		cont.add(phone1);
		cont.add(pre_visit);
		cont.add(pre_visit1);
		cont.add(delete);
		cont.add(exit);
		cont.add(send_msg);
		cont.add(id_search);
		cont.add(search);
		cont.add(reset);
		cont.add(confirm);
		cont.add(canvas);
		
		delete.addActionListener(this);
		exit.addActionListener(this);
		send_msg.addActionListener(this);
		search.addActionListener(this);
		address1.addActionListener(this);
		reset.addActionListener(this);
		
		setDefaultLookAndFeelDecorated(true);
		setIconImage(new ImageIcon("Sunset.jpg").getImage());
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}


public void actionPerformed(ActionEvent ae)
{
	String str= ae.getActionCommand();
	if(ae.getSource()==exit)
	{
		dispose();
	}

	if(ae.getSource()==reset)
	{
		 name1.setText("");
		cust_id1.setText("");
		phone1.setText("");
		pre_visit1.setText("");
		id_search.setText("");
		confirm.setText("");
	}

	if(ae.getSource()==address1)
	{
		addresses add=new addresses();
		//add.cust_id1.setText(cust_id1.getText());
		String s=id_search.getText().toString();
		
		try
		{
			st=con.prepareStatement("Select * from address where CUST_ID=?");
			st.setString(1,s);
			rs=st.executeQuery();
			if(!rs.next())
			{
				confirm.setText("Sorry ! No Entry Found");
			}
			
			rs=st.executeQuery();
			while(rs.next())
			{
				//name1.setText(rs.getString("Name"));
				add.cust_id1.setText(rs.getString("CUST_ID"));
				add.h_no1.setText(rs.getString("H_NO"));
				add.street1.setText(rs.getString("STREET"));
				add.city1.setText(rs.getString("CITY"));
				add.state1.setText(rs.getString("STATE"));
				add.p_code1.setText(rs.getString("PIN"));
				add.country1.setText(rs.getString("COUNTRY"));
				//address1.setText(rs.getString("Address"));
				//phone1.setText(rs.getString("Phone"));
				//pre_visit1.setText(rs.getString("L_Visit"));
			}
		}

		catch(Exception e)
		{	
	
		}
	}

	if(ae.getSource()==delete)
	{
		String z=cust_id1.getText().toString();

		try
		{
			st=con.prepareStatement("delete from customers where Cust_Id=?");
			st.setString(1,z);
			rs=st.executeQuery();
		}
		catch(Exception e)
		{	
	
		}
		confirm.setText("Customer's Details deleted");
	}


	if(ae.getSource()==search)
	{
		String t=id_search.getText().toString();
		
		try
		{
			st=con.prepareStatement("Select * from customers where CUST_ID=?");
			st.setString(1,t);
			rs=st.executeQuery();
			if(!rs.next())
			{
				//customers cs=new customers();
				confirm.setText("Sorry ! No Entry Found");
			}
			
			rs=st.executeQuery();
			while(rs.next())
			{
				name1.setText(rs.getString("Name"));
				cust_id1.setText(rs.getString("Cust_id"));
				//address1.setText(rs.getString("Address"));
				phone1.setText(rs.getString("Phone"));
				pre_visit1.setText(rs.getString("L_Visit"));
			}
		}

		catch(Exception e)
		{	
	
		}
	}
}

class GCanvas extends Canvas
{
  public void paint(Graphics g)
  {
    Image img=null;
    try {img=ImageIO.read(new File("Sunset.jpg"));}
    catch(IOException e)
      {System.out.println("ok");System.exit(0);}
    g.drawImage(img,0,0,460,460,this);
  }
}


public static void main(String args[]) 
{

	customers cs=new customers();
}	
}