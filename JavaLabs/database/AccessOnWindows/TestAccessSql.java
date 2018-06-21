// @Copyright Prof. Lixin Tao, Pace University, 2008; revised for DNS-less connection
// Test SQL with an Access database on a Windows PC (This code will not work on Linux PC)
// Before you run this program, make sure there is an Access file "TestDB.mdb"
// in the same directory as this program. You can just create an empty Access
// file with this name. Don't set password for the Access database/file.
import java.sql.*;

public class TestAccessSql {
  Connection con;     // A connection to the database
  Statement stmt;     // All purpose statement
  String jdbcDriver = "sun.jdbc.odbc.JdbcOdbcDriver";  // Sun's JDBC driver for bridging ODBC
  // In connectionString, change Access file name or path if necessary, using / for \ in the path
  String connectionString = "jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=TestDB.mdb"; 
  String user = "";  // if the database uses user name, insert it here
  String password = ""; // if the database uses a password, insert it here
  
  public TestAccessSql() throws Exception {
    Class.forName(jdbcDriver); // load JDBC driver class
    con = DriverManager.getConnection(connectionString, user, password); // connect to the database
    stmt = con.createStatement(); // create a statement object for the connection
    String sql = "drop table testTable"; // drop existing table; get warning if you run this code the first time
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
    new TestAccessSql();
  }
}
