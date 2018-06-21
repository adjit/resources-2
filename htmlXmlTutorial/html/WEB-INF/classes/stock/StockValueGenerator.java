package stock;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Random;

public class StockValueGenerator extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Random r = new java.util.Random();
    int stockValue = 20 + r.nextInt(20);
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.println(stockValue); 
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doPost(request, response);
  }
}
