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

public class Newcust extends JFrame implements ActionListener{

	Container cont;
	JLabel name,cust_id,address,phone,pre_visit,confirm;
	JTextField name1,cust_id1,phone1,pre_visit1,id_search;
	JButton delete,exit,send_msg,search,address1,reset,add;
	ResultSet rs;
	PreparedStatement st;
	Connection con;

	Newcust()
	{
		cont=getContentPane();
		cont.setLayout(null);//new GridLayout(8,2));
		name=new JLabel("Name");
		cust_id=new JLabel("Customer id");
		address=new JLabel("Address");
		phone=new JLabel("Phone/Mobile No");
		pre_visit=new JLabel("Last Visit");
		confirm=new JLabel("");
		
		name1=new JTextField(10);
		cust_id1=new JTextField(10);
		address1=new JButton("Click to add address");
		phone1=new JTextField(10);
		pre_visit1=new JTextField(10);
		id_search=new JTextField(10);
		
		delete=new JButton("Delete");
		exit=new JButton("EXIT");
		//send_msg=new JButton("Send Msg");	
		reset=new JButton("Reset");
		add=new JButton("Add");
		
		search=new JButton("Search");

		
		search.setBounds(390,50,120,30);
		id_search.setBounds(370,20,150,30);
		//send_msg.setBounds(150,50,150,30);
		name.setBounds(50,100,150,30);
		name1.setBounds(250,100,200,30);
		cust_id.setBounds(50,140,150,30);
		cust_id1.setBounds(250,140,200,30);
		address.setBounds(50,180,150,30);
		address1.setBounds(250,180,200,30);
		phone.setBounds(50,220,150,30);
		phone1.setBounds(250,220,200,30);
		pre_visit.setBounds(50,260,150,30);
		pre_visit1.setBounds(250,260,200,30);
		add.setBounds(70,300,100,30);
		delete.setBounds(200,300,100,30);
		reset.setBounds(330,300,100,30);
		exit.setBounds(460,300,100,30);
		confirm.setBounds(70,340,200,30);
		
		
				
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
		cont.add(add);
		cont.add(delete);
		cont.add(exit);
		//cont.add(send_msg);
		cont.add(id_search);
		cont.add(search);
		cont.add(reset);
		cont.add(confirm);
		
		delete.addActionListener(this);
		exit.addActionListener(this);
		//send_msg.addActionListener(this);
		search.addActionListener(this);
		address1.addActionListener(this);
		reset.addActionListener(this);
		add.addActionListener(this);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultLookAndFeelDecorated(true);
		setIconImage(new ImageIcon("Sunset.jpg").getImage());
		//setSize(600,500);
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
	}

	if(ae.getSource()==address1)
	{
		addresses add=new addresses();
		add.cust_id1.setText(cust_id1.getText());
		
	}

	if(ae.getSource()==delete)
	{
		String z=id_search.getText().toString();

		try
		{
			st=con.prepareStatement("delete from customers where Cust_Id=?");
			st.setString(1,z);
			rs=st.executeQuery();
		}
		catch(Exception e)
		{	
	
		}
	}

	if(ae.getSource()==add)
	{

		String a=name1.getText();
		String b=cust_id1.getText();
		String x=phone1.getText();
		//String x=address1.getText();
		String y=pre_visit1.getText();

		try
		{
			st=con.prepareStatement("Insert into customers values(?,?,?,?)");
			st.setString(1,a);
			st.setString(2,b);
			st.setString(3,x);
			//st.setString(4,x);
			st.setString(4,y);
			
			st.executeUpdate();
		}

		catch(Exception e)
		{
		}		
		
		confirm.setText("Entry successfully saved");		

		
	}



	if(ae.getSource()==search)
	{
		String t=id_search.getText().toString();
		
		try
		{
			st=con.prepareStatement("Select * from customers where Cust_Id=?");
			st.setString(1,t);
			rs=st.executeQuery();
			/*if(!rs.next())
			{
				confirm.setText("Sorry ! No Entry Found");
			}*/
			
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


public static void main(String args[]) 
{

	Newcust cs=new Newcust();
}	
}