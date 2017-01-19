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

public class addresses extends JFrame implements ActionListener{

	Container cont;
	JLabel cust_id,h_no,street,city,state,country,p_code,email,confirm,a1;
	JTextField cust_id1,h_no1,street1,city1,state1,country1,p_code1,email1,search_box;
	JButton search,save,exit,reset;
	ACanvas canvas1;
	ResultSet rs;
	PreparedStatement st;
	Connection con;

	addresses()
	{
		super("Address Record");
		cont=getContentPane();
		cont.setLayout(null);
		cust_id=new JLabel("Customer id");
		h_no=new JLabel("House No");
		a1=new JLabel("Address Book");
		street=new JLabel("Street");
		city=new JLabel("City");
		state=new JLabel("State");
		p_code=new JLabel("Postal code");
		country=new JLabel("Country");
		email=new JLabel("e-mail");
		cust_id1=new JTextField(20);
		h_no1=new JTextField(10);
		street1=new JTextField(20);
		city1=new JTextField(20);
		state1=new JTextField(20);
		p_code1=new JTextField(20);
		country1=new JTextField(20);
		search_box=new JTextField(20);
		email1=new JTextField(50);
		search=new JButton("search");
		save=new JButton("save");
		exit=new JButton("exit");
		reset=new JButton("reset");
		confirm=new JLabel("");
		GCanvas canvas=new GCanvas();
		canvas1=new ACanvas();
		
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}

		a1.setBounds(400,15,600,50);
		search_box.setBounds(550,120,130,30);
		search.setBounds(570,160,100,30);
		cust_id.setBounds(45,200,200,30);
		cust_id1.setBounds(300,200,200,30);
		h_no.setBounds(50,250,200,30);
		h_no1.setBounds(300,250,200,30);
		street.setBounds(50,300,200,30);
		street1.setBounds(300,300,200,30);
		city.setBounds(50,350,200,30);
		city1.setBounds(300,350,200,30);
		state.setBounds(50,400,200,30);
		state1.setBounds(300,400,200,30);
		p_code.setBounds(50,450,200,30);
		p_code1.setBounds(300,450,200,30);
		country.setBounds(50,500,200,30);
		country1.setBounds(300,500,200,30);
		email.setBounds(50,550,200,30);
		email1.setBounds(300,550,200,30);
		save.setBounds(150,630,100,30);
		reset.setBounds(350,630,100,30);
		exit.setBounds(550,630,100,30);
		canvas.setBounds(750,150,500,400);
		confirm.setBounds(830,610,300,30);
		canvas1.setBounds(770,610,50,50);
		
		Font font = new Font("Dialog",Font.PLAIN,50);
		a1.setFont(font);	
	
		cont.add(a1);
		cont.add(search_box);
		cont.add(search);
		cont.add(cust_id);
		cont.add(cust_id1);
		cont.add(h_no);
		cont.add(h_no1);
		cont.add(street);
		cont.add(street1);
		cont.add(city);
		cont.add(city1);
		cont.add(state);
		cont.add(state1);
		cont.add(p_code);
		cont.add(p_code1);
		cont.add(country);
		cont.add(country1);
		cont.add(email);
		cont.add(email1);
		cont.add(save);
		cont.add(exit);
		cont.add(reset);
		cont.add(confirm);
		cont.add(canvas);
		add(canvas1);
		
		font = new Font("Dialog", Font.PLAIN, 20);
		confirm.setFont(font);
		canvas1.setVisible(false);
		exit.addActionListener(this);
		save.addActionListener(this);
		search.addActionListener(this);
		reset.addActionListener(this);
		
		setDefaultLookAndFeelDecorated(true);
				
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
		cust_id1.setText("");
		h_no1.setText("");
		street1.setText("");
		city1.setText("");
		state1.setText("");
		country1.setText("");
		p_code1.setText("");
		email1.setText("");
		search_box.setText("");
		confirm.setText("");
		canvas1.setVisible(false);
		
	}

	if(ae.getSource()==save)
	{
		String a=cust_id1.getText();
		String b=h_no1.getText();
		String c=street1.getText();
		String d=city1.getText();
		String w=state1.getText();
		String x=p_code1.getText();
		String y=country1.getText();
		String z=email1.getText();
		
		try
		{
			st=con.prepareStatement("Insert into address  values(?,?,?,?,?,?,?,?)");
			st.setString(1,a);
			st.setString(2,b);
			st.setString(3,c);
			st.setString(4,d);
			st.setString(5,w);
			st.setString(6,x);
			st.setString(7,y);
			st.setString(8,z);
			
			st.executeUpdate();
			
		}

		catch(Exception e)
		{
		}		
		
		confirm.setText("Entry successfully saved");
		canvas1.setVisible(true);	
	}

	if(ae.getSource()==search)
	{
		String s=search_box.getText().toString();
		
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
				
				cust_id1.setText(rs.getString("CUST_ID"));
				h_no1.setText(rs.getString("H_NO"));
				street1.setText(rs.getString("STREET"));
				city1.setText(rs.getString("CITY"));
				state1.setText(rs.getString("STATE"));
				p_code1.setText(rs.getString("PIN"));
				country1.setText(rs.getString("COUNTRY"));
				email1.setText(rs.getString("EMAIL"));
				
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
    try {img=ImageIO.read(new File("house.png"));}
    catch(IOException e)
      {System.out.println("ok");System.exit(0);}
    g.drawImage(img,0,0,460,460,this);
  }
}

class ACanvas extends Canvas
{
  public void paint(Graphics g)
  {
    Image img=null;
    try {img=ImageIO.read(new File("bulb.gif"));}
    catch(IOException e)
      {System.out.println("IMG_ERROR");System.exit(0);}
    g.drawImage(img,0,0,50,50,this);
  }
}


public static void main(String args[]) 
{

	addresses add=new addresses();
}	
}
		