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

public class admin extends JFrame implements ActionListener{

	Container cont;
	JLabel a1,a2;
	JButton cust_add,send_msg,exit,search1,search2,search3,server;
	JLabel cust_info,cust_del,history;
	JTextField id_info,id_del,id_history;
	ResultSet rs;
	PreparedStatement st;
	Connection con;

	admin()
	{
		super("Administrator");
		cont=getContentPane();
		cont.setLayout(null);
		a1=new JLabel("Welcome to Administrator's Page");
		cust_info=new JLabel("Customer's Details");
		cust_del=new JLabel("Delete a customer");
		history=new JLabel("Shopping History");
		GCanvas canvas=new GCanvas();
		
		cust_add=new JButton("Add a Customer");
		send_msg=new JButton("Send a message");
		exit=new JButton("Exit");
		search1=new JButton("Search");
		search2=new JButton("Search");
		search3=new JButton("Search");
		id_info=new JTextField(10);
		id_info.setText("Enter id");
		id_del=new JTextField(10);
		id_del.setText("Enter id");
		id_history=new JTextField(10);
		id_history.setText("Enter id");	
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}
	
		a1.setBounds(400,10,600,50);
		cust_add.setBounds(200,200,300,30);
		cust_info.setBounds(200,250,150,30);
		id_info.setBounds(350,250,150,30);
		search1.setBounds(550,250,100,30);
		cust_del.setBounds(200,300,150,30);
		id_del.setBounds(350,300,150,30);
		search2.setBounds(550,300,100,30);
		history.setBounds(200,350,150,30);
		id_history.setBounds(350,350,150,30);
		search3.setBounds(550,350,100,30);
		send_msg.setBounds(200,400,250,30);
		exit.setBounds(200,500,100,30);
		canvas.setBounds(750,150,500,400);
		Font font = new Font("Dialog", Font.PLAIN, 40);
		a1.setFont(font);

		cont.add(a1);
		cont.add(cust_add);	
		cont.add(cust_info);
		cont.add(id_info);
		cont.add(search1);
		cont.add(cust_del);
		cont.add(id_del);
		cont.add(search2);
		cont.add(history);
		cont.add(id_history);
		cont.add(search3);
		cont.add(send_msg);
		cont.add(exit);
		cont.add(canvas);
				
		cust_add.addActionListener(this);
		search1.addActionListener(this);
		search2.addActionListener(this);
		search3.addActionListener(this);
		exit.addActionListener(this);
		send_msg.addActionListener(this);
		id_info.addActionListener(this);
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

	if(ae.getSource()==cust_add)
	{
		Newcust nc=new Newcust();
	}
	
	if(ae.getSource()==search1)
	{
		customers cus=new customers();
		String t=id_info.getText().toString();
		cus.id_search.setText(id_info.getText());
		try
		{
			st=con.prepareStatement("Select * from customers where CUST_ID=?");
			st.setString(1,t);
			rs=st.executeQuery();
			/*if(!rs.next())
			{
				confirm.setText("Sorry ! No Entry Found");
			}*/
			//cus.id_search.setText("hELLO");
			rs=st.executeQuery();
			while(rs.next())
			{
				cus.name1.setText(rs.getString("NAME"));
				cus.cust_id1.setText(rs.getString("CUST_ID"));
				cus.phone1.setText(rs.getString("PHONE"));
				cus.pre_visit1.setText(rs.getString("L_VISIT"));
			}
		}

		catch(Exception e)
		{	
	
		}
	}
	
	if(ae.getSource()==search2)
	{
		customers cus=new customers();
		String t=id_del.getText().toString();
		cus.id_search.setText(id_del.getText());
		try
		{
			st=con.prepareStatement("Select * from customers where CUST_ID=?");
			st.setString(1,t);
			rs=st.executeQuery();
			
			rs=st.executeQuery();
			while(rs.next())
			{
				cus.name1.setText(rs.getString("NAME"));
				cus.cust_id1.setText(rs.getString("CUST_ID"));
				//address1.setText(rs.getString("Address"));
				cus.phone1.setText(rs.getString("PHONE"));
				cus.pre_visit1.setText(rs.getString("L_VISIT"));
			}
		}

		catch(Exception e)
		{	
	
		}	}

	if(ae.getSource()==search3)
	{
		history hist=new history();
		hist.id_add.setText(id_history.getText());
		hist.id_search.setText(id_history.getText());
		hist.id_del.setText(id_history.getText());
		
	}

	if(ae.getSource()==id_info)
	{
		id_info.setText("");
	}
	
	if(ae.getSource()==send_msg)
	{
		send_msg sm=new send_msg();
		sam();
	}
}
class GCanvas extends Canvas
{
  public void paint(Graphics g)
  {
    Image img=null;
    try {img=ImageIO.read(new File("Admin.jpg"));}
    catch(IOException e)
      {System.out.println("ok");System.exit(0);}
    g.drawImage(img,0,0,460,460,this);
  }
}


public void sam(){		
Thread reader = new Thread() {
			public void run() {
				PCServerCOMM5 pc=new PCServerCOMM5();}
	
		};
		reader.start();
	}

public static void main(String args[]) 
{

	admin s=new admin();
}	
}
		
		
