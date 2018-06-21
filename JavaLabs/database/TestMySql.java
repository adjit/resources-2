// @Copyright Prof. Lixin Tao, Pace University, 2008
// Test SQL on an MySQL database 
// Before you run this program, make sure you have installed MySQL on your PC
// with "123456" as password for root, and MySQL has a database named "test".
import java.sql.*;

public class TestMySql {
  Connection con;     // A connection to the database
  Statement stmt;     // All purpose statement
  static String jdbcDriver = "com.mysql.jdbc.Driver";
  static String dbURL = "jdbc:mysql://localhost/test";
  static String user = "root";
  static String password = "123456";
  
  public TestMySql() throws Exception {
    Class.forName(jdbcDriver); // load JDBC driver class
    con = DriverManager.getConnection(dbURL, user, password); // connect to the database
    stmt = con.createStatement(); // create a statement object for the connection
    String sql = "drop table if exists testTable"; // drop existing table testTable
    System.out.println(sql);
    stmt.executeUpdate(sql);  
    // create a table with three columns named "name", "phone" and "id", with "id" being the primary key 
    sql = "create table testTable (name varchar(32), phone char(8), id integer, primary key (id))";
    System.out.println(sql);
    stmt.executeUpdate(sql);
    // insert into the table two data records (rows)
    sql = "insert into testTable values ('Michael', '999-9999', 12345)";
    System.out.println(sql);
    stmt.executeUpdate(sql);
    sql = "insert into testTable values ('Victor', '999-9999', 12346)";
    System.out.println(sql);
    stmt.executeUpdate(sql);
    // create a prepared statement which can be reused with different parameter values represented by ?
    PreparedStatement pstmt = con.prepareStatement("select * from testTable where phone = ?");
    pstmt.setString(1, "999-9999"); // replace the first ? in the SQl command with "999-9999"
    System.out.println("select * from testTable where phone = '999-9999'");
    ResultSet rs = pstmt.executeQuery();
    // check result and print data if found.
    while (rs.next()) {  // rs.next() reads next row of return data; it returns false if no more data to read 
      System.out.println("name = '" + rs.getString(1) +      // read first column value as string
                         "', phone = '" + rs.getString(2) +  // read second column value as string
                         "', id = " + rs.getInt(3));         // read third column value as integer
    }
    rs.close();
  }

  static public void main(String[] args) throws Exception {
    new TestMySql();
  }
}
