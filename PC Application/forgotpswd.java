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

public class forgotpswd extends JFrame implements ActionListener{

	Container cont;
	ResultSet rs;
	PreparedStatement st;
	Connection con;
	JLabel a1,uname,pwd,quest,ans,confirm,msg,quest1;
	JTextField uname1,ans1,pwd1;
	JButton submit,exit,check,change;
	


	forgotpswd()
	{
		super("Password Retrieval");
		cont=getContentPane();
		cont.setLayout(null);
		
		a1=new JLabel("Enter the following details to retrieve your password");
		uname=new JLabel("Username");
		pwd=new JLabel("Password");
		quest=new JLabel("Security Question");
		ans=new JLabel("Your answer");
		msg=new JLabel("");
		confirm=new JLabel("");
		uname1=new JTextField(20);
		pwd1=new JTextField(15);
		ans1=new JTextField(20);
	      	submit=new JButton("submit");
		exit=new JButton("exit");
		quest1=new JLabel("");
		check=new JButton("check");
		change=new JButton("Change Password");
		
		
		GCanvas canvas=new GCanvas();

		Font font = new Font("Dialog", Font.PLAIN, 30);
		a1.setFont(font);
	
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}

		add(a1);
		add(uname);
		add(pwd);
		add(quest);
		add(ans);
		add(uname1);
		add(ans1);
		add(submit);
		add(exit);
		add(quest1);
		add(confirm);
		add(msg);
		add(check);
		add(canvas);

		font = new Font("Dialog", Font.PLAIN, 20);
		confirm.setFont(font);

		

		a1.setBounds(180,30,700,50);	
		uname.setBounds(150,150,150,30);
		uname1.setBounds(300,150,200,30);
		check.setBounds(510,150,70,30);
		//pwd.setBounds(150,200,150,30);
		//pwd1.setBounds(400,200,150,30);
		quest.setBounds(150,200,150,30);
		quest1.setBounds(300,200,250,30);
		ans.setBounds(150,250,150,30);
		ans1.setBounds(300,250,200,30);
		submit.setBounds(200,400,150,30);
		exit.setBounds(450,400,150,30);
		confirm.setBounds(150,500,500,30);
		canvas.setBounds(750,150,500,400);


		submit.addActionListener(this);
		exit.addActionListener(this);
		check.addActionListener(this);
		
		font = new Font("Dialog", Font.PLAIN, 15);
		quest1.setFont(font);



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

		

		
		else if(ae.getSource()==check)
		{
			String t=uname1.getText().toString();
		
			try
			{
				st=con.prepareStatement("Select quest from login where UNAME=?");
				st.setString(1,t);
				rs=st.executeQuery();

				if(!rs.next())
				{
					confirm.setText("No Entry Found for given Username");
				}
			
				rs=st.executeQuery();
				while(rs.next())
				{
					quest1.setText(rs.getString("QUEST"));
					}
				}

			catch(Exception e)
			{	
	
			}
		}

		else if(ae.getSource()==submit)
		{
			String a=uname1.getText().toString();
			String b=ans1.getText().toString();
			try
			{
				st=con.prepareStatement("Select passwd from login where UNAME=? and ANS=?");
				st.setString(1,a);
				st.setString(2,b);
				rs=st.executeQuery();

				if(!rs.next())
				{
					confirm.setText("No Entry Found for given Username and Answer");
				}
			
				//rs=st.executeQuery();
				else
				{
					passwd pd=new passwd();
					pd.p2.setText(rs.getString("PASSWD"));
				}
			}
			
			catch(Exception e)
			{	
		
			}
		}
	
	}


public class passwd extends JFrame implements ActionListener

{
	Container cont;
	JLabel a2,p2;
	JButton exit;
	
	passwd()
	{
		cont=getContentPane();
		cont.setLayout(null);
		a2=new JLabel("Your Password : ");
		p2=new JLabel("");
		exit=new JButton("exit");
		add(a2);
		add(p2);
		add(exit);
		
		a2.setBounds(70,30,250,30);
		p2.setBounds(110,70,200,50);
		exit.setBounds(150,150,70,30);
		exit.addActionListener(this);
		Font font = new Font("Dialog", Font.PLAIN, 30);
		p2.setFont(font);
		a2.setFont(font);
		setVisible(true);
		setSize(400,250);		
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

	

	public static void main(String args[]) 
	{

		forgotpswd reg=new forgotpswd();
	}	
}

