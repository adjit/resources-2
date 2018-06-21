package demo;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class EchoHttpRequest extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    try {
      String name = request.getParameter("cookie_name");
      String value = request.getParameter("cookie_value");
      String timeString = request.getParameter("cookie_time");
      if (name != null) {
        int time = Integer.parseInt(timeString);
        Cookie c = new Cookie(name, value);
		    c.setMaxAge(time);
		    response.addCookie(c);
		  }
		} catch (Exception e) {}
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("<head>");
    out.println("<title>Echo HTTP Request Information</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h3 align=\"center\"><em>Echo HTTP Request Information</em></h3>");

    out.println("<h4>Submitted Parameters</h4>"); 
    Enumeration parameterNames = request.getParameterNames();
    if (parameterNames.hasMoreElements() == false) 
      out.println("&nbsp;&nbsp;No submitted parameters<br/>");
    else {
      while (parameterNames.hasMoreElements()) {
        String name = (String)parameterNames.nextElement();
        String value = request.getParameter(name);
        out.println("&nbsp;&nbsp;" + name + " = " + value + "<br/>");
      }
    }
    out.println("<hr/>");
    out.println("<h4>General Information about This HTTP Request</h4>");
    out.println("&nbsp;&nbsp;Method: " + request.getMethod() + "<br/>");
    out.println("&nbsp;&nbsp;Request URI: " + request.getRequestURI() + "<br/>");
    out.println("&nbsp;&nbsp;Protocol: " + request.getProtocol() + "<br/>");
    out.println("&nbsp;&nbsp;PathInfo: " + request.getPathInfo() + "<br/>");
    out.println("&nbsp;&nbsp;Remote Address: " + request.getRemoteAddr() + "<br/>");
    out.println("<hr/>");

    out.println("<h4>Information about This Web Application</h4>");
    // Find absolute URL for the Web application root
    String absoluteURL = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
    absoluteURL = absoluteURL.substring(0, absoluteURL.lastIndexOf('/'));
    out.println("&nbsp;&nbsp;URL for This Web Application Root: <br/>");
    out.println("&nbsp;&nbsp;&nbsp;&nbsp;" + absoluteURL + "<br/>");

    String dir = request.getRealPath("/");
    //dir = dir.replace('\\', '/');
    out.println("&nbsp;&nbsp;Absolute File System Path for This Web Application: <br/>");
    out.println("&nbsp;&nbsp;&nbsp;&nbsp;" + dir + "<br/>");
    out.println("<hr/>");

    out.println("<h4>Submitted Request Headers</h4>");
    Enumeration e = request.getHeaderNames();
    if (e.hasMoreElements() == false) 
      out.println("&nbsp;&nbsp;No submitted request headers" + "<br/>");
    else {
      while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
        String value = request.getHeader(name);
        out.println("&nbsp;&nbsp;" + name + " = " + value + "<br/>");
      }
    }
    out.println("<hr/>");

    out.println("<h4>Submitted Cookies</h4>");
    Cookie[] cookies = request.getCookies();
    if (cookies == null || cookies.length == 0) 
      out.println("&nbsp;&nbsp;No submitted cookies <br/>");
    else {
      for (int i = 0; i < cookies.length; i++) {
        Cookie c = cookies[i];
        String name = c.getName();
        String value = c.getValue();
        out.println("&nbsp;&nbsp;" + name + " = " + value + "<br/>");
      }
    }
    out.println("<hr/>");
    out.println("</body>");
    out.println("</html>");
  }
	 
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    doPost(request, response);
  }
}