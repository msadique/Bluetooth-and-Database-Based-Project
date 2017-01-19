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

public class addhistory extends JFrame implements ActionListener{
	

	Container cont;
	JLabel a1,cust_id,prod_id,shop_id,p_date,price,discount,f_price,brand,type,confirm;
	JTextField cust_id1,prod_id1,shop_id1,p_date1,price1,discount1,f_price1,brand1,type1;
	JButton save,reset,exit,calc,conti,check;
	ResultSet rs;
	PreparedStatement st;
	Connection con;
	double fp;
	addhistory()
	{
		super("Add Shopping Details of Customers");
		cont=getContentPane();
		cont.setLayout(null);
		a1=new JLabel("Add Shopping Details");
		cust_id=new JLabel("Customer ID");
		prod_id=new JLabel("Product ID");
		shop_id=new JLabel("Shop ID");
		p_date=new JLabel("Purchase Date");
		price=new JLabel("MRP");
		discount=new JLabel("Discount (%)");
		f_price=new JLabel("Final Price");
		brand=new JLabel("Brand");
		type=new JLabel("Product Type");
		confirm=new JLabel("");
		cust_id1=new JTextField(20);
		prod_id1=new JTextField(20);
		shop_id1=new JTextField(20);
		p_date1=new JTextField(20);
		price1=new JTextField(20);
		discount1=new JTextField(20);
		f_price1=new JTextField(20);
		brand1=new JTextField(20);
		type1=new JTextField(30);

		save=new JButton("save");
		reset=new JButton("reset");
		exit=new JButton("exit");
		calc=new JButton("amt");
		conti=new JButton("cont");
		check=new JButton("check");
		Font font = new Font("Dialog", Font.PLAIN, 40);
		a1.setFont(font);

		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}

		cont.add(a1);
		cont.add(cust_id);
		cont.add(prod_id);
		cont.add(shop_id);
		cont.add(p_date);
		cont.add(price);
		cont.add(discount);
		cont.add(f_price);
		cont.add(brand);
		cont.add(type);
		cont.add(confirm);

		cont.add(cust_id1);
		cont.add(prod_id1);
		cont.add(shop_id1);
		cont.add(p_date1);
		cont.add(price1);
		cont.add(discount1);
		cont.add(f_price1);
		cont.add(brand1);
		cont.add(type1);

		cont.add(check);
		cont.add(save);
		cont.add(reset);
		cont.add(exit);
		cont.add(calc);
		cont.add(conti);
		

		a1.setBounds(400,50,600,70);
		cust_id.setBounds(200,150,200,30);
		cust_id1.setBounds(450,150,200,30);
		prod_id.setBounds(200,200,200,30);
		prod_id1.setBounds(450,200,200,30);
		check.setBounds(680,200,80,30);
		shop_id.setBounds(200,250,200,30);
		shop_id1.setBounds(450,250,200,30);
		p_date.setBounds(200,300,200,30);
		p_date1.setBounds(450,300,200,30);
		price.setBounds(200,350,200,30);
		price1.setBounds(450,350,200,30);
		discount.setBounds(200,400,200,30);	
		discount1.setBounds(450,400,200,30);
		f_price.setBounds(200,450,200,30);
		f_price1.setBounds(450,450,200,30);
		brand.setBounds(200,500,200,30);
		brand1.setBounds(450,500,200,30);
		type.setBounds(200,550,200,30);
		type1.setBounds(450,550,200,30);
		calc.setBounds(350,450,80,30);							
		save.setBounds(150,650,150,30);
		reset.setBounds(350,650,150,30);
		exit.setBounds(550,650,150,30);
		conti.setBounds(750,650,150,30);
		confirm.setBounds(780,200,200,30);
		
		exit.addActionListener(this);
		reset.addActionListener(this);
		save.addActionListener(this);
		calc.addActionListener(this);
		conti.addActionListener(this);
		check.addActionListener(this);


		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultLookAndFeelDecorated(true);
		setIconImage(new ImageIcon("Sunset.jpg").getImage());
		
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
		prod_id1.setText("");
		shop_id1.setText("");
		p_date1.setText("");
		price1.setText("");
		discount1.setText("");
		f_price1.setText("");
		brand1.setText("");
		type1.setText("");
	}

	if(ae.getSource()==conti)
	{
		prod_id1.setText("");
		shop_id1.setText("");
		p_date1.setText("");
		price1.setText("");
		discount1.setText("");
		f_price1.setText("");
		brand1.setText("");
		type1.setText("");
		
	}

	if(ae.getSource()==check)
	{
		String t=prod_id1.getText().toString();
		
		try
		{
			st=con.prepareStatement("Select * from products where PROD_ID=?");
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
				price1.setText(rs.getString("Price"));
				brand1.setText(rs.getString("Brand"));
				type1.setText(rs.getString("Type"));					
			}
		}
		catch(Exception e)
		{	
	
		}

	}

	
	
	if(ae.getSource()==calc)
	{
		String x=price1.getText();
		String y=discount1.getText();
		double pr = Double.parseDouble(x);
		double di = Double.parseDouble(y);
		fp=pr-(di*pr/100);
		f_price1.setText(Double.toString(fp));
	}

	
	
		

	if(ae.getSource()==save)
	{
		String a=cust_id1.getText();
		String b=prod_id1.getText();
		String c=shop_id1.getText();
		String d=p_date1.getText();
		String p=price1.getText();
		String q=discount1.getText();
		String r=f_price1.getText();
		String s=brand1.getText();
		String t=type1.getText();
		
		System.out.print(fp);
		try
		{
			st=con.prepareStatement("Insert into history values(?,?,?,?,?,?,?,?,?)");
			st.setString(1,a);
			st.setString(2,b);
			st.setString(3,c);
			st.setString(4,d);
			st.setString(5,p);
			st.setString(6,q);
			st.setString(7,r);
			st.setString(8,s);
			st.setString(9,t);
		
			st.executeUpdate();
		}

		catch(Exception e)
		{
			System.out.print("hello " + e);
		}		
	
	}

}

	
public static void main(String args[])
{
	addhistory ah=new addhistory();
}
}

	