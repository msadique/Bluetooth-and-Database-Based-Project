import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class DataTable extends JFrame implements ActionListener
{
	JTextField cust_id;
	JButton reset,exit,search;
	QueryDB qtm;
		
	public DataTable()
	{
		super("Shopping Table");
		
		setSize(350, 200);
		qtm = new QueryDB();
		JTable table = new JTable(qtm);
		JScrollPane scrollpane = new JScrollPane(table);
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 2));
		exit=new JButton("Exit");
		search=new JButton("search");
		reset=new JButton("reset");
				
		p1.add(new JLabel("Enter Customer's ID: "));
		p1.add(cust_id = new JTextField());
		p1.add(new JLabel("Click here to search: "));
		p1.add(search);
		//p1.add(search);
		p1.add(reset);
		p1.add(exit);
		
		search.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);
		
		getContentPane().add(p1, BorderLayout.NORTH);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}



public void actionPerformed(ActionEvent ae)
	{
		String str= ae.getActionCommand();
		if(ae.getSource()==exit)
		{
			dispose();
			history hi=new history();
		}

		if(ae.getSource()==reset)
		{
			dispose();
			DataTable dt = new DataTable();
			
		}

		if(ae.getSource()==search)
		{
			qtm.setQuery(cust_id.getText().trim());
		}

	}	

	public static void main(String args[])
	{
		DataTable dt = new DataTable();
		
	}
}