package My_code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws SQLException {
		readRecords();
		insertRecords();
	}
	
	//READ/SELECT DATA FROM SQL
	public static void readRecords() throws SQLException {
			String url="jdbc:mysql://localhost:3306/jdbcdemo";//jdbc:mysql    in sql link/   db name
			String username="root";
			String password="root";
			String query="select*from employee";   //QUERY
			Connection con=DriverManager.getConnection(url,username,password);// to connect with sql(all 3 r strings
	
			Statement st=con.createStatement();//used to execute query//The Statement object (st in this case) is used to execute SQL commands (like SELECT, INSERT, UPDATE, DELETE).
			//statement allows you to send SQL queries to the database and handle the returned results.
		    ResultSet rs=st.executeQuery(query);//it is a method in Statement class this execute the query and  it  executes the query and gives/returns the result set so create the object to result set
		    //intially rs set points to attribute in the table to retrive data i.e it has only 1 data give rs.next()
		    rs.next();//moves to 1 recor//if it has only 1 record use this no need while loop
		    //to get the values
		    while(rs.next()) {//returns the boolean value i.e it check whethr row exits or not
		    System.out.println("Id is "+rs.getInt(1));//to getthe int id from sql
		    System.out.println("Name is"+ rs.getString(2));
		    System.out.println("Salary is"+rs.getInt(3));}
		    con.close();//to close the conenction
		}
//INSERT 
	
	public static void insertRecords() throws SQLException {
		String url="jdbc:mysql://localhost:3306/jdbcdemo";//jdbc:mysql    in sql link/   db name
		String username="root";
		String password="root";
		String query="insert into employee values(4,'priya',250000)";   //QUERY->give ' ' ot string to avoid confusion in"  " in Query
		Connection con=DriverManager.getConnection(url,username,password);// to connect with sql(all 3 r strings

		Statement st=con.createStatement();//used to execute query//The Statement object (st in this case) is used to execute SQL commands (like SELECT, INSERT, UPDATE, DELETE).
		//statement allows you to send SQL queries to the database and handle the returned results.
	    int row=st.executeUpdate(query);//\ it  executes the query and gives/returns the result set so create the object to result set and result int bze it doent return table it gives only the int//i.e how rows affected
	    //intially rs set points to attribute in the table to retrive data i.e it has only 1 data give rs.next()
	    System.out.println("Number of rows affected: "+row);
	    con.close();//to close the conenction
	}
	
	
	

}
