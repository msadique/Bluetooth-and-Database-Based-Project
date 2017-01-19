import java.sql.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class QueryDB extends AbstractTableModel 
{
	Vector cache; // will hold String[] objects . . .
	int colCount;
	String[] headers;
	Connection con;
	ResultSet rs;
	PreparedStatement st;
	
	public QueryDB()
	{
		cache = new Vector();
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=java.sql.DriverManager.getConnection("jdbc:odbc:IBM");
		}
		catch(Exception e){}
	}


	public String getColumnName(int i) { return headers[i]; }
	public int getColumnCount() { return colCount; }
	public int getRowCount() { return cache.size();}
	public Object getValueAt(int row, int col) 
	{
		return ((String[])cache.elementAt(row))[col];
	}

				
	public void setQuery(String s) 
	{
		cache = new Vector();
		try 
		{
		
			st=con.prepareStatement("Select * from history where CUST_ID=?");
			st.setString(1,s);			
			rs = st.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			colCount = meta.getColumnCount();
			headers = new String[colCount];
			for (int h=1; h <= colCount; h++) 
			{
				headers[h-1] = meta.getColumnName(h);
			}

			while (rs.next())
			{
				String[] record = new String[colCount];
				for (int i=0; i < colCount; i++) 
				{
					record[i] = rs.getString(i + 1);
				}
				cache.addElement(record);
			}
			fireTableChanged(null); // notify everyone that we have a new table.
		}
		catch(Exception e) 
		{
			cache = new Vector(); // blank it out and keep going.
			e.printStackTrace();
		}
	}

	
public static void main(String args[]) 
{

	QueryDB qt=new QueryDB();
}	
}		