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

public class register extends JFrame implements ActionListener{

	Container cont;
	ResultSet rs;
	PreparedStatement st;
	Connection con;
	JLabel a1,uname,pwd,quest,ans,confirm;
	JTextField uname1,ans1;
	JButton submit,exit;
	JPasswordField pwd1;
	JComboBox qbox;



	register()
	{
		super("Registration");
		cont=getContentPane();
		cont.setLayout(null);

		a1=new JLabel("Register once to Create your Account");
		uname=new JLabel("Username");
		pwd=new JLabel("Password");
		quest=new JLabel("Security Question");
		ans=new JLabel("Your answer");
		confirm=new JLabel("");
		uname1=new JTextField(20);
		pwd1=new JPasswordField (15);
		ans1=new JTextField(20);
	      	submit=new JButton("submit");
		exit=new JButton("exit");
		qbox=new JComboBox();

		Font font = new Font("Dialog", Font.PLAIN, 30);
		a1.setFont(font);
	
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}

		qbox.addItem("select");
		qbox.addItem("What is your pet's name");
		qbox.addItem("What is your mother's maiden name");
		qbox.addItem("Where did you first met your spouse");
		qbox.addItem("What is your favourite food");
		qbox.addItem("What is the name of your first school");
		cont.add(a1);
		cont.add(uname);
		cont.add(pwd);
		cont.add(quest);
		cont.add(ans);
		cont.add(uname1);
		cont.add(pwd1);
		cont.add(ans1);
		cont.add(submit);
		cont.add(exit);
		add(qbox);
		add(confirm);

		font = new Font("Dialog", Font.PLAIN, 20);
		confirm.setFont(font);


		qbox.addActionListener(this);
		submit.addActionListener(this);
		exit.addActionListener(this);

		a1.setBounds(300,30,600,50);	
		uname.setBounds(200,150,200,30);
		uname1.setBounds(450,150,200,30);
		pwd.setBounds(200,200,200,30);
		pwd1.setBounds(450,200,200,30);
		quest.setBounds(200,250,200,30);
		qbox.setBounds(450,250,250,30);
		ans.setBounds(200,300,200,30);
		ans1.setBounds(450,300,200,30);
		submit.setBounds(250,450,150,30);
		exit.setBounds(500,450,150,30);
		confirm.setBounds(200,600,300,30);

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

		
		if(ae.getSource()==submit)
		{
			String a=uname1.getText();
			String b=pwd1.getText();
			String c=qbox.getSelectedItem().toString();
			String d=ans1.getText();

			try
			{
				st=con.prepareStatement("Insert into login values(?,?,?,?)");
				st.setString(1,a);
				st.setString(2,b);
			 	st.setString(3,c);
				st.setString(4,d);
				
				st.executeUpdate();
			}

			catch(Exception e)
			{
				System.out.print(e);
			}		
			
				confirm.setText("Account successfully created");	
		}	
	}

	public static void main(String args[]) 
	{

		register reg=new register();
	}	
}
