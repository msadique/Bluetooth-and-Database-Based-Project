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

public class send_msgs extends JFrame implements ActionListener{
	
	Container cont;
	JLabel a1,a2;
	JTextField msg_box,store_box,amount_req;
	JButton send,exit,add_msg,reset;
	ResultSet rs;
	PreparedStatement st;
	Connection con;
	
	send_msgs()
	{
		cont=getContentPane();
		cont.setLayout(null);
		add_msg=new JButton("Add Msg");
		a1=new JLabel("Enter tag");
		a2=new JLabel("Enter Msg");
		//tag_box=new JTextField(20);
		store_box=new JTextField(20);
		store_box.setText("Store name");
		amount_req=new JTextField(10);
		amount_req.setText("amnt_req");
		msg_box=new JTextField(30);
		send=new JButton("send");
		reset=new JButton("reset");
		exit=new JButton("Exit");
		add_msg.setBounds(50,50,100,30);
		//a1.setBounds(50,130,100,30);
		a2.setBounds(50,230,100,30);
		//tag_box.setBounds(200,130,300,30);
		msg_box.setBounds(200,200,300,100);
		store_box.setBounds(550,230,100,40);
		
		amount_req.setBounds(300,50,100,40);
		send.setBounds(150,350,100,30);
		reset.setBounds(300,350,100,30);
		exit.setBounds(450,350,100,30);
		
		cont.add(add_msg);
		//cont.add(a1);
		//cont.add(store_box);
		//cont.add(amount_req);
		//cont.add(tag_box);
		cont.add(a2);
		cont.add(store_box);
		cont.add(msg_box);
		cont.add(amount_req);
		cont.add(send);
		cont.add(reset);
		cont.add(exit);

		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}

		exit.addActionListener(this);
		setSize(700,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

public void actionPerformed(ActionEvent ae)
{
	String str=ae.getActionCommand();
	if(ae.getSource()==exit)
	{
		dispose();
	}




}



public static void main(String args[]) 
{

	send_msgs sm=new send_msgs();
}	
}
	
