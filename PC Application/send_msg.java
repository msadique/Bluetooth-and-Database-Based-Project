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

public class send_msg extends JFrame implements ActionListener{
	
	Container cont;
	JLabel a1,a2;
	JTextField msg_box,tag_box;
	JButton send,exit;
	ResultSet rs;
	PreparedStatement st;
	Connection con;
	
	send_msg()
	{
		super("Send Messages");
		cont=getContentPane();
		cont.setLayout(null);
		a1=new JLabel("Enter tag");
		a2=new JLabel("Enter Msg");
		tag_box=new JTextField(20);
		msg_box=new JTextField(30);
		send=new JButton("send");
		exit=new JButton("Exit");
		a1.setBounds(50,30,100,30);
		a2.setBounds(50,130,100,30);
		tag_box.setBounds(200,30,300,30);
		msg_box.setBounds(200,100,300,100);
		send.setBounds(250,250,100,30);
		exit.setBounds(400,250,100,30);
		
		cont.add(a1);
		cont.add(tag_box);
		cont.add(a2);
		cont.add(msg_box);
		cont.add(send);
		cont.add(exit);

		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}

		exit.addActionListener(this);
		setSize(600,350);
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

	send_msg sm=new send_msg();
}	
}
	
