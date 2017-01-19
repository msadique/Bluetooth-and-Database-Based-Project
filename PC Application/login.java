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
public class login extends JFrame implements ActionListener{
	Container cont;
	JButton login,reset,forgot,exit,signup,change;
	JTextField uname;
	JPasswordField pwd;
	JLabel a1,jl1,jl2,confirm,msg;
	ResultSet rs;
	PreparedStatement st;
	Connection con;

login()
{
	super("Secure Login");
	cont=getContentPane();
	cont.setLayout(null);
	jl1=new JLabel("User Name");
	jl2=new JLabel("Password");
	uname=new JTextField("");
	pwd=new JPasswordField();
	login=new JButton("Login");
	reset=new JButton("Reset");
	forgot=new JButton("Forgot password");
	exit=new JButton("Exit");
	signup=new JButton("Register");
	confirm=new JLabel("");
	a1=new JLabel("Welcome User");
	msg=new JLabel("Create Account");
	change=new JButton("Change password");
	
	GCanvas canvas=new GCanvas();
	
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}


	cont.add(a1);	
	cont.add(jl1);
	cont.add(uname);
	cont.add(jl2);
	cont.add(pwd);
	cont.add(login);
	cont.add(reset);
	cont.add(forgot);
	cont.add(confirm);
	cont.add(exit);
	cont.add(signup);
	add(msg);
	add(change);
	cont.add(canvas);
	Font font = new Font("Dialog", Font.PLAIN, 40);
	a1.setFont(font);

	a1.setBounds(400,50,600,70);
	jl1.setBounds(200,250,100,30);
	uname.setBounds(350,250,200,30);
	jl2.setBounds(200,300,100,30);
	pwd.setBounds(350,300,200,30);
	forgot.setBounds(450,340,150,20);
	login.setBounds(250,400,100,30);
	reset.setBounds(400,400,100,30);
	exit.setBounds(550,400,100,30);
	confirm.setBounds(250,470,300,30);
	signup.setBounds(300,550,200,30);
	msg.setBounds(330,600,500,30);
	change.setBounds(270,340,150,20);
	
	canvas.setBounds(750,150,500,400);

	login.addActionListener(this);
	reset.addActionListener(this);
	forgot.addActionListener(this);
	exit.addActionListener(this);
	signup.addActionListener(this);
	change.addActionListener(this);
	
	font = new Font("Dialog", Font.PLAIN, 20);
	msg.setFont(font);

	setDefaultLookAndFeelDecorated(true);
	setExtendedState(Frame.MAXIMIZED_BOTH);

	setVisible(true);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
}
public void actionPerformed(ActionEvent ae)
{
	String str=ae.getActionCommand();
	String p="FARAZ";
	if(ae.getSource()==login)

	{
		String t=uname.getText().toString();
		try
		{
				st=con.prepareStatement("Select passwd from login where uname=?");
				st.setString(1,t);
				rs=st.executeQuery();
				while(rs.next())
				{
					p=rs.getString("passwd");
				}
				/*if(!rs.next())
				{
					
					msgbox mb=new msgbox();
				}*/
				
				if(pwd.getText().equals(p))
				{
					admin ad=new admin();
					dispose();
				}
				else
				{
					
					msgbox mb=new msgbox();
				}
		}

		catch(Exception e)
		{	
	
		}
	}
	
	if(ae.getSource()==reset)
	{
		uname.setText("");
		pwd.setText("");
		confirm.setText("");
		msg.setText("Create Account");
	}
	
	if(ae.getSource()==exit)
	{
		dispose();
	}

	
	if(ae.getSource()==change)
	{
		changepswd cp=new changepswd();
	}
	
	if(ae.getSource()==signup)
	{
		
		try
		{
			st=con.prepareStatement("Select * from login");
			rs=st.executeQuery();
			if(!rs.next())
			{
				register reg=new register();	
			}	
		
			else
				msg.setText("Account Already Exists");
		}
	
		catch(Exception e)
		{	
	
		}
	}
	
	if(ae.getSource()==forgot)
	{
		forgotpswd fgt=new forgotpswd();
	}	

}

class GCanvas extends Canvas
{
  public void paint(Graphics g)
  {
    Image img=null;
    try {img=ImageIO.read(new File("secure.gif"));}
    catch(IOException e)
      {System.out.println("IMG_ERROR");System.exit(0);}
    g.drawImage(img,0,0,460,460,this);
  }
}


public class msgbox extends JFrame implements ActionListener

{
	Container cont;
	JLabel p1,p2;
	JButton exit;
	
	msgbox()
	{
		cont=getContentPane();
		cont.setLayout(null);
		p1=new JLabel("");
		p2=new JLabel("do not match");
		p1.setText("Username and password");
		exit=new JButton("OK");
		ACanvas canvas=new ACanvas();
		add(p1);
		add(p2);
		add(exit);
		add(canvas);
		
		p1.setBounds(20,60,220,50);
		p2.setBounds(40,90,170,50);
		exit.setBounds(80,150,70,30);
		canvas.setBounds(250,30,235,250);
		exit.addActionListener(this);
		Font font = new Font("Dialog", Font.PLAIN, 20);
		p1.setFont(font);
		p2.setFont(font);
		setVisible(true);
		setSize(500,350);		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String str= ae.getActionCommand();
	
		if(ae.getSource()==exit)
		{
			dispose();
		}
	}
}

class ACanvas extends Canvas
{
  public void paint(Graphics g)
  {
    Image img=null;
    try {img=ImageIO.read(new File("stop.gif"));}
    catch(IOException e)
      {System.out.println("IMG_ERROR");System.exit(0);}
    g.drawImage(img,0,0,260,260,this);
  }
}



static public void main(String args[])
{
	login Lg=new login();
}
}